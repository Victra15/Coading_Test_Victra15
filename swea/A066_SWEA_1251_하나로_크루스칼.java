package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A066_SWEA_1251_하나로_크루스칼 {
    private static final int FROM = 0;
    private static final int TO = 1;

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        long distance(Position p) {
            long disX = Math.abs(x - p.x);
            long disY = Math.abs(y - p.y);
            return disX * disX + disY * disY;
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

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A066_SWEA_1251_하나로_크루스칼.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            Position[] islands = new Position[N];


            StringTokenizer stX = new StringTokenizer(br.readLine());
            StringTokenizer stY = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < N; loop++) {
                islands[loop] = new Position(Integer.parseInt(stX.nextToken()), Integer.parseInt(stY.nextToken()));
            }

            final double environFee = Double.parseDouble(br.readLine());
            Edge[] edges = new Edge[N * (N - 1)];
            int idx = 0;
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    if (from != to) {
                        long weight = islands[from].distance(islands[to]);
                        edges[idx++] = new Edge(from, to, weight);
                    }
                }
            }
            long result = calcMinFee(N, environFee, edges);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static long calcMinFee(int N, double environFee, Edge[] edges) {
        Arrays.sort(edges);
        long sumOfFee = 0;
        int unionCnt = 0;
        int[] unionArr = makeSet(N);
        for (int loop = 0; loop < edges.length; loop++) {
            if (find(unionArr, edges[loop].from) != find(unionArr, edges[loop].to)) {
                union(unionArr, edges[loop].from, edges[loop].to);
                unionCnt++;
                sumOfFee += edges[loop].weight;
            }
            if(unionCnt == N - 1) {
                break;
            }
        }

        double result = sumOfFee * environFee;
        return (long) Math.round(result);
    }

    private static void union(int[] unionArr, int from, int to) {
        unionArr[find(unionArr, from)] = find(unionArr, to);
    }

    private static int find(int[] unionArr, int idx) {
        if (unionArr[idx] == idx) return unionArr[idx];
        else {
            int parent = find(unionArr, unionArr[idx]);
            unionArr[idx] = parent;
            return parent;
        }
    }

    private static int[] makeSet(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
}