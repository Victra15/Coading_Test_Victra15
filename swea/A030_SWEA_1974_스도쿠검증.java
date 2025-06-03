package 이용태;

import java.io.*;
import java.util.Arrays;

public class A030_SWEA_1974_스도쿠검증 {
    //    private static final int TEST_CASE_CNT = 10;
    private static final int SDOKU_SIZE = 9;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A030_SWEA_1974_스도쿠검증.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            // input
            int[][] sdokuMap = new int[SDOKU_SIZE][SDOKU_SIZE];
            for (int loop = 0; loop < SDOKU_SIZE; loop++) {
                int col = 0;
                for (String numStr : br.readLine().trim().split(" ")) {
                    sdokuMap[loop][col++] = numStr.charAt(0) - '0';
                }
            }

            int result = getSdokuResult(sdokuMap);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getSdokuResult(int[][] sdokuMap) {
        int[][] sdokuMapTrans = new int[SDOKU_SIZE][SDOKU_SIZE];
        for (int rLoop = 0; rLoop < SDOKU_SIZE; rLoop++) {
            for (int cLoop = 0; cLoop < SDOKU_SIZE; cLoop++) {
                sdokuMapTrans[rLoop][cLoop] = sdokuMap[cLoop][rLoop];
            }
        }

        for (int loop = 0; loop < sdokuMap.length; loop++) {
            if (!isNotDuplicateNumber(sdokuMap[loop]))
                return 0;
        }
        for (int loop = 0; loop < sdokuMapTrans.length; loop++) {
            if (!isNotDuplicateNumber(sdokuMapTrans[loop]))
                return 0;
        }
        int[] checkMap = new int[SDOKU_SIZE];
        for (int rStart = 0; rStart < SDOKU_SIZE / 3; rStart++) {
            for (int cStart = 0; cStart < SDOKU_SIZE / 3; cStart++) {
                initCheckMap(checkMap, sdokuMap, rStart * 3, cStart * 3);
                if (!isNotDuplicateNumber(checkMap))
                    return 0;
            }
        }
        return 1;
    }

    private static void initCheckMap(int[] checkMap, int[][] sdokuMap, int rStart, int cStart) {
        int inputLoop = 0;
        for (int rLoop = rStart; rLoop < rStart + (SDOKU_SIZE / 3); rLoop++) {
            for (int cLoop = cStart; cLoop < cStart + (SDOKU_SIZE / 3); cLoop++) {
                checkMap[inputLoop++] = sdokuMap[rLoop][cLoop];
            }
        }
    }

    private static boolean isNotDuplicateNumber(int[] sdokuLine) {
        Boolean[] check = new Boolean[SDOKU_SIZE + 1];
        Arrays.fill(check, false);
        Boolean[] allTrue = new Boolean[SDOKU_SIZE + 1];
        Arrays.fill(allTrue, true);
        allTrue[0] = false;
        for (int loop = 0; loop < sdokuLine.length; loop++) {
            check[sdokuLine[loop]] = true;
        }
        return Arrays.deepEquals(check, allTrue);
    }
}
