package geeks_for_geeks.ds.linked_list;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class CircularLinkedList implements LinkedList {
    Node last;

    @Override
    public String toString() {
        if (this.last == null) {
            return "{}";
        }
        Node curr = this.last.next;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        do {
            sb.append(curr.data).append(",");
            curr = curr.next;
        } while (curr != last.next);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int size() {
        if (this.last == null) {
            return 0;
        }
        int size = 0;
        Node curr = last.next;
        do {
            size++;
            curr = curr.next;
        } while (curr != last.next);
        return size;
    }

    @Override
    public CircularLinkedList insertAtFront(int data) {
        if (this.last == null) {
            return insertEmpty(data);
        }
        Node node = new Node(data);
        node.next = last.next;
        last.next = node;
        return this;
    }

    @Override
    public CircularLinkedList insertAtPosition(int pos, int data) {
        if (this.last == null) {
            return insertEmpty(data);
        }
        if (pos > size()) {
            return insertAtEnd(data);
        }
        if (pos == 0) {
            return insertAtFront(data);
        }
        Node curr = last.next;

        for (int i = 0; i < pos - 1; i++) {
            curr = curr.next;
        }
        Node node = new Node(data);
        node.next = curr.next;
        curr.next = node;
        return this;
    }

    private CircularLinkedList insertEmpty(int data) {
        this.last = new Node(data);
        this.last.next = last;
        return this;
    }

    @Override
    public CircularLinkedList insertAtEnd(int data) {
        if (this.last == null) {
            return insertEmpty(data);
        }
        Node node = new Node(data);
        node.next = last.next;
        last.next = node;
        last = node;
        return this;
    }

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        cll.insertAtFront(4).insertAtFront(5).insertAtFront(6);
        cll.insertAtPosition(1, 10);
        System.out.println(cll.size());
        System.out.println(cll);
    }
}
