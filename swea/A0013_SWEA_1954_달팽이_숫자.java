package 이용태;

import java.io.*;


public class A0013_SWEA_1954_달팽이_숫자 {
    private static final int POINT_Y = 0;
    private static final int POINT_X = 1;

    static enum MoveState {
        UP, DOWN, LEFT, RIGHT
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("src/이용태/input/A0013_SWEA_1954_달팽이_숫자.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int snailArrSize = Integer.parseInt(br.readLine());
            int[][] snailArr = new int[snailArrSize][snailArrSize];

            int[] currPoint = new int[2];
            currPoint[POINT_X] = 0;
            currPoint[POINT_Y] = 0;

            MoveState currMoveState = MoveState.RIGHT;

            for (int loop = 1; loop <= (snailArrSize * snailArrSize); loop++) {
                snailArr[currPoint[POINT_Y]][currPoint[POINT_X]] = loop;

                if (loop == (snailArrSize * snailArrSize)) {
                    break;
                }

                int[] nextPoint = findNextPoint(currPoint, currMoveState);
                if (outOfIndex(snailArrSize, nextPoint) || snailArr[nextPoint[POINT_Y]][nextPoint[POINT_X]] != 0) {
                    currMoveState = turn(currMoveState);
                    nextPoint = findNextPoint(currPoint, currMoveState);
                }
                currPoint = nextPoint;
            }

            sb.append('#').append(testCase).append('\n');
            for (int rLoop = 0; rLoop < snailArrSize; rLoop++) {
                for (int cLoop = 0; cLoop < snailArrSize; cLoop++) {
                    sb.append(snailArr[rLoop][cLoop]).append(' ');
                }
                sb.append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
    }

    private static MoveState turn(MoveState currMoveState) {
        switch (currMoveState) {
            case UP:
                return MoveState.RIGHT;
            case DOWN:
                return MoveState.LEFT;
            case LEFT:
                return MoveState.UP;
            case RIGHT:
                return MoveState.DOWN;
            default:
                throw new IllegalStateException("Unexpected value: " + currMoveState);
        }
    }

    private static int[] findNextPoint(int[] currPoint, MoveState currMoveState) {
        int[] nextPoint = new int[2];
        nextPoint[POINT_X] = currPoint[POINT_X];
        nextPoint[POINT_Y] = currPoint[POINT_Y];
        switch (currMoveState) {
            case UP:
                nextPoint[POINT_Y] = currPoint[POINT_Y] - 1;
                break;
            case DOWN:
                nextPoint[POINT_Y] = currPoint[POINT_Y] + 1;
                break;
            case LEFT:
                nextPoint[POINT_X] = currPoint[POINT_X] - 1;
                break;
            case RIGHT:
                nextPoint[POINT_X] = currPoint[POINT_X] + 1;
                break;
        }
        return nextPoint;
    }

    private static boolean outOfIndex(int arrSize, int[] point) {
        return arrSize <= point[POINT_X] || 0 > point[POINT_X] || arrSize <= point[POINT_Y] || 0 > point[POINT_Y];
    }

}
