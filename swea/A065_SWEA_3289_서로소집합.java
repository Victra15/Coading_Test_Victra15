package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A065_SWEA_3289_서로소집합 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A065_SWEA_3289_서로소집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] unionArr = initUnionArr(N);
			for(int loop = 0; loop < M; loop++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int nodeA = Integer.parseInt(st.nextToken());
				int nodeB = Integer.parseInt(st.nextToken());
				switch (cmd.charAt(0)){
					case '0':
						union(unionArr, nodeA, nodeB);
						break;
					case '1':
						if(find(unionArr, nodeA) == find(unionArr, nodeB))
							sb.append('1');
						else
							sb.append('0');
						break;
				}
			}
			String result = sb.toString();
			sb.setLength(0);
			sb.append('#').append(testCase).append(' ').append(result).append('\n');
			bw.write(sb.toString());
			bw.flush();
			sb.setLength(0);
		}
		bw.close();
		br.close();
	}

	private static void union(int[] unionArr, int nodeA, int nodeB) {
		unionArr[find(unionArr, nodeA)] = find(unionArr, nodeB);
	}

	private static int find(int[] unionArr, int node) {
		if(unionArr[node] == node) return node;
		else {
			int parent = find(unionArr, unionArr[node]);
			unionArr[node] = parent;
			return parent;
		}
	}

	private static int[] initUnionArr(int n) {
		int[] arr = new int[n + 1];
		for(int loop = 1; loop <= n; loop++) {
			arr[loop] = loop;
		}
		return arr;
	}
}