package org.uma.ed.datastructures.graph;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

/**
 * Class for undirected graphs implemented with a dictionary
 *
 * @param <V> Type for vertices in graph
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryGraph<V> implements Graph<V> {
  private final Set<V> vertices;                // set with all vertices in graph
  private final Dictionary<V, Set<V>> diEdges;  // dictionary with sources as keys and Set of destinations as values

  /**
   * Creates an empty graph.
   */
  public DictionaryGraph() {
    vertices = JDKHashSet.empty();
    diEdges = JDKHashDictionary.empty();
  }

  /**
   * Creates an empty graph.
   *
   * @param <V> Type for vertices in graph.
   *
   * @return An empty DictionaryGraph.
   */
  public static <V> DictionaryGraph<V> empty() {
    return new DictionaryGraph<>();
  }

  /**
   * Creates a graph with given vertices and edges.
   *
   * @param vertices vertices to add to graph.
   * @param edges edges to add to graph.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryGraph with given vertices and edges.
   */
  public static <V> DictionaryGraph<V> of(Set<V> vertices, Set<Edge<V>> edges) {
    DictionaryGraph<V> graph = new DictionaryGraph<>();
    for (V vertex : vertices) {
      graph.addVertex(vertex);
    }
    for (Edge<V> edge : edges) {
      graph.addEdge(edge.vertex1(), edge.vertex2());
    }
    return graph;
  }

  /**
   * Creates a graph with same vertices and edges as given graph.
   *
   * @param graph Graph to copy.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryGraph with same vertices and edges as given graph.
   */
  public static <V> DictionaryGraph<V> copyOf(Graph<V> graph) {
    return DictionaryGraph.of(graph.vertices(), graph.edges());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return vertices.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addVertex(V vertex) {
    vertices.insert(vertex);
    if (!diEdges.isDefinedAt(vertex)) {
      diEdges.insert(vertex, JDKHashSet.empty());
    }
  }

  private void addDiEdge(V source, V destination) {
    diEdges.valueOf(source).insert(destination);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addEdge(V vertex1, V vertex2) {
    if (!vertices.contains(vertex1)) {
      throw new IllegalArgumentException("Vertex " + vertex1 + " is not in graph");
    }
    if (!vertices.contains(vertex2)) {
      throw new IllegalArgumentException("Vertex " + vertex2 + " is not in graph");
    }

    addDiEdge(vertex1, vertex2);
    addDiEdge(vertex2, vertex1);
  }

  private void deleteDiEdge(V source, V destination) {
    if (!vertices.contains(source)) {
      throw new IllegalArgumentException("Vertex " + source + " is not in graph");
    }
    if (!vertices.contains(destination)) {
      throw new IllegalArgumentException("Vertex " + destination + " is not in graph");
    }

    diEdges.valueOf(source).delete(destination);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEdge(V vertex1, V vertex2) {
    deleteDiEdge(vertex1, vertex2);
    deleteDiEdge(vertex2, vertex1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteVertex(V vertex) {
    if (!vertices.contains(vertex)) throw new NoSuchElementException();

    diEdges.delete(vertex);
    vertices.delete(vertex);

    for (var entries : diEdges.entries() ) {
      entries.value().delete(vertex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> vertices() {
    return vertices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Edge<V>> edges() {
    Set<Edge<V>> setToReturn = JDKHashSet.empty();

    for ( var entry : diEdges.entries() ) {
      for ( var edge : entry.value() ) {
        setToReturn.insert( Edge.of( entry.key(), edge ) );
      }
    }

    return setToReturn;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfVertices() {
    return vertices.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfEdges() {
    return edges().size();
  }

  /**
   * Returns the successors of a vertex in graph (i.e. vertices to which there is an edge from given vertex).
   *
   * @param vertex vertex for which we want to obtain its successors.
   *
   * @return Successors of a vertex.
   */
  @Override
  public Set<V> successors(V vertex) {
    return diEdges.valueOf(vertex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int degree(V vertex) {
    return successors(vertex).size();
  }

  @Override
  public String toString() {
    String className = getClass().getSimpleName();

    StringJoiner verticesSJ = new StringJoiner(", ", "vertices(", ")");
    for (V vertex : vertices()) {
      verticesSJ.add(vertex.toString());
    }

    StringJoiner edgesSJ = new StringJoiner(", ", "edges(", ")");
    for (Edge<V> edge : edges()) {
      edgesSJ.add(edge.toString());
    }

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(verticesSJ.toString());
    sj.add(edgesSJ.toString());
    return sj.toString();
  }
}
