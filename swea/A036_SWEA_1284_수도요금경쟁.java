package 이용태;

import java.io.*;

public class A036_SWEA_1284_수도요금경쟁 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A036_SWEA_1284_수도요금경쟁.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String[] inputs = br.readLine().trim().split(" ");
            int literCostA = Integer.parseInt(inputs[0]);
            int baseRateB = Integer.parseInt(inputs[1]);
            int usageLimitB = Integer.parseInt(inputs[2]);
            int excessCostB = Integer.parseInt(inputs[3]);
            int monthlyUseLiter = Integer.parseInt(inputs[4]);

            int totalCostA = literCostA * monthlyUseLiter;
            int totalCostB = usageLimitB > monthlyUseLiter ? baseRateB : baseRateB + excessCostB * (monthlyUseLiter - usageLimitB);

            int result = Math.min(totalCostA, totalCostB);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
