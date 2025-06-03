package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A007_SWEA_2063_중간값찾기 {

	public static void main(String[] args) throws IOException {
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A007_SWEA_2063_중간값찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		// 테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		
		int result = 0;
		// 값 입력
		st = new StringTokenizer(br.readLine());

		List<Integer> scoreList = new ArrayList<>();
		while (true) {
			try {
				int score = Integer.parseInt(st.nextToken());
				scoreList.add(score);
				
			} catch (NoSuchElementException e) {
				break;
			}
		}
		
		Collections.sort(scoreList);
		result = scoreList.get((scoreList.size() / 2));
		// 결과값 출력
		sb = new StringBuilder().append(result).append('\n');
		bw.write(sb.toString());
		bw.flush();

		// 입출력 스트림 닫기
		bw.close();
		br.close();

	}

}
