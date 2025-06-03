package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A005_SWEA_1206_View {
	public static void main(String[] args) throws IOException {
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A005_SWEA_1206_View.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		// 5개의 빌딩을 담을 Linked List 선언
		// (삽입, 삭제를 자주 사용할 예정이고, index를 통해 중간값을 가져올 예정이므로)
		LinkedList<Integer> buildQueue = new LinkedList<>();
		for (int test_case = 1; test_case <= 10; test_case++) {
			//초기값 입력 및 초기화
			int result = 0;
			int buildCnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			// 5개의 비어있는 값 삽입
			for (int loop = 0; loop < 5; loop++) {
				buildQueue.addFirst(0);
			}

			// 새로운 입력값을 buildQueue에 삽입하고, 마지막 위치의 값을 삭제 후, calcViewLength() 메소드 호출
			for (int loop = 0; loop < buildCnt; loop++) {
				int input = Integer.parseInt(st.nextToken());
				buildQueue.addFirst(input);
				buildQueue.removeLast();
				result += calcViewLength(buildQueue);
			}
			//buildQueue 초기화
			buildQueue.clear();
			// 결과값 출력
			sb = new StringBuilder().append('#').append(test_case).append(' ').append(result).append('\n');
			bw.write(sb.toString());
			bw.flush();
		}
		// 입출력 스트림 닫기
		bw.close();
		br.close();
	}

	/**
	 * 현재 buildQueue에서 중앙에 위치한 빌딩에서 조망권이 확보된 가구의 수를 계산하는 메서드
	 * 
	 * @param buildQueue 5개의 이어진 빌딩 높이가 기록된 Queue
	 * @return 중간에 위치한 빌딩에서 조망권이 확보된 가구의 수
	 */
	private static int calcViewLength(LinkedList<Integer> buildQueue) {
		Integer midPosHeight = buildQueue.get(2);
		Integer maxHeightExceptMid = 0;
		int idx = 0;
		for (Integer height : buildQueue) {
			if (idx == 2) {
				idx++;
				continue;
			}
			if (height > maxHeightExceptMid) {
				maxHeightExceptMid = height;
			}
			if (height > midPosHeight) {
				return 0;
			}
			idx++;
		}
		return midPosHeight - maxHeightExceptMid;
	}

}
