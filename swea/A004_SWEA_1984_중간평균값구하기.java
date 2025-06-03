package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A004_SWEA_1984_중간평균값구하기 {
	private final static int NUM_CNT = 10;

	public static void main(String[] args) throws IOException {
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A004_SWEA_1984_중간평균값구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());

		for (int loop = 0; loop < T; loop++) {
			st = new StringTokenizer(br.readLine());

			//변수 초기화
			int maxInt = 0;
			int minInt = Integer.MAX_VALUE;
			int sum = 0;
			int input;
			
			
			// 순회 돌면서 최댓값, 최솟값, 총합 계산
			for (int inputLoop = 0; inputLoop < NUM_CNT; inputLoop++) {
				input = Integer.parseInt(st.nextToken());
				sum += input;
				if (input > maxInt) {
					maxInt = input;
				}
				if (input < minInt) {
					minInt = input;
				}
			}
			
			// 최댓값, 최솟값 제외한 평균 계산
			double average = (sum - maxInt - minInt) / ((double) (NUM_CNT - 2));
			if (average - ((int) average) >= 0.5) {
				average += 1.0;
			}
			
			// 결과값 출력
			sb = new StringBuilder().append('#').append(loop + 1).append(' ').append((int)average).append('\n');
			bw.write(sb.toString());
			bw.flush();
		}
		// 입출력 스트림 닫기
		bw.close();
		br.close();
	}
}
