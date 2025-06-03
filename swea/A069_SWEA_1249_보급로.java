package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class A069_SWEA_1249_보급로 {
    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Adjacency implements Comparable<Adjacency> {
        Position adjPos;
        int weight;

        @Override
        public int compareTo(Adjacency o) {
            return weight - o.weight;
        }

        public Adjacency(Position adjPos, int weight) {
            this.adjPos = adjPos;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A069_SWEA_1249_보급로.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            String[] mapStr = new String[N];
            for (int loop = 0; loop < N; loop++) {
                mapStr[loop] = br.readLine();
            }

            List<Adjacency>[][] adjList = new ArrayList[N][N];
            for (int rowLoop = 0; rowLoop < N; rowLoop++) {
                for (int colLoop = 0; colLoop < N; colLoop++) {
                    adjList[rowLoop][colLoop] = new ArrayList<>();
                    initAdjList(adjList, mapStr, new Position(rowLoop, colLoop), new Position(rowLoop + 1, colLoop));
                    initAdjList(adjList, mapStr, new Position(rowLoop, colLoop), new Position(rowLoop - 1, colLoop));
                    initAdjList(adjList, mapStr, new Position(rowLoop, colLoop), new Position(rowLoop, colLoop + 1));
                    initAdjList(adjList, mapStr, new Position(rowLoop, colLoop), new Position(rowLoop, colLoop - 1));
                }
            }

            int result = getMinRestoreTime(adjList, N);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getMinRestoreTime(List<Adjacency>[][] adjList, int mapSize) {
        int[][] distance = new int[mapSize][mapSize];
        boolean[][] visited = new boolean[mapSize][mapSize];
        for (int[] distanceLine : distance)
            Arrays.fill(distanceLine, Integer.MAX_VALUE);

        PriorityQueue<Adjacency> adjPq = new PriorityQueue<>();
        distance[0][0] = 0;
        adjPq.offer(new Adjacency(new Position(0, 0), 0));
        while (!adjPq.isEmpty()) {
            Adjacency adjacency = adjPq.poll();

            if(adjacency.adjPos.x == mapSize - 1 && adjacency.adjPos.y == mapSize - 1) break;


            for(Adjacency to : adjList[adjacency.adjPos.x][adjacency.adjPos.y]) {
                if(visited[to.adjPos.x][to.adjPos.y]) continue;
                if(distance[to.adjPos.x][to.adjPos.y] <= distance[adjacency.adjPos.x][adjacency.adjPos.y] + to.weight) continue;

                visited[to.adjPos.x][to.adjPos.y] = true;
                distance[to.adjPos.x][to.adjPos.y] = distance[adjacency.adjPos.x][adjacency.adjPos.y] + to.weight;
                adjPq.offer(new Adjacency(to.adjPos, distance[to.adjPos.x][to.adjPos.y]));
            }
        }
        return distance[mapSize - 1][mapSize - 1];
    }

    private static void initAdjList(List<Adjacency>[][] adjList, String[] mapStr, Position from, Position to) {
        try {
            int weight = mapStr[to.x].charAt(to.y) - '0';
            adjList[from.x][from.y].add(new Adjacency(to, weight));
        } catch (Exception e) {

        }
    }
}