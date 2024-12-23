package BinaryTree;

public class BinaryTree {
    private Node root;

    //Arvore com pelo menos a raiz
    public BinaryTree(Node root) {
        this.root = root;
    }

    //arvore vazia
    public BinaryTree() {
        this(null);
    }
    
    //Getters e Setters
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
     
    private int getDegree(Node root){
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
    private String inOrderTraversal(Node root){
        if(root == null) return "";
        else return "" + inOrderTraversal(root.getLeft()) +
                    " " + root.getData().getTax() +
                    " " + inOrderTraversal(root.getRight());
        
    }
    
    //Funcao chamada pelo usuario
    public String inOrderTraversal(){
        return inOrderTraversal(root);
    }

    /*Pre-ordem*/

    //Funcao recursiva
    private String preOrderTraversal(Node root){
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
    private String postOrderTraversal(Node root){
        if(root == null) return "";
        else return "" + postOrderTraversal(root.getLeft()) +
                    "" + postOrderTraversal(root.getRight()) +
                    "" + root.getData();
    }
    
    //Funcao chamada pelo usuario
    public String postOrderTraversal(){
        return postOrderTraversal(root);
    }

    
}
