package 이용태;

import java.io.*;
import java.util.Arrays;

public class A076_크루스칼 {
    private static class Edge {
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
        System.setIn(new FileInputStream("src/이용태/input/A076_크루스칼.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        Edge[] edges = new Edge[E];
        for (int loop = 0; loop < E; loop++) {
            String[] edgeStr = br.readLine().trim().split(" ");
            int from = Integer.parseInt(edgeStr[0]);
            int to = Integer.parseInt(edgeStr[1]);
            int weight = Integer.parseInt(edgeStr[2]);
            edges[loop] = new Edge(from, to, weight);
        }
        int minSum = getMinWeightSum(V, edges);
        sb.append(minSum).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getMinWeightSum(int V, Edge[] edges) {
        Arrays.sort(edges, (a, b) -> {
            return a.weight - b.weight;
        });
        int[] unionSet = makeSet(V);
        int minWeight = 0;
        int unionCnt = 0;
        for (int loop = 0; loop < edges.length; loop++) {
            if (find(unionSet, edges[loop].from) != find(unionSet, edges[loop].to)) {
                union(unionSet, edges[loop].from, edges[loop].to);
                unionCnt++;
                minWeight += edges[loop].weight;
            }
            if(unionCnt >= V - 1) break;
        }
        return minWeight;
    }

    private static void union(int[] unionSet, int from, int to) {
        unionSet[find(unionSet, from)] = find(unionSet, to);
    }

    private static int[] makeSet(int v) {
        int[] set = new int[v];
        for (int loop = 0; loop < v; loop++) {
            set[loop] = loop;
        }
        return set;
    }

    private static int find(int[] unionSet, int v) {
        if (unionSet[v] == v) return v;
        else {
            int parent = find(unionSet, unionSet[v]);
            unionSet[v] = parent;
            return parent;
        }
    }
}