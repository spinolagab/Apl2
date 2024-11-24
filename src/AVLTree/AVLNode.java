package AVLTree;

import BinaryTree.Node;
import entities.PIBData;

public class AVLNode extends Node {
   
    private int fb;

    public AVLNode(PIBData data) {
        super(data);
        fb = 0;
    }

    public int getFb() {
        return fb;
    }

    // 'node' é o nó que deseja-se encontrar o fator de balanceamento.
    // Os valores válidos para preservar a AVL são: [-1, 0, 1]
    // -1 -> Subárvore esquerda é maior.
    //  0 -> Árvore esquerda tem mesmo tamanho da árvore direita.
    //  1 -> Subárvore direita é maior.
    private void setFb(AVLNode node) {
        if (node == null){
            this.fb = 0;
            return;
        }

        int leftHeight = node.getLeft() != null ? node.getLeft().getHeight() : -1;
        int rightHeight = node.getRight() != null ? node.getRight().getHeight() : -1;

        this.fb = rightHeight - leftHeight;
    }

    //Chamada pública, que não necessita de parâmetros
    public void setFb() {
        setFb(this);
    }
}
