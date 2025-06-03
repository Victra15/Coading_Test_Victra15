package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class A068_SWEA_1251_하나로_프림 {
    private static class Postion {
        int x;
        int y;

        public Postion(int x, int y) {
            this.x = x;
            this.y = y;
        }

        long getDistance(Postion pos) {
            long diffX = Math.abs(x - pos.x);
            long diffY = Math.abs(y - pos.y);
            return diffX * diffX + diffY * diffY;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        long weight;

        @Override
        public int compareTo(Edge o) {
            return Long.compare(weight, o.weight);
        }

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A068_SWEA_1251_하나로_프림.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            String[] xPos = br.readLine().trim().split(" ");
            String[] yPos = br.readLine().trim().split(" ");
            Postion[] islands = new Postion[N];
            for (int loop = 0; loop < N; loop++) {
                islands[loop] = new Postion(Integer.parseInt(xPos[loop]), Integer.parseInt(yPos[loop]));
            }
            double environFee = Double.parseDouble(br.readLine());
            boolean[] selected = new boolean[N];
            int selectCnt = 0;
            long minSpanningSum = 0;
            PriorityQueue<Edge> selectPq = new PriorityQueue<>();
            selectPq.offer(new Edge(0, 0, 0));
            while (!selectPq.isEmpty()) {
                Edge edge = selectPq.poll();
                int currNode = edge.to;
                if(selected[currNode]) continue;
                selectCnt++;
                minSpanningSum += edge.weight;
                selected[currNode] = true;
                if(selectCnt == N)
                    break;
                for (int loop = 0; loop < N; loop++) {
                    if (!selected[loop]) {
                        selectPq.offer(new Edge(currNode, loop, islands[currNode].getDistance(islands[loop])));
                    }
                }
            }

            long result = Math.round(minSpanningSum * environFee);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}