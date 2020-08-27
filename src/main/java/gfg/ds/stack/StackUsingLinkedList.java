package gfg.ds.stack;

import gfg.ds.linked_list.SinglyLinkedList;
import gfg.ds.stack.adt.Stack;

public class StackUsingLinkedList implements Stack {
  private SinglyLinkedList.Node top;

  @Override
  public void push(int data) {
    SinglyLinkedList.Node node = new SinglyLinkedList.Node(data);
    node.next = top;
    top = node;
  }

  @Override
  public int pop() {
    assert !isEmpty() : "Stack is empty";
    int data = top.data;
    top = top.next;
    return data;
  }

  @Override
  public boolean isEmpty() {
    return top == null;
  }

  @Override
  public int peek() {
    assert !isEmpty() : "Stack is emtpy";
    return top.data;
  }
}
