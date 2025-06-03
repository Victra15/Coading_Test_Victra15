package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class A054_SWEA_5215_햄버거다이어트 {
	private static class Input {
		int N;
		int L;
		Ingredient[] ingredients;
	}

	private static class DfsInfo {
		int currSelect;
		int flavorSum;
		int caloriesSum;

		public DfsInfo(int currSelect, int flavorSum, int caloriesSum) {
			this.currSelect = currSelect;
			this.flavorSum = flavorSum;
			this.caloriesSum = caloriesSum;
		}
	}

	private static class Ingredient implements Comparable<Ingredient> {
		int flavorScore;
		int calories;

		public Ingredient(int flavorScore, int calories) {
			this.flavorScore = flavorScore;
			this.calories = calories;
		}

		@Override
		public int compareTo(Ingredient o) {
			return (o.flavorScore / o.calories) - (this.flavorScore / this.calories);
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A054_SWEA_5215_햄버거다이어트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			Input input = new Input();
			getInput(input, br);
			printOutput(bw, testCase, getMostQualified(input));
		}
	}

	private static void printOutput(BufferedWriter bw, int testCase, int mostQualified) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append('#').append(testCase).append(' ').append(mostQualified).append('\n');
		bw.write(sb.toString());
		bw.flush();
	}

	private static int getMostQualified(Input input) {
		Arrays.sort(input.ingredients);
		int maxFlavorScoreSum = 0;
		Stack<DfsInfo> stack = new Stack<>();
		stack.push(new DfsInfo(-1, 0, 0));
		while(!stack.isEmpty()) {
			DfsInfo currInfo = stack.pop();
			boolean isEndPoint = true;
			for(int loop = 0; loop < input.N; loop++) {
				if(loop > currInfo.currSelect) {
					int newFlavorSum = currInfo.flavorSum + input.ingredients[loop].flavorScore;
					int newCaloriesSum = currInfo.caloriesSum + input.ingredients[loop].calories;
					if(newCaloriesSum < input.L) {
						stack.push(new DfsInfo(loop, newFlavorSum, newCaloriesSum));
						isEndPoint = false;
					}
				}
			}
			if(isEndPoint) {
				maxFlavorScoreSum = Math.max(maxFlavorScoreSum, currInfo.flavorSum);
			}
		}
		return maxFlavorScoreSum;
	}



	private static void getInput(Input input, BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		input.N = Integer.parseInt(st.nextToken());
		input.L = Integer.parseInt(st.nextToken());

		input.ingredients = new Ingredient[input.N];
		for(int loop = 0; loop < input.N; loop++) {
			st = new StringTokenizer(br.readLine());
			input.ingredients[loop] = new Ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
}