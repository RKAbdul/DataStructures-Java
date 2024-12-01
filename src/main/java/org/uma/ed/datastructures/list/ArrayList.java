package org.uma.ed.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractList<T> implements List<T> {
  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private T[] elements;
  private int size;

  public ArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("initial capacity must be greater than 0");
    }
    elements = (T[]) new Object[initialCapacity];
    size = 0;
  }

  public ArrayList() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public static <T> ArrayList<T> empty() {
    return new ArrayList<>();
  }

  public static <T> ArrayList<T> withCapacity(int initialCapacity) {
    return new ArrayList<>(initialCapacity);
  }

  @SafeVarargs
  public static <T> ArrayList<T> of(T... elements) {
    ArrayList<T> list = new ArrayList<>(elements.length);
    int size = 0;
    for (T element : elements) {
      list.elements[size] = element;
      size++;
    }
    list.size = size;
    return list;
  }

  public static <T> ArrayList<T> from(Iterable<T> iterable) {
    ArrayList<T> list = new ArrayList<>();
    for (T element : iterable) {
      list.append(element);
    }
    return list;
  }

  public static <T> ArrayList<T> copyOf(ArrayList<T> that) {
    ArrayList<T> list = new ArrayList<>(that.size);
    System.arraycopy(that.elements, 0, list.elements, 0, that.size);
    list.size = that.size;
    return list;
  }

  public static <T> ArrayList<T> copyOf(List<T> that) {
    ArrayList<T> list = new ArrayList<>(that.size());
    int index = 0;
    for (T element : that) {
      list.elements[index++] = element;
    }
    list.size = index;
    return list;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  private void ensureCapacity() {
    if (size >= elements.length) {
      elements = Arrays.copyOf(elements, 2 * elements.length);
    }
  }

  private void validateIndex(int index) {
    if (index < 0 || index >= size) {
      throw new ListException("Invalid index " + index);
    }
  }

  @Override
  public T get(int index) {
    validateIndex(index);
    return elements[index];
  }

  @Override
  public void set(int index, T element) {
    validateIndex(index);
    elements[index] = element;
  }

  @Override
  public void append(T element) {
    ensureCapacity();
    elements[size++] = element;
  }

  @Override
  public void prepend(T element) {
    ensureCapacity();
    System.arraycopy(elements, 0, elements, 1, size);
    elements[0] = element;
    size++;
  }

  @Override
  public void insert(int index, T element) {
    if (index < 0 || index > size) {
      throw new ListException("Invalid index " + index);
    }
    ensureCapacity();
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = element;
    size++;
  }

  @Override
  public void delete(int index) {
    validateIndex(index);
    System.arraycopy(elements, index + 1, elements, index, size - index - 1);
    elements[--size] = null;
  }

  @Override
  public void clear() {
    Arrays.fill(elements, 0, size, null);
    size = 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayListIterator();
  }

  private final class ArrayListIterator implements Iterator<T> {
    int current;

    ArrayListIterator() {
      current = 0;
    }

    public boolean hasNext() {
      return current < size;
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return elements[current++];
    }
  }
}