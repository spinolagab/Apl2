package BTree;

import java.util.LinkedList;
import java.util.Queue;

public class BTree<T> {
    private BNode<T> root;

    //Arvore com pelo menos a raiz
    public BTree(BNode<T> root) {
        this.root = root;
    }

    //arvore vazia
    public BTree() {
        this(null);
    }
    
    //Getters e Setters
    public BNode getRoot() {
        return root;
    }

    public void setRoot(BNode<T> root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
     
    private int getDegree(BNode<T> root){
        if(root == null || root.isLeaf()) return 0;
        int leftDegree = getDegree(root.getLeft());
        int rightDegree = getDegree(root.getRight());
        int biggerDegree = Math.max(leftDegree,rightDegree);

        return Math.max(root.getDegree(),biggerDegree);
    }
    public int getDegree(){
        return getDegree(root);
    }
    
    public int getHeight(){
        return root.getHeight();
    }
    
    
    //Percursos

    /*Em ordem*/

    //Funcao recursiva
    private String inOrderTraversal(BNode<T> root){
        if(root == null) return "";
        else return "" + inOrderTraversal(root.getLeft()) +
                    " " + root.getData() +
                    " " + inOrderTraversal(root.getRight());
        
    }
    
    //Funcao chamada pelo usuario
    public String inOrderTraversal(){
        return inOrderTraversal(root);
    }

    /*Pre-ordem*/

    //Funcao recursiva
    private String preOrderTraversal(BNode<T> root){
        if(root == null) return "";
        else return "" + root.getData() +
                    "" + preOrderTraversal(root.getLeft()) +
                    "" + preOrderTraversal(root.getRight());
    }
    
    //Funcao chamada pelo usuario
    public String preOrderTraversal(){
        return preOrderTraversal(root);
    }
    
    /*Pos-ordem*/

    //Funcao recursiva
    private String postOrderTraversal(BNode<T> root){
        if(root == null) return "";
        else return "" + postOrderTraversal(root.getLeft()) +
                    "" + postOrderTraversal(root.getRight()) +
                    "" + root.getData();
    }
    
    //Funcao chamada pelo usuario
    public String postOrderTraversal(){
        return postOrderTraversal(root);
    }
    
    /*Por nivel*/
   public void levelOrderTraversal(){
      Queue<BNode> q = new LinkedList<>();
      q.add(root);
      while(!q.isEmpty()) {
          if(q.peek().getLeft() != null )q.add(q.peek().getLeft());
          if(q.peek().getRight() != null )q.add(q.peek().getRight());
          System.out.print(q.poll().getData());
   }
  }
    
}
