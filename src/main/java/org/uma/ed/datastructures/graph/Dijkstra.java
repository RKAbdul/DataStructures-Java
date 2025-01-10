
package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.HashDictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.priorityqueue.JDKPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.MaxiphobicHeapPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;
import org.uma.ed.datastructures.set.HashSet;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.tuple.Tuple2;

/**
 * Class for computing shortest paths in a weighted graph using Dijkstra's algorithm.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Dijkstra {
  /**
   * Computes costs of shortest paths from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source        The source vertex.
   * @param <V>           The type of the vertices in the graph.
   * @return a dictionary where keys are vertices and values are the minimum cost to reach them from the source.
   */
  public static <V> Dictionary<V, Integer> dijkstra(
          WeightedGraph<V, Integer> weightedGraph, V source) {

    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost) implements Comparable<Extension<V>> {
      @Override

      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost) {
        return new Extension<>(source, destination, totalCost);
      }
    }

    Set<V> vertices = JDKHashSet.copyOf(weightedGraph.vertices());
    vertices.delete(source);

    Set<V> verticesOpt = JDKHashSet.of(source);

    Dictionary<V, Integer> dictionary = HashDictionary.of(Dictionary.Entry.of(source, 0));

    while (!vertices.isEmpty()) {
      PriorityQueue<Extension> priorityQueue = JDKPriorityQueue.empty();
      for (V vertex : verticesOpt) {
        for (var succ : weightedGraph.successors(vertex)) {
          V destination = succ.vertex();
          Integer weight = succ.weight();

          if (vertices.contains(destination)) {
            priorityQueue.enqueue(Extension.of(vertex, destination, weight + dictionary.valueOf(vertex)));
          }
        }

        var firstExtension = priorityQueue.first();
        var bestDestination = firstExtension.destination;
        var bestCost = firstExtension.totalCost;

        verticesOpt.insert((V) bestDestination);
        vertices.delete((V) bestDestination);
        dictionary.insert(Dictionary.Entry.of((V) bestDestination, bestCost));
      }
    }

    return dictionary;
  }

  /**
   * Computes shortest paths (and their costs) from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source        The source vertex.
   * @param <V>           The type of the vertices in the graph.
   * @return a dictionary where keys are vertices and values are pairs with the minimum cost to reach them from the
   * source and the path to reach them.
   */
  public static <V> Dictionary<V, Tuple2<Integer, List<V>>> dijkstraPaths(
          WeightedGraph<V, Integer> weightedGraph, V source) {

    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination plus path from source to destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost, List<V> path) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost, List<V> path) {
        return new Extension<>(source, destination, totalCost, path);
      }
    }


    Set<V> vertices = JDKHashSet.copyOf(weightedGraph.vertices());
    vertices.delete(source);

    Set<V> verticesOpt = JDKHashSet.of(source);

    Dictionary<V, Tuple2<Integer, List<V>>> dictionary = HashDictionary.of(Dictionary.Entry.of(source, Tuple2.of(0, JDKArrayList.empty())));

    while (!vertices.isEmpty()) {
      PriorityQueue<Extension> priorityQueue = JDKPriorityQueue.empty();
      for (V vertex : verticesOpt) {
        for (var succ : weightedGraph.successors(vertex)) {
          V destination = succ.vertex();
          Integer weight = succ.weight();

          if (vertices.contains(destination)) {

            List<V> path = JDKArrayList.copyOf(dictionary.valueOf(vertex)._2());
            path.append(destination);

            Integer totalCost = weight + dictionary.valueOf(vertex)._1();

            priorityQueue.enqueue(Extension.of(vertex, destination, totalCost, path));
          }
        }

        var firstExtension = priorityQueue.first();
        var bestDestination = firstExtension.destination;
        var bestCost = firstExtension.totalCost;
        var bestPath = firstExtension.path;

        verticesOpt.insert((V) bestDestination);
        vertices.delete((V) bestDestination);
        dictionary.insert(Dictionary.Entry.of((V) bestDestination, Tuple2.of(bestCost, bestPath)));
      }
    }
    return dictionary;
  }
}

