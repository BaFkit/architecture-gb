package gb.consolidation.lesson2;

public interface MyList<E> extends Iterable<E>{

    void insertFirst(E value);

    E removeFirst();

    boolean remove(E value);

    boolean contains(E value);

    int size();

    boolean isEmpty();

    void display();

    E getFirst();

    class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E>prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
