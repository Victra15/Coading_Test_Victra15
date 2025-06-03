package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A0011_이차원배열연습문제3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("src/이용태/input/A0011_이차원배열연습문제3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine().trim());
		int[][] arr = new int[N][N];

		for (int r = 0; r < N; r++) {
			String[] rowStr = br.readLine().trim().split(" ");
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(rowStr[c]);
			}
		}
		
		int maxSum = 0;
		for (int rLoop = 0; rLoop < arr.length; rLoop++) {
			for (int cLoop = 0; cLoop < arr.length; cLoop++) {
				int sum = calcSumAtPosition(arr, rLoop, cLoop);
				if(maxSum < sum)
					maxSum = sum;
			}
		}
		System.out.println(maxSum);
	}

	private static int calcSumAtPosition(int[][] arr, int r, int c) {
		// TODO Auto-generated method stub
		
		int sum = 0;
		for (int loop = r - 1; loop <= r + 1; loop++) {
			try {
				sum += arr[loop][c];				
			} catch (Exception e) {
				continue;
			}
		}
		
		for (int loop = c - 1; loop <= c + 1; loop++) {
			try {
				sum += arr[r][loop];
			} catch (Exception e) {
				continue;
			}
		}
		
		sum -= arr[r][c];
		return sum;
	}
}
