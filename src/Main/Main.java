package Main;

import BTree.BST;
import AVLTree.AVL;
import entities.PIBData;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insertBalanced(new PIBData("1",2012.0,-0.12));
        avl.insertBalanced(new PIBData("2",2012.0,-0.2012));
        avl.insertBalanced(new PIBData("3",2012.0,-0.312));
        avl.insertBalanced(new PIBData("4",2012.0,0.202));
        avl.insertBalanced(new PIBData("4",2012.0,-0.30));
        avl.insertBalanced(new PIBData("6",2012.0,0.4));

        System.out.println("Antes de remover: " + avl.inOrderTraversal());
        System.out.println("Removendo -0.12 e 0.202...");
        avl.deleteBalanced(-0.12);
        //System.out.println(avl.getRoot());
        avl.deleteBalanced(0.202);
        //System.out.println(avl.getRoot());
        System.out.println("Depois: " + avl.inOrderTraversal());
    }
}
