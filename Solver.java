import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import collections.ArrayStack;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        ArrayStack<String> stack = new ArrayStack<String>();
        ArrayStack<Double> numStack = new ArrayStack<Double>();
        char ch;
        for (int i = 0; i < values.length ; i++) {
            ch = values[i].charAt(0);
            switch(ch){
            case RIGHT_PAREN: {
                double a = numStack.pop();
                char op = stack.pop().charAt(0);
                double b = numStack.pop();
                stack.pop();
                switch (op){
                case DIVISION: {
                    numStack.push(b/a);
                    break;
                }
                case TIMES: {
                    numStack.push(b*a);
                    break;
                }
                case MINUS: {
                    numStack.push(b-a);
                    break;
                }
                case PLUS: {
                    numStack.push(b+a);
                    break;
                }
                }
                break;
            }
            case LEFT_PAREN: stack.push(values[i]); break;
            case PLUS: stack.push(values[i]); break;
            case MINUS: stack.push(values[i]); break;
            case TIMES: stack.push(values[i]); break;
            case DIVISION: stack.push(values[i]); break;
            default:{
                numStack.push(Double.parseDouble(values[i]));
            }
            }
        }

        return numStack.pop();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
* ( 1 + ( 2 * 8 ) )
* ( 25 + ( 2 - ( 100 / ( 100 / ( 20 + 5 ) ) ) ) )
*/