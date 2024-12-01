package org.uma.ed.datastructures.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.uma.ed.datastructures.searchtree.AVL;
import org.uma.ed.datastructures.searchtree.SearchTree;

/**
 * Sets implemented using AVL Search Trees. Order of elements is defined by provided comparator or natural order if none
 * is provided.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class AVLSet<T> extends AbstractSortedSet<T> implements SortedSet<T> {
  private final SearchTree<T> avlTree;

  private AVLSet(AVL<T> avlTree) {
    this.avlTree = avlTree;
  }

  /**
   * Constructs an empty AVLSet with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public AVLSet(Comparator<T> comparator) {
    this(AVL.empty(comparator));
  }

  /**
   * Constructs an empty AVLSet with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public static <T> AVLSet<T> empty(Comparator<T> comparator) {
    return new AVLSet<>(comparator);
  }

  /**
   * Constructs an empty AVLSet set with natural order of elements.
   * <p> Time complexity: O(1)
   */
  public static <T extends Comparable<? super T>> AVLSet<T> empty() {
    return AVLSet.<T>empty(Comparator.naturalOrder());
  }

  /**
   * Creates a new AVLSet with provided comparator and elements.
   * <p> Time complexity: O(n x log n)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New AVLSet with provided comparator and elements.
   */
  @SafeVarargs
  public static <T> AVLSet<T> of(Comparator<T> comparator, T... elements) {
    AVLSet<T> avlSet = new AVLSet<>(comparator);
    avlSet.insert(elements);
    return avlSet;
  }

  /**
   * Creates a new AVLSet with natural order and provided elements.
   * <p> Time complexity: O(n x log n)
   *
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return a new AVLSet with natural order and provided elements
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> AVLSet<T> of(T... elements) {
    return AVLSet.of(Comparator.naturalOrder(), elements);
  }

  /**
   * Creates a new AVLSet with provided comparator and elements in iterable.
   * <p> Time complexity: O(n x log n)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param iterable iterable with elements to include in new sorted set.
   * @param <T> Type of elements in new sorted set.
   *
   * @return New AVLSet with provided comparator and elements.
   */
  public static <T> AVLSet<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    AVLSet<T> avlSet = new AVLSet<>(comparator);
    for (T element : iterable) {
      avlSet.insert(element);
    }
    return avlSet;
  }

  /**
   * Creates a new AVLSet with natural order and elements in iterable.
   * <p> Time complexity: O(n x log n)
   *
   * @param iterable iterable with elements to include in new sorted set.
   * @param <T> Type of elements in new sorted set.
   *
   * @return New AVLSet with provided comparator and elements.
   */
  public static <T extends Comparable<? super T>> AVLSet<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * Returns a new AVLSet with same elements as argument.
   * <p> Time complexity: O(n)
   *
   * @param that AVLSet to be copied.
   *
   * @return a new AVLSet with same elements as {@code that}.
   */
  public static <T> AVLSet<T> copyOf(AVLSet<T> that) {
    return from(
            that.comparator(), that.avlTree.inOrder()
    );
    // return new AVLSet<>(copyof(that.avlTree))
  }

  /**
   * Returns a new AVLSet with same elements as argument.
   * <p> Time complexity: O(n x log n)
   *
   * @param that Sorted set to be copied.
   *
   * @return a new AVLSet with same elements as {@code that}.
   */
  public static <T> AVLSet<T> copyOf(SortedSet<T> that) {
    AVLSet<T> avlSet = AVLSet.empty(that.comparator());
    for (T element : that) {
      avlSet.insert(element);
    }
    return avlSet;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)<>
   */
  @Override
  public Comparator<T> comparator() {
    return avlTree.comparator();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return avlTree.isEmpty();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return avlTree.size();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void insert(T element) {
    avlTree.insert(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public boolean contains(T element) {
    return avlTree.contains(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void delete(T element) {
    avlTree.delete(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    avlTree.clear();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public T minimum() {
    if (isEmpty()) {
      throw new NoSuchElementException("No Such Element");
    }
    return avlTree.minimum();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public T maximum() {
    if (isEmpty()) throw new NoSuchElementException("NO ELEMENT");
    return avlTree.maximum();
  }

  /**
   * Iterator over elements in set. Notice that {@code remove} method is not supported. Note also that set should not be
   * modified during iteration as iterator state may become inconsistent.
   *
   * @see Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    return avlTree.inOrder().iterator();
  }
}
