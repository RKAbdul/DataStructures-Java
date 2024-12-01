import org.junit.jupiter.api.Test;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.tree.Tree;
import org.uma.ed.datastructures.tree.Tree.Node;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    @Test
    public void testSize() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        assertEquals(5, Tree.size(root));
    }

    @Test
    public void testHeight() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        assertEquals(3, Tree.height(root));
    }

    @Test
    public void testSum() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        assertEquals(15, Tree.sum(root));
    }

    @Test
    public void testMaximum() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        assertEquals(5, Tree.maximum(root, Comparator.naturalOrder()));
    }

    @Test
    public void testCount() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5), Node.of(2)));
        assertEquals(2, Tree.count(root, 2));
    }

    @Test
    public void testLeaves() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        List<Integer> leaves = Tree.leaves(root);
        assertEquals(3, leaves.size());
        assertTrue(leaves.contains(2));
        assertTrue(leaves.contains(4));
        assertTrue(leaves.contains(5));
    }

    @Test
    public void testPreorder() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        List<Integer> preorder = Tree.preorder(root);
        assertEquals(5, preorder.size());
        assertEquals(1, preorder.get(0));
        assertEquals(2, preorder.get(1));
        assertEquals(3, preorder.get(2));
        assertEquals(4, preorder.get(3));
        assertEquals(5, preorder.get(4));
    }

    @Test
    public void testPostorder() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        List<Integer> postorder = Tree.postorder(root);
        assertEquals(5, postorder.size());
        assertEquals(2, postorder.get(0));
        assertEquals(4, postorder.get(1));
        assertEquals(5, postorder.get(2));
        assertEquals(3, postorder.get(3));
        assertEquals(1, postorder.get(4));
    }

    @Test
    public void testBreadthFirst() {
        Node<Integer> root = Node.of(1, Node.of(2), Node.of(3, Node.of(4), Node.of(5)));
        List<Integer> breadthFirst = Tree.breadthFirst(root);
        assertEquals(5, breadthFirst.size());
        assertEquals(1, breadthFirst.get(0));
        assertEquals(2, breadthFirst.get(1));
        assertEquals(3, breadthFirst.get(2));
        assertEquals(4, breadthFirst.get(3));
        assertEquals(5, breadthFirst.get(4));
    }
}