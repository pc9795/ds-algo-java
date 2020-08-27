package gfg.ds.stack;

import gfg.ds.stack.adt.Stack;

/** @noinspection WeakerAccess */
public class StackUsingArray implements Stack {
  private int[] values;
  private int top;

  public StackUsingArray(int size) {
    values = new int[size];
    top = -1;
  }

  /**
   * t=O(1)
   */
  @Override
  public void push(int data) {
    assert !isFull() : "Stack is full";

    values[++top] = data;
  }

  /**
   * t=O(1)
   */
  @Override
  public int pop() {
    assert !isEmpty() : "Stack is empty";
    return values[top--];
  }

  /**
   * t=O(1)
   */
  @Override
  public boolean isEmpty() {
    return top == -1;
  }

  private boolean isFull() {
    return top == values.length - 1;
  }

  /**
   * t=O(1)
   */
  @Override
  public int peek() {
    assert !isEmpty() : "Stack is empty";
    return values[top];
  }
}
