package geeks_for_geeks.ds.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static util.Utils.getPrecedence;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 02:54
 **/
public class StackApplications {
    /**
     * In infix expression the compiler have to rescan because of precedence rules.
     *
     * @param exp
     */
    public static String infixToPostFix(String exp) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isLetterOrDigit(curr)) {
                postfix.append(curr);
            } else if (curr == '(') {
                stack.push(curr);
            } else if (curr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (stack.isEmpty()) {
                    throw new RuntimeException("Invalid expression!");
                } else {
                    stack.pop();
                }
            } else {
//                no need to check for '(' because it's precedence will be always low and any character would sit on it.
                for (; !stack.isEmpty() && getPrecedence(curr) < getPrecedence(stack.peek()); ) {
                    postfix.append(stack.pop());
                }
                stack.push(curr);
            }
        }
        for (; !stack.isEmpty(); ) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    private static double evaluateExpression(double first, double second, Character operation) {
        switch (operation) {

            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            default:
                throw new RuntimeException("Invalid operation");
        }
    }

    public static void evaluatePostfix(String exp) {
        ArrayDeque<Double> stack = new ArrayDeque<>();
        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isDigit(curr)) {
                stack.push(Double.parseDouble("" + curr));
            } else {
                try {
                    double second = stack.pop();
                    double first = stack.pop();
                    stack.push(evaluateExpression(first, second, curr));
                } catch (NoSuchElementException e) {
                    throw new RuntimeException("Invalid expression", e);
                }
            }
        }
        System.out.println("Answer:" + stack.pop());
    }

    public static void reverseString(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        for (; !stack.isEmpty(); ) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    public static boolean checkBalancedParenthesis(String exp) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < exp.length(); i++) {
            Character curr = exp.charAt(i);
            if (curr == '[' || curr == '{' || curr == '(') {
                stack.push(curr);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                switch (curr) {
                    case ']':
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.peek() == '{') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ')':
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    default:
                        throw new RuntimeException("Invalid Character!");
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void reverseStack(ArrayDeque<Integer> stack) {
        if (!stack.isEmpty()) {
            int val = stack.peek();
            stack.pop();
            reverseStack(stack);
            insertAtBottom(stack, val);
        }
    }

    private static void insertAtBottom(ArrayDeque<Integer> stack, int val) {
        if (stack.isEmpty()) {
            stack.push(val);
        } else {
            int temp = stack.peek();
            stack.pop();
            insertAtBottom(stack, val);
            stack.push(temp);
        }
    }

    public static int[] nextGreaterElements(int arr[]) {
        assert arr != null;
        int[] nge = new int[arr.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = arr.length - 1; i > -1; i--) {
            while (!stack.isEmpty() && stack.peek() < arr[i]) {
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        System.out.println("Array:" + Arrays.toString(arr));
        System.out.println("NGE:" + Arrays.toString(nge));
        return nge;
    }

    public static void sortStack(ArrayDeque<Integer> stack) {
        if (!stack.isEmpty()) {
            int val = stack.pop();
            sortStack(stack);
            sortedInsert(stack, val);
        }
    }

    private static void sortedInsert(ArrayDeque<Integer> stack, int val) {
        if (stack.isEmpty() || stack.peek() >= val) {
            stack.push(val);
        } else {
            int temp = stack.pop();
            sortedInsert(stack, val);
            stack.push(temp);
        }
    }

    public static int[] stockSpan(int[] stockValues) {
        assert stockValues != null && stockValues.length != 0;

        int[] stockSpans = new int[stockValues.length];
        stockSpans[0] = 1;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < stockValues.length; i++) {
            while (!stack.isEmpty() && stockValues[stack.peek()] <= stockValues[i]) {
                stack.pop();
            }
            stockSpans[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return stockSpans;
    }
}
