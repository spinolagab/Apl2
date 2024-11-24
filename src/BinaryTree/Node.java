package BinaryTree;

import entities.PIBData;

public class Node {
    private PIBData data;
    private Node parent;
    private Node left;
    private Node right;

    //Nohs em geral
    public Node(PIBData data, Node parent) {
        this.data = data;
        this.parent = parent;
        right = left = null;
    }

    //Raiz
    public Node(PIBData data) {
        this(data,null);
    }
    
    // Getters e Setters
    public PIBData getData() {
        return data;
    }

    public void setData(PIBData data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public int getDegree(){
        int degree = 0;
        if(left != null) ++degree;
        if(right != null) ++degree;
        return degree;
    }
    
    public int getLevel(){
        if (isRoot()) return 0;
        else return parent.getLevel()+1;
    }
    
    public int getHeight(){
       if(isLeaf()) return 0;
       return 1 + Math.max(left == null ? -1 : left.getHeight(),
                right == null ? -1 : right.getHeight());
    }

    //isRoot() --> Retorna se eh a raiz da arvore
    public boolean isRoot(){
        return parent == null;
    }
    
    //isLeaf() --> Retorna se eh um noh folha
    public boolean isLeaf(){
        return left == right;
    }

    @Override
    public String toString() {
        return "Data = " + data
                + ", parent = " + ((parent == null)? "null" : parent.getData())
                + ", left = " + ((left == null)? "null" : left.getData()) 
                + ", right = " + ((right == null)? "null" : right.getData())
                +"\n";
    }
    
}
