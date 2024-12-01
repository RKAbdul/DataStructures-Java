package org.uma.ed.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList is a class that implements a List interface using a linked structure to store elements.
 *
 * @param <T> Type of elements in list.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class LinkedList<T> extends AbstractList<T> implements List<T> {
  /**
   * This class represents a node in a linked structure. Each node contains an element and a reference to the next node.
   *
   * @param <E> Type of elements in node.
   */
  private static final class Node<E> {
    E element;
    Node<E> next;

    Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }
  }

  /**
   * References to nodes with first and last elements in list.
   */
  private Node<T> first, last;

  /**
   * Number of elements in list.
   */
  private int size;

  /* INVARIANT:
   * - `size` is number of elements in list.
   * - `first` is a reference to the node storing first element in list or null if list is empty.
   * - `last` is a reference to the node with last element in list or null if list is empty.
   * - each node contains a reference to the next node or null if it is the last node.
   */

  /**
   * Creates an empty LinkedList.
   * <p> Time complexity: O(1)
   */
  public LinkedList() {
    first = null;
    last = null;
    size = 0;
  }

  /**
   * Creates an empty LinkedList.
   * <p> Time complexity: O(1)
   */
  public static <T> LinkedList<T> empty() {
    return new LinkedList<>();
  }

  /**
   * Creates a LinkedList with elements in given iterable.
   * <p> Time complexity: O(n)
   *
   * @param iterable {@code Iterable} of elements to be added to list.
   * @param <T> Type of elements in iterable.
   *
   * @return a LinkedList with elements in given iterable.
   */
  public static <T> LinkedList<T> from(Iterable<T> iterable) {
    LinkedList<T> list = LinkedList.empty();
    for (T element : iterable) {
      list.append(element);
    }
    return list;
  }

  /**
   * Returns a new LinkedList with given elements.
   * <p> Time complexity: O(n)
   *
   * @param elements Elements to append to new list.
   * @param <T> Type of elements in set.
   *
   * @return a new LinkedList with given elements.
   */
  @SafeVarargs
  public static <T> LinkedList<T> of(T... elements) {
    int size = elements.length;
    LinkedList<T> list = LinkedList.empty();
    if (size > 0) {
      // append first element
      Node<T> last = new Node<>(elements[0], null);
      list.first = last;

      // append rest of elements
      for (int i = 1; i < size; i++) {
        last.next = new Node<>(elements[i], null);
        last = last.next;
      }
      list.last = last;
      list.size = size;
    }
    return list;
  }

  /**
   * Returns a new LinkedList with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that List to be copied.
   *
   * @return a new LinkedList with same elements and order as {@code that}.
   */
  public static <T> LinkedList<T> copyOf(List<T> that) {
    int size = that.size();
    LinkedList<T> linkedList = empty();

    if (size > 0) {
      Node<T> prevElement = new Node<>(that.get(0), null);
      Node<T> lastElement = prevElement;
      linkedList.first = prevElement;

      for (int i = 1; i < size; i++) {
        lastElement = new Node<>(that.get(i), null);
        prevElement.next = lastElement;
        prevElement = lastElement;
      }

      linkedList.last = lastElement;
      linkedList.size = that.size();
    }


    return linkedList;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks if the given index is in the range of valid indices for the ArrayList.
   * @param index the index to check
   * @throws ListException if the index is out of range (index < 0 || index >= size())
   */
  private void validateIndex(int index) {
    if (index < 0 || index >= size) {
      throw new ListException("Invalid index " + index);
    }
  }

  /**
   * Returns the node at given index.
   * @param index index of node to return
   *
   * @return node at given index.
   */
  private Node<T> atIndex(int index) {
    // assumes index is valid
    if (index == size - 1) {
      return last;
    } else {
      Node<T> node = first;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
      return node;
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public T get(int index) {

    validateIndex(index);

    Node<T> node = atIndex(index);

    return node.element;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void set(int index, T element) {
    validateIndex(index);

    Node<T> node = atIndex(index);

    node.element = element;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void append(T element) {
    Node<T> newNode = new Node<>(element, null);

    //// CASE 1: LIST IS EMPTY

    if ( isEmpty() )
    {
      this.first = newNode;
    }
    //// CASE 2: LIST IS NOT EMPTY
    else
    {
      this.last.next = newNode;
    }
      this.last = newNode;

      size++;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void prepend(T element) {

    Node<T> newNode = new Node<>(element, this.first);
    this.first = newNode;

    if ( isEmpty() ) {
      this.last = newNode;
    }

    size++;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void insert(int index, T element) {

      if (index < 0 || index > size) throw new ListException("INVALID INDEX");

      ///// CASE 1: AT POSITION 1

      if (index == 0) {
          prepend( element );
      } else if (index == size) {
          append( element );
      } else {
          Node<T> prevNode = atIndex( index - 1 );
          Node<T> newNode = new Node<>(element, prevNode.next);
          prevNode.next = newNode;
          size++;
      }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void delete(int index) {
      validateIndex(index);

      Node<T> node = atIndex(index);

      if (size == 1) {
          this.first = null;
          this.last = null;
      } else {
        if (index == size - 1) {
          Node<T> prevNode = atIndex(index - 1);
          this.last = prevNode;
          prevNode.next = null;
        } else if (index == 0) {
          this.first = first.next;
        } else {
          Node<T> prevNode = atIndex(index - 1);
          prevNode.next = node.next;
        }
      }

      size--;
  }

  /**
   * Removes all elements from this LinkedList
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
      this.last = null;
      this.first = null;
      size = 0;
  }

  /**
   * Returns an iterator over elements in this LinkedList. Notice that, if the list is structurally modified in any way
   * while the iterator is being used, the iterator state will become inconsistent and will not behave as expected.
   *
   * @see java.lang.Iterable#iterator()
   *
   * @return an iterator over the elements in this list from first to last.
   */
  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  /**
   * This class implements an iterator over elements in this LinkedList.
   * INVARIANT:
   *  - `current` is a reference to the node containing the next element to be returned by next().
   *  - if `current` is null, there are no more elements to be returned.
   */
  private final class LinkedListIterator implements Iterator<T> {
    Node<T> current;

    LinkedListIterator() {
      current = first;
    }

    public boolean hasNext() {
      return current != null;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      T element = current.element;
      current = current.next;
      return element;
    }
  }
}
