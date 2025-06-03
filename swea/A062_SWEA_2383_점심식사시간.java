package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A062_SWEA_2383_점심식사시간 {
    private static int STAIR_A = 0;
    private static int STAIR_B = 1;

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A062_SWEA_2383_점심식사시간.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            List<Position> positions = new ArrayList<>();
            List<Position> stairPosition = new ArrayList<>();
            List<Integer> stairTime = new ArrayList<>();
            for (int row = 0; row < N; row++) {
                String[] inputStr = br.readLine().trim().split(" ");
                for (int col = 0; col < inputStr.length; col++) {
                    switch (Integer.parseInt(inputStr[col])) {
                        case 0:
                            break;
                        case 1:
                            positions.add(new Position(row, col));
                            break;
                        default:
                            stairPosition.add(new Position(row, col));
                            stairTime.add(Integer.parseInt(inputStr[col]));
                            break;
                    }
                }
            }

            int[][] distances = new int[positions.size()][2];

            for (int loop = 0; loop < positions.size(); loop++) {
                Position pos = positions.get(loop);
                Position stairA = stairPosition.get(0);
                Position stairB = stairPosition.get(1);
                distances[loop][STAIR_A] = Math.abs(pos.x - stairA.x) + Math.abs(pos.y - stairA.y);
                distances[loop][STAIR_B] = Math.abs(pos.x - stairB.x) + Math.abs(pos.y - stairB.y);
            }

            int[] select = new int[distances.length];
            int result = getMinTime(select, 0, distances, stairTime);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getMinTime(int[] select, int stuIdx, int[][] distances, List<Integer> stairTime) {
        int minTime = Integer.MAX_VALUE;
        if (stuIdx >= distances.length) {
            return calcTotalTime(select, distances, stairTime);
        }

        select[stuIdx] = STAIR_A;
        minTime = Math.min(minTime, getMinTime(select, stuIdx + 1, distances, stairTime));
        select[stuIdx] = STAIR_B;
        minTime = Math.min(minTime, getMinTime(select, stuIdx + 1, distances, stairTime));

        return minTime;
    }

    private static int calcTotalTime(int[] select, int[][] distances, List<Integer> stairTime) {
        PriorityQueue<Integer>[] distancePQ = new PriorityQueue[2];
        for(int loop = 0; loop < 2; loop++) {
            distancePQ[loop] = new PriorityQueue<>();
        }

        for (int loop = 0; loop < select.length; loop++) {
            int selectedStair = select[loop];
            distancePQ[selectedStair].add(distances[loop][selectedStair]);
        }
        return Math.max(calcStairTime(distancePQ[STAIR_A], stairTime.get(STAIR_A)), calcStairTime(distancePQ[STAIR_B], stairTime.get(STAIR_B)));
    }

    private static int calcStairTime(PriorityQueue<Integer> distancePQ, int stairTime) {
        int totalTime = 0;
        Queue<Integer> stairQueueTime = new LinkedList<>();
        while(!distancePQ.isEmpty()){
            int time = distancePQ.poll();
            if(stairQueueTime.size() >= 3){
                time = Math.max(time, stairQueueTime.poll() - 1);
            }
            totalTime = time + stairTime + 1;
            stairQueueTime.add(totalTime);
        }

        return totalTime;
    }
}