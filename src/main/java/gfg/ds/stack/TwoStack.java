package gfg.ds.stack;

/** @noinspection WeakerAccess */
public class TwoStack {
  private int[] values;
  private int top1;
  private int top2;

  public TwoStack(int size) {
    values = new int[size];
    top1 = -1;
    top2 = values.length;
  }

  public boolean isEmpty() {
    return top1 == -1 && top2 == values.length;
  }

  public boolean isFull() {
    return top1 + 1 == top2;
  }

  /**
   * t=O(1)
   */
  public TwoStack push1(int value) {
    assert !isFull() : "Stack is full";
    values[++top1] = value;
    return this;
  }

  /**
   * t=O(1)
   */
  public TwoStack push2(int value) {
    assert !isFull() : "Stack is full";
    values[--top2] = value;
    return this;
  }

  /**
   * t=O(1)
   */
  public int pop1() {
    assert !isEmpty() : "Stack is empty";
    return values[top1--];
  }

  /**
   * t=O(1)
   */
  public int pop2() {
    assert !isEmpty() : "Stack is empty";
    return values[top2++];
  }
}
