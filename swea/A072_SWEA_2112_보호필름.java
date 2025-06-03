package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A072_SWEA_2112_보호필름 {
    private static class Inputs {
        int D;
        int W;
        int K;
        int[][] cellMap;
    }

    private static void testInput(BufferedReader br, Inputs inputs) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        inputs.D = Integer.parseInt(st.nextToken());
        inputs.W = Integer.parseInt(st.nextToken());
        inputs.K = Integer.parseInt(st.nextToken());
        inputs.cellMap = new int[inputs.D][inputs.W];

        for (int layer = 0; layer < inputs.D; layer++) {
            st = new StringTokenizer(br.readLine());
            for (int cell = 0; cell < inputs.W; cell++) {
                inputs.cellMap[layer][cell] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printInput(Inputs inputs) {
        StringBuilder builder = new StringBuilder();
        builder.append(inputs.D).append(' ').append(inputs.W).append(' ').append(inputs.K).append('\n');
        for (int layer = 0; layer < inputs.D; layer++) {
            for (int cell = 0; cell < inputs.W; cell++) {
                builder.append(inputs.cellMap[layer][cell]).append(' ');
            }
            builder.append('\n');
        }
        System.out.println(builder.toString());
    }

    private static void printOutput(BufferedWriter bw, int testCase, int result) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append('#').append(testCase).append(' ').append(result).append('\n');
        bw.write(builder.toString());
        bw.flush();
    }

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A072_SWEA_2112_보호필름.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
	}
}