/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Emilio Jr
 */
public class Stack {
        private NodeStack top;
        
        public Stack(){
                top=null;
        }
        
        public void insert(NodeTree element){
                NodeStack nuevo;
                nuevo=new NodeStack(element);
                nuevo.next=top;
                top=nuevo;
        }
        
        public boolean voidStack(){
                return top==null;
        }
        
        public NodeTree getTop(){
                return top.info;
        }
        
        public void resetStack(){
                top=null;
        }
        
        public NodeTree delete(){
                NodeTree aux=null;
                if(!voidStack()){
                        aux=top.info;
                        top=top.next;
                }
                return aux;
        }
}
