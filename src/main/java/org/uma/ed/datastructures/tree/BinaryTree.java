package org.uma.ed.datastructures.tree;

import org.uma.ed.datastructures.list.ArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.queue.ArrayQueue;
import org.uma.ed.datastructures.queue.Queue;

import java.util.Comparator;

/**
 * This class defines different methods to process binary trees. A binary tree is represented by a root node. If the
 * tree is empty, this root node is null. Otherwise, the root node contains an element and references to left and right
 * children nodes.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BinaryTree {
  /**
   * This class represents a node in a binary tree. Each node contains an element and references to left and right
   * children nodes.
   *
   * @param <E>
   */
  public static final class Node<E> {
    private final E element;
    private final Node<E> left, right;

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     */
    public Node(E element) {
      this(element, null, null);
    }

    /**
     * Creates a node with an element and left and right children.
     *
     * @param element The element in the node.
     * @param left The left child of the node.
     * @param right The right child of the node.
     */
    public Node(E element, Node<E> left, Node<E> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    /**
     * Creates a node with an element and no children.
     *
     * @param element The element in the node.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and no children.
     */
    public static <T> Node<T> of(T element) {
      return new Node<>(element);
    }

    /**
     * Creates a node with an element and left and right children.
     *
     * @param element The element in the node.
     * @param left The left child of the node.
     * @param right The right child of the node.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    public static <T> Node<T> of(T element, Node<T> left, Node<T> right) {
      return new Node<>(element, left, right);
    }
  }

  /**
   * Returns the number of nodes in a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The number of nodes in the tree.
   */
  public static int size(Node<?> root) {
    int size = 1;
    if (root.left != null) size += size(root.left);
    if (root.right != null) size += size(root.right);
    return size;
  }

  /**
   * Returns the height of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The height of the tree.
   */
  public static int height(Node<?> root) {
    int height = 1;
    if (root.left != null) {
      height = 1 + height(root.left);
    }
    if (root.right != null) {
      height = Math.max(height, 1 + height(root.right));
    }
    return height;
  }

  /**
   * Returns the sum of the elements in a binary tree of integers.
   *
   * @param root The root node of the tree.
   *
   * @return The sum of the elements in the tree.
   */
  public static int sum(Node<Integer> root) {
    int sum = root.element;
    if (root.right != null) sum += sum(root.right);
    if (root.left != null) sum += sum(root.left);
    return sum;
  }

  /**
   * Returns the maximum element in a binary tree of integers.
   *
   * @param root The root node of the tree.
   * @param comparator Comparator to compare elements.
   *
   * @return The maximum element in the tree.
   */
  public static int maximum(Node<Integer> root, Comparator<Integer> comparator) {
    int currentMaximum = root.element;
    if (root.left != null) {
      int leftChildMax = maximum(root.left, comparator);
      if ( comparator.compare(currentMaximum, leftChildMax) < 0 ) {
        currentMaximum = leftChildMax;
      }
    }
    if (root.right != null) {
      int rightChildMax = maximum(root.right, comparator);
      if ( comparator.compare(currentMaximum, rightChildMax) < 0 ) {
        currentMaximum = rightChildMax;
      }
    }
    return currentMaximum;
  }

  /**
   * Returns the number of times an element appears in a binary tree.
   *
   * @param root The root node of the tree.
   * @param element The element to count.
   *
   * @return The number of times the element appears in the tree.
   */
  public static int count(Node<Integer> root, int element) {
    int count = root.element == element ? 1 : 0;
    if (root.left != null) count += count(root.left, element);
    if (root.right != null) count += count(root.right, element);
    return count;
  }

  /**
   * Returns the leaves of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The leaves of the tree.
   */
  public static <T> List<T> leaves(Node<T> root) {
    List<T> leaves = ArrayList.empty();
    leaves(root, leaves);
    return leaves;
  }

  /**
   * Auxiliary method to compute leaves of a binary tree.
   *
   * @param root The root node of the tree.
   * @param leaves List to store leaves.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void leaves(Node<T> root, List<T> leaves) {
    if (root.right == null && root.left == null) {
      leaves.append(root.element);
    } else {
      if (root.right != null) leaves(root.right, leaves);
      if (root.left != null) leaves(root.left, leaves);
    }
  }

  /**
   * Returns the preorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The preorder traversal of the tree.
   */
  public static <T> List<T> preorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    preorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute preorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void preorder(Node<T> root, List<T> traversal) {
    traversal.append(root.element);
    if (root.left != null) preorder(root.left, traversal);
    if (root.right != null) preorder(root.right, traversal);
  }

  /**
   * Returns the postorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The postorder traversal of the tree.
   */
  public static <T> List<T> postorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    postorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute postorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void postorder(Node<T> root, List<T> traversal) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the inorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The inorder traversal of the tree.
   */
  public static <T> List<T> inorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    inorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute inorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void inorder(Node<T> root, List<T> traversal) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the breadth-first traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The breadth-first traversal of the tree.
   */
  public static <T> List<T> breadthFirst(Node<T> root) {
    List<T> arrayList = ArrayList.empty();

    Queue<Node<T>> queue = ArrayQueue.empty();
    queue.enqueue(root);

    while (!queue.isEmpty()) {
      Node<T> currElement = queue.first();
      queue.dequeue();

      arrayList.append(currElement.element);
      if (currElement.right != null) queue.enqueue(currElement.right);
      if (currElement.left != null) queue.enqueue(currElement.left);
    }

    return arrayList;
  }
}
