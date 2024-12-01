package org.uma.ed.datastructures.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * BinaryHeap is a class that implements a min binary heap data structure.
 * A binary heap is a complete binary tree which satisfies the heap ordering property.
 * This implementation uses an array to store the elements of the heap, and it can dynamically resize itself.
 * The heap is always kept complete by adding elements to the bottom level of the tree at the leftmost open space.
 *
 * @param <T> Type of elements in heap.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BinaryHeap<T> implements Heap<T> {
  /*
   * INVARIANT:
   * - The array elements[0..size-1] represents a heap-ordered binary tree.
   * - The tree's root is at index 0.
   * - For any node at index i:
   *   - The parent is at index (i-1)/2.
   *   - The left child is at index 2*i+1.
   *   - The right child is at index 2*i+2.
   */

  /**
   * Default initial capacity for the heap
   */private static final int DEFAULT_INITIAL_CAPACITY = 16;

  /**
   * Index of the root element in the heap
   */
  private static final int ROOT_INDEX = 0;

  /**
   * Comparator used for ordering the elements in the heap
   */
  private final Comparator<T> comparator;

  /**
   * Current number of elements in the heap
   */
  private int size;

  /**
   * Array used to store the elements in the heap
   */
  private T[] elements;

  /**
   * Private constructor for creating a binary heap with a given comparator, size, and elements.
   *
   * @param comparator The comparator used to order the elements in the heap.
   * @param size The current size of the heap.
   * @param elements The elements in the heap.
   */
  private BinaryHeap(Comparator<T> comparator, int size, T[] elements) {
    this.comparator = comparator;
    this.size = size;
    this.elements = elements;
  }

  /**
   * Creates an empty binary heap. Initial capacity is {@code initialCapacity} elements. Capacity is automatically
   * increased when needed.
   *
   * @param comparator comparator for defining order of elements.
   * @param initialCapacity Initial capacity.
   *
   * @throws IllegalArgumentException if initial capacity is less than 1.
   * <p> Time complexity: O(1)
   */
  @SuppressWarnings("unchecked")
  public BinaryHeap(Comparator<T> comparator, int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("initial capacity must be greater than 0");
    }
    this.comparator = comparator;
    this.elements = (T[]) new Object[initialCapacity];
    this.size = 0;
  }

  /**
   * Creates an empty binary heap with provided comparator and default initial capacity. Capacity is automatically
   * increased when needed.
   * <p> Time complexity: O(1)
   */
  public BinaryHeap(Comparator<T> comparator) {
    this(comparator, DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Creates an empty binary heap with the provided comparator and default initial capacity.
   *
   * @param comparator The comparator used to order the elements in the heap.
   * @return A new empty binary heap.
   */
  public static <T> BinaryHeap<T> empty(Comparator<T> comparator) {
    return new BinaryHeap<>(comparator, DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Creates a binary heap with the given initial capacity and uses the natural order comparator.
   *
   * @param initialCapacity The initial capacity of the heap.
   * @return A new binary heap with the specified initial capacity.
   */
  public static <T extends Comparable<? super T>> BinaryHeap<T> withCapacity(int initialCapacity) {
    return new BinaryHeap<T>(Comparator.naturalOrder(), initialCapacity);
  }

  /**
   * Creates an empty binary heap with the natural order comparator and default initial capacity.
   *
   * @return A new empty binary heap.
   */
  public static <T extends Comparable<? super T>> BinaryHeap<T> empty() {
    return new BinaryHeap<T>(Comparator.naturalOrder(), DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Creates a binary heap with the given initial capacity and comparator.
   *
   * @param comparator The comparator used to order the elements in the heap.
   * @param initialCapacity The initial capacity of the heap.
   * @return A new binary heap with the specified initial capacity and comparator.
   */
  public static <T> BinaryHeap<T> withCapacity(Comparator<T> comparator, int initialCapacity) {
    return new BinaryHeap<>(comparator, initialCapacity);
  }

  /**
   * Creates a binary heap with given elements and comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator for defining order of elements.
   * @param elements elements to insert in heap.
   * @param <T> type of elements
   *
   * @return a binary heap with elements in sequence
   */
  @SafeVarargs
  public static <T> BinaryHeap<T> of(Comparator<T> comparator, T... elements) {
    int size = elements.length;
    BinaryHeap<T> heap = BinaryHeap.withCapacity(comparator, size);
    System.arraycopy(elements, 0, heap.elements, 0, size);
    heap.size = size;
    for (int i = size / 2 - 1; i >= 0; i--) {
      heap.heapifyDown(i);
    }
    return heap;
  }

  /**
   * Creates a binary heap with given elements and natural order comparator.
   * <p>
   * Time complexity: O(n)
   * @param elements elements to insert in heap.
   * @param <T> type of elements
   *
   * @return a binary heap with elements in sequence
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> BinaryHeap<T> of(T... elements) {
    return of(Comparator.naturalOrder(), elements);
  }

  /**
   * Creates a binary heap with given elements and comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator for defining order of elements.
   * @param iterable elements to insert in heap.
   * @param <T> type of elements
   *
   * @return a binary heap with elements in iterable
   */
  public static <T> BinaryHeap<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    // first count elements in iterable
    int size = 0;
    for (T _elem : iterable) {
      size++;
    }

    // then create heap with elements
    BinaryHeap<T> heap = BinaryHeap.withCapacity(comparator, size);
    int i = 0;
    for (T element : iterable) {
      heap.elements[i] = element;
      i++;
    }
    heap.size = size;

    // heapify down from last non-leaf to root
    for (int j = size / 2 - 1; j >= 0; j--) {
      heap.heapifyDown(j);
    }
    return heap;
  }

  /**
   * Creates a binary heap with given elements and natural order comparator.
   * <p>
   * Time complexity: O(n)
   * @param iterable elements to insert in heap.
   * @param <T> type of elements
   *
   * @return a binary heap with elements in iterable
   */
  public static <T extends Comparable<? super T>> BinaryHeap<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * Creates a binary heap with same elements and comparator as given heap.
   * <p>
   * Time complexity: O(n)
   * @param that heap to copy
   * @param <T> type of elements
   *
   * @return a binary heap with same elements and comparator as given heap
   */
  @SuppressWarnings("unchecked")
  public static <T> BinaryHeap<T> copyOf(BinaryHeap<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
    return this.comparator;
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
    return this.size;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elements[i] = null;
    }
    size = 0;
  }

  /**
   * Ensures that the capacity of the heap is sufficient to hold a new element.
   * If the current size is equal to the length of the elements array, the capacity is doubled.
   */
  private void ensureCapacity() {
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, 2 * elements.length);
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: mostly O(log n). O(n) when heap capacity has to be increased.
   */
  @Override
  public void insert(T element) {
    ensureCapacity();
    elements[size] = element;
    heapifyUp(size);
    size++;
  }

  // Checks if elements[index1] < elements[index2]
  private boolean lessThan(int index1, int index2) {
    return this.comparator.compare(elements[index1], elements[index2]) < 0;
  }

  // Swaps elements in array at positions index1 and index2
  private void swap(int index1, int index2) {
    T auxiliar = elements[index1];
    elements[index1] = elements[index2];
    elements[index2] = auxiliar;
  }

  // Checks if the given index is the root of the heap
  private static boolean isRoot(int index) {
    return index == ROOT_INDEX;
  }

  // Returns the index for the parent of the node with the given index
  private static int parent(int index) {
    return (index - 1) / 2;
  }

  // Returns the index for the left child of the node with the given index
  private static int leftChild(int index) {
    return index * 2 + 1;
  }

  // Returns the index for the right child of the node with the given index
  private static int rightChild(int index) {
    return index * 2 + 2;
  }

  // Checks if the given index corresponds to a node in the heap
  private boolean isNode(int index) {
    return index < size;
  }

  // Checks if the node with the given index has a left child
  private boolean hasLeftChild(int index) {
    int leftChildIndex = leftChild(index);
    return leftChildIndex < size;
  }

  // Checks if the node with the given index is a leaf node
  // !hasLeftChild(index) => !hasRightChild(index) as tree is complete
  private boolean isLeaf(int index) {
    return !hasLeftChild(index);
  }

  /**
   * Moves the element at the given index up the heap until it is in the correct position.
   *
   * @param index The index of the element to move.
   */
  private void heapifyUp(int index) {
    int parentPosition = parent(index);

    while ( !isRoot(index) && lessThan(index, parentPosition) ) {
      swap(index, parentPosition);
      index = parentPosition;
      parentPosition = parent(index);
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public T minimum() {
    if (isEmpty()) throw new EmptyHeapException();
    return elements[0];
  }

  /**
   * Moves the element at the given index down the heap until it is in the correct position.
   *
   * @param index The index of the element to move.
   */
  private void heapifyDown(int index) {
//    int leftChild = leftChild(index);
//    int rightChild = rightChild(index);
//    boolean heap = false;
//
//    if (leftChild < size && lessThan(leftChild, index)) {
//      if (rightChild < size && lessThan(rightChild, index)) {
//        heap = true;
//      } else if (rightChild >= size) {
//        heap = true;
//      }
//    }
//
//    while( !isLeaf(index) && !heap) {
//      int minChild = leftChild;
//      if (rightChild < size) {
//        minChild = lessThan(rightChild, leftChild) ? rightChild : leftChild;
//      }
//      swap(leftChild, index);
//
//      index = leftChild;
//      leftChild = leftChild(index);
//      rightChild = rightChild(index);
//
//      if (leftChild < size && lessThan(leftChild, index)) {
//        if (rightChild < size && lessThan(rightChild, index)) {
//          heap = true;
//        } else if (rightChild >= size) {
//          heap = true;
//        }
//      }
//    }

    while (!isLeaf(index)) {
      int chosenChild  = leftChild(index);
      int rightChild = rightChild(index);

      if (isNode(rightChild) && lessThan(rightChild, chosenChild)) {
        chosenChild = rightChild;
      }

      if (!lessThan(index, chosenChild)) {
        swap(index, chosenChild);
        index = chosenChild;
      } else {
        break;
      }
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public void deleteMinimum() {
    if (isEmpty()) throw new EmptyHeapException();
    elements[0] = elements[size - 1];
    elements[size - 1] = null;
    size--;
    heapifyDown(0);
  }

  /**
   * Returns representation of this heap as a String.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder(className).append("(");
    toString(sb, ROOT_INDEX);
    sb.append(")");
    return sb.toString();
  }

  /**
   * Helper method for the toString method. It recursively builds a string representation of the heap.
   *
   * @param sb The StringBuilder to append to.
   * @param index The index of the current node.
   */
  private void toString(StringBuilder sb, int index) {
    if (!isNode(index)) {
      sb.append("null");
    } else {
      String className = "Node";
      sb.append(className).append("(");
      toString(sb, leftChild(index));
      sb.append(", ");
      sb.append(elements[index]);
      sb.append(", ");
      toString(sb, rightChild(index));
      sb.append(")");
    }
  }
}