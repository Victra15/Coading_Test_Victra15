package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A053_SWEA_4012_요리사 {
    private static class Input {
        int N;
        int[][] flavorMap;
    }

    private static class DfsInfo {
        int currSelect;
        Set<Integer> selected;

        DfsInfo(int currSelect, Set<Integer> selected) {
            this.currSelect = currSelect;
            this.selected = selected;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A053_SWEA_4012_요리사.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            Input input = new Input();
            getInput(input, br);
            printOutput(bw, testCase, findMinFlavorDiff(input));
        }
        bw.close();
        br.close();
    }

    private static int findMinFlavorDiff(Input input) {
        int minFlavorDiff = Integer.MAX_VALUE;
        Stack<DfsInfo> stack = new Stack<>();
        stack.push(new DfsInfo(-1, new HashSet<>()));
        while (!stack.isEmpty()) {
            DfsInfo info = stack.pop();
            if (info.selected.size() >= input.N / 2) {
                minFlavorDiff = Math.min(minFlavorDiff, getFlavorDiff(input, info.selected));
                continue;
            }
            for (int loop = 0; loop < input.N; loop++) {
                if (info.currSelect < loop) {
                    Set<Integer> newSelected = new HashSet<>(info.selected);
                    newSelected.add(loop);
                    stack.push(new DfsInfo(loop, newSelected));
                }
            }
        }
        return minFlavorDiff;
    }

    private static int getFlavorDiff(Input input, Set<Integer> selected) {
        Set<Integer> unselected = new HashSet<>();
        for(int loop = 0; loop < input.N; loop++) {
            if(!selected.contains(loop)) {
                unselected.add(loop);
            }
        }

        int cuisineAFlavor = 0;
        for (int select1 : selected){
            for(int select2 : selected) {
                if(select1 == select2) continue;
                cuisineAFlavor += input.flavorMap[select1][select2];
            }
        }

        int cuisineBFlavor = 0;
        for (int select1 : unselected){
            for(int select2 : unselected) {
                if(select1 == select2) continue;
                cuisineBFlavor += input.flavorMap[select1][select2];
            }
        }

        return Math.abs(cuisineAFlavor - cuisineBFlavor);
    }

    private static void printOutput(BufferedWriter bw, int testCase, int minFlavorDiff) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ').append(minFlavorDiff).append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(Input input, BufferedReader br) throws IOException {
        input.N = Integer.parseInt(br.readLine());
        input.flavorMap = new int[input.N][input.N];
        for (int row = 0; row < input.N; row++) {
            String[] line = br.readLine().trim().split(" ");
            for (int col = 0; col < input.N; col++) {
                input.flavorMap[row][col] = Integer.parseInt(line[col]);
            }
        }
    }
}