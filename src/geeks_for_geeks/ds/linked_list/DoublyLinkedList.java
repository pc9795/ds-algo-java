package geeks_for_geeks.ds.linked_list;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class DoublyLinkedList implements LinkedList {
    DNode head;

    @Override
    public String toString() {
        if (this.head == null) {
            return "{}";
        }
        DNode curr = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (curr != null) {
            sb.append(curr.data).append(",");
            curr = curr.next;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public int size() {
        int size = 0;
        for (DNode temp = this.head; temp != null; temp = temp.next, size++) ;
        return size;
    }

    @Override
    public DoublyLinkedList insertAtFront(int data) {
        DNode node = new DNode(data);
        node.next = this.head;
        if (this.head != null) {
            this.head.prev = node;
        }
        this.head = node;
        return this;
    }

    @Override
    public DoublyLinkedList insertAtPosition(int pos, int data) {
        if (pos == 0) {
            insertAtFront(data);
            return this;
        }
        int size = size();
        if (pos > size) {
            insertAtEnd(data);
            return this;
        }
        DNode temp = this.head;
        for (int i = 0; i < pos; i++, temp = temp.next) ;
        DNode node = new DNode(data);
        node.prev = temp.prev;
        temp.prev = node;
        node.next = temp;
        node.prev.next = node;
        return this;
    }

    @Override
    public DoublyLinkedList insertAtEnd(int data) {
        if (this.head == null) {
            this.head = new DNode(data);
            return this;
        }
        DNode temp;
        for (temp = this.head; temp.next != null; temp = temp.next) ;
        temp.next = new DNode(data);
        temp.next.prev = temp;
        return this;
    }

    public DoublyLinkedList delete(int data) {
        if (this.head == null) {
            System.out.println("List is empty!");
            return this;
        }
        DNode curr = this.head;
        for (; curr != null && curr.data != data; curr = curr.next) {
        }

        if (curr == null) {
            System.out.println("Data is not found in the list");
            return this;
        }
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
        if (curr.prev != null) {
            curr.prev.next = curr.next;
        } else {
            this.head = curr.next;
        }
        return this;
    }

    public DoublyLinkedList reverse() {
        if (this.head == null) {
            System.out.println("List is empty!");
            return this;
        }
        DNode prev = null;
        DNode curr = this.head;
        for (; curr != null; ) {
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;
            curr = curr.prev;
        }
        this.head = prev.prev;
        return this;
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertAtEnd(1).insertAtEnd(2).insertAtEnd(3);
        dll.insertAtFront(4).insertAtFront(5).insertAtFront(6);
        dll.insertAtPosition(1, 10);
        System.out.println(dll);
        System.out.println(dll.reverse());
    }

}
