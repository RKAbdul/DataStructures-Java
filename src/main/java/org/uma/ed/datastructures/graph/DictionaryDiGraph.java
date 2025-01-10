
package org.uma.ed.datastructures.graph;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

/**
 * Class for directed graphs implemented with a dictionary.
 *
 * @param <V> Type for vertices in graph
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryDiGraph<V> implements DiGraph<V> {
  private final Set<V> vertices;               // set with all vertices in graph
  private final Dictionary<V, Set<V>> diEdges; // dictionary with sources as keys and Set of destinations as values

  public DictionaryDiGraph() {
    vertices = JDKHashSet.empty();
    diEdges = JDKHashDictionary.empty();
  }

  /**
   * Creates an empty directed graph.
   *
   * @param <V> Type for vertices in graph.
   *
   * @return An empty directed graph.
   */
  public static <V> DictionaryDiGraph<V> empty() {
    return new DictionaryDiGraph<>();
  }

  /**
   * Creates a directed graph with given vertices and edges.
   *
   * @param vertices vertices to add to graph.
   * @param edges edges to add to graph.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryDiGraph with given vertices and edges.
   */
  public static <V> DictionaryDiGraph<V> of(Set<V> vertices, Set<DiEdge<V>> edges) {
    DictionaryDiGraph<V> diGraph = new DictionaryDiGraph<>();
    for (V vertex : vertices) {
      diGraph.addVertex(vertex);
    }
    for (DiEdge<V> edge : edges) {
      diGraph.addDiEdge(edge.source(), edge.destination());
    }
    return diGraph;
  }

  /**
   * Creates a directed graph with same vertices and edges as given graph.
   *
   * @param diGraph Graph to copy.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryDiGraph with same vertices and edges as given graph.
   */
  public static <V> DictionaryDiGraph<V> copyOf(DiGraph<V> diGraph) {
      return DictionaryDiGraph.of( diGraph.vertices(), diGraph.edges() );
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
  public void addVertex(V v) {
      vertices.insert(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addDiEdge(V source, V destination) {
      if (!(vertices.contains(source) && vertices.contains(destination))) throw new NoSuchElementException();

      Set<V> values = diEdges.valueOfOrDefault( source, new JDKHashSet<>() );
      values.insert(destination);

      Dictionary.Entry<V, Set<V>> newEntry= Dictionary.Entry.of(source, values );

      diEdges.insert(newEntry);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteDiEdge(V source, V destination) {

      if (!(vertices.contains(source) && vertices.contains(destination))) throw new NoSuchElementException();

      Set<V> values = diEdges.valueOfOrDefault( source, new JDKHashSet<>() );

      if ( values.contains(destination) ) {
          values.delete(destination);

          if ( values.isEmpty() ) {
              diEdges.delete(source);
          } else {
              diEdges.insert(Dictionary.Entry.of(source, values));
          }

      } else {
          throw new NoSuchElementException();
      }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteVertex(V vertex) {
      if (!vertices.contains(vertex)) throw new NoSuchElementException();

      diEdges.delete(vertex);

      for (V ver : vertices) {
          if ( diEdges.valueOf(ver).contains(vertex) ) this.deleteDiEdge(ver, vertex);
      }

      vertices.delete(vertex);
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
  public Set<DiEdge<V>> edges() {
    Set<DiEdge<V>> setToReturn = JDKHashSet.empty();
    for (var entry : diEdges.entries() ) {
        for (var elem : entry.value() ) {
            setToReturn.insert( DiEdge.of( entry.key(), elem) );
        }
    }
    return setToReturn;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfVertices() {
      return vertices().size();
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
   * @param source vertex for which we want to obtain its successors.
   *
   * @return Successors of a vertex.
   */
  @Override
  public Set<V> successors(V source) {
      return diEdges.valueOf(source);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> predecessors(V destination) {
      Set<V> setToReturn = JDKHashSet.empty();
      for ( var edge : edges() ) {
          if (edge.destination() == destination) {
              setToReturn.insert(edge.source());
          }
      }
      return setToReturn;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int inDegree(V vertex) {
    return predecessors(vertex).size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int outDegree(V vertex) {
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
    for (DiEdge<V> edge : edges()) {
      edgesSJ.add(edge.toString());
    }

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(verticesSJ.toString());
    sj.add(edgesSJ.toString());
    return sj.toString();
  }
}
