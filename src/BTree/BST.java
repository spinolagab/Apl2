package BTree;

import entities.PIBData;

public class BST extends Tree {

    // 'root' é a raiz da árvore para árvore com apenas um nó
    public BST(Node root) {
        super(root);
    }

    // árvore nula
    public BST() {
        this(null);
    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private Node insert(Node root, PIBData pibNode) {

       if (root == null) {
           Node newNode = new Node(pibNode);
           if(isEmpty()) setRoot(newNode);
           return newNode;
       }
       Node left = root.getLeft();
       Node right = root.getRight();
       double rootData = root.getData().getAgropecuaria();

       if (pibNode.getAgropecuaria() <  rootData){
          Node newLeft = insert(left, pibNode);
          root.setLeft(newLeft);
          

        } else if (pibNode.getAgropecuaria() >  rootData){
            Node newRight = insert(right, pibNode);
            root.setRight(newRight);
            

        } 
            return root;

    }

    // 'pibNode' é o valor do node
    public Node insert(PIBData pibNode) {
        return insert(this.getRoot(), pibNode);
    }

    // 'root' é a raiz da árvore
    // 'curr' é o node atual a partir da subárvore à direita
    protected Node getSucessor(Node root) {
        Node curr = root.getRight();
        while(curr != null && curr.getLeft() != null) { curr = curr.getLeft(); }    
        return curr;
    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    // 'suc' é o node sucessor
    private Node remove (Node root, double key) {
        if (root == null) return root;
        if (key < root.getData().getAgropecuaria()) root.setLeft(remove(root.getLeft(), key));
        else if (key > root.getData().getAgropecuaria()) root.setRight(remove(root.getRight(), key));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            
            Node suc = getSucessor(root);
            root.setData(suc.getData());
            root.setRight(remove(root.getRight(), root.getData().getAgropecuaria()));
            
        }
        return root;
    }

    // 'key' é o valor do node
    public Node remove (double key) {
        return remove(this.getRoot(), key);
    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private Node search(Node root, double key) {
        if (root == null) return null;
        if (key < root.getData().getAgropecuaria()) root = search(root.getLeft(), key);
        else if (key > root.getData().getAgropecuaria()) root = search(root.getRight(), key);
        return root;
    }

    // 'key' é o valor do node
    public Node search (double key) {
        return search(this.getRoot(), key);
    }

    // 'root' é a raiz da árvore
    private boolean isBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null && root.getLeft().getData().getAgropecuaria() > root.getData().getAgropecuaria()) ||
           (root.getRight() != null && root.getRight().getData().getAgropecuaria() < root.getData().getAgropecuaria())) {
            return false; 
        } else return isBST(root.getLeft()) && isBST(root.getRight());
    }

    public boolean isBST() {
        return isBST(this.getRoot());
    }

}
