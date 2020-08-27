package gfg.ds.linked_list;


public class SortedCircularLinkedList extends CircularLinkedList {
    public SortedCircularLinkedList insert(int data) {
        if (size == 0) {
            super.insertEmpty(data);
            return this;
        }
        if (data <= this.last.next.data) {
            super.insertAtFront(data);
            return this;
        }
        if (data >= this.last.data) {
            super.insertAtEnd(data);
            return this;
        }

        //Finding a position to insert the node
        SinglyLinkedList.Node prev = last;
        SinglyLinkedList.Node curr = last.next;
        do {
            if (curr.data > data) {
                break;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != last.next);

        prev.next = new SinglyLinkedList.Node(data);
        prev.next.next = curr;
        size++;
        return this;
    }

    public SortedCircularLinkedList insert(int... data) {
        for (Integer item : data) {
            this.insert(item);
        }
        return this;
    }

    @Override
    public SortedCircularLinkedList insertAtFront(int data) {
        if (size == 0) {
            super.insertEmpty(data);
            return this;
        }
        assert data <= last.next.data : "The input should be less than or equal to the first node of the list";
        super.insertAtFront(data);
        return this;
    }

    @Override
    public CircularLinkedList insertAtPos(int pos, int data) {
        throw new UnsupportedOperationException("Can't use with sorted circular list");
    }

    @Override
    public CircularLinkedList append(int... data) {
        throw new UnsupportedOperationException("Can't use with sorted circular list");
    }

    @Override
    public SortedCircularLinkedList insertAtEnd(int data) {
        if (size == 0) {
            super.insertEmpty(data);
            return this;
        }
        assert data >= last.data : "The input should be greater than or equal to the last node of the list";
        super.insertAtEnd(data);
        return this;
    }
}