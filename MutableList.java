package sol;

import src.IListWithNode;

/**
 * A class that implements singly-linked mutable lists.
 *
 * @param <T> - the type of items in the list
 */
public class MutableList<T> implements IList<T>, IListWithNode<T> {
    private Node<T> start;
    private Node<T> end;
    private int length;

    /**
     * A constructor for Mutable List.
     */
    public MutableList() {
        this.start = null;
        this.end = null;
        this.length = 0;
    }

    @Override
    public boolean isEmpty() {
        return (this.start == null);
    }

    @Override
    public T getFirst() {
        if (this.isEmpty()) {
            throw new RuntimeException("List is empty");
        }
        else {
            return this.start.getItem();
        }
    }

    /**
     * Adds an item to the beginning of a MutableList
     * @param item - the item to be added
     */

    @Override
    public void addFirst(T item) {
        Node<T> oldStart = this.start;
        Node<T> newNode = new Node<T>(item, null, oldStart);
        if (this.isEmpty()) {
            this.start = newNode;
            this.end = newNode;
        }
        else {
            this.start = newNode;
            oldStart.setPrev(this.start);

        }
        this.length = this.length + 1;
    }

    /**
     * Adds an item to the end of a MutableList
     * @param item - the item to be added
     */

    @Override
    public void addLast(T item) {
        Node<T> oldEnd = this.end;
        Node<T> newNode = new Node<T>(item, oldEnd, null);
        if (this.isEmpty()) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.end.setNext(newNode);
            this.end = newNode;
            oldEnd.setNext(this.end);
        }
        this.length = this.length + 1;
    }

    /**
     * Computes the length/number of items in a MutableList.
     * @return the length of the list
     */

    @Override
    public int size() {
        if (this.isEmpty())
            return 0;
        else
            return this.length;
    }

    //The runtime of size is constant because it simply returns the
    // length field.

    /**
     * Converts a MutableList into a printable String.
     * @return the list as a String
     */
    public String toString() {
        if (this.start == null)
            return "[]";
        else
            return "[" + this.start.toString() + "]";
    }

    /**
     * Takes a position in the list and returns the item in that
     * position (0 based index)
     * @param index - an item index
     * @return The item of the index given
     * @throws RuntimeException if the index is out of list bounds
     */

    public T get(int index) {
        if (index > this.length | index < 0) {
            throw new RuntimeException("Index out of bounds");
        } else {
            return this.start.get(index);
        }
    }
// In the worst case, get is linear because it has to go through
// the list and be called per element.

    /**
     * Removes an item from a MutableList.
     * @param item - the item to remove
     * @return True if the item was found and removed.
     */

    @Override
    public boolean remove(T item) {
        if (this.isEmpty()) {
            return false;
        } else {
            Node<T> toRemove = this.start.findNode(item); // node to be removed?
            if (toRemove == null) //item has not been found in list
            { return false; }
            if (toRemove == this.start) //if start node is being removed
            {
                this.start = toRemove.getNext();
                this.length = this.length - 1;
                return true;
            } else if (toRemove == this.end) //if end node being removed
            {
                this.end = toRemove.getPrev();
                this.end.setNext(null);
                this.length = this.length - 1;
                return true;
            } else {
                toRemove.nextToPrev();
                toRemove.prevToNext();
                this.length = this.length - 1; //update list length
                return true;
            }}}


// Remove's worst-case running time is linear because it must traverse
    // across each element of the list.

    /**
     * Takes in an item and returns the Node that contains the item.
     * @param item The item in the Node being sought out
     * @return The Node that the item is in.
     */
    @Override
    public Node<T> getNode(T item) {
        if (this.start.findNode(item) == null) {
            throw new RuntimeException("List is empty");
        } else {
            return this.start.findNode(item);
        }
    }

    /**
     * Takes a Node object and removes it from the MutableList
     * @param node The node to be removed
     * @throws RuntimeException if List is empty.
     */
    @Override
    public void removeNode(Node<T> node) {
        if (node == this.start) //if start node is being removed
            {
                this.start = node.getNext();
                this.length = this.length - 1;
            } else if (node == this.end) //if end node being removed
            {
                node.prevToNext();
                this.length = this.length - 1;
            } else {
                node.nextToPrev();
                node.prevToNext();
                this.length = this.length - 1;
            }
        }
    }