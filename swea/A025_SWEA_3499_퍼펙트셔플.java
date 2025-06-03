package 이용태;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class A025_SWEA_3499_퍼펙트셔플 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A025_SWEA_3499_퍼펙트셔플.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int cardCnt = Integer.parseInt(br.readLine());
            String[] cards = br.readLine().trim().split(" ");
            Queue<String> cardQueue1 = new LinkedList<>();
            Queue<String> cardQueue2 = new LinkedList<>();
            for (int loop = 0; loop < (cardCnt + 1) / 2; loop++) {
                cardQueue1.offer(cards[loop]);
            }
            for (int loop = (cardCnt + 1) / 2; loop < cardCnt; loop++) {
                cardQueue2.offer(cards[loop]);
            }
            Queue<String> cardQueue = new LinkedList<>();
            while (!cardQueue1.isEmpty() || !cardQueue2.isEmpty()) {
                if(!cardQueue1.isEmpty()) {
                    cardQueue.offer(cardQueue1.poll());
                }
                if(!cardQueue2.isEmpty()) {
                    cardQueue.offer(cardQueue2.poll());
                }
            }
            sb.append('#').append(testCase).append(' ');
            while (!cardQueue.isEmpty()) {
                sb.append(cardQueue.poll());
                if (cardQueue.size() != 0)
                    sb.append(' ');
                else
                    sb.append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
