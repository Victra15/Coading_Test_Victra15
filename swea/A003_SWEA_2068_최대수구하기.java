package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class A003_SWEA_2068_최대수구하기 {
	public static void main(String[] args) throws IOException {
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A003_SWEA_2068_최대수구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());

		for (int loop = 0; loop < T; loop++) {
			st = new StringTokenizer(br.readLine());

			int maxInt = 0;
			int input;
			while (true) {
				try {
					input = Integer.parseInt(st.nextToken());
					// 현재 maxInt보다 input이 더 크면 maxInt = input
					if (input > maxInt) {
						maxInt = input;
					}
				} catch (NoSuchElementException e) {
					break;
				}
			}
			
			//결과값 출력
			sb = new StringBuilder().append('#').append(loop + 1).append(' ').append(maxInt).append('\n');
			bw.write(sb.toString());
			bw.flush();
		}
		
		// 입출력 스트림 닫기
		bw.close();
		br.close();
	}
}
