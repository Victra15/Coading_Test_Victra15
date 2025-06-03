package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A075_SWEA_2382_미생물격리 {
    private static enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public static Direction fromInt(int direction) {
            switch (direction) {
                case 1:
                    return UP;
                case 2:
                    return DOWN;
                case 3:
                    return LEFT;
                case 4:
                    return RIGHT;
                default:
                    throw new IllegalArgumentException("Invalid value: " + direction);
            }
        }
    }

    private static class Input {
        int N;
        int M;
        int K;

        Map<Position, MicrobeInfo> microbeInfo;
    }

    private static class Position {
        int x, y;

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Position))
                return false;

            if (this == obj)
                return true;

            Position cast = (Position) obj;
            return x == cast.x && y == cast.y;
        }

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static class MicrobeInfo {
        int microbeCnt;
        Direction moveDirection;

        MicrobeInfo() {
        }

        MicrobeInfo(int microbeCnt, Direction moveDirection) {
            this.microbeCnt = microbeCnt;
            this.moveDirection = moveDirection;
        }

        @Override
        public String toString() {
            return "MicrobeInfo{" +
                    "microbeCnt=" + microbeCnt +
                    ", moveDirection=" + moveDirection +
                    '}';
        }
    }

    private static void input(Input input, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        input.N = Integer.parseInt(st.nextToken());
        input.M = Integer.parseInt(st.nextToken());
        input.K = Integer.parseInt(st.nextToken());

        input.microbeInfo = new HashMap<Position, MicrobeInfo>();
        for (int loop = 0; loop < input.K; loop++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int microbeCnt = Integer.parseInt(st.nextToken());
            Direction moveDirection = Direction.fromInt(Integer.parseInt(st.nextToken()));
            input.microbeInfo.put(new Position(x, y), new MicrobeInfo(microbeCnt, moveDirection));
        }
    }

    private static void printResult(BufferedWriter bw, int testCase, int result) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(result).append('\n');
        bw.write(sb.toString());
        bw.flush();
        sb.setLength(0);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A075_SWEA_2382_미생물격리.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            Input input = new Input();
            input(input, br);
            printResult(bw, testCase, getMicrobeCntAfterTime(input));
        }
        bw.close();
        br.close();
    }


    private static int getMicrobeCntAfterTime(Input input) {
        for (int loop = 0; loop < input.M; loop++) input.microbeInfo = afterTime(input);
        int result = 0;
        for (MicrobeInfo mi : input.microbeInfo.values()) result += mi.microbeCnt;

        return result;
    }

    private static Map<Position, MicrobeInfo> afterTime(Input input) {
        Map<Position, List<MicrobeInfo>> afterMoveMap = new HashMap<>();

        for (Position pos : input.microbeInfo.keySet()) {
            MicrobeInfo microbeInfo = input.microbeInfo.get(pos);
            MicrobeInfo newMicrobeInfo = new MicrobeInfo(microbeInfo.microbeCnt, microbeInfo.moveDirection);
            Position newPos = getMovedPosition(pos, microbeInfo.moveDirection);

            List<MicrobeInfo> afterMoves;
            if (afterMoveMap.containsKey(newPos)) {
                afterMoves = afterMoveMap.get(newPos);
            } else {
                afterMoves = new ArrayList<>();
                afterMoveMap.put(newPos, afterMoves);
            }
            afterMoves.add(newMicrobeInfo);
        }

        return getMergedMicrobeMap(input, afterMoveMap);
    }

    private static Map<Position, MicrobeInfo> getMergedMicrobeMap(Input input, Map<Position, List<MicrobeInfo>> afterMoveMap) {
        Map<Position, MicrobeInfo> afterMicrobeMerge = new HashMap<>();

        for (Position pos : afterMoveMap.keySet()) {
            List<MicrobeInfo> afterMoves = afterMoveMap.get(pos);

            MicrobeInfo newMicrobeInfo = new MicrobeInfo();
            int maxMicrobeCnt = 0;
            for(MicrobeInfo microbeInfo : afterMoves) {
                if(maxMicrobeCnt < microbeInfo.microbeCnt) {
                    maxMicrobeCnt = microbeInfo.microbeCnt;
                    newMicrobeInfo.moveDirection = microbeInfo.moveDirection;
                }
                newMicrobeInfo.microbeCnt += microbeInfo.microbeCnt;
            }

            if (pos.x == input.N - 1 || pos.y == input.N - 1 || pos.x == 0 || pos.y == 0) {
                newMicrobeInfo.microbeCnt /= 2;
                if(newMicrobeInfo.microbeCnt == 0) continue;
                newMicrobeInfo.moveDirection = reverseDirection(newMicrobeInfo.moveDirection);
            }
            afterMicrobeMerge.put(pos, newMicrobeInfo);
        }
        return afterMicrobeMerge;
    }

    private static Direction reverseDirection(Direction moveDirection) {
        switch (moveDirection) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                throw new IllegalStateException();
        }
    }

    private static Position getMovedPosition(Position pos, Direction moveDirection) {
        switch (moveDirection) {
            case UP:
                return new Position(pos.x, pos.y - 1);
            case DOWN:
                return new Position(pos.x, pos.y + 1);
            case LEFT:
                return new Position(pos.x - 1, pos.y);
            case RIGHT:
                return new Position(pos.x + 1, pos.y);
            default:
                throw new IllegalStateException();
        }
    }
}