package 이용태;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class A026_SWEA_1225_암호생성기 {
    private static final int TEST_CASE_CNT = 10;
    private static final int CYCLE_CNT = 5;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A026_SWEA_1225_암호생성기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int loop = 0; loop < TEST_CASE_CNT; loop++) {
            int testCase = Integer.parseInt(br.readLine());
            Queue<Integer> passwdQueue = new LinkedList<>();
            for (String passwdStr : br.readLine().trim().split(" ")) {
                passwdQueue.offer(Integer.parseInt(passwdStr));
            }
            createPasswd(passwdQueue);

            sb.append('#').append(testCase).append(' ');
            while (!passwdQueue.isEmpty()) {
                sb.append(passwdQueue.poll()).append(' ');
            }
            sb.append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static void createPasswd(Queue<Integer> passwdQueue) {
        while (doCycle(passwdQueue)) ;
    }

    private static boolean doCycle(Queue<Integer> passwdQueue) {
        for (int subtractNum = 1; subtractNum <= CYCLE_CNT; subtractNum++) {
            int subtractResult = subtractNoneMinus(passwdQueue.poll(), subtractNum);
            passwdQueue.offer(subtractResult);
            if(subtractResult == 0)
                return false;
        }
        return true;
    }

    private static int subtractNoneMinus(int targetNum, int subtractNum) {
        int subtractResult = targetNum - subtractNum;
        return subtractResult > 0 ? subtractResult : 0;
    }
}
