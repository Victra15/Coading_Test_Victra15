package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A0012_SWEA_1210_ladder1 {

    private static final int TEST_CASE_CNT = 10;
    private static final int MAP_SIZE = 100;

    static class Point {
        int x, y;
    }

    static enum MoveState {
        STATE_LEFT, STATE_RIGHT, STATE_UP
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A0012_SWEA_1210_ladder1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= TEST_CASE_CNT; test_case++) {
            int testCaseNum = Integer.parseInt(br.readLine());

            int[][] ladderMap = new int[MAP_SIZE][MAP_SIZE];
            Point endPoint = new Point();

            dataInit(ladderMap, endPoint, br);
            Point startPoint = findStartPoint(ladderMap, endPoint);

            sb.append('#').append(testCaseNum).append(' ').append(startPoint.x).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        br.close();
        bw.close();
    }

    private static Point findStartPoint(int[][] ladderMap, Point endPoint) {
        Point currPoint = endPoint;
        MoveState currMoveState = MoveState.STATE_UP;
        while (currPoint.y > 0) {
            switch (currMoveState) {
                case STATE_UP:
                    currMoveState = canMoveHorizontal(ladderMap, currPoint, currMoveState);
                    if (currMoveState == MoveState.STATE_RIGHT || currMoveState == MoveState.STATE_LEFT) {
                        moveHorizontal(currPoint, currMoveState);
                    } else
                        moveUp(currPoint);
                    break;
                case STATE_LEFT:
                case STATE_RIGHT:
                    currMoveState = canMoveVertical(ladderMap, currPoint, currMoveState);
                    if (currMoveState == MoveState.STATE_UP)
                        moveUp(currPoint);
                    else
                        moveHorizontal(currPoint, currMoveState);
                    break;
            }
        }
        return currPoint;
    }

    private static MoveState canMoveVertical(int[][] ladderMap, Point currPoint, MoveState currMoveState) {
        if (currPoint.y + 1 < ladderMap.length && ladderMap[currPoint.y + 1][currPoint.x] == 1) {
            return MoveState.STATE_UP;
        } else {
            return currMoveState;
        }

    }

    private static MoveState canMoveHorizontal(int[][] ladderMap, Point currPoint, MoveState currMoveState) {
        if (currPoint.x - 1 >= 0 && ladderMap[currPoint.y][currPoint.x - 1] == 1)
            return MoveState.STATE_LEFT;
        else if (currPoint.x + 1 < ladderMap[0].length && ladderMap[currPoint.y][currPoint.x + 1] == 1)
            return MoveState.STATE_RIGHT;
        else
            return currMoveState;
    }

    private static void moveUp(Point currPoint) {
        currPoint.y--;
    }

    private static void moveHorizontal(Point currPoint, MoveState currMoveState) {
        switch (currMoveState) {
            case STATE_LEFT:
                currPoint.x--;
                break;
            case STATE_RIGHT:
                currPoint.x++;
                break;
        }
    }

    private static void dataInit(int[][] ladderMap, Point endPoint, BufferedReader br) throws IOException {
        for (int rLoop = 0; rLoop < MAP_SIZE; rLoop++) {
            String[] line = br.readLine().trim().split(" ");
            for (int cLoop = 0; cLoop < MAP_SIZE; cLoop++) {
                ladderMap[rLoop][cLoop] = Integer.parseInt(line[cLoop]);
                if (ladderMap[rLoop][cLoop] == 2) {
                    endPoint.y = rLoop;
                    endPoint.x = cLoop;
                }
            }
        }
    }
}
