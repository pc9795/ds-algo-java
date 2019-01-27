package stack;

import geeks_for_geeks.ds.stack.GetMiddleStack;
import geeks_for_geeks.ds.stack.StackApplications;
import geeks_for_geeks.exceptions.DSException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 15:19
 * Purpose: TODO:
 **/
public class TestStack {

    @Test
    void testInfixToPostFix() {
        String exp = ")";
        Assertions.assertThrows(RuntimeException.class, () -> {
            StackApplications.infixToPostFix(exp);
        });

        String exp2 = "a+b+(c*d)";
        System.out.println(StackApplications.infixToPostFix(exp2));
    }

    @Test
    void testNextGreaterElement() {
        int[] arr = {4, 5, 2, 25};
        Assertions.assertArrayEquals(StackApplications.nextGreaterElements(arr),
                new int[]{5, 25, 25, -1});
        int[] arr2 = {13, 7, 6, 12};
        Assertions.assertArrayEquals(StackApplications.nextGreaterElements(arr2),
                new int[]{-1, 12, 12, -1});
    }

    @Test
    void testSortStack() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-3);
        stack.push(14);
        stack.push(18);
        stack.push(-5);
        stack.push(30);
        System.out.println(stack);
        StackApplications.sortStack(stack);
        System.out.println(stack);
    }

    @Test
    void testStockSpan() {
        int[] arr = {100, 80, 60, 70, 60, 75, 85};
        Assertions.assertArrayEquals(StackApplications.stockSpan(arr),
                new int[]{1, 1, 1, 2, 1, 4, 6});
    }

    @Test
    void testGetMiddleStack() {
        GetMiddleStack stack = new GetMiddleStack();
        stack.push(1);
        System.out.println(stack.peek() + "," + stack.peekMiddle());
        stack.push(2);
        System.out.println(stack.peek() + "," + stack.peekMiddle());
        stack.push(3);
        System.out.println(stack.peek() + "," + stack.peekMiddle());
        stack.push(4);
        System.out.println(stack.peek() + "," + stack.peekMiddle());
        stack.push(5);
        System.out.println(stack.peek() + "," + stack.peekMiddle());

        System.out.println("---");
        System.out.println(stack.pop() + "," + stack.peekMiddle());
        System.out.println(stack.pop() + "," + stack.peekMiddle());
        System.out.println(stack.pop() + "," + stack.peekMiddle());
        System.out.println(stack.pop() + "," + stack.peekMiddle());
        Assertions.assertThrows(DSException.class, () -> {
            System.out.println(stack.pop() + "," + stack.peekMiddle());
        });

        System.out.println("---");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.popMiddle() + "," + stack.peekMiddle());
        System.out.println(stack.popMiddle() + "," + stack.peekMiddle());
        System.out.println(stack.popMiddle() + "," + stack.peekMiddle());
        System.out.println(stack.popMiddle() + "," + stack.peekMiddle());
        Assertions.assertThrows(DSException.class, () -> {
            System.out.println(stack.popMiddle() + "," + stack.peekMiddle());
        });
    }
}
