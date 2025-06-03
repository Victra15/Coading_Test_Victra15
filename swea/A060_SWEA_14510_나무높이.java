package 이용태;

import java.io.*;
import java.util.Arrays;

public class A060_SWEA_14510_나무높이 {
    private static class Input {
        int treeCnt;
        int[] treeHeights;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/이용태/input/A060_SWEA_14510_나무높이.txt"));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());

            for (int testCase = 1; testCase <= T; testCase++) {
                Input input = new Input();
                getInput(br, input);
                printOutput(bw, testCase, calcMinDay(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int calcMinDay(Input input) {
        int maxHeight = Arrays.stream(input.treeHeights).max().getAsInt();
        int oddCnt = 0;
        int evenCnt = 0;
        for (int loop = 0; loop < input.treeHeights.length; loop++) {
            evenCnt += (maxHeight - input.treeHeights[loop]) / 2;
            oddCnt += (maxHeight - input.treeHeights[loop]) % 2;
        }

        if (evenCnt >= oddCnt) {
            int remain = (evenCnt - oddCnt) * 2;
            evenCnt = oddCnt;
            evenCnt += remain / 3;
            oddCnt += remain / 3;
            if(remain % 3 == 2) evenCnt++;
            else if(remain % 3 == 1) oddCnt++;
            return evenCnt > oddCnt ? evenCnt * 2 : oddCnt + evenCnt;
        }
        else {
            return oddCnt * 2 - 1;
        }
    }

    private static void printOutput(BufferedWriter bw, int testCase, int result) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(result).append('\n');
        bw.write(sb.toString());
        bw.flush();

    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        input.treeCnt = Integer.parseInt(br.readLine());
        input.treeHeights = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}