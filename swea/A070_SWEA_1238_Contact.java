package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A070_SWEA_1238_Contact {
    private static final int T = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A070_SWEA_1238_Contact.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Set<Integer>[] adjList = new Set[101];
            for(int loop = 0; loop < adjList.length; loop++) {
                adjList[loop] = new HashSet<>();
            }
            for (int loop = 0; loop < N/2; loop++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
            }

            boolean[] visited = new boolean[101];
            Queue<Integer> prevQueue = new LinkedList<>();
            Queue<Integer> nextQueue = new LinkedList<>();
            prevQueue.add(start);

            int max = 0;
            while(!prevQueue.isEmpty() || !nextQueue.isEmpty()) {
                max = 0;
                while (!prevQueue.isEmpty()){
                    int num = prevQueue.poll();
                    nextQueue.add(num);
                    if(max < num)
                        max = num;
                }
                while (!nextQueue.isEmpty()) {
                    for(Integer num : adjList[nextQueue.poll()]){
                        if(visited[num]) continue;
                        visited[num] = true;
                        prevQueue.add(num);
                    }
                }
            }
            sb.append('#').append(testCase).append(' ').append(max).append("\n");
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}