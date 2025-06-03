package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A071_SWEA_1949_등산로조성 {
    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class State {
        Position pos;
        Position prevPos;
        int currElevation;
        int currDistance;
        boolean hasModifiedTerrain;

        public State(Position pos, Position prevPos, int currElevation, int currDistance) {
            this.pos = pos;
            this.prevPos = prevPos;
            this.currElevation = currElevation;
            this.currDistance = currDistance;
        }

        public State(Position pos, Position prevPos, int currElevation, int currDistance, boolean hasModifiedTerrain) {
            this.pos = pos;
            this.prevPos = prevPos;
            this.currElevation = currElevation;
            this.currDistance = currDistance;
            this.hasModifiedTerrain = hasModifiedTerrain;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A071_SWEA_1949_등산로조성.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] maps = new int[N][N];

            int maxHeight = 0;
            for (int rowLoop = 0; rowLoop < N; rowLoop++) {
                st = new StringTokenizer(br.readLine());
                for (int colLoop = 0; colLoop < N; colLoop++) {
                    maps[rowLoop][colLoop] = Integer.parseInt(st.nextToken());
                    if (maxHeight < maps[rowLoop][colLoop]) {
                        maxHeight = maps[rowLoop][colLoop];
                    }
                }
            }

            List<Position> startPosition = new ArrayList<Position>();
            for (int rowLoop = 0; rowLoop < N; rowLoop++) {
                for (int colLoop = 0; colLoop < N; colLoop++) {
                    if (maps[rowLoop][colLoop] == maxHeight) {
                        startPosition.add(new Position(rowLoop, colLoop));
                    }
                }
            }

            int result = 0;
            for (Position start : startPosition) {
                int longestTrailAtStart = findLongestTrailAtStartPostion(maps, start, K);
                result = Math.max(result, longestTrailAtStart);
            }

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findLongestTrailAtStartPostion(int[][] map, Position start, int k) {
        //find with DFS without recursion
        boolean[][] visited = new boolean[map.length][map[0].length];
        Stack<State> trailStack = new Stack<>();
        Stack<State> searchStack = new Stack<>();
        searchStack.push(new State(start, new Position(0,0), map[start.x][start.y], 1, false));
        int maxDistance = 0;
        while (!searchStack.isEmpty()) {
            while (!trailStack.isEmpty() && trailStack.peek().pos != searchStack.peek().prevPos) {
                visited[trailStack.peek().pos.x][trailStack.peek().pos.y] = false;
                trailStack.pop();
            }
            trailStack.push(searchStack.pop());
            State current = trailStack.peek();
            visited[current.pos.x][current.pos.y] = true;
            int currStackSize = searchStack.size();
            pushStack(map, visited, searchStack, current, new Position(current.pos.x + 1, current.pos.y), k);
            pushStack(map, visited, searchStack, current, new Position(current.pos.x, current.pos.y + 1), k);
            pushStack(map, visited, searchStack, current, new Position(current.pos.x - 1, current.pos.y), k);
            pushStack(map, visited, searchStack, current, new Position(current.pos.x, current.pos.y - 1), k);
            if (maxDistance < current.currDistance) {
                maxDistance = current.currDistance;
            }
        }
        return maxDistance;
    }

    private static void pushStack(int[][] map, boolean[][] visited, Stack<State> searchStack, State current, Position newPos, int k) {
        try {
            if (visited[newPos.x][newPos.y]) return;
            int elevationDifference = map[newPos.x][newPos.y] - current.currElevation;
            if (elevationDifference < 0) {
                searchStack.push(new State(newPos, current.pos, map[newPos.x][newPos.y], current.currDistance + 1, current.hasModifiedTerrain));
            } else if (!current.hasModifiedTerrain && elevationDifference < k) {
                searchStack.push(new State(newPos, current.pos, current.currElevation - 1, current.currDistance + 1, true));
            }
        } catch (Exception e) {
        }
    }
}