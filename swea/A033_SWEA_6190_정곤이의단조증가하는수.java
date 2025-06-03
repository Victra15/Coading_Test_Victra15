package 이용태;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class A033_SWEA_6190_정곤이의단조증가하는수 {
    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A033_SWEA_6190_정곤이의단조증가하는수.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            int inputIdx = 0;
            for (String numStr : br.readLine().trim().split(" ")) {
                numArr[inputIdx++] = Integer.parseInt(numStr);
            }
            int result = getMaxMonoIncreseNum(numArr);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getMaxMonoIncreseNum(int[] numArr) {
        int maxMonoIncreseNum = -1;
        for (int loopI = 0; loopI < numArr.length - 1; loopI++) {
            for (int loopJ = loopI + 1; loopJ < numArr.length; loopJ++) {
                int checkNum = numArr[loopI] * numArr[loopJ];
                if (maxMonoIncreseNum < checkNum && isMonoIncrese(checkNum)) {
                    maxMonoIncreseNum = checkNum;
                }
            }
        }
        return maxMonoIncreseNum;
    }

    private static boolean isMonoIncrese(int checkNum) {
        String numStr = Integer.toString(checkNum);
        for (int loop = 0; loop < numStr.length() - 1; loop++) {
            if (numStr.charAt(loop) > numStr.charAt(loop + 1))
                return false;
        }
        return true;
    }
}
