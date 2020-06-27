package gfg.ds.stack;

import gfg.ds.stack.adt.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 15:19
 * Purpose: Test
 **/
class TestStack {

    @Test
    void testInfixToPostFixUnbalancedParenthesis() {
        String exp = ")";
        Assertions.assertThrows(AssertionError.class, () -> Utils.infixToPostFix(exp));
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
    void testGetMiddleStackOperations() {
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
        Assertions.assertThrows(AssertionError.class, () -> System.out.println(stack.peekMiddle()));

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
        Assertions.assertThrows(AssertionError.class, () -> System.out.println(stack.peekMiddle()));
    }

    @Test
    void testEvaluatePostfix() {
        String exp = "231*+9-";
        assert Applications.evaluatePostfix(exp) == -4;
    }

    @Test
    void testCheckParenthesis() {
        String exp = "{()}[]";
        assert Applications.checkBalancedParenthesis(exp);
    }

    @Test
    void testReverseStack() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Applications.reverseStack(stack);
        assert new ArrayList<>(stack).equals(Arrays.asList(1, 2, 3));
    }

    @Test
    void testGetMinStackOperations() {
        GetMinStack stack = new GetMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assert stack.getMin() == 1;

        stack.push(0);
        stack.push(4);
        assert stack.getMin() == 0;

        stack.pop();
        stack.pop();
        assert stack.getMin() == 1;
    }

    @Test
    void testKStackOperations() {
        KStack kStack = new KStack(2, 6);
        kStack.push(0, 1);
        kStack.push(0, 2);
        kStack.push(1, 11);
        kStack.push(1, 12);

        int data = kStack.pop(0);
        assert data == 2;
        data = kStack.pop(0);
        assert data == 1;
        data = kStack.pop(1);
        assert data == 12;
        data = kStack.pop(1);
        assert data == 11;

        assert kStack.isEmpty(0);
        assert kStack.isEmpty(1);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testStackOperations() {
        return Stream.of(
                Arguments.of(new StackUsingArray(5)),
                Arguments.of(new StackUsingLinkedList()),
                Arguments.of(new StackUsingQueues(false)),
                Arguments.of(new StackUsingQueues(true))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testStackOperations(Stack stack) {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assert stack.peek() == 3;
        int data = stack.pop();
        assert data == 3;
        data = stack.pop();
        assert data == 2;
        data = stack.pop();
        assert data == 1;

        assert stack.isEmpty();
    }

    @Test
    void testTwoStackOperations() {
        TwoStack twoStack = new TwoStack(4);
        twoStack.push1(1).push1(2).push2(11).push2(12);

        assert twoStack.isFull();

        int data = twoStack.pop1();
        assert data == 2;
        data = twoStack.pop1();
        assert data == 1;
        data = twoStack.pop2();
        assert data == 12;
        data = twoStack.pop2();
        assert data == 11;

        assert twoStack.isEmpty();
    }
}
