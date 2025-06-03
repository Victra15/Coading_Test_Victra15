package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class A076_SWEA_1267_작업순서 {
    private static class Input {
        int V;
        int E;
        Set<Integer>[] adjSet;
    }

    private static final int T = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A076_SWEA_1267_작업순서.txt"));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int testCase = 1; testCase <= T; testCase++) {
                Input input = new Input();
                getInput(br, input);
                printOutput(bw, testCase, getTaskOrder(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static int[] getTaskOrder(Input input) {
        int[] inDegree = new int[input.V + 1];
        boolean[] visited = new boolean[input.V + 1];
        int[] taskOrder = new int[input.V];
        Arrays.stream(input.adjSet).forEach(set -> set.stream().forEach( vertex -> inDegree[vertex]++));

        Queue<Integer> vertexQueue = new LinkedList<>();
        for (int vertex = 1; vertex < inDegree.length; vertex++) {
            if(inDegree[vertex] == 0) vertexQueue.offer(vertex);
        }

        int idx = 0;
        while (!vertexQueue.isEmpty()) {
            int currVertex = vertexQueue.poll();

            taskOrder[idx++] = currVertex;
            for(int vertex : input.adjSet[currVertex]){
                if(!visited[vertex]){
                    inDegree[vertex]--;
                    if(inDegree[vertex] == 0) vertexQueue.offer(vertex);
                }
            }
        }

        return taskOrder;
    }

    private static void printOutput(BufferedWriter bw, int testCase, int[] order) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('#').append(testCase).append(' ');
        Arrays.stream(order).forEach(vertex -> sb.append(vertex).append(' '));
        sb.append('\n');
        bw.write(sb.toString());
        bw.flush();
    }

    private static void getInput(BufferedReader br, Input input) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        input.V = Integer.parseInt(st.nextToken());
        input.E = Integer.parseInt(st.nextToken());
        input.adjSet = Arrays.stream(new Set[input.V + 1])
                .map(i -> new HashSet<Integer>())  // 각 인덱스에 대해 새로운 HashSet<Integer> 생성
                .toArray(HashSet[]::new);

        st = new StringTokenizer(br.readLine());
        for (int loop = 0; loop < input.E; loop++) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            input.adjSet[from].add(to);
        }
    }
}