package lab.chernyshev;

import java.util.Iterator;

/**
 * This class is a container implemented using a linked list.
 * @param <T> the type of elements in the container
 */
public class Container<T> implements Iterable<T>{
    private  Node<T> head;

    private int size;

    /**
     * Returns the number of elements in this container.
     * @return the number of elements in this container
     */
    public int size() {
        return size;
    }


    /**
     * Appends the specified element to the end of this container
     * @param item element to be appended to this container
     */
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
        }
        else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Returns the element at the specified position in this container.
     * @param index index of the element to return
     * @return the element at the specified position in this container
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;

    }

    /**
     * Removes the first occurrence of the specified element from this container, if it is present.
     * @param item element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(T item) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(item)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(item)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;

    }

    /**
     * Returns an iterator over the elements in this container in proper sequence.
     * @return an iterator over the elements in this container in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.data;
                current=current.next;
                return data;
            }

        };
    }
}
