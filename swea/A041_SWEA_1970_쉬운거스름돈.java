package 이용태;

import java.io.*;

public class A041_SWEA_1970_쉬운거스름돈 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A041_SWEA_1970_쉬운거스름돈.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] currencyType = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int cost = Integer.parseInt(br.readLine());
            int[] currencyCount = new int[currencyType.length];
            for (int loop = 0; loop < currencyCount.length; loop++) {
                currencyCount[loop] = cost / currencyType[loop];
                cost %= currencyType[loop];
            }
            sb.append('#').append(testCase).append('\n');
            for (int loop = 0; loop < currencyCount.length; loop++) {
                sb.append(currencyCount[loop]);
                if (loop != currencyType.length - 1)
                    sb.append(' ');
            }
            sb.append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
