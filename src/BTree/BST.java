package BTree;

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
    private Node insert(Node root, double key) {

       if (root == null) {
           root = new Node(key);
           return root;
       }
       if (key < root.getData()) root.setLeft(insert(root.getLeft(), key));
       else if(key > root.getData()) root.setRight(insert(root.getRight(), key));
       return root;
    }

    // 'key' é o valor do node
    public void insert(double key) {
        insert(this.getRoot(), key);
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
        if (key < root.getData()) root.setLeft(remove(root.getLeft(), key));
        else if (key > root.getData()) root.setRight(remove(root.getRight(), key));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            
            Node suc = getSucessor(root);
            root.setData(suc.getData());
            root.setRight(remove(root.getRight(), root.getData()));
            
        }
        return root;
    }

    // 'key' é o valor do node
    public Node remove (double key) { return remove(this.getRoot(), key); }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private Node search(Node root, double key) {
        if (root == null) return null;
        if (key < root.getData()) root = search(root.getLeft(), key);
        else if (key > root.getData()) root = search(root.getRight(), key);
        return root;
    }

    // 'key' é o valor do node
    public Node search (double key) { return search(this.getRoot(), key); }

    // 'root' é a raiz da árvore
    private boolean isBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null && (double)root.getLeft().getData() > root.getData()) ||
           (root.getRight() != null && (double)root.getRight().getData() < root.getData())) {
            return false; 
        } else return isBST(root.getLeft()) && isBST(root.getRight());
    }

    public boolean isBST() { return isBST(this.getRoot()); }

}
