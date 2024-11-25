package org.uma.ed.demos.tree;

import org.uma.ed.datastructures.tree.BinaryTree;
import org.uma.ed.datastructures.tree.BinaryTree.Node;

/**
 * Class to demonstrate usage of BinaryTree methods.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BinaryTreeDemo {
  public static void main(String[] args) {
    Node<Integer> tree = Node.of(1, Node.of(2, Node.of(3), null), Node.of(4, Node.of(5), Node.of(6, null, Node.of(7))));

    System.out.println("The height of the tree is: " + BinaryTree.height(tree)); // 4
    System.out.println("The size of the tree is: " + BinaryTree.size(tree)); // 7
    System.out.println("The sum of the tree is: " + BinaryTree.sum(tree)); // 28
    System.out.println("The maximum element in the tree is: " + BinaryTree.maximum(tree, Integer::compareTo)); // 7
    System.out.println("The element 7 is " + BinaryTree.count(tree, 7) + " times in the tree"); // 1
    System.out.println("The leaves of the tree are: " + BinaryTree.leaves(tree)); // ArrayList(3, 5, 7)
    System.out.println("The preorder traversal of the tree is: " + BinaryTree.preorder(tree));
    // ArrayList(1, 2, 3, 4, 5, 6, 7)
    System.out.println("The postorder traversal of the tree is: " + BinaryTree.postorder(tree));
    // ArrayList(3, 2, 5, 7, 6, 4, 1)
    System.out.println("The inorder traversal of the tree is: " + BinaryTree.inorder(tree));
    // ArrayList(3, 2, 1, 5, 4, 6, 7)
    System.out.println("The breadth-first traversal of the tree is: " + BinaryTree.breadthFirst(tree));
    // ArrayList(1, 2, 4, 3, 5, 6, 7)
  }
}
