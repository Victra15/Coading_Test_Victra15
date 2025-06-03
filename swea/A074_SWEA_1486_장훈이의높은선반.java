package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class A074_SWEA_1486_장훈이의높은선반 {
	private static class Inputs{
		int N;
		int B;
		int[] assistantHeights;
	}

	private static class SelectInfo{
		int remain;
		int heightSum;

		SelectInfo(int remain, int heightSum){
			this.remain = remain;
			this.heightSum = heightSum;
		}
	}

	private static Inputs getInput(BufferedReader br) throws IOException {
		Inputs inputs = new Inputs();

		StringTokenizer st = new StringTokenizer(br.readLine());
		inputs.N = Integer.parseInt(st.nextToken());
		inputs.B = Integer.parseInt(st.nextToken());

		inputs.assistantHeights = new int[inputs.N];
		st = new StringTokenizer(br.readLine());
		for(int loop = 0; loop < inputs.N; loop++){
			inputs.assistantHeights[loop] = Integer.parseInt(st.nextToken());
		}

		return inputs;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A074_SWEA_1486_장훈이의높은선반.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++){
			Inputs inputs = getInput(br);
			printOutput(bw, testCase, getMostFitTowerHeight(inputs));
		}
		br.close();
		bw.close();
	}

	private static void printOutput(BufferedWriter bw, int testCase, int result) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append('#').append(testCase).append(' ').append(result).append('\n');
		bw.write(sb.toString());
		bw.flush();
	}

	private static int getMostFitTowerHeight(Inputs inputs) {
		int minSum = Integer.MAX_VALUE;
		Stack<SelectInfo> stack = new Stack<SelectInfo>();
		stack.push(new SelectInfo(inputs.N - 1, 0));
		while(!stack.isEmpty()){
			SelectInfo info = stack.pop();

			if(info.heightSum == inputs.B) return 0;
			else if(info.heightSum > inputs.B) {
				minSum = Math.min(minSum, info.heightSum);
				continue;
			}else if(info.remain < 0) continue;

			stack.push(new SelectInfo(info.remain - 1, info.heightSum + inputs.assistantHeights[info.remain]));
			stack.push(new SelectInfo(info.remain - 1, info.heightSum));
		}
		return minSum - inputs.B;
	}
}