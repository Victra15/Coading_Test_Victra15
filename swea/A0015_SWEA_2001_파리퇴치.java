package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A0015_SWEA_2001_파리퇴치 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A0015_SWEA_2001_파리퇴치.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] flyMap = new int[N][N];
            for (int rLoop = 0; rLoop < N; rLoop++) {
                String[] line = br.readLine().trim().split(" ");
                for (int cLoop = 0; cLoop < N; cLoop++) {
                    flyMap[rLoop][cLoop] = Integer.parseInt(line[cLoop]);
                }
            }
            int maxFlyNum = 0;
            for (int rLoop = 0; rLoop <= N - M; rLoop++) {
                for (int cLoop = 0; cLoop <= N - M; cLoop++) {
                    int deltaSum = deltaSum(flyMap, M, rLoop, cLoop);
                    if(deltaSum > maxFlyNum) {
                        maxFlyNum = deltaSum;
                    }
                }
            }

            sb.append('#').append(testCase).append(' ').append(maxFlyNum).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();

    }

    private static int deltaSum(int[][] flyMap, int M, int rLoop, int cLoop) {
        int sum = 0;
        for (int row = rLoop; row < rLoop + M; row++) {
            for (int col = cLoop; col < cLoop + M; col++) {
                sum += flyMap[row][col];
            }
        }
        return sum;
    }
}
