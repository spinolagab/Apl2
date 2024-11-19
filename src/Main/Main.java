package Main;

import AVLTree.AVL;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insertBalanced(10);
        System.out.println(avl.getRoot());
        avl.insertBalanced(20);
        System.out.println(avl.getRoot());
        avl.insertBalanced(15);
        System.out.println(avl.getRoot());
        avl.insertBalanced(17);
        System.out.println(avl.getRoot());
        avl.insertBalanced(18);
        System.out.println(avl.getRoot());
        avl.insertBalanced(21);
        System.out.println(avl.getRoot());
        //System.out.println(avl.inOrderTraversal());
        avl.deleteBalanced(21);
        avl.deleteBalanced(20);
    }
}
