package 이용태;

import java.io.*;

public class A0014_SWEA_1209_Sum {
    private static final int TEST_CASES = 10;
    private static final int ARRAY_SIZE = 100;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A0014_SWEA_1209_Sum.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] array = new int[ARRAY_SIZE][ARRAY_SIZE];
        for (int loop = 1; loop <= TEST_CASES; loop++) {
            int maxSum = 0;
            String testCase = br.readLine();
            for (int rLoop = 0; rLoop < ARRAY_SIZE; rLoop++) {
                String[] inputLine = br.readLine().trim().split(" ");
                for (int cLoop = 0; cLoop < ARRAY_SIZE; cLoop++) {
                    array[rLoop][cLoop] = Integer.parseInt(inputLine[cLoop]);
                }
            }

            maxSum = findMaxSum(array);

            sb.append('#').append(testCase).append(' ').append(maxSum).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findMaxSum(int[][] array) {
        int maxSum = 0;
        int rSum = 0;
        int cSum = 0;

        for (int rLoop = 0; rLoop < array.length; rLoop++) {
            rSum = 0;
            cSum = 0;
            for (int cLoop = 0; cLoop < array[0].length; cLoop++) {
                rSum += array[rLoop][cLoop];
                cSum += array[cLoop][rLoop];
            }
            if(rSum > maxSum) {
                maxSum = rSum;
            }
            if(cSum > maxSum) {
                maxSum = cSum;
            }
        }

        int diagSum = 0;
        for (int diagLoop = 0; diagLoop < array.length; diagLoop++) {
            diagSum += array[diagLoop][diagLoop];
        }
        if(diagSum > maxSum) {
            maxSum = diagSum;
        }

        diagSum = 0;
        for (int diagLoop = 0; diagLoop < array.length; diagLoop++) {
            diagSum += array[diagLoop][(array.length - 1) - diagLoop];
        }
        if(diagSum > maxSum) {
            maxSum = diagSum;
        }

        return maxSum;
    }

}
