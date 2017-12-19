

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        Stack<Character> stack = new Stack<Character>();
        char ch;
        for (int i = 0; i < sequence.length() ; i++) {
            ch=sequence.charAt(i);
            switch(ch){
                case LEFT_PAREN: {
                    stack.push(ch);
                    break;
                }
                case LEFT_BRACE:{
                    stack.push(ch);
                    break;
                }
                case LEFT_BRACKET:{
                    stack.push(ch);
                    break;
                }
                case RIGHT_PAREN: {
                    if (stack.isEmpty() || stack.peek() != LEFT_PAREN) {
                        return false;
                    } else {
                        stack.pop();
                        break;
                    }
                }
                case RIGHT_BRACE: {
                    if (stack.isEmpty() || stack.peek() != LEFT_BRACE) {
                        return false;
                    } else {
                        stack.pop();
                        break;
                    }
                }
                case RIGHT_BRACKET: {
                    if (stack.isEmpty() || stack.peek() != LEFT_BRACKET) {
                        return false;
                    } else {
                        stack.pop();
                        break;
                    }
                }
            }
        }

        if(stack.isEmpty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
