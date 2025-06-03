package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A045_SWEA_1285_아름이의돌던지기 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A045_SWEA_1285_아름이의돌던지기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int minDistance = Integer.MAX_VALUE;
            int minDistanceCnt = 0;
            for (int loop = 0; loop < N; loop++) {
                int distance = Math.abs(Integer.parseInt(st.nextToken()));
                if(minDistance > distance){
                    minDistance = distance;
                    minDistanceCnt = 0;
                }
                if(minDistance == distance){
                    minDistanceCnt++;
                }
            }


            sb.append('#').append(testCase).append(' ').append(minDistance).append(' ').append(minDistanceCnt).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
