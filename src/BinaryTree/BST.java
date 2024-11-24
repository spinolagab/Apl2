package BinaryTree;

import entities.PIBData;

public class BST extends BinaryTree {

    // 'root' é a raiz da árvore para árvore com apenas um nó
    public BST(Node root) {
        super(root);
    }

    // árvore nula
    public BST() {
        this(null);
    }

    //Insere um nó na árvore, usando a taxa como critério
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

    //Insere um noh na árvore, usando a data como critério
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

    //Chamada pública, que recebe apenas o dado a ser inserido
    // 'pibNode' é o valor do node
    public void insertAsTax(PIBData pibNode) {
        insertAsTax(this.getRoot(), pibNode);
    }

    //Chamada pública, que recebe apenas o dado a ser inserido
    // 'pibNode' é o valor do node
    public  void insertAsDate(PIBData pibNode) {
        insertAsDate(this.getRoot(), pibNode);
    }

    //Obtém o menor valor na subárvore direita de um nó
    // 'root' é a raiz da árvore
    // 'curr' é o node atual a partir da subárvore à direita
    protected Node getSucessor(Node root) {
        Node curr = root.getRight();
        while(curr != null && curr.getLeft() != null) { curr = curr.getLeft(); }    
        return curr;
    }

    //Remove um nó com base na chave passada
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
    
    //Chamada pública, que recebe apenas o dado a ser removido
    // 'key' é o valor do node
    public Node remove (double key) {
        return remove(this.getRoot(), key);
    }

    //Retorna um nó com base na chave passada ou null se não encontrar
    // 'root' é a raiz da árvore
    // 'key' é o valor do node
    private Node search(Node root, double key) {
        if (root == null) return null;
        if (key < root.getData().getTax()) root = search(root.getLeft(), key);
        else if (key > root.getData().getTax()) root = search(root.getRight(), key);
        return root;
    }
    
    
    //Chamada pública, que recebe apenas o dado a ser procurado
    // 'key' é o valor do node
    public Node search (double key) {
        return search(this.getRoot(), key);
    }

    //Retorna a somatória das taxas de um ano
    //'root' é a raiz da subárvore
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

    //Retorna a média do ano passado
    //'year' é o ano que terá a média calculada
    public double getAverageFromYear(double year) {
        return getTotalFromYear(this.getRoot(), year) / 12.0;
    }


    //Retorna a maior variação percentual negativa
    public double getMaxDecrease(){
       Node aux = getRoot();
       while(aux.getLeft() != null) aux = aux.getLeft();
       return aux.getData().getTax();

    }

    //Retorna a maior variação percentual positiva
    public double getMaxIncrease(){
        Node aux = getRoot();
        while(aux.getRight() != null) aux = aux.getRight();
        return aux.getData().getTax();

    }

    //Verifica se uma árvore binaria baseada em taxas corresponde a uma BST
    // 'root' é a raiz da árvore
    private boolean isTaxBST(Node root) {
        if (root == null) return true;
        if ((root.getLeft() != null && root.getLeft().getData().getTax() > root.getData().getTax()) ||
           (root.getRight() != null && root.getRight().getData().getTax() < root.getData().getTax())) {
            return false; 
        } else return isTaxBST(root.getLeft()) && isTaxBST(root.getRight());
    }

    //Chamada pública, que não necessita de parametros
    public boolean isTaxBST() {
        return isTaxBST(this.getRoot());
    }
    
    //Verifica se uma árvore binaria baseada em datas corresponde a uma BST
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

    //Chamada pública, que não necessita de parâmetros
    public boolean isDateBST() {
        return isDateBST(this.getRoot());
    }


}
