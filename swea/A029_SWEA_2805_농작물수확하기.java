package 이용태;

import java.io.*;
import java.util.Arrays;
import java.util.function.BinaryOperator;

public class A029_SWEA_2805_농작물수확하기 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A029_SWEA_2805_농작물수확하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int farmSize = Integer.parseInt(br.readLine());
            int[][] farm = new int[farmSize][farmSize];
            for (int loop = 0; loop < farmSize; loop++) {
                String line = br.readLine();
                for (int strLoop = 0; strLoop < line.length(); strLoop++) {
                    farm[loop][strLoop] = line.charAt(strLoop) - '0';
                }
            }
            int result = calcHarvestValue(farm);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int calcHarvestValue(int[][] farm) {
        int mid = farm.length / 2;
        int havested = 0;
        for (int rLoop = 0; rLoop < farm.length; rLoop++) {
            for (int cLoop = Math.abs(rLoop - mid); cLoop <= 2 * mid - Math.abs(rLoop - mid); cLoop++) {
                havested += farm[rLoop][cLoop];
            }
        }
        return havested;
    }
}
