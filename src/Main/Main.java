package Main;

import AVLTree.AVL;

public class Main {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insertBalanced(10);
        //avl.insertBalanced(20).getHeight();  -> Aqui vai dar StackOverflow
//        avl.insertBalanced(15);
//        avl.insertBalanced(17);
       // System.out.println(avl.getRoot());
    }
}
