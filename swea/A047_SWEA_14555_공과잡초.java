package 이용태;

import java.io.*;

public class A047_SWEA_14555_공과잡초 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A047_SWEA_14555_공과잡초.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String weedMap = br.readLine();
            int ballCnt = 0;
            char prevChar = '.';
            for (int loop = 0; loop < weedMap.length(); loop++) {
                switch (weedMap.charAt(loop)) {
                    case '(':
                        ballCnt++;
                        break;
                    case ')':
                        if(prevChar != '(')
                            ballCnt++;
                        break;
                }
                prevChar = weedMap.charAt(loop);
            }

            int result = ballCnt;

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
