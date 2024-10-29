package BTree;

public class BST<T> extends BTree{

    // 'root' é a raiz da árvore para árvore com apenas um nó
    public BST(BNode root) {
        super(root);
    }

    // árvore nula
    public BST() {
        this(null);
    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private BNode<Integer> insert(BNode<Integer> root,int key) {
       if (root == null) {
           root = new BNode<>(key);
           return root;
       }
       if (key < (int)root.getData()) root.setLeft(insert(root.getLeft(), key));
       else if(key > (int)root.getData()) root.setRight(insert(root.getRight(), key));
       return root;
    }

    // 'key' é o valor do node
    public void insert(int key) {
        insert(this.getRoot(), key);
    }

    // 'root' é a raiz da árvore
    // 'curr' é o node atual a partir da subárvore à direita
    protected BNode<Integer> getSucessor(BNode<Integer> root) {
        BNode<Integer> curr = root.getRight();
        while(curr != null && curr.getLeft() != null) { curr = curr.getLeft(); }    
        return curr;
    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    // 'suc' é o node sucessor
    private BNode<Integer> remove (BNode<Integer> root, int key) {
        if (root == null) return root;
        if (key < root.getData()) root.setLeft(remove(root.getLeft(), key));
        else if (key > root.getData()) root.setRight(remove(root.getRight(), key));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            
            BNode<Integer> suc = getSucessor(root);
            root.setData(suc.getData());
            root.setRight(remove(root.getRight(), root.getData()));
            
        }
        return root;
    }

    // 'key' é o valor do node
    public BNode<Integer> remove (int key) { return remove(this.getRoot(), key); }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private BNode<Integer> search(BNode<Integer> root, int key) {
        if (root == null) return null;
        if (key < root.getData()) root = search(root.getLeft(), key);
        else if (key > root.getData()) root = search(root.getRight(), key);
        return root;
    }

    // 'key' é o valor do node
    public BNode<Integer> search (int key) { return search(this.getRoot(), key); }

    // 'root' é a raiz da árvore
    private boolean isBST(BNode<Integer> root) {
        if (root == null) return true;
        if ((root.getLeft() != null && (Integer)root.getLeft().getData() > root.getData()) ||
           (root.getRight() != null && (Integer)root.getRight().getData() < root.getData())) {
            return false; 
        } else return isBST(root.getLeft()) && isBST(root.getRight());
    }

    public boolean isBST() { return isBST(this.getRoot()); }

}
