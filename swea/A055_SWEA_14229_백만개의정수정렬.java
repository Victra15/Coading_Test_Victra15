package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A055_SWEA_14229_백만개의정수정렬 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/이용태/input/A055_SWEA_14229_백만개의정수정렬.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int[] count = new int[10000001];
		String[] inputStr = br.readLine().trim().split(" ");
		for (String numStr : inputStr) {
			count[Integer.parseInt(numStr)]++;
		}

		int countSum = 0;
		int halfNum = 0;
		for(int loop = 0; loop < count.length; loop++) {
			if(count[loop] > 0) { countSum += count[loop]; }
			if(countSum > 500000) {
				halfNum = loop;
				break;
			}
		}
		bw.write(halfNum + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}