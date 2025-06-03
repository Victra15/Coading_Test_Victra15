package 이용태;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Command {
    char robot;
    int buttonNum;
}

public class A046_SWEA_10761_신뢰 {
    private static final int ORANGE_ROBOT = 0;
    private static final int BLUE_ROBOT = 1;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A046_SWEA_10761_신뢰.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmdCnt = Integer.parseInt(st.nextToken());
            Queue<Command> cmdQueue = new LinkedList<>();
            Queue<Integer>[] eachRobotCmdQueue = new LinkedList[2];
            eachRobotCmdQueue[ORANGE_ROBOT] = new LinkedList<>();
            eachRobotCmdQueue[BLUE_ROBOT] = new LinkedList<>();
            for (int loop = 0; loop < cmdCnt; loop++) {
                Command cmdInput = new Command();
                cmdInput.robot = st.nextToken().charAt(0);
                cmdInput.buttonNum = Integer.parseInt(st.nextToken());
                cmdQueue.add(cmdInput);
                switch (cmdInput.robot) {
                    case 'O':
                        eachRobotCmdQueue[ORANGE_ROBOT].add(cmdInput.buttonNum);
                        break;
                    case 'B':
                        eachRobotCmdQueue[BLUE_ROBOT].add(cmdInput.buttonNum);
                        break;
                }
            }

            int result = getMinTime(cmdQueue, eachRobotCmdQueue);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getMinTime(Queue<Command> cmdQueue, Queue<Integer>[] eachRobotCmdQueue) {
        int[] currRobotPos = new int[2];
        Arrays.fill(currRobotPos, 1);
        int totalTime = 0;
        while (!cmdQueue.isEmpty()) {
            Command currCmd = cmdQueue.poll();
            int buttonRobot = currCmd.robot == 'O' ? ORANGE_ROBOT : BLUE_ROBOT;
            int moveRobot = currCmd.robot == 'O' ? BLUE_ROBOT : ORANGE_ROBOT;
            eachRobotCmdQueue[buttonRobot].poll();

            int moveTime = Math.abs(currCmd.buttonNum - currRobotPos[buttonRobot]) + 1;
            totalTime += moveTime;
            currRobotPos[buttonRobot] = currCmd.buttonNum;

            if (!eachRobotCmdQueue[moveRobot].isEmpty()) {
                int moveDest = eachRobotCmdQueue[moveRobot].peek();
                int moveDistance = Math.abs(moveDest - currRobotPos[moveRobot]);
                int moveDirection = moveDest > currRobotPos[moveRobot] ? 1 : -1;
                currRobotPos[moveRobot] = moveTime > moveDistance ? moveDest : currRobotPos[moveRobot] + (moveDirection * moveTime);
            }
        }
        return totalTime;
    }
}
