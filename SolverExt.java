import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import collections.ArrayStack;
import collections.LinkedQueue;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * ( 101 - 1 ) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        ArrayStack<Character> stack = new ArrayStack<Character>();
        ArrayStack<String> lastStack = new ArrayStack<String>();
        LinkedQueue<String> fullStack = new LinkedQueue<String>();

        for (int i = 0; i < values.length ; i++) {
            switch (values[i].charAt(0)){
                case RIGHT_PAREN: {
                    while (stack.peek()!=LEFT_PAREN){
                        fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
                    }
                    stack.pop();
                    break;
                }
                case LEFT_PAREN: {
                    stack.push(LEFT_PAREN);
                    break;
                }
                case PLUS: {
                    if(!stack.isEmpty())
                    while(stack.peek()==PLUS || stack.peek()==MINUS || stack.peek()==TIMES || stack.peek()==DIVISION )
                        fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
                    stack.push(PLUS);
                    break;
                }
                case MINUS: {
                    if(!stack.isEmpty())
                    while(stack.peek()==PLUS || stack.peek()==MINUS || stack.peek()==TIMES || stack.peek()==DIVISION )
                        fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
                    stack.push(MINUS);
                    break;
                }
                case TIMES: {
                    if(!stack.isEmpty())
                    while(stack.peek()==DIVISION || stack.peek()==TIMES)
                        fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
                    stack.push(TIMES);
                    break;
                }
                case DIVISION: {
                    if(!stack.isEmpty())
                    while(stack.peek()==TIMES || stack.peek()==DIVISION)
                        fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
                    stack.push(DIVISION);
                    break;
                }
                default:{
                    fullStack.enqueue(values[i]);
                }
            }
        }
        while (!stack.isEmpty()){
            fullStack.enqueue(Character.toString(stack.pop())); //danger! stackoverflow
        }

        while(fullStack.size()>0) {
            String temp = fullStack.dequeue();
            switch (temp.charAt(0)){
            case PLUS: {
                double a = Double.parseDouble(lastStack.pop());
                double b = Double.parseDouble(lastStack.pop());
                lastStack.push(Double.toString(a+b));
                break;
            }
            case MINUS: {
                double a = Double.parseDouble(lastStack.pop());
                double b = Double.parseDouble(lastStack.pop());
                lastStack.push(Double.toString(b-a));
                break;
            }
            case TIMES: {
                double a = Double.parseDouble(lastStack.pop());
                double b = Double.parseDouble(lastStack.pop());
                lastStack.push(Double.toString(a*b));
                break;
            }
            case DIVISION: {
                double a = Double.parseDouble(lastStack.pop());
                double b = Double.parseDouble(lastStack.pop());
                lastStack.push(Double.toString(b/a));
                break;
            }
            default:{
                lastStack.push(temp);
            }
            }
        }

        return(Double.parseDouble(lastStack.pop()));

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
