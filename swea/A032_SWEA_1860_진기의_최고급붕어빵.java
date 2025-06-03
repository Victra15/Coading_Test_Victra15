package 이용태;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A032_SWEA_1860_진기의_최고급붕어빵 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A032_SWEA_1860_진기의 최고급붕어빵.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            PriorityQueue<Integer> waitTimes = new PriorityQueue<Integer>();
            for (String numStr : br.readLine().trim().split(" ")) {
                waitTimes.add(Integer.parseInt(numStr));
            }

            String result = isPossible(M, K, waitTimes) ? "Possible" : "Impossible";

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static boolean isPossible(int m, int k, PriorityQueue<Integer> waitTimes) {
        int currTime = 0;
        int currFishBread = 0;
        while (!waitTimes.isEmpty()){
            int queueTime = waitTimes.poll();
            currFishBread += ((queueTime - currTime) / m) * k;
            currTime = queueTime - (queueTime - currTime) % m;
            if (currFishBread <= 0)
                return false;
            else
                currFishBread--;
        }
        return true;
    }
}
