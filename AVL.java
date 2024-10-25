package Exs_Treino;

public class AVL<T> extends BST<T> {

    public AVL(BNode<Integer> root) {
        super(root);
    }

    public AVL() {
    }
    
    // Método para obter o fator de balanceamento de um nó
    int getBalance(BNode<Integer> n) {
        if (n == null)
            return 0;
        return n.getLeft().getHeight() - n.getRight().getHeight();
    }

    // Função para realizar uma rotação à direita
    BNode<Integer> rightRotate(BNode<Integer> y) {
        BNode<Integer> x = (AVLNode)y.getLeft();
        BNode<Integer> T2 = (AVLNode)x.getRight();

        // Realizando a rotação
        x.setRight(y);
        y.setLeft(T2);

        // Retornando a nova raiz
        return x;
    }

    // Função para realizar uma rotação à esquerda
    BNode<Integer> leftRotate(BNode<Integer> x) {
        BNode<Integer> y = (AVLNode)x.getRight();
        BNode<Integer> T2 = (AVLNode)y.getLeft();

        // Realizando a rotação
        y.setLeft(x);
        x.setRight(T2);

        // Retornando a nova raiz
        return y;
    }

    // Método para inserir um nó na árvore AVL
    BNode<Integer> insert(BNode<Integer> node, int key) {
        // Passo 1: Inserir como em uma BST normal
        if (node == null)
            return new AVLNode<>(key);

        if (key < node.getData())
           node.setLeft(insert((BNode<Integer>)node.getLeft(), key));
        else if (key > node.getData())
            node.setRight(insert((BNode<Integer>)node.getRight(), key));
        else // Chaves duplicadas não são permitidas
            return node;

        // Passo 3: Verificar o fator de balanceamento deste nó
        int balance = getBalance(node);

        // Caso 1: Desbalanceamento à esquerda-esquerda
        if (balance > 1 && key < (Integer)node.getLeft().getData())
            return rightRotate(node);

        // Caso 2: Desbalanceamento à direita-direita
        if (balance < -1 && key > (Integer)node.getRight().getData())
            return leftRotate(node);

        // Caso 3: Desbalanceamento à esquerda-direita
        if (balance > 1 && key > (Integer)node.getLeft().getData()) {
            node.setLeft(leftRotate((BNode<Integer>)node.getLeft()));
            return rightRotate(node);
        }

        // Caso 4: Desbalanceamento à direita-esquerda
        if (balance < -1 && key < (Integer)node.getRight().getData()) {
            node.setRight(rightRotate((BNode<Integer>) node.getRight()));
            return leftRotate(node);
        }

        // Retornar o ponteiro do nó (inalterado)
        return node;
    }
    
     // Método para remover um nó da árvore AVL
    BNode<Integer> deleteNode(BNode root, int key) {
        // Passo 1: Realizar a remoção como em uma BST normal
        if (root == null)
            return root;

        // Se o valor a ser removido for menor que o valor da raiz
        if (key < (int)root.getData())
            root.setLeft(deleteNode(root.getLeft(), key));
        // Se o valor a ser removido for maior que o valor da raiz
        else if (key > (int)root.getData())
            root.setRight(deleteNode(root.getRight(), key));
        // Se o valor for igual ao valor da raiz, este é o nó a ser removido
        else {
            // Nó com apenas um filho ou sem filhos
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                BNode<Integer> temp;
                if (root.getLeft() == null)
                    temp = root.getRight();
                else
                    temp = root.getLeft();

                // Caso 1: Sem filhos
                if (temp == null) {
                    root = null;
                }
                // Caso 2: Um filho
                else
                    root = temp; // Copiando o conteúdo do filho não vazio
            }
            else {
                // Nó com dois filhos: obter o sucessor inorder (menor na subárvore direita)
                BNode temp = getSucessor(root);

                // Copiar o valor do sucessor inorder para este nó
                root.setData(temp.getData());

                // Remover o sucessor inorder
                root.setRight(deleteNode(root.getRight(),(Integer)temp.getData()));
            }
        }

        // Se a árvore tinha apenas um nó, então retorne
        if (root == null)
            return root;

        // Passo 3: Verificar o fator de balanceamento do nó atual
        int balance = getBalance(root);

        // Caso 1: Desbalanceamento à esquerda-esquerda
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        // Caso 2: Desbalanceamento à esquerda-direita
        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        // Caso 3: Desbalanceamento à direita-direita
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        // Caso 4: Desbalanceamento à direita-esquerda
        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }
}
