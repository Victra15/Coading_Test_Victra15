package 이용태;

import java.io.*;
import java.util.Stack;

public class A021_SWEA_8931_제로 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A021_SWEA_8931_제로.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int expendRecordCnt = Integer.parseInt(br.readLine());
            Stack<Integer> recordStack = new Stack<Integer>();
            for (int expendLoop = 0; expendLoop < expendRecordCnt; expendLoop++) {
                int expendNum = Integer.parseInt(br.readLine());
                if (expendNum == 0) {
                    recordStack.pop();
                } else {
                    recordStack.push(expendNum);
                }
            }

            long expendSum = 0;
            while (!recordStack.isEmpty()) {
                expendSum += recordStack.pop();
            }
            sb.append('#').append(testCase).append(' ').append(expendSum).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
