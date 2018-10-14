package geeks_for_geeks.ds.stack;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 02:54
 **/
public class StackApplications {
    private int getPrecedence(Character ch) {
        switch (ch) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                throw new RuntimeException("Invalid Operator:" + ch);
        }
    }

    public void infixToPostFix(String exp) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isLetterOrDigit(curr)) {
                postfix.append(curr);
            } else if (curr == '(') {
                stack.push(curr);
            } else if (curr == ')') {
                for (char temp = stack.pop(); temp != '('; temp = stack.pop()) {
                    postfix.append(temp);
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
        System.out.println(postfix);
    }

    private double evaluateExpression(double first, double second, Character operation) {
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

    public void evaluatePostfix(String exp) {
        ArrayDeque<Double> stack = new ArrayDeque<>();
        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isDigit(curr)) {
                stack.push(Double.parseDouble("" + curr));
            } else {
                double second = stack.pop();
                double first = stack.pop();
                stack.push(evaluateExpression(first, second, curr));
            }
        }
        System.out.println("Answer:" + stack.pop());
    }

    public void reverseString(String str) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        for (; !stack.isEmpty(); ) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    public boolean checkBalancedParenthesis(String exp) {
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

    public void reverseStack(ArrayDeque<Integer> stack) {
        if (!stack.isEmpty()) {
            int val = stack.peek();
            stack.pop();
            reverseStack(stack);
            insertAtBottom(stack, val);
        }
    }

    private void insertAtBottom(ArrayDeque<Integer> stack, int val) {
        if (stack.isEmpty()) {
            stack.push(val);
        } else {
            int temp = stack.peek();
            stack.pop();
            insertAtBottom(stack, val);
            stack.push(temp);
        }
    }

    public static void main(String[] args) {
        StackApplications app = new StackApplications();
//        app.infixToPostFix("a+b*(c^d-e)^(f+g*h)-i");
//        app.evaluatePostfix("231*+9-");
//        app.reverseString("GeeksForGeeks");
//        System.out.println(app.checkBalancedParenthesis("[()]{}{[()()]()}"));
//        System.out.println(app.checkBalancedParenthesis("[(])"));
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("before:" + stack);
        app.reverseStack(stack);
        System.out.println("after:" + stack);
    }
}
