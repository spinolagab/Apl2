package BinaryTree;

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
    private Node insertAsTax(Node root, PIBData pibNode) {

       if (root == null) {
           Node newNode = new Node(pibNode);
           if(isEmpty()) setRoot(newNode);
           return newNode;
       }
       Node left = root.getLeft();
       Node right = root.getRight();
       double rootData = root.getData().getTax();

       if (pibNode.getTax() <  rootData){
          Node newLeft = insertAsTax(left, pibNode);
          root.setLeft(newLeft);

        } else if (pibNode.getTax() >  rootData){
            Node newRight = insertAsTax(right, pibNode);
            root.setRight(newRight);

        }
        return root;

    }

    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private Node insertAsDate(Node root, PIBData pibNode) {

        if (root == null) {
            Node newNode = new Node(pibNode);
            if(isEmpty()) setRoot(newNode);
            return newNode;
        }
        Node left = root.getLeft();
        Node right = root.getRight();
        double rootYear = root.getData().getYear();

        if (pibNode.getYear() <  rootYear){
            Node newLeft = insertAsDate(left, pibNode);
            root.setLeft(newLeft);


        } else if (pibNode.getYear() >  rootYear){
            Node newRight = insertAsDate(right, pibNode);
            root.setRight(newRight);


        } else{
            int rootMonth = root.getData().getMonth();

            if(pibNode.getMonth() <  rootMonth){
                Node newLeft = insertAsDate(left, pibNode);
                root.setLeft(newLeft);
            } else if (pibNode.getMonth() >  rootMonth){
                Node newRight = insertAsDate(right, pibNode);
                root.setRight(newRight);
            } else return root;
        }
        return root;
    }

    // 'pibNode' é o valor do node
    public void insertAsTax(PIBData pibNode) {
        insertAsTax(this.getRoot(), pibNode);
    }

    public  void insertAsDate(PIBData pibNode) {
        insertAsDate(this.getRoot(), pibNode);
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
        if (key < root.getData().getTax()) root.setLeft(remove(root.getLeft(), key));
        else if (key > root.getData().getTax()) root.setRight(remove(root.getRight(), key));
        else {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            
            Node suc = getSucessor(root);
            root.setData(suc.getData());
            root.setRight(remove(root.getRight(), root.getData().getTax()));
            
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
        if (key < root.getData().getTax()) root = search(root.getLeft(), key);
        else if (key > root.getData().getTax()) root = search(root.getRight(), key);
        return root;
    }

    // 'key' é o valor do node
    public Node search (double key) {
        return search(this.getRoot(), key);
    }

    //'root' é a raiz da sub-árvore
    // 'year' é o ano procurado
    // 'total' é o total acumulado
    private double getTotalFromYear(Node root, double year) {
        if(root == null) return 0.0;
        if(year < root.getData().getYear()) return getTotalFromYear(root.getLeft(), year);
        else if(year > root.getData().getYear()) return getTotalFromYear(root.getRight(),year);
        else return root.getData().getTax()
                    + getTotalFromYear(root.getLeft(), year)
                    + getTotalFromYear(root.getRight(),year);
    }

    //'year' é o ano que terá a média calculada
    public double getAverageFromYear(double year) {
        return getTotalFromYear(this.getRoot(), year) / 12.0;
    }



    public double getMaxDecrease(){
       Node aux = getRoot();
       while(aux.getLeft() != null) aux = aux.getLeft();
       return aux.getData().getTax();

    }

    public double getMaxIncrease(){
        Node aux = getRoot();
        while(aux.getRight() != null) aux = aux.getRight();
        return aux.getData().getTax();

    }

    // 'root' é a raiz da árvore
    private boolean isTaxBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null && root.getLeft().getData().getTax() > root.getData().getTax()) ||
           (root.getRight() != null && root.getRight().getData().getTax() < root.getData().getTax())) {
            return false; 
        } else return isTaxBST(root.getLeft()) && isTaxBST(root.getRight());
    }

    public boolean isTaxBST() {
        return isTaxBST(this.getRoot());
    }
    // 'root' é a raiz da árvore
    private boolean isDateBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null
                && root.getLeft().getData().getYear() > root.getData().getYear()
                && root.getLeft().getData().getMonth() > root.getData().getMonth()) ||
           (root.getRight() != null
                   && root.getRight().getData().getYear() < root.getData().getYear()
                   && root.getRight().getData().getMonth() < root.getData().getMonth())) {
            return false;
        } else
            return isDateBST(root.getLeft()) && isDateBST(root.getRight());
    }

    public boolean isDateBST() {
        return isDateBST(this.getRoot());
    }


}
