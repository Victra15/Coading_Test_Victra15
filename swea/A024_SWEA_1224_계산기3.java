package 이용태;

import java.io.*;
import java.util.Stack;

public class A024_SWEA_1224_계산기3 {
    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A024_SWEA_1224_계산기3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= TEST_CASE_CNT; testCase++) {
            int operLength = Integer.parseInt(br.readLine());
            String infixNotation = br.readLine();
            String rpn = convertRpn(infixNotation);
            int result = operateRpn(rpn);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int operateRpn(String rpn) {
        Stack<Integer> numberStack = new Stack<>();
        for (int loop = 0; loop < rpn.length(); loop++) {
            char rpnChar = rpn.charAt(loop);
            if (isOperator(rpnChar)) {
                performOperation(numberStack, rpnChar);
            } else {
                numberStack.push(rpnChar - '0');
            }
        }
        return numberStack.pop();
    }

    private static void performOperation(Stack<Integer> numberStack, char rpnChar) {
        switch (rpnChar) {
            case '*':
                numberStack.push(numberStack.pop() * numberStack.pop());
                break;
            case '+':
                numberStack.push(numberStack.pop() + numberStack.pop());
                break;
        }
    }

    private static String convertRpn(String infixNotation) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        for (int loop = 0; loop < infixNotation.length(); loop++) {
            char operChar = infixNotation.charAt(loop);
            if (isOperator(operChar)) {
                handleOperator(operChar, operatorStack, sb);
            } else {
                sb.append(operChar);
            }
        }
        while (!operatorStack.isEmpty()) {
            sb.append(operatorStack.pop());
        }
        return sb.toString();
    }

    private static void handleOperator(char operChar, Stack<Character> operatorStack, StringBuilder sb) {
        switch (operChar) {
            case '+':
                while (operatorStack.peek() == '*') {
                    sb.append(operatorStack.pop());
                }
                operatorStack.push(operChar);
                break;
            case '*':
                operatorStack.push(operChar);
                break;
            case '(':
                operatorStack.push(operChar);
                break;
            case ')':
                while (operatorStack.peek() != '(')
                    sb.append(operatorStack.pop());
                operatorStack.pop();
                break;
        }
    }

    private static boolean isOperator(char operChar) {
        return operChar == '+' || operChar == '-' || operChar == '*' || operChar == '/' || operChar == '(' || operChar == ')';
    }
}
