package 이용태;

import java.io.*;
import java.util.Stack;

public class A019_SWEA_5432_쇠막대기자르기 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A019_SWEA_5432_쇠막대기자르기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String brackets = br.readLine();
            Stack<Character> bracketStack = new Stack<Character>();
            char beforeBracket = '\0';
            int pieceOfPipe = 0;
            for (int loop = 0; loop < brackets.length(); loop++) {
                char currBracket = brackets.charAt(loop);
                if (currBracket == '(') {
                    bracketStack.push(currBracket);
                } else {
                    bracketStack.pop();
                    if (beforeBracket == '(') {
                        pieceOfPipe += bracketStack.size();
                    }else {
                        pieceOfPipe++;
                    }
                }
                beforeBracket = currBracket;
            }
            sb.append('#').append(testCase).append(' ').append(pieceOfPipe).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
