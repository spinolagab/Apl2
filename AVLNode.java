package Exs_Treino;
public class AVLNode<T> extends BNode<T> {
   
    private int fb;
    public AVLNode(T data, BNode parent) {
        super(data, parent);
        fb = 0;
    }

    public AVLNode(T data) {
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
