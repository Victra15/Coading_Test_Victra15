package 이용태;

import java.io.*;
import java.util.Stack;

public class A022_SWEA_1222_계산기1 {
    private static final int TEST_CASE_CNT = 10;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A022_SWEA_1222_계산기1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= TEST_CASE_CNT; testCase++) {
            int operationLength = Integer.parseInt(br.readLine());
            String operation = br.readLine();

            int result = operate(operation, operationLength);
            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int operate(String operation, int operationLength) {
        Stack<Integer> stack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        for (int loop = 0; loop < operationLength; loop++) {
            char operationChar = operation.charAt(loop);
            if(operationChar == '+') {
                operatorStack.push(operationChar);
            } else {
                stack.push(operationChar - '0');
            }
        }

        while (!operatorStack.isEmpty()) {
            if(operatorStack.pop() == '+')
                stack.push(stack.pop() + stack.pop());
        }
        return stack.pop();
    }
}
