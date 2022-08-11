package sol;

// It might be hard to do this with the stencil code  with
// just the start field, it would be hard to dig into it to remove
// the item itself.
// you still have to relink the list after removing the item, and this
// would be a challenge if
// you had a one-element list and that element ended up being removed.

/**
 * A class that implements doubly linked list nodes.
 */
public class Node<S> {
    private S item;
    private Node<S> next;
    private Node<S> prev;

    /**
     * A constructor for Node.
     *
     * @param item - the item stored at this node
     */
    public Node(S item) {
        this.item = item;
        this.next = null;
        this.prev = null;
    }

    public Node(S item, Node<S> prev, Node<S> next) {
        this.prev = prev;
        this.item = item;
        this.next = next;
    }

    public S getItem() {
        return this.item;
    }

    private boolean hasNext() {
        return (this.next != null);
    }

    public Node<S> getNext() {
        return this.next;
    }

    public Node<S> getPrev() {
        return this.prev;
    }

    public void setNext(Node<S> newN) {
        this.next = newN;
    }

    public void setPrev(Node<S> newN) {
        this.prev = newN;
    }


    public int size() {
        if (this.hasNext())
            return 1 + this.next.size();
        else
            return 1;
    }

    public String toString() {
        if (this.hasNext())
            return this.item.toString() + ", " + this.next.toString();
        else if (this.item == null) {
            return " ";
        }
        else
            return this.item.toString();
    }

    /**
     * Helper that returns an item based on a given index number
     * @param index The position of the item to be returned
     * @return Item that the index refers to
     */

    public S get(int index) {
        if (index == 0) {
            return this.item;
        } else if (!this.hasNext()) {
            throw new RuntimeException("Out-of-bounds");
        } else {
            int indexCount = index;
            int i = indexCount - 1;
            return this.next.get(i);
        }
    }

    /**
     * Takes in an item and checks if a Node contains the item
     * @param item The item to be checked for
     * @return True if the Node contains the item.
     */

    public boolean checkItem(S item) {
        return this.item.equals(item);
        //     return true;
        //} else if (this.hasNext()) {
        //    this.getNext().checkItem(item);
        //}
        //return false;
    }

    /**
     * Recursive helper that takes in an item and returns the Node
     * that contains it.
     * @param item The item whose Node is to be found
     * @return The Node with the item.
     */
    public Node<S> findNode(S item) {
        if (this.checkItem(item)) {
            return this;
        }
        else if (this.next == null) {
            return null;
        }
        else {
            return this.next.findNode(item);
        }
    }

    /**
     * When called on Node A, sets its next Node (Node B)'s previous
     * field to Node A's previous field (backward bypassing Node A)
     */

    public void nextToPrev() {
        this.next.prev = this.prev;
    }

    /**
     * When called on Node A, sets its previous Node (Node P)'s
     * next field to Node A's next field (forward bypassing Node A)
     */

    public void prevToNext() {
        this.prev.next = this.next;
    }
}
