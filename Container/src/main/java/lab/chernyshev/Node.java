package lab.chernyshev;

/**
 * This class is a node of a linked list.
 * @param <T> the type of element
 */
class Node<T> {
    T data;
    Node<T> next;

    /**
     * Constructor with the parameter.
     * @param data the element to be stored in the Node
     */
    Node(T data) {
        this.data = data;
    }
}
