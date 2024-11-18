package AVLTree;

import BTree.Node;

public class AVLNode extends Node {
   
    private int fb;
    public AVLNode(double data, Node parent) {
        super(data, parent);
        fb = 0;
    }

    public AVLNode(double data) {
        super(data);
        fb = 0;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }
    
    
    
   
}
