package geeks_for_geeks.stack;

import geeks_for_geeks.ds.stack.GetMiddleStack;
import geeks_for_geeks.ds.stack.Applications;
import geeks_for_geeks.exceptions.DSException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 15:19
 * Purpose: Test
 **/
class TestStack {

    @Test
    void testInfixToPostFixUnbalancedParenthesis() {
        String exp = ")";
        Assertions.assertThrows(AssertionError.class, () -> {
            Utils.infixToPostFix(exp);
        });
    }

    @Test
    void testInfixToPostFix() {
        String exp = "a+b+(c*d)";
        assert Utils.infixToPostFix(exp).equals("abcd*++");
    }

    @Test
    void testNextGreaterElement() {
        int[] arr = {4, 5, 2, 25};
        Assertions.assertArrayEquals(Applications.nextGreaterElements(arr),
                new int[]{5, 25, 25, -1});
        int[] arr2 = {13, 7, 6, 12};
        Assertions.assertArrayEquals(Applications.nextGreaterElements(arr2),
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
        ArrayDeque<Integer> expected = new ArrayDeque<>();
        expected.push(30);
        expected.push(18);
        expected.push(14);
        expected.push(-3);
        expected.push(-5);
        Applications.sortStack(stack);
        assert new ArrayList<>(expected).equals(new ArrayList<>(stack));
    }

    @Test
    void testStockSpan() {
        int[] arr = {100, 80, 60, 70, 60, 75, 85};
        Assertions.assertArrayEquals(Applications.stockSpan(arr), new int[]{1, 1, 1, 2, 1, 4, 6});
    }

    @Test
    void testGetMiddleStack() {
        GetMiddleStack stack = new GetMiddleStack();
        stack.push(1);
        assert stack.peek() == 1 && stack.peekMiddle() == 1;
        stack.push(2);
        assert stack.peek() == 2 && stack.peekMiddle() == 1;
        stack.push(3);
        assert stack.peek() == 3 && stack.peekMiddle() == 2;
        stack.push(4);
        assert stack.peek() == 4 && stack.peekMiddle() == 2;
        stack.push(5);
        assert stack.peek() == 5 && stack.peekMiddle() == 3;

        boolean ans = stack.pop() == 5 && stack.peekMiddle() == 2;
        assert ans;
        ans = stack.pop() == 4 && stack.peekMiddle() == 2;
        assert ans;
        ans = stack.pop() == 3 && stack.peekMiddle() == 1;
        assert ans;
        ans = stack.pop() == 2 && stack.peekMiddle() == 1;
        assert ans;
        ans = stack.pop() == 1;
        assert ans;
        Assertions.assertThrows(AssertionError.class, () -> {
            System.out.println(stack.peekMiddle());
        });

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        ans = stack.popMiddle() == 3 && stack.peekMiddle() == 2;
        assert ans;
        ans = stack.popMiddle() == 2 && stack.peekMiddle() == 4;
        assert ans;
        ans = stack.popMiddle() == 4 && stack.peekMiddle() == 1;
        assert ans;
        ans = stack.popMiddle() == 1 && stack.peekMiddle() == 5;
        assert ans;
        ans = stack.popMiddle() == 5;
        assert ans;
        Assertions.assertThrows(AssertionError.class, () -> {
            System.out.println(stack.peekMiddle());
        });
    }
}
