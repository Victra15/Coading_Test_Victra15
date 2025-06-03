package 이용태;

import java.io.*;
import java.util.*;

public class A058_SWEA_1873_상호의배틀필드 {
    private static class Input {
        int H;
        int W;
        char[][] map;
        int N;
        String commands;
        Position startTankPosition;
        Direction startTankDirection;
    }

    private static class GameInfo {
        Position tankPos;
        Direction tankDirection;
        char[][] map;

        public GameInfo(Position tankPos, Direction tankDirection, char[][] map) {
            this.tankPos = tankPos;
            this.tankDirection = tankDirection;
            this.map = map;
        }
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Position add(Position pos) {
            return new Position(this.row + pos.row, this.col + pos.col);
        }

        public void addToSelf(Position pos) {
            this.row += pos.row;
            this.col += pos.col;
        }

        public boolean positionOutOfBounds(int rowLength, int colLength) {
            return 0 <= this.row && this.row < rowLength && 0 <= this.col && this.col < colLength;
        }
    }

    private enum Direction {
        UP('^', new Position(-1, 0)),
        DOWN('v', new Position(1, 0)),
        LEFT('<', new Position(0, -1)),
        RIGHT('>', new Position(0, 1));

        public final char symbol;
        public final Position delta;

        Direction(char c, Position delta) {
            this.symbol = c;
            this.delta = delta;
        }

        public static Direction fromSymbol(char c) {
            for (Direction direction : Direction.values()) {
                if (direction.symbol == c) {
                    return direction;
                }
            }
            assert true; // 절대 이 위치에 도달하면 안됨
            return null;
        }
    }

    private enum Command {
        GO_UP('U') {
            @Override
            public void apply(GameInfo info) {
                move(Direction.UP, info);
            }
        },
        GO_DOWN('D') {
            @Override
            public void apply(GameInfo info) {
                move(Direction.DOWN, info);
            }
        },
        GO_LEFT('L') {
            @Override
            public void apply(GameInfo info) {
                move(Direction.LEFT, info);
            }
        },
        GO_RIGHT('R') {
            @Override
            public void apply(GameInfo info) {
                move(Direction.RIGHT, info);
            }
        },
        SHOT('S') {
            @Override
            public void apply(GameInfo info) {
                Set<Character> wallCharset = new HashSet<>(Arrays.asList('#', '*'));
                for(Position shellPosition = info.tankPos.add(info.tankDirection.delta); shellPosition.positionOutOfBounds(info.map.length, info.map[0].length); shellPosition.addToSelf(info.tankDirection.delta)){
                    if(wallCharset.contains(info.map[shellPosition.row][shellPosition.col])){
                        if(info.map[shellPosition.row][shellPosition.col] == '*'){
                            info.map[shellPosition.row][shellPosition.col] = '.';
                        }
                        break;
                    }
                }
            }
        };

        public final char symbol;

        Command(char s) {
            this.symbol = s;
        }

        public static Command fromChar(char c) {
            for (Command cmd : Command.values()) {
                if (cmd.symbol == c) {
                    return cmd;
                }
            }
            assert true; // 절대 여기에 도달하면 안됨.
            return null; // 해당하는 명령이 없을 경우 null 반환
        }

        private static void move(Direction direction, GameInfo info) {
            Position newPos = info.tankPos.add(direction.delta);
            try {
                if (info.map[newPos.row][newPos.col] == '.') {
                    info.map[info.tankPos.row][info.tankPos.col] = '.';
                    info.map[newPos.row][newPos.col] = direction.symbol;
                    info.tankDirection = direction;
                    info.tankPos = newPos;
                }
                else {
                    info.map[info.tankPos.row][info.tankPos.col] = direction.symbol;
                    info.tankDirection = direction;
                }
            } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                info.map[info.tankPos.row][info.tankPos.col] = direction.symbol;
                info.tankDirection = direction;
                return;
            }
        }

        public abstract void apply(GameInfo info);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A058_SWEA_1873_상호의배틀필드.txt"));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int T = Integer.parseInt(br.readLine());
            for (int testCase = 1; testCase <= T; testCase++) {
                Input input = new Input();
                getInput(br, input);
                char[][] outputMap = getAfterCommand(input);
                printOutput(bw, testCase, outputMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    private static char[][] getAfterCommand(Input input) {
        GameInfo gameInfo = new GameInfo(input.startTankPosition,
                input.startTankDirection,
                Arrays.stream(input.map).map(char[]::clone).toArray(char[][]::new));

        for (int loop = 0; loop < input.commands.length(); loop++) {
            Command cmd = Command.fromChar(input.commands.charAt(loop));
            assert cmd != null;
            cmd.apply(gameInfo);
        }
        return gameInfo.map;
    }

    private static void printOutput(BufferedWriter bw, int testCase, char[][] outputMap) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ');
        for (char[] mapRow : outputMap) {
            for (char mapChar : mapRow) {
                sb.append(mapChar);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        input.H = Integer.parseInt(st.nextToken());
        input.W = Integer.parseInt(st.nextToken());

        input.map = new char[input.H][input.W];
        Set<Character> tankChars = new HashSet<>(Arrays.asList('>', '<', '^', 'v'));

        for (int row = 0; row < input.H; row++) {
            input.map[row] = br.readLine().toCharArray();
            for (int col = 0; col < input.W; col++) {
                if (tankChars.contains(input.map[row][col])) {
                    input.startTankPosition = new Position(row, col);
                    input.startTankDirection = Direction.fromSymbol(input.map[row][col]);
                }
            }
        }

        input.N = Integer.parseInt(br.readLine());
        input.commands = br.readLine();
    }
}