package AVLTree;


import BinaryTree.BST;
import entities.PIBData;

public class AVL extends BST {

    public AVL(AVLNode root) {
        super(root);
    }

    public AVL() {
    }

    //Retorna se um noh corresponde a raiz da AVL
    private boolean nodeIsRoot(AVLNode node){
        return (AVLNode)getRoot() == node;
    }


    //Realiza uma rotação simples à direita
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

    //Realiza uma rotação simples à esquerda
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

    //Insere um nó na árvore, usando a taxa como critério, respeitando o balanceamento
    // 'node' é o nó na árvore, inicialmente o root até virar folha.
    // 'valueToInsert' é o novo valor a ser inserido.
    // Retorna o nó inserido.
    private AVLNode insertBalancedAsTax(AVLNode node, PIBData valueToInsert) {
        if (node == null){
            AVLNode newNode = new AVLNode(valueToInsert);
            if(isEmpty()) setRoot(newNode);
            return newNode;
        }

        AVLNode left = (AVLNode) node.getLeft();
        AVLNode right = (AVLNode) node.getRight();
        double nodeData = node.getData().getTax();

        if (valueToInsert.getTax() <  nodeData){
          AVLNode newLeft = insertBalancedAsTax(left, valueToInsert);
          node.setLeft(newLeft);

        } else if (valueToInsert.getTax() >  nodeData){
            AVLNode newRight = insertBalancedAsTax(right, valueToInsert);
            node.setRight(newRight);

        } else 
            return node;

        node.setFb();
        int balance = node.getFb();

        if (balance > 1 ){
          if (right.getFb() < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              node.setRight(rotatedRight);
              return rotateLeft(node);
          }

          else
            return rotateLeft(node); //LL

        }

        if (balance < -1){
          if (left.getFb() > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              node.setLeft(rotatedLeft);
              return rotateRight(node);
          }

         else //RR
              return rotateRight(node);
        }
        
        return node;
    }


    //Insere um noh na árvore, usando a data como critério, respeitando o balanceamento
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
        double nodeYear = node.getData().getYear();

        if (valueToInsert.getYear() <  nodeYear){
          AVLNode newLeft = insertBalancedAsDate(left, valueToInsert);
          node.setLeft(newLeft);

        } else if (valueToInsert.getYear() >  nodeYear){
            AVLNode newRight = insertBalancedAsDate(right, valueToInsert);
            node.setRight(newRight);

        } else {
            int nodeMonth = node.getData().getMonth();
            if(valueToInsert.getMonth() < nodeMonth){
                AVLNode newLeft = insertBalancedAsDate(left, valueToInsert);
                node.setLeft(newLeft);
            } else if(valueToInsert.getMonth() > nodeMonth){
                AVLNode newRight = insertBalancedAsDate(right, valueToInsert);
                node.setRight(newRight);
            } else return node;
        }

        node.setFb();
        int balance = node.getFb();

        if (balance > 1 ){
          if (right.getFb() < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              node.setRight(rotatedRight);
              return rotateLeft(node);
          }

          else
            return rotateLeft(node); //LL

        }

        if (balance < -1){
          if (left.getFb() > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              node.setLeft(rotatedLeft);
              return rotateRight(node);
          }

         else //RR
              return rotateRight(node);
        }

        return node;
    }

    //Chamada pública, que recebe apenas o valor a ser inserido
    public void insertBalancedAsTax(PIBData valueToInsert){

        insertBalancedAsTax((AVLNode) this.getRoot(), valueToInsert);
    }

    //Chamada pública, que recebe apenas o valor a ser inserido
    public void insertBalancedAsDate (PIBData valueToInsert){

        insertBalancedAsDate((AVLNode) this.getRoot(), valueToInsert);
    }

    //Remove um nó com base na chave passada, respeitando o balanceamento
    // 'root' é a raiz da árvore AVL a remover um valor.
    // 'valueToRemove' é a chave do nó a ser removido.
    // retorna a raiz após a inserção e ajustes para preservar as propriedades de AVL.
    private AVLNode deleteNode(AVLNode node, double valueToRemove) {
        if (node == null)
            return node;

        double nodeValue = node.getData().getTax();
        AVLNode left = (AVLNode) node.getLeft();
        AVLNode right = (AVLNode) node.getRight();

        if (valueToRemove < nodeValue){
            AVLNode newLeft = deleteNode(left, valueToRemove);
            node.setLeft(newLeft);
        } else if (valueToRemove > nodeValue){
            AVLNode newRight = deleteNode(right, valueToRemove);
            node.setRight(newRight);
        } else {
            if (node.getDegree() < 2) {
                AVLNode temp;
                if (left == null)
                    temp = right;
                else
                    temp = left;

                node = temp;

            } else {
                AVLNode temp = (AVLNode) getSucessor(node);
                PIBData tempValue = temp.getData();

                node.setData(tempValue);

                AVLNode newRight = deleteNode(right, tempValue.getTax());
                node.setRight(newRight);
            }
        }

        if (node == null)
            return node;

        node.setFb();
        int balance = node.getFb();

        left = (AVLNode) node.getLeft();
        right = (AVLNode) node.getRight();

        if (balance > 1 ){
          if (right.getFb() < 0){ //RL
              AVLNode rotatedRight = rotateRight(right);
              node.setRight(rotatedRight);
              return rotateLeft(node);
          }

          else
            return rotateLeft(node); //LL

        }

        if (balance < -1){
          if (left.getFb() > 0){ //LR
              AVLNode rotatedLeft = rotateLeft(left);
              node.setLeft(rotatedLeft);
              return rotateRight(node);
          }

         else //RR
              return rotateRight(node);
        }
    return node;
    }

    //Chamada pública, que recebe apenas o valor a ser removido
    public AVLNode deleteBalanced(double valueToRemove) {
        return deleteNode((AVLNode) this.getRoot(), valueToRemove);
    }
}
