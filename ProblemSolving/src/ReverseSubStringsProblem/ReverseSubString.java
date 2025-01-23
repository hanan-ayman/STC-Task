package ReverseSubStringsProblem;

import java.util.Stack;

public class ReverseSubString {
    public String reverseTheSubStrings(String str) {
        StringBuilder reversed = new StringBuilder();
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                i++; //Skip '('
                while (str.charAt(i) != ')') {
                    s.push(str.charAt(i));
                    i++; //put next char in Stack
                }
                String stackAsString = popStackToString(s);
                reversed.append('(').append(stackAsString).append(')');
            }else {
                reversed.append(str.charAt(i));
            }

        }
        return reversed.toString();
    }
    private static String popStackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
