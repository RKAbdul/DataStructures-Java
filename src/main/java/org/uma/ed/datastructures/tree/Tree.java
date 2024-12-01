package org.uma.ed.datastructures.tree;

import org.uma.ed.datastructures.list.ArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.queue.ArrayQueue;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * This class defines different methods to process general trees. A tree is represented by a root node. If the tree is
 * empty, this root node is null. Otherwise, the root node contains an element and a list of children nodes.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Tree {
  /**
   * This class represents a node in a general tree. Each node contains an element and a list of children nodes.
   *
   * @param <E>
   */
  public static final class Node<E> {
    private final E element;
    private final List<Node<E>> children;

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     */
    public Node(E element) {
      this.element = element;
      this.children = ArrayList.empty();
    }

    /**
     * Creates a node with an element and a list of children.
     *
     * @param element The element in the node.
     * @param children The list of children nodes.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    @SafeVarargs
    public static <T> Node<T> of(T element, Node<T>... children) {
      Node<T> node = new Node<>(element);
      for (Node<T> child : children) {
        node.children.append(child);
      }
      return node;
    }
  }

  /**
   * Returns the number of nodes in a tree.
   *
   * @param root The root node of the tree.
   *
   * @return The number of nodes in the tree.
   */
  public static int size(Node<?> root) {
    int size = 1;
    if (root == null) {
      size = 0;
    } else {
      for (Node<?> child : root.children) {
        size += size(child);
      }
    }
    return size;
  }

  /**
   * Returns the height of a tree.
   *
   * @param root The root node of the tree.
   *
   * @return The height of the tree.
   */
  public static int height(Node<?> root) {
    int height = 1;
    if (root == null) {
      height = 0;
    } else {
      for (Node<?> child : root.children) {
        if (1 + height(child) > height) {
          height = 1 + height(child);
        }
      }
    }
    return height;
  }

  /**
   * Returns the sum of elements in a tree of integers.
   *
   * @param root The root node of the tree.
   *
   * @return The sum of elements in the tree.
   */
  public static int sum(Node<Integer> root) {
    int totalSum = 0;
    if (root != null) {
      totalSum += root.element;
      for (Node<Integer> child : root.children) {
        totalSum += sum(child);
      }
    }

    return totalSum;
  }

  /**
   * Returns the maximum element in a tree.
   *
   * @param root The root node of the tree.
   * @param comparator A comparator to compare elements in the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return The maximum element in the tree according to the comparator.
   */
  public static <T> T maximum(Node<T> root, Comparator<T> comparator) {
    T maximum;
    if (root == null) {
      throw new NoSuchElementException();
    } else {
      maximum = root.element;
      for (Node<T> child : root.children) {
        T maximumOfChild = maximum(child, comparator);
        if (comparator.compare(maximum, maximumOfChild) < 0) {
          maximum = maximumOfChild;
        }
      }
    }

    return maximum;
  }

  /**
   * Returns the number of occurrences of an element in a tree.
   *
   * @param root The root node of the tree.
   * @param element The element to count.
   * @param <T> The type of elements in the tree.
   *
   * @return The number of occurrences of the element in the tree.
   */
  public static <T> int count(Node<T> root, T element) {
    if (root == null) throw new NoSuchElementException();
    int count = root.element == element ? 1 : 0;
    for (Node<T> child : root.children) {
      count += count(child, element);
    }
    return count;
  }

  /**
   * Returns the leaves of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the leaves of the tree.
   */
  public static <T> List<T> leaves(Node<T> root) {
    List<T> leaves = ArrayList.empty();
    if (root != null) {
      leaves(root, leaves);
    }
    return leaves;
  }

  /**
   * Auxiliary method to compute the leaves of a tree.
   *
   * @param root The root node of the tree.
   * @param leaves A list to store the leaves of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void leaves(Node<T> root, List<T> leaves) {
    if (root.children.isEmpty()) {
      leaves.append(root.element);
    } else {
      for (Node<T> childRoot : root.children) {
        leaves(childRoot, leaves);
      }
    }
  }

  /**
   * Returns the preorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the preorder traversal of the tree.
   */
  public static <T> List<T> preorder(Node<T> root) {
    List<T> preorder = ArrayList.empty();
    if (root != null) {
      preorder(root, preorder);
    }
    return preorder;
  }

  /**
   * Auxiliary method to compute the preorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param preorder A list to store the preorder traversal of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void preorder(Node<T> root, List<T> preorder) {
    if (root != null) {
      preorder.append(root.element);
      for (int i = 0; i < root.children.size(); i++) {
        preorder(root.children.get(i), preorder);
      }
    }
  }

  /**
   * Returns the postorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the postorder traversal of the tree.
   */
  public static <T> List<T> postorder(Node<T> root) {
    List<T> postorder = ArrayList.empty();
    if (root != null) {
      postorder(root, postorder);
    }
    return postorder;
  }

  /**
   * Auxiliary method to compute the postorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param postorder A list to store the postorder traversal of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void postorder(Node<T> root, List<T> postorder) {
    if (root != null) {
      for (int i = 0; i < root.children.size(); i++) {
        postorder(root.children.get(i), postorder);
      }
      postorder.append(root.element);
    }
  }

  /**
   * Returns the breadth-first traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the breadth-first traversal of the tree.
   */
  public static <T> List<T> breadthFirst(Node<T> root) {
    List<T> breadthFirst = ArrayList.empty();

    ArrayQueue<Node<T>> newQueue = ArrayQueue.empty();
    newQueue.enqueue(root);

    while(!newQueue.isEmpty()) {
      Node<T> current = newQueue.first();
      newQueue.dequeue();
      breadthFirst.append(current.element);

      for (Node<T> child : current.children) {
        newQueue.enqueue(child);
      }
    }

    return breadthFirst;
  }
}
