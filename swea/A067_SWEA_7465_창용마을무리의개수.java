package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A067_SWEA_7465_창용마을무리의개수 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A067_SWEA_7465_창용마을무리의개수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] unionSet = makeSet(N + 1);
			for(int loop = 0; loop < M; loop++){
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(unionSet, from, to);
			}

			int result = 0;
			for(int loop = 1; loop < unionSet.length; loop++) {
				if(unionSet[loop] == loop)
					result++;
			}
			sb.append('#').append(testCase).append(' ').append(result).append('\n');
			bw.write(sb.toString());
			bw.flush();
			sb.setLength(0);
		}
		bw.close();
		br.close();
	}

	private static void union(int[] unionSet, int from, int to) {
		unionSet[find(unionSet, from)] = find(unionSet,to);
	}

	private static int find(int[] unionSet, int node) {
		if(unionSet[node] == node) return node;
		else{
			int parent = find(unionSet, unionSet[node]);
			unionSet[node] = parent;
			return parent;
		}
	}

	private static int[] makeSet(int n) {
		int[] set = new int[n];
		for(int i = 0; i < n; i++) {
			set[i] = i;
		}
		return set;
	}
}