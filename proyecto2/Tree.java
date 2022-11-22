/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Emilio Jr
 */
public class Tree {

        NodeTree root;

        public Tree() {
                root = null;
        }

        public Tree(String string) {
                root = createTree(string);
        }

        public void resetTree() {
                root = null;
        }

        public void createNode(Object data) {
                root = new NodeTree(data);
        }

        public NodeTree createSubTree(NodeTree data2, NodeTree data1, NodeTree operator) {
                operator.leftSon = data1;
                operator.rightSon = data2;
                return operator;
        }

        public boolean voidTree() {
                return root == null;
        }

        public String toString(int d) {
                String string = "";
                switch (d) {
                        case 0 ->
                                string = showPreOrder(root, string);
                        case 1 ->
                                string = showInOrder(root, string);
                        case 2 ->
                                string = showPosOrder(root, string);
                }
                return string;
        }

        private String showPreOrder(NodeTree subTree, String s) {
                String string;
                string = "";
                if (subTree != null) {
                        string = s + subTree.info.toString() + "" + showPreOrder(subTree.leftSon, s) + showPreOrder(subTree.rightSon, s);
                }
                return string;
        }

        private String showInOrder(NodeTree subTree, String s) {
                String string;
                string = "";
                if (subTree != null) {
                        string = s + showInOrder(subTree.leftSon, s) + subTree.info.toString() + "" + showInOrder(subTree.rightSon, s);
                }
                return string;
        }

        private String showPosOrder(NodeTree subTree, String s) {
                String string;
                string = "";
                if (subTree != null) {
                        string = s + showPosOrder(subTree.leftSon, s) + showPosOrder(subTree.rightSon, s) + subTree.info.toString() + "";
                }
                return string;
        }

        private int priority(char c) { //método donde definimos la prioridad de los caracteres a través de un número, laprioridad más aja será p=10;
                int p = 100;
                p = switch (c) {
                        case '^' ->
                                30;
                        case '*', '/' ->
                                20;
                        case '+', '-' ->
                                10;
                        default ->
                                0;
                }; //se asume que lo que estará dentro de la pila tendra un valor 0
                return p;
        }

        private boolean isOperator(char c) {
                boolean result;
                result = switch (c) {
                        case '(', ')', '^', '*', '/', '+', '-' ->
                                true;
                        default ->
                                false;
                };
                return result;
        }

        private NodeTree createTree(String string) {
                Stack stackOperators;
                Stack stackExpressions;
                NodeTree token;
                NodeTree op1;
                NodeTree op2;
                NodeTree op;
                stackOperators = new Stack();
                stackExpressions = new Stack();
                char evaluatedCharacter;
                for (int i = 0; i < string.length(); i++) {
                        evaluatedCharacter = string.charAt(i);
                        token = new NodeTree(evaluatedCharacter);
                        if (!isOperator(evaluatedCharacter)) {
                                stackExpressions.insert(token);
                        } else {
                                switch (evaluatedCharacter) {
                                        case '(' ->
                                                stackOperators.insert(token);
                                        case ')' -> {
                                                while (!stackOperators.voidStack() && !stackOperators.getTop().info.equals('(')) {
                                                        op2 = stackExpressions.delete();
                                                        op1 = stackExpressions.delete();
                                                        op = stackOperators.delete();
                                                        op = createSubTree(op2, op1, op);
                                                        stackExpressions.insert(op);
                                                }
                                                stackOperators.delete();
                                        }
                                        default -> {
                                                while (!stackOperators.voidStack() && priority(evaluatedCharacter) <= priority(stackOperators.getTop().info.toString().charAt(0))) {
                                                        op2 = stackExpressions.delete();
                                                        op1 = stackExpressions.delete();
                                                        op = stackOperators.delete();
                                                        op = createSubTree(op2, op1, op);
                                                        stackExpressions.insert(op);
                                                }
                                                stackOperators.insert(token);
                                        }
                                }
                        }

                }
                while (!stackOperators.voidStack()) {
                        op2 = stackExpressions.delete();
                        op1 = stackExpressions.delete();
                        op = stackOperators.delete();
                        op = createSubTree(op2, op1, op);
                        stackExpressions.insert(op);
                }
                op = stackExpressions.delete();
                return op;
        }
        
}
