package AVLTree;

import BTree.Node;
import entities.PIBData;

public class AVLNode extends Node {
   
    private int fb;
    public AVLNode(PIBData data, Node parent) {
        super(data, parent);
        fb = 0;
    }

    public AVLNode(PIBData data) {
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
