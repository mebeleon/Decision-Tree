package sol;

public class Pointer {

    private String attribute;
    private ITreeNode next;

    public Pointer(String attribute, ITreeNode next){
        this.attribute = attribute;
        this.next = next;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public ITreeNode getNext() {
        return this.next;
    }

}

