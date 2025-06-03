package 이용태;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A061_2178_미로탐색_For_Test {

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A061_2178_미로탐색.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] mazeMap = new String[N];
        for (int loop = 0; loop < N; loop++) {
            mazeMap[loop] = br.readLine();
        }

        int result = findDest(mazeMap);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int findDest(String[] mazeMap) {
        int[][] distance = new int[mazeMap.length][mazeMap[0].length()];
        distance[0][0] = 1;

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0));

        while (!queue.isEmpty()) {
            Position curPos = queue.poll();
            offerPos(queue, distance, mazeMap, curPos.x + 1, curPos.y, distance[curPos.y][curPos.x]);
            offerPos(queue, distance, mazeMap, curPos.x, curPos.y + 1, distance[curPos.y][curPos.x]);
            offerPos(queue, distance, mazeMap, curPos.x - 1, curPos.y, distance[curPos.y][curPos.x]);
            offerPos(queue, distance, mazeMap, curPos.x, curPos.y - 1, distance[curPos.y][curPos.x]);
        }
        return distance[mazeMap.length - 1][mazeMap[0].length() - 1];
    }

    private static void offerPos(Queue<Position> queue, int[][] distance, String[] map, int x, int y, int prevDistance) {
        try {
            if (distance[y][x] == 0 && map[y].charAt(x) == '1') {
                distance[y][x] = prevDistance + 1;
                queue.offer(new Position(x, y));
            }

        } catch (Exception e) {

        }
    }


}