package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class A077_SWEA_1970_쉬운거스름돈 {
    private static class Input {
        int N;
    }

    private static final int[] MONEY_SET = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A076_SWEA_1970_쉬운거스름돈.txt"));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int T = Integer.parseInt(br.readLine());
            for (int testCase = 1; testCase <= T; testCase++) {
                Input input = new Input();
                getInput(br, input);
                printOutput(bw, testCase, getMinMoneyCnt(input));
            }
        }
    }

    private static int[] getMinMoneyCnt(Input input) {
        int[] moneyCnt = new int[MONEY_SET.length];
        int remainChange = input.N;
        for(int loop = 0; loop < MONEY_SET.length; loop++){
            moneyCnt[loop] = remainChange / MONEY_SET[loop];
            remainChange = remainChange % MONEY_SET[loop];
        }
        return moneyCnt;
    }

    private static void printOutput(BufferedWriter bw, int testCase, int[] minMoneyCnt) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append('\n');
        Arrays.stream(minMoneyCnt).forEach((moneyCnt) -> sb.append(moneyCnt).append(' '));
        sb.append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        input.N = Integer.parseInt(br.readLine());
    }
}