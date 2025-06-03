package 이용태;

import java.io.*;
import java.util.PriorityQueue;

public class A078_다익스트라 {
    private static class Edge implements Comparable<Edge> {
        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A078_다익스트라.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(br.readLine());
        String[] inputStr = br.readLine().split(" ");
        int start = Integer.parseInt(inputStr[0]);
        int end = Integer.parseInt(inputStr[1]);
        int E = Integer.parseInt(br.readLine());

        int[][] matrix = new int[V][V];
        for (int loop = 0; loop < E; loop++) {
            String[] edgeStr = br.readLine().trim().split(" ");
            int from = Integer.parseInt(edgeStr[0]);
            int to = Integer.parseInt(edgeStr[1]);
            int weight = Integer.parseInt(edgeStr[2]);
            matrix[from][to] = matrix[to][from] = weight;
        }

        int minSum = getMinWeightSum(start,end,V, matrix);
        sb.append(minSum).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinWeightSum(int start, int end, int V, int[][] matrix) {
        boolean[] visited = new boolean[V];
        int[] minDistance = new int[V];
        for(int loop = 0; loop < V; loop++) {
            minDistance[loop] = Integer.MAX_VALUE;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(start, start, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) continue;

            visited[edge.to] = true;

            if (edge.to == end) break;

            for (int nt = 0; nt < V; nt++) {
                if (!visited[nt] && matrix[edge.to][nt] != 0 &&
                        minDistance[nt] > edge.weight+matrix[edge.to][nt]){
                    minDistance[nt] = edge.weight+matrix[edge.to][nt];
                    pq.offer(new Edge(edge.to, nt, minDistance[nt]));
                }
            }

        }
        return minDistance[end];
    }
}