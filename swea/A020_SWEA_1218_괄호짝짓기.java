package 이용태;

import java.io.*;
import java.util.Stack;

public class A020_SWEA_1218_괄호짝짓기 {
    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A020_SWEA_1218_괄호짝짓기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= TEST_CASE_CNT; testCase++) {
            int lineLength = Integer.parseInt(br.readLine());
            String braceLine = br.readLine();
            int isBraceValid = findBraceLineValidity(braceLine, lineLength);

            sb.append('#').append(testCase).append(' ').append(isBraceValid).append("\n");
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findBraceLineValidity(String braceLine, int lineLength) {
        Stack<Character> stack = new Stack<>();
        for (int loop = 0; loop < lineLength; loop++) {
            char braceChar = braceLine.charAt(loop);
            if (isCloseBrace(braceChar)) {
                if (Math.abs(stack.peek() - braceChar) <= 2) {
                    stack.pop();
                } else {
                    return 0;
                }
            } else {
                stack.push(braceChar);
            }
        }
        return 1;
    }

    private static boolean isCloseBrace(char braceChar) {
        if (braceChar == '}' || braceChar == ']' || braceChar == ')' || braceChar == '>') {
            return true;
        } else {
            return false;
        }
    }
}
