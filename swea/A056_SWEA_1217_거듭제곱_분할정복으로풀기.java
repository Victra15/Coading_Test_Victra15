package 이용태;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class A056_SWEA_1217_거듭제곱_분할정복으로풀기  {
	private static final int T = 10;

	private static class Input{
		int N;
		int M;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A056_SWEA_1217_거듭제곱_분할정복으로풀기.txt"));
		Scanner sc = new Scanner(System.in);

		for(int loop = 1 ; loop <= T; loop++) {
			Input input = new Input();
			int testCase = getInput(sc, input);

			System.out.printf("#%d %d\n", testCase, getResult(input));
		}
		sc.close();
	}

	private static long getResult(Input input) {
		long[] nPowM = new long[input.M + 1];
		return getNPowM(input.N, input.M, nPowM);
	}

	private static long getNPowM(int n, int m, long[] nPowM) {
		if(nPowM[m] != 0) { return nPowM[m]; }
		else {
			if (m == 0) return 1;
			else if (m == 1) return n;
			else {
				return getNPowM(n, m / 2, nPowM) * getNPowM(n, m - (m / 2), nPowM);
			}
		}
	}

	private static int getInput(Scanner sc, Input input) {
		int testCase = sc.nextInt();
		input.N = sc.nextInt();
		input.M = sc.nextInt();
		return testCase;
	}
}