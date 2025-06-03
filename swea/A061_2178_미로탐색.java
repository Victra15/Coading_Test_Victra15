package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A061_2178_미로탐색 {

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
        int result = countMinMove(mazeMap);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int countMinMove(String[] mazeMap) {
        int[][] visited = new int[mazeMap.length][mazeMap[0].length()];
        visited[0][0] = 1;
        Queue<Position> queue = new LinkedList<>();
        Position startPos = new Position(0, 0);
        queue.offer(startPos);
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            pushToStack(queue, mazeMap, visited, pos.x - 1, pos.y, visited[pos.x][pos.y]);
            pushToStack(queue, mazeMap, visited, pos.x, pos.y - 1, visited[pos.x][pos.y]);
            pushToStack(queue, mazeMap, visited, pos.x + 1, pos.y, visited[pos.x][pos.y]);
            pushToStack(queue, mazeMap, visited, pos.x, pos.y + 1, visited[pos.x][pos.y]);
        }

        return visited[mazeMap.length - 1][mazeMap[0].length() - 1];
    }

    private static void pushToStack(Queue<Position> queue, String[] map, int[][] visited, int x, int y, int distance) {
        try {
            if (visited[x][y] == 0 && map[x].charAt(y) == '1') {
                visited[x][y] = distance + 1;
                queue.offer(new Position(x, y));
            }
        } catch (Exception e) {

        }
    }
}