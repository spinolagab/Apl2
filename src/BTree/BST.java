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
    private Node insertAsAgro(Node root, PIBData pibNode) {

       if (root == null) {
           Node newNode = new Node(pibNode);
           if(isEmpty()) setRoot(newNode);
           return newNode;
       }
       Node left = root.getLeft();
       Node right = root.getRight();
       double rootData = root.getData().getAgropecuaria();

       if (pibNode.getAgropecuaria() <  rootData){
          Node newLeft = insertAsAgro(left, pibNode);
          root.setLeft(newLeft);

        } else if (pibNode.getAgropecuaria() >  rootData){
            Node newRight = insertAsAgro(right, pibNode);
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
        double rootYear = root.getData().getAno();

        if (pibNode.getAno() <  rootYear){
            Node newLeft = insertAsDate(left, pibNode);
            root.setLeft(newLeft);


        } else if (pibNode.getAno() >  rootYear){
            Node newRight = insertAsDate(right, pibNode);
            root.setRight(newRight);


        } else{
            int rootMonth = root.getData().getMes();

            if(pibNode.getMes() <  rootMonth){
                Node newLeft = insertAsDate(left, pibNode);
                root.setLeft(newLeft);
            } else if (pibNode.getMes() >  rootMonth){
                Node newRight = insertAsDate(right, pibNode);
                root.setRight(newRight);
            } else return root;
        }
        return root;
    }

    // 'pibNode' é o valor do node
    public void insertAsAgro(PIBData pibNode) {
        insertAsAgro(this.getRoot(), pibNode);
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

    //'root' é a raiz da sub-árvore
    // 'year' é o ano procurado
    // 'total' é o total acumulado
    private double getTotalFromYear(Node root, double year) {
        if(root == null) return 0.0;
        if(year < root.getData().getAno()) return getTotalFromYear(root.getLeft(), year);
        else if(year > root.getData().getAno()) return getTotalFromYear(root.getRight(),year);
        else return root.getData().getAgropecuaria()
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
       return aux.getData().getAgropecuaria();

    }

    public double getMaxIncrease(){
        Node aux = getRoot();
        while(aux.getRight() != null) aux = aux.getRight();
        return aux.getData().getAgropecuaria();

    }

    // 'root' é a raiz da árvore
    private boolean isAgroBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null && root.getLeft().getData().getAgropecuaria() > root.getData().getAgropecuaria()) ||
           (root.getRight() != null && root.getRight().getData().getAgropecuaria() < root.getData().getAgropecuaria())) {
            return false; 
        } else return isAgroBST(root.getLeft()) && isAgroBST(root.getRight());
    }

    public boolean isAgroBST() {
        return isAgroBST(this.getRoot());
    }
    // 'root' é a raiz da árvore
    private boolean isDateBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null
                && root.getLeft().getData().getAno() > root.getData().getAno()
                && root.getLeft().getData().getMes() > root.getData().getMes()) ||
           (root.getRight() != null
                   && root.getRight().getData().getAno() < root.getData().getAno()
                   && root.getRight().getData().getMes() < root.getData().getMes())) {
            return false;
        } else
            return isDateBST(root.getLeft()) && isDateBST(root.getRight());
    }

    public boolean isDateBST() {
        return isDateBST(this.getRoot());
    }


}
