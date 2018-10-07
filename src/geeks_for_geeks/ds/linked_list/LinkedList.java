package geeks_for_geeks.ds.linked_list;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:35
 **/
public class LinkedList {
    public Node head;

    @Override
    public String toString() {
        if (this.head == null) {
            return "{}";
        }
        Node curr = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (curr != null) {
            sb.append(curr.data).append(",");
            curr = curr.next;
        }
        sb.append("}");
        return sb.toString();
    }

    public int size() {
        int size = 0;
        for (Node temp = this.head; temp != null; temp = temp.next, size++) ;
        return size;
    }

    public LinkedList insertAtFront(int data) {
        Node node = new Node(data);
        node.next = this.head;
        this.head = node;
        return this;
    }

    public LinkedList insertAtPosition(int pos, int data) {
        if (pos == 0) {
            insertAtFront(data);
            return this;
        }
        int size = size();
        if (pos > size) {
            insertAtEnd(data);
            return this;
        }
        Node temp = this.head;
        for (int i = 0; i < pos - 1; i++, temp = temp.next) ;
        Node node = new Node(data);
        node.next = temp.next;
        temp.next = node;
        return this;
    }

    public LinkedList insertAtEnd(int data) {
        if (this.head == null) {
            this.head = new Node(data);
            return this;
        }
        Node temp;
        for (temp = this.head; temp.next != null; temp = temp.next) ;
        temp.next = new Node(data);
        return this;
    }

    public void delete(int data) {
        Node temp = head;
        Node prev = null;
        for (; temp != null; prev = temp, temp = temp.next) {
            if (temp.data == data) {
                break;
            }
        }
        if (temp == null) {
            throw new RuntimeException("Element not found");
        }
        if (prev == null) {
            System.out.println("Head node is going to be deleted");
            head = head.next;
        } else {
            prev.next = temp.next;
            temp.next = null;
        }
    }

    public void deleteAtPosition(int pos) {
        int i = 0;
        Node curr = head;
        Node prev = null;
        for (; i != pos; i++, prev = curr, curr = curr.next) {
        }
        if (prev == null) {
            System.out.println("Head is going to be deleted");
            head = head.next;
            return;
        }
        if (curr.next == null) {
            System.out.println("Last node is going to be deletd");
            prev.next = null;
            return;
        }
        prev.next = curr.next;
    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.insertAtFront(0);
        list.insertAtEnd(4);
        list.insertAtPosition(0, -1);
        list.insertAtPosition(7, 5);
        list.insertAtPosition(3, 'x');
//        list.delete(17);
        list.delete(-1);
        list.delete(5);
        list.delete(1);
        System.out.println(list);
        list.deleteAtPosition(0);
        list.deleteAtPosition(3);
        list.deleteAtPosition(1);
        System.out.println(list);
    }
}
