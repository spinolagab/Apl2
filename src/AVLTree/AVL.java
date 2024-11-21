package AVLTree;


import BTree.BST;
import entities.PIBData;

public class AVL extends BST {

    public AVL(AVLNode root) {
        super(root);
    }

    public AVL() {
    }

    private boolean nodeIsRoot(AVLNode node){
        return (AVLNode)getRoot() == node;
    }
    
    // 'node' é o nó que deseja-se encontrar o fator de balanceamento.
    // Os valores válidos para preservar a AVL são: [-1, 0, 1]
    // -1 -> Subárvore esquerda é maior.
    //  0 -> Árvore esquerda tem mesmo tamanho da árvore direita.
    //  1 -> Subárvore direita é maior.
    int getBalance(AVLNode node) {
        if (node == null)
            return 0;

        int leftHeight = node.getLeft() != null ? node.getLeft().getHeight() : -1;
        int rightHeight = node.getRight() != null ? node.getRight().getHeight() : -1;

        return rightHeight - leftHeight;
    }

    // 'node' é o nó a ser rotacionado.
    // 'alpha' é raiz de uma subárvore.
    // 'newRoot' é a nova raiz da subárvore já rotacionada. 
    AVLNode rotateRight(AVLNode node) {
        AVLNode newRoot = (AVLNode) node.getLeft();
        AVLNode alpha = (AVLNode) newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(alpha);

        if(nodeIsRoot(node)) setRoot(newRoot);
        return newRoot;
    }

    // 'node' é o nó a ser rotacionado.
    // 'alpha' é raiz de uma subárvore.
    // 'newRoot' é a nova raiz da subárvore já rotacionada. 
    AVLNode rotateLeft(AVLNode node) {
        AVLNode newRoot =(AVLNode) node.getRight();
        AVLNode alpha = (AVLNode) newRoot.getLeft();

        newRoot.setLeft(node);
        node.setRight(alpha);

        if(nodeIsRoot(node)) setRoot(newRoot);
        return newRoot;
    }

    // 'node' é o nó na árvore, inicialmente o root até virar folha.
    // 'valueToInsert' é o novo valor a ser inserido.
    // Retorna o nó inserido.
    private AVLNode insertBalancedAsAgro(AVLNode node, PIBData valueToInsert) {
        if (node == null){
            AVLNode newNode = new AVLNode(valueToInsert);
            if(isEmpty()) setRoot(newNode);
            return newNode;
        }

        AVLNode left = (AVLNode) node.getLeft();
        AVLNode right = (AVLNode) node.getRight();
        double nodeData = node.getData().getAgropecuaria();

        if (valueToInsert.getAgropecuaria() <  nodeData){
          AVLNode newLeft = insertBalancedAsAgro(left, valueToInsert);
          node.setLeft(newLeft);

        } else if (valueToInsert.getAgropecuaria() >  nodeData){
            AVLNode newRight = insertBalancedAsAgro(right, valueToInsert);
            node.setRight(newRight);

        } else 
            return node;

        int balance = getBalance(node);

        if (balance > 1 ){
          if (getBalance(right) < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              node.setRight(rotatedRight);
              return rotateLeft(node);
          }

          else
            return rotateLeft(node); //LL

        }

        if (balance < -1){
          if (getBalance(left) > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              node.setLeft(rotatedLeft);
              return rotateRight(node);
          }

         else //RR
              return rotateRight(node);
        }
        
        return node;
    }


    // 'node' é o nó na árvore, inicialmente o root até virar folha.
    // 'valueToInsert' é o novo valor a ser inserido.
    // Retorna o nó inserido.
    private AVLNode insertBalancedAsDate(AVLNode node, PIBData valueToInsert) {
        if (node == null){
            AVLNode newNode = new AVLNode(valueToInsert);
            if(isEmpty()) setRoot(newNode);
            return newNode;
        }

        AVLNode left = (AVLNode) node.getLeft();
        AVLNode right = (AVLNode) node.getRight();
        double nodeYear = node.getData().getAno();

        if (valueToInsert.getAno() <  nodeYear){
          AVLNode newLeft = insertBalancedAsDate(left, valueToInsert);
          node.setLeft(newLeft);

        } else if (valueToInsert.getAno() >  nodeYear){
            AVLNode newRight = insertBalancedAsDate(right, valueToInsert);
            node.setRight(newRight);

        } else {
            int nodeMonth = node.getData().getMes();
            if(valueToInsert.getMes() < nodeMonth){
                AVLNode newLeft = insertBalancedAsDate(left, valueToInsert);
                node.setLeft(newLeft);
            } else if(valueToInsert.getMes() > nodeMonth){
                AVLNode newRight = insertBalancedAsDate(right, valueToInsert);
                node.setRight(newRight);
            } else return node;
        }

        int balance = getBalance(node);

        if (balance > 1 ){
          if (getBalance(right) < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              node.setRight(rotatedRight);
              return rotateLeft(node);
          }

          else
            return rotateLeft(node); //LL

        }

        if (balance < -1){
          if (getBalance(left) > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              node.setLeft(rotatedLeft);
              return rotateRight(node);
          }

         else //RR
              return rotateRight(node);
        }

        return node;
    }

    public void insertBalancedAsAgro (PIBData valueToInsert){

        insertBalancedAsAgro((AVLNode) this.getRoot(), valueToInsert);
    }

    public void insertBalancedAsDate (PIBData valueToInsert){

    }
    
    // 'root' é a raiz da árvore AVL a remover um valor.
    // 'valueToRemove' é a chave do nó a ser removido.
    // retorna a raiz após a inserção e ajustes para preservar as propriedades de AVL.
    private AVLNode deleteNode(AVLNode root, double valueToRemove) {
        if (root == null)
            return root;

        double rootValue = root.getData().getAgropecuaria();
        AVLNode left = (AVLNode) root.getLeft();
        AVLNode right = (AVLNode) root.getRight();

        if (valueToRemove < rootValue){
            AVLNode newLeft = deleteNode(left, valueToRemove);
            root.setLeft(newLeft);
        } else if (valueToRemove > rootValue){
            AVLNode newRight = deleteNode(right, valueToRemove);
            root.setRight(newRight);
        } else {
            if (root.getDegree() < 2) {
                AVLNode temp;
                if (left == null)
                    temp = right;
                else
                    temp = left;

                root = temp;

            } else {
                AVLNode temp = (AVLNode) getSucessor(root);
                PIBData tempValue = temp.getData();

                root.setData(tempValue);

                AVLNode newRight = deleteNode(right, tempValue.getAgropecuaria());
                root.setRight(newRight);
            }
        }

        if (root == null)
            return root;

        int balance = getBalance(root);

        left = (AVLNode) root.getLeft();
        right = (AVLNode) root.getRight();

        if (balance > 1 ){
          if (getBalance(right) < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              root.setRight(rotatedRight);
              return rotateLeft(root);
          }

          else
            return rotateLeft(root); //LL

        }

        if (balance < -1){
          if (getBalance(left) > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              root.setLeft(rotatedLeft);
              return rotateRight(root);
          }

         else //RR
              return rotateRight(root);
        }
    return root;
    }

    public AVLNode deleteBalanced(double valueToRemove) {
        return deleteNode((AVLNode) this.getRoot(), valueToRemove);
    }
}
