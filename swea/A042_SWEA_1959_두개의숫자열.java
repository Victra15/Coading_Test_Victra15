package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A042_SWEA_1959_두개의숫자열 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A042_SWEA_1959_두개의숫자열.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] Ai = new int[N];
            int[] Bi = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < N ; loop++) {
                Ai[loop] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < M ; loop++) {
                Bi[loop] = Integer.parseInt(st.nextToken());
            }
            if(N < M) {
                int[] temp = Ai;
                Ai = Bi;
                Bi = temp;
            }
            int maxSum = 0;
            for (int matchLoop = 0; matchLoop <= Ai.length - Bi.length; matchLoop++) {
                int multSum = 0;
                for(int multLoop = 0; multLoop < Bi.length; multLoop++){
                    multSum += Ai[matchLoop + multLoop] * Bi[multLoop];
                }
                if(maxSum < multSum){
                    maxSum = multSum;
                }
            }
            int result = maxSum;

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
