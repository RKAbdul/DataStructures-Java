package org.uma.ed.datastructures.heap;

import java.util.Comparator;
import org.uma.ed.datastructures.list.ArrayList;

/**
 * Heap implemented using weight biased leftist heap-ordered binary trees.
 *
 * @param <T> Type of elements in heap.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class WBLeftistHeap<T> implements Heap<T> {
  private static final class Node<E> {
    E element;
    int weight; // number of elements in tree
    Node<E> left, right;

    // constructs a node given its element, weight and children
    Node(E element, int weight, Node<E> left, Node<E> right) {
      this.element = element;
      this.weight = weight;
      this.left = left;
      this.right = right;
    }

    // constructs a singleton node with given element
    Node(E element) {
      this(element, 1, null, null);
    }
  }

  /*
   * INVARIANT:
   * - All nodes adhere to the Heap Order Property (HOP): a node's element is ≤ its children's elements.
   * - The tree is structured as a weight biased leftist heap:
   *   - Each node's left child has a weight (total elements) ≥ the right child's weight.
   *   - This ensures the right spine's length is logarithmic relative to the heap's size.
   */

  /**
   * Comparator used to order elements in heap.
   */
  private final Comparator<T> comparator;

  /**
   * Reference to root of this heap.
   */
  private Node<T> root;

  private WBLeftistHeap(Comparator<T> comparator, Node<T> root) {
    this.comparator = comparator;
    this.root = root;
  }

  /**
   * Creates an empty Weight Biased Leftist Heap.
   * <p> Time complexity: O(1)
   */
  public WBLeftistHeap(Comparator<T> comparator) {
    this(comparator, null);
  }

  public static <T> WBLeftistHeap<T> empty(Comparator<T> comparator) {
    return new WBLeftistHeap<>(comparator);
  }

  public static <T extends Comparable<? super T>> WBLeftistHeap<T> empty() {
    return new WBLeftistHeap<T>(Comparator.naturalOrder());
  }

  /**
   * Constructs a Weight Biased Leftist Heap from a list of singleton nodes in O(n) time.
   * @param comparator comparator to use
   * @param nodes list of singleton nodes
   * @param <T> type of elements
   *
   * @return skew heap with elements in nodes
   */
  private static <T> WBLeftistHeap<T> merge(Comparator<T> comparator, ArrayList<Node<T>> nodes) {
    WBLeftistHeap<T> heap = empty(comparator);
    int size = nodes.size();
    if (size > 0)
    {
      while (size > 1)
      {
        for (int i = 0, j = 0; i < size - 1; i += 2, j++)
        {
          Node<T> node1 = nodes.get(i);
          Node<T> node2 = nodes.get(i+1);
          Node<T> m = heap.merge(node1, node2);
          nodes.set(j, m);
        }

        if (size % 2 == 1) {
          nodes.set(size/2, nodes.get(size - 1));
          size = size/2 + 1;
        } else {
          size = size / 2;
        }
      }
    }
    heap.root = nodes.get(0);
    return heap;
  }

  /**
   * Constructs a Weight Biased Leftist Heap from a sequence of elements and a comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator to use
   * @param elements elements to insert in heap
   * @param <T> type of elements
   *
   * @return a Weight Biased Leftist Heap with elements in sequence
   */
  @SafeVarargs
  public static <T> WBLeftistHeap<T> of(Comparator<T> comparator, T... elements) {
    ArrayList<Node<T>> nodes = ArrayList.withCapacity(elements.length);
    for (T element : elements) {
      nodes.append(new Node<>(element));
    }
    return merge(comparator, nodes);
  }

  /**
   * Constructs a Weight Biased Leftist Heap from a sequence of elements and natural order comparator.
   * <p>
   * Time complexity: O(n)
   * @param elements elements to insert in heap
   * @param <T> type of elements
   *
   * @return a Weight Biased Leftist Heap with elements in sequence
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> WBLeftistHeap<T> of(T... elements) {
    return of(Comparator.naturalOrder(), elements);
  }

  /**
   * Constructs a Weight Biased Leftist Heap from an iterable collection and a comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator to use
   * @param iterable collection of elements to insert in heap
   * @param <T> type of elements
   *
   * @return a Weight Biased Leftist Heap with elements in collection
   */
  public static <T> WBLeftistHeap<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    ArrayList<Node<T>> nodes = ArrayList.empty();
    for (T element : iterable) {
      nodes.append(new Node<>(element));
    }
    return merge(comparator, nodes);
  }

  /**
   * Constructs a Weight Biased Leftist Heap from an iterable collection of elements and natural order comparator.
   * <p>
   * Time complexity: O(n)
   * @param iterable collection of elements to insert in heap
   * @param <T> type of elements
   *
   * @return a Weight Biased Leftist Heap with elements in collection
   */
  public static <T extends Comparable<? super T>> WBLeftistHeap<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * <p> Time complexity: O(n)
   */
  public static <T> WBLeftistHeap<T> copyOf(WBLeftistHeap<T> that) {
    return new WBLeftistHeap<>(that.comparator, copyOf(that.root));
  }

  // copies a tree
  private static <T> Node<T> copyOf(Node<T> node) {
    if (node == null) {
      return null;
    } else {
      return new Node<T>(node.element, node.weight, copyOf(node.left), copyOf(node.right));
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
    return comparator;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  // returns weight of a node
  private static <T> int weight(Node<T> node) {
    return node == null ? 0 : node.weight;
  }
  
  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return weight(root);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    root = null;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public void insert(T element) {
    Node<T> nodeToInsert = new Node<>(element);
    root = merge(root, nodeToInsert);
  }

  /* Merges two heap trees along their right spines.
   * Returns merged heap. Reuses nodes during merge
   */
  private Node<T> merge(Node<T> node1, Node<T> node2) {
    if (node1 == null) {
      return node2;
    } else if (node2 == null) {
      return node1;
    }

    if (comparator.compare(node1.element, node2.element) > 0) {
      Node<T> auxiliarNode = node1;
      node1 = node2;
      node2 = auxiliarNode;
    }

    node1.right = merge(node1.right, node2);

    node1.weight = weight(node1.right) + weight(node1.left) + 1;

    if (weight(node1.right) > weight(node1.left)) {
      Node<T> auxiliarNode = node1.right;
      node1.right = node1.left;
      node1.left = auxiliarNode;
    }

    return node1;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public T minimum() {
    if (isEmpty()) throw new EmptyHeapException("EMPTY");
    return root.element;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public void deleteMinimum() {
    if (isEmpty()) throw new EmptyHeapException("EMPTY");
    root = merge(root.left, root.right);
  }

  /**
   * Returns representation of this heap as a String.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder(className).append("(");
    toString(sb, root);
    sb.append(")");
    return sb.toString();
  }

  private static void toString(StringBuilder sb, Node<?> node) {
    if (node == null) {
      sb.append("null");
    } else {
      String className = node.getClass().getSimpleName();
      sb.append(className).append("(");
      toString(sb, node.left);
      sb.append(", ");
      sb.append(node.element);
      sb.append(", ");
      toString(sb, node.right);
      sb.append(")");
    }
  }
}
