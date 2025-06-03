package 이용태;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class A028_SWEA_1966_숫자를정렬하자 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A028_SWEA_1966_숫자를정렬하자.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int numCnt = Integer.parseInt(br.readLine());
            Queue<Integer> numPq = new PriorityQueue<>();
            for(String numStr : br.readLine().trim().split(" ")) {
                numPq.add(Integer.parseInt(numStr));
            }

            sb.append('#').append(testCase).append(' ');
            while(!numPq.isEmpty()) {
                sb.append(numPq.poll()).append(' ');
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
