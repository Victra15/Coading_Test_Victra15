package 이용태;

import java.io.*;
import java.util.Arrays;

public class A044_SWEA_1288_새로운불면증치료법 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A044_SWEA_1288_새로운불면증치료법.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            Boolean[] allTrue = new Boolean[10];
            Arrays.fill(allTrue, true);
            Boolean[] check = new Boolean[10];
            Arrays.fill(check, false);
            int kN = 0;
            while (!Arrays.deepEquals(allTrue, check)) {
                kN += N;
                String checkStr = Integer.toString(kN);
                for(int loop = 0; loop < checkStr.length(); loop++) {
                    check[checkStr.charAt(loop) - '0'] = true;
                }
            }

            int result = kN;

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
