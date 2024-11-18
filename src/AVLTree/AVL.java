package AVLTree;

import BTree.BNode;
import BTree.BST;

public class AVL<T> extends BST<T> {

    public AVL(BNode<Integer> root) {
        super(root);
    }

    public AVL() {
    }
    
    // 'node' é o nó que deseja-se encontrar o fator de balanceamento.
    // Os valores válidos para preservar a AVL são: [-1, 0, 1]
    // -1 -> Subárvore esquerda é maior.
    //  0 -> Árvore esquerda tem mesmo tamanho da árvore direita.
    //  1 -> Subárvore direita é maior.
    int getBalance(BNode<Integer> node) {
        if (node == null)
            return 0;

        int leftHeight = node.getLeft().getHeight();
        int rightHeight = node.getRight().getHeight();

        return leftHeight - rightHeight;
    }

    // 'node' é o nó a ser rotacionado.
    // 'alpha' é raiz de uma subárvore.
    // 'newRoot' é a nova raiz da subárvore já rotacionada. 
    BNode<Integer> rotateRight(BNode<Integer> node) {
        BNode<Integer> newRoot = node.getLeft();
        BNode<Integer> alpha = newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(alpha);

        return newRoot;
    }

    // 'node' é o nó a ser rotacionado.
    // 'alpha' é raiz de uma subárvore.
    // 'newRoot' é a nova raiz da subárvore já rotacionada. 
    BNode<Integer> rotateLeft(BNode<Integer> node) {
        BNode<Integer> newRoot = node.getRight();
        BNode<Integer> alpha = newRoot.getLeft();

        newRoot.setLeft(node);
        node.setRight(alpha);

        return newRoot;
    }

    // 'node' é o nó na árvore, inicialmente o root até virar folha.
    // 'valueToInsert' é o novo valor a ser inserido.
    // Retorna o nó inserido.
    BNode<Integer> insert(BNode<Integer> node, int valueToInsert) {
        if (node == null)
            return new AVLNode<>(valueToInsert);
        
        BNode<Integer> left = node.getLeft();
        BNode<Integer> right = node.getRight();
        int nodeData = node.getData();

        if (valueToInsert < nodeData){
          BNode<Integer> newLeft = insert(left, valueToInsert);
          node.setLeft(newLeft);

        } else if (valueToInsert > nodeData){
            BNode<Integer> newRight = insert(right, valueToInsert);
            node.setRight(newRight);

        } else 
            return node;

        int balance = getBalance(node);
        int leftValue = (Integer)node.getLeft().getData();
        int rightValue = (Integer)node.getRight().getData();

        if (balance > 1 ){
          if (valueToInsert < leftValue)
            return rotateRight(node);

          if (valueToInsert > leftValue){
            BNode<Integer> rotatedLeft = rotateLeft(left);
            node.setLeft(rotatedLeft);
            return rotateRight(node);
          }
        }
        
        if (balance < -1){
          if (valueToInsert > rightValue)
            return rotateLeft(node);
          
          if (valueToInsert < rightValue){
            BNode<Integer> rotatedRight = rotateRight(right);
            node.setRight(rotatedRight);
            return rotateLeft(node);
          }
        }
        
        return node;
    }
    
    // 'root' é a raiz da árvore AVL a remover um valor.
    // 'valueToRemove' é a chave do nó a ser removido.
    // retorna a raiz após a inserção e ajustes para preservar as propriedades de AVL.
    BNode<Integer> deleteNode(BNode root, int valueToRemove) {
        if (root == null)
            return root;

        int rootValue = (Integer)root.getData();
        BNode<Integer> left = root.getLeft();
        BNode<Integer> right = root.getRight();

        if (valueToRemove < rootValue){
            BNode<Integer> newLeft = deleteNode(left, valueToRemove);
            root.setLeft(newLeft);
        } else if (valueToRemove > rootValue){
            BNode<Integer> newRight = deleteNode(right, valueToRemove);
            root.setRight(newRight);
        } else {
            if (root.getDegree() < 2) {
                BNode<Integer> temp;
                if (left == null)
                    temp = right;
                else
                    temp = left;

                root = temp;

            } else {
                BNode temp = getSucessor(root);
                int tempValue = (Integer)temp.getData();

                root.setData(tempValue);

                BNode newRight = deleteNode(right, tempValue);
                root.setRight(newRight);
            }
        }

        if (root == null)
            return root;

        int balance = getBalance(root);
        
        if (balance > 1) {
            if (getBalance(left) >= 0) 
              return rotateRight(root);

            else {
              BNode<Integer> rotatedLeft = rotateLeft(left);
              root.setLeft(rotatedLeft);
              return rotateRight(root);
            }
        }
        
        if (balance < -1) {
          if (getBalance(right) <= 0)
            return rotateLeft(root);
          else {
            BNode<Integer> rotatedRight = rotateRight(right);
            root.setRight(rotatedRight);
            return rotateLeft(root);
          }
        }
    return root;
    }
}
