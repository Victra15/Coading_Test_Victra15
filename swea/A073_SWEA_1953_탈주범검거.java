package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.function.Function;

public class A073_SWEA_1953_탈주범검거 {
    private static class Inputs {
        int N;
        int M;
        int R;
        int C;
        int L;
        int[][] tunnelMap;
    }

    private static class Position {
        int row;
        int col;

        Position() {
        }

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    private static class MoveInfo {
        Position position;
        int moveCnt;

        MoveInfo() {
        }

        ;

        MoveInfo(Position position, int moveCnt) {
            this.position = position;
            this.moveCnt = moveCnt;
        }
    }

    public static enum MoveType {
        MOVE_UP(position -> new Position(position.row - 1, position.col)),
        MOVE_DOWN(position -> new Position(position.row + 1, position.col)),
        MOVE_LEFT(position -> new Position(position.row, position.col - 1)),
        MOVE_RIGHT(position -> new Position(position.row, position.col + 1));

        public final Function<Position, Position> expression;

        MoveType(Function<Position, Position> expression) {
            this.expression = expression;
        }

        public Position move(Position position) {
            return expression.apply(position);
        }

        public MoveType reverse() {
            switch (this) {
                case MOVE_UP:
                    return MoveType.MOVE_DOWN;
                case MOVE_DOWN:
                    return MoveType.MOVE_UP;
                case MOVE_LEFT:
                    return MoveType.MOVE_RIGHT;
                case MOVE_RIGHT:
                    return MoveType.MOVE_LEFT;
                default:
                    throw new IllegalArgumentException("invalid move type: " + this);
            }
        }
    }

    private static void getInput(BufferedReader br, Inputs inputs) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        inputs.N = Integer.parseInt(st.nextToken());
        inputs.M = Integer.parseInt(st.nextToken());
        inputs.R = Integer.parseInt(st.nextToken());
        inputs.C = Integer.parseInt(st.nextToken());
        inputs.L = Integer.parseInt(st.nextToken());

        inputs.tunnelMap = new int[inputs.N][inputs.M];
        for (int rowLoop = 0; rowLoop < inputs.N; rowLoop++) {
            st = new StringTokenizer(br.readLine());
            for (int colLoop = 0; colLoop < inputs.M; colLoop++) {
                inputs.tunnelMap[rowLoop][colLoop] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printOutput(BufferedWriter bw, int testCase, int possiblePositionCount) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(possiblePositionCount).append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A073_SWEA_1953_탈주범검거.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            Inputs inputs = new Inputs();
            getInput(br, inputs);
            printOutput(bw, testCase, getPossiblePositionCount(inputs));
        }
        bw.close();
        br.close();
    }

    private static int getPossiblePositionCount(Inputs inputs) {
        int possiblePositionCount = 0;

        Queue<MoveInfo> bfsQueue = new LinkedList<>();
        boolean[][] visited = new boolean[inputs.N][inputs.M];

        visited[inputs.R][inputs.C] = true;
        bfsQueue.offer(new MoveInfo(new Position(inputs.R, inputs.C), 1));

        while (!bfsQueue.isEmpty()) {
            MoveInfo moveInfo = bfsQueue.poll();
            possiblePositionCount++;
            if (moveInfo.moveCnt >= inputs.L) continue;
            else offerAtQueue(bfsQueue, visited, inputs, moveInfo);
        }

        return possiblePositionCount;
    }

    private static List<MoveType> getMoveTypeList(int structType) {
        List<MoveType> moveTypeList = new ArrayList<>();

        switch (structType) {
            case 0:
                return null;
            case 1:
                moveTypeList.add(MoveType.MOVE_UP);
                moveTypeList.add(MoveType.MOVE_DOWN);
                moveTypeList.add(MoveType.MOVE_LEFT);
                moveTypeList.add(MoveType.MOVE_RIGHT);
                break;
            case 2:
                moveTypeList.add(MoveType.MOVE_UP);
                moveTypeList.add(MoveType.MOVE_DOWN);
                break;
            case 3:
                moveTypeList.add(MoveType.MOVE_LEFT);
                moveTypeList.add(MoveType.MOVE_RIGHT);
                break;
            case 4:
                moveTypeList.add(MoveType.MOVE_UP);
                moveTypeList.add(MoveType.MOVE_RIGHT);
                break;
            case 5:
                moveTypeList.add(MoveType.MOVE_DOWN);
                moveTypeList.add(MoveType.MOVE_RIGHT);
                break;
            case 6:
                moveTypeList.add(MoveType.MOVE_DOWN);
                moveTypeList.add(MoveType.MOVE_LEFT);
                break;
            case 7:
                moveTypeList.add(MoveType.MOVE_UP);
                moveTypeList.add(MoveType.MOVE_LEFT);
                break;
            default:
                throw new IllegalArgumentException("invalid structType : " + structType);
        }
        return moveTypeList;
    }

    private static void offerAtQueue(Queue<MoveInfo> bfsQueue, boolean[][] visited, Inputs inputs, MoveInfo currMoveInfo) {
        final int structAtPosition = inputs.tunnelMap[currMoveInfo.position.row][currMoveInfo.position.col];
        List<MoveType> moveTypeList = getMoveTypeList(structAtPosition);
        findMovableAndOfferQueue(inputs, bfsQueue, visited, moveTypeList, currMoveInfo);
    }

    private static void findMovableAndOfferQueue(Inputs inputs, Queue<MoveInfo> bfsQueue, boolean[][] visited, List<MoveType> moveTypeList, MoveInfo currMoveInfo) {
        for (MoveType moveType : moveTypeList) {
            try {
                Position positionAfterMove = moveType.move(currMoveInfo.position);
                int destStruct = inputs.tunnelMap[positionAfterMove.row][positionAfterMove.col];
                List<MoveType> destMoveTypeList = getMoveTypeList(destStruct);

                if (destMoveTypeList == null) continue;
                if (!destMoveTypeList.contains(moveType.reverse())) continue;

                if (!visited[positionAfterMove.row][positionAfterMove.col]) {
                    visited[positionAfterMove.row][positionAfterMove.col] = true;
                    bfsQueue.offer(new MoveInfo(positionAfterMove, currMoveInfo.moveCnt + 1));
                }
            } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            }
        }
    }
}