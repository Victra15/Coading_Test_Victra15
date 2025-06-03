package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class A006_SWEA_1204_최빈수구하기 {

	public static void main(String[] args) throws IOException {
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A006_SWEA_1204_최빈수구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());

		for (int loop = 0; loop < T; loop++) {
			//값 입력
			int test_case = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			// 각 점수를 인덱스로 하고, 점수를 받은 학생의 숫자를 저장하는 배열 scoreCnt 선언
			int[] scoreCnt = new int[101];
			int freqNum = 0;
			
			// score 입력받아서 scoreCnt[score]에 1씩 추가,
			// 현재 freqNum의 scoreCnt보다 score의 scoreCnt가 더 크면 freqNum = score
			while(true) {
				try {
					int score = Integer.parseInt(st.nextToken());
					scoreCnt[score]++;
					if(scoreCnt[score] >= scoreCnt[freqNum])
						freqNum = score;
				} catch (NoSuchElementException e) {
					break;
				}
			}

			// 결과값 출력
			sb = new StringBuilder().append('#').append(test_case).append(' ').append(freqNum).append('\n');
			bw.write(sb.toString());
			bw.flush();
		}

		// 입출력 스트림 닫기
		bw.close();
		br.close();
	}

}
