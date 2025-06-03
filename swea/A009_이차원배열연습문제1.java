package 이용태;

import java.io.IOException;
import java.util.Arrays;

public class A009_이차원배열연습문제1 {

	public static void main(String[] args) throws IOException{
		int[][] arr = new int[5][5];
		
		for (int rLoop = 0; rLoop < arr.length; rLoop++) {
			for (int cLoop = 0; cLoop < arr[0].length; cLoop++) {
				arr[rLoop][cLoop] = (int)(Math.random() * 100);
			}
		}
		
		int absSumAll = 0;
		
		for (int rLoop = 0; rLoop < arr.length; rLoop++) {
			for (int cLoop = 0; cLoop < arr[0].length; cLoop++) {
				absSumAll += calcAbsSum(arr, rLoop, cLoop);
			}
		}
		
		System.out.println(Arrays.deepToString(arr));
		System.out.println(absSumAll);
		
	}

	private static int calcAbsSum(int[][] arr, int r, int c) {
		int result = 0;
		for (int loop = r - 1; loop <= r + 1; loop++) {
			try {
				result += Math.abs(arr[loop][c] - arr[r][c]);
			} catch (Exception e) {
				continue;
			}
		}
		
		for (int loop = c - 1; loop <= c + 1; loop++) {
			try {
				result += Math.abs(arr[r][loop] - arr[r][c]);
			} catch (Exception e) {
				continue;
			}
		}
		
		return result;
	}
}
