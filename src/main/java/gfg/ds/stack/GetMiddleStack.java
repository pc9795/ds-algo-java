package gfg.ds.stack;

import gfg.ds.linked_list.DoublyLinkedList;
import gfg.ds.stack.adt.Stack;

/**
 * Deleting an element from middle is not O(1) for array. Also, we may need to move middle pointer
 * up when we push an element and move down when we pop(). In singly linked list, moving middle
 * pointer in both directions is not possible.
 *
 * @noinspection WeakerAccess
 */
public class GetMiddleStack implements Stack {
  private DoublyLinkedList.DNode top;
  private DoublyLinkedList.DNode mid;
  private int size;

  @Override
  public void push(int data) {
    DoublyLinkedList.DNode node = new DoublyLinkedList.DNode(data);
    // new node is always added to the head of the stack.
    node.prev = null;
    node.next = top;
    if (node.next != null) {
      node.next.prev = node;
    }
    top = node;
    size++;
    if (size == 1) {
      mid = top;
    } else if (size % 2 == 1) {
      // If now total elements are odd then move up.
      mid = mid.prev;
    }
  }

  @Override
  public int pop() {
    assert !isEmpty() : "Stack is empty";
    int val = top.data;
    top = top.next;
    if (top != null) {
      top.prev = null;
    }
    size--;
    if (size == 0) {
      mid = top = null;
    } else if (size % 2 == 0) {
      // If now total elements are even then move down.
      mid = mid.next;
    }
    return val;
  }

  @Override
  public boolean isEmpty() {
    return top == null;
  }

  @Override
  public int peek() {
    assert !isEmpty() : "Stack is empty";
    return top.data;
  }

  public int peekMiddle() {
    assert !isEmpty() : "Stack is empty";
    return mid.data;
  }

  public int popMiddle() {
    assert !isEmpty() : "Stack is empty";
    int val = mid.data;
    DoublyLinkedList.DNode temp;
    if (size % 2 == 1) {
      temp = mid.next;
    } else {
      temp = mid.prev;
    }
    if (mid.prev != null) {
      mid.prev.next = mid.next;
    }
    if (mid.next != null) {
      mid.next.prev = mid.prev;
    }
    size--;
    if (size == 0) {
      mid = top = null;
    } else {
      mid = temp;
    }
    return val;
  }
}
