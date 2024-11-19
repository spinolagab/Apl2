package Main;

import BTree.BST;

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(20);
        bst.insert(15);
        bst.insert(17);
        bst.insert(18);
        bst.insert(21);
        System.out.println("Antes de remover: " + bst.inOrderTraversal());
        bst.remove(21);
        System.out.println(bst.getRoot());
        bst.remove(20);
        System.out.println(bst.getRoot());
        System.out.println("Depois: " + bst.inOrderTraversal());
    }
}
