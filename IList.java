package sol;

/**
 * An interface for a very basic List.
 *
 * @param <T> - the type of items in the list
 */
public interface IList<T> {

  /**
   * Returns whether the list is empty.
   *
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty();

  /**
   * Returns the first item in the list.
   *
   * @throws RuntimeException "List is empty" when the list is empty
   * @return the first item in the list
   */
  public T getFirst();

  /**
   * Adds an item to the start of the list.
   *
   * @param item - the item to be added
   */
  public void addFirst(T item);

  /**
   * Returns the number of elements in the list.
   *
   * @return the number of elements in the list
   */
  public int size();

  //////////////////////////////////////////////////////////////////////////////
  // The methods below should be included in the interface and
  // implemented as part of your final submission
  //////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the item at the specified index.
   *
   * @param index - an item index
   * @throws RuntimeException "Index out of bounds" if index is too small or big
   * @return the item at the specified index
   */
  public T get(int index);


  /**
   * Removes an item from the list. If the item is not in the list, the list is
   * unchanged. If the item occurs more than once in the list, removes only the
   * first instance.
   *
   * @param item - the item to remove
   *
   * @return whether or not the item was successfully removed from the list
   */
  public boolean remove(T item);

  /**
   * Adds an item to the end of the list.
   *
  * @param item - the item to be added
   */
  public void addLast(T item);
}
