package 이용태;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A035_SWEA_4613_러시아국기같은깃발 {
    private static final int WHITE_CNT = 0;
    private static final int RED_CNT = 1;
    private static final int BLUE_CNT = 2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A035_SWEA_4613_러시아국기같은깃발.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] colorPrefixSumCnt = new int[3][N + 1];

            for (int rLoop = 1; rLoop <= N; rLoop++) {
                String line = br.readLine();
                for (int cLoop = 0; cLoop < M; cLoop++) {
                    switch (line.charAt(cLoop)) {
                        case 'R':
                            colorPrefixSumCnt[RED_CNT][rLoop]++;
                            break;
                        case 'W':
                            colorPrefixSumCnt[WHITE_CNT][rLoop]++;
                            break;
                        case 'B':
                            colorPrefixSumCnt[BLUE_CNT][rLoop]++;
                            break;
                    }
                }
            }
            //누적 합 배열 만들기
            Arrays.parallelPrefix(colorPrefixSumCnt[WHITE_CNT], Integer::sum);
            Arrays.parallelPrefix(colorPrefixSumCnt[RED_CNT], Integer::sum);
            Arrays.parallelPrefix(colorPrefixSumCnt[BLUE_CNT], Integer::sum);

            int result = (N * M) - findMaxMatchColor(colorPrefixSumCnt);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findMaxMatchColor(int[][] flagColorCnt) {
        int maxMatchColorCnt = 0;
        for (int whiteBound = 1; whiteBound < flagColorCnt[0].length - 2; whiteBound++) {
            for (int blueBound = whiteBound + 1; blueBound < flagColorCnt[0].length - 1; blueBound++) {
                int whiteCnt = flagColorCnt[WHITE_CNT][whiteBound] - flagColorCnt[WHITE_CNT][0];
                int blueCnt = flagColorCnt[BLUE_CNT][blueBound] - flagColorCnt[BLUE_CNT][whiteBound];
                int redCnt = flagColorCnt[RED_CNT][flagColorCnt[0].length - 1] - flagColorCnt[RED_CNT][blueBound];
                maxMatchColorCnt = Math.max(maxMatchColorCnt, whiteCnt + blueCnt + redCnt);
            }
        }
        return maxMatchColorCnt;
    }
}
