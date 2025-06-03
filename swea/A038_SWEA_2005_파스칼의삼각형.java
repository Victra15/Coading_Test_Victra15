package 이용태;

import java.io.*;
import java.util.Arrays;

public class A038_SWEA_2005_파스칼의삼각형 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A038_SWEA_2005_파스칼의삼각형.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int[][] pascalTriangle = new int[N][N];
            for (int[] line : pascalTriangle) {
                Arrays.fill(line, 0);
            }
            for (int rLoop = 0; rLoop < N; rLoop++) {
                for(int cLoop = 0; cLoop <= rLoop; cLoop++) {
                    try {
                        pascalTriangle[rLoop][cLoop] = 1;
                        pascalTriangle[rLoop][cLoop] = pascalTriangle[rLoop - 1][cLoop - 1] + pascalTriangle[rLoop - 1][cLoop];
                    }catch (Exception e) {
                        continue;
                    }
                }
            }

            sb.append('#').append(testCase).append('\n');
            for (int rLoop = 0; rLoop < N; rLoop++) {
                for(int cLoop = 0; cLoop <= rLoop; cLoop++) {
                    sb.append(pascalTriangle[rLoop][cLoop]).append(' ');
                }
                sb.append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
