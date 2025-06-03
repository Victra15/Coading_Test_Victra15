package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class A059_SWEA_6808_규영이와인영이의카드게임 {
    private static class Input {
        int[] cards;
    }

    private static class DfsInfo {
        int remainGameRounds;
        int scoreA;
        int scoreB;
        boolean[] visited;

        public DfsInfo(int remainGameRounds, int winCount, int loseCount, boolean[] visited) {
            this.remainGameRounds = remainGameRounds;
            this.scoreA = winCount;
            this.scoreB = loseCount;
            this.visited = visited;
        }
    }

    private static final int WIN = 0;
    private static final int LOSE = 1;
    private static final int TOTAL_ROUNDS = 9;
    private static final int TOTAL_SUM = 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10 + 11 + 12 + 13 + 14 + 15 + 16 + 17 + 18;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A059_SWEA_6808_규영이와인영이의카드게임.txt"));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            for (int testCase = 1; testCase <= T; testCase++) {
                Input input = new Input();
                getInput(br, input);
                printOutput(bw, testCase, getCaseCount(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] getCaseCount(Input input) {
        int[] result = new int[2];
        int[] remainCards = new int[TOTAL_ROUNDS];
        Stack<DfsInfo> dfsStack = new Stack<>();
        {
            int[] selectCard = new int[19];
            for (int card : input.cards) {
                selectCard[card] = 1;
            }
            int index = 0;
            for (int loop = 1; loop < selectCard.length; loop++) {
                if (selectCard[loop] == 0) remainCards[index++] = loop;
            }
        }
        dfsStack.push(new DfsInfo(TOTAL_ROUNDS, 0, 0, new boolean[TOTAL_ROUNDS]));
        while (!dfsStack.isEmpty()) {
            DfsInfo currInfo = dfsStack.pop();
            if (currInfo.scoreA > TOTAL_SUM / 2) {
                result[WIN] += getFactorial(currInfo.remainGameRounds);
                continue;
            }
            if (currInfo.scoreB > TOTAL_SUM / 2) {
                result[LOSE] += getFactorial(currInfo.remainGameRounds);
                continue;
            }
            if (currInfo.remainGameRounds == 0) continue;
            for(int loop = 0; loop < TOTAL_ROUNDS; loop++) {
                if(!currInfo.visited[loop]) {
                    DfsInfo nextInfo = new DfsInfo(currInfo.remainGameRounds - 1, currInfo.scoreA, currInfo.scoreB, currInfo.visited.clone());
                    nextInfo.visited[loop] = true;
                    if (input.cards[nextInfo.remainGameRounds] > remainCards[loop])
                        nextInfo.scoreA += remainCards[loop] + input.cards[nextInfo.remainGameRounds];
                    else if (input.cards[nextInfo.remainGameRounds] < remainCards[loop])
                        nextInfo.scoreB += remainCards[loop] + input.cards[nextInfo.remainGameRounds];
                    dfsStack.push(nextInfo);
                }
            }
        }
        return result;
    }

    private static int getFactorial(int remainGameRounds) {
        int factorial = 1;
        for (int i = 1; i <= remainGameRounds; i++) {
            factorial *= i;
        }
        return factorial;
    }

    private static void printOutput(BufferedWriter bw, int testCase, int[] caseCount) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(caseCount[WIN]).append(' ').append(caseCount[LOSE]).append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        input.cards = Arrays.
                stream(br.readLine().trim().split(" ")).
                mapToInt(Integer::parseInt).
                toArray();

    }
}