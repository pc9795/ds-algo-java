package geeks_for_geeks.ds.linked_list;

import geeks_for_geeks.ds.nodes.Node;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class CircularLinkedList implements LinkedList {
    public Node last;
    public int size;

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
        size++;
        return this;
    }

    @Override
    public CircularLinkedList insertAtPosition(int pos, int data) {
        assert pos >= 0 && pos <= size();
        if (this.last == null) {
            return insertEmpty(data);
        }
        if (pos == size()) {
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
        size++;
        return this;
    }

    private CircularLinkedList insertEmpty(int data) {
        this.last = new Node(data);
        this.last.next = last;
        size++;
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
//        This is one line in addition to the steps of insertAtFront
        last = node;
        size++;
        return this;
    }

}
