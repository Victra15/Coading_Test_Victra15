package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A027_SWEA_7102_준홍이의카드놀이 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A027_SWEA_7102_준홍이의카드놀이.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            String result = findMaxProbNum(N, M);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static String findMaxProbNum(int n, int m) {
        StringBuilder sb = new StringBuilder();
        int min = Math.min(n, m);
        int max = Math.max(n, m);
        for (int loop = 1; loop <= max - min + 1; loop++) {
            sb.append(min+loop).append(' ');
        }
        return sb.toString();
    }
}
