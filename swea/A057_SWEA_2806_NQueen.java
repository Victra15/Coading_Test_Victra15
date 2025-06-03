package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class A057_SWEA_2806_NQueen {
    private static class Input {
        int N;
    }

    private static class DfsInfo {
        int nextQueenNum;
        int[] queenMap;

        public DfsInfo(int nextQueenNum, int[] queenMap) {
            this.nextQueenNum = nextQueenNum;
            this.queenMap = queenMap;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A057_SWEA_2806_NQueen.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            Input input = new Input();
            getInput(br, input);
            printOutput(bw, testCase, findNQueenSolution(input));
        }
        bw.close();
        br.close();
    }

    private static int findNQueenSolution(Input input) {
        int caseCnt = 0;
        Stack<DfsInfo> stack = new Stack<>();

        stack.push(new DfsInfo(0, new int[input.N]));
        while (!stack.isEmpty()) {
            DfsInfo currInfo = stack.pop();
            if(currInfo.nextQueenNum == input.N) {
                caseCnt++;
                continue;
            }
            for (int queenCol = 0; queenCol < input.N; queenCol++) {
                if (canQueenLocated(queenCol, currInfo)) {
                    int[] newQueenMap = currInfo.queenMap.clone();
                    newQueenMap[currInfo.nextQueenNum] = queenCol;
                    stack.push(new DfsInfo(currInfo.nextQueenNum + 1, newQueenMap));
                }
            }
        }

        return caseCnt;
    }

    private static boolean canQueenLocated(int queenCol, DfsInfo currInfo) {
        for (int row = 0; row < currInfo.nextQueenNum; row++) {
            if (currInfo.queenMap[row] == queenCol || Math.abs(currInfo.queenMap[row] - queenCol) == Math.abs(row - currInfo.nextQueenNum)) return false;
        }
        return true;
    }

    private static void printOutput(BufferedWriter bw, int testCase, int nQueenSolution) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(nQueenSolution).append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        input.N = Integer.parseInt(br.readLine());
    }
}