package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class A008_SWEA_1208_flatten {
	private static int TEST_CASE_CNT = 10;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		// 입출력 스트림 객체 선언 및 초기화
		System.setIn(new FileInputStream("src/이용태/input/A008_SWEA_1208_flatten.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// String 처리 객체 선언
		StringTokenizer st;
		StringBuilder sb;

		for (int test_case = 1; test_case <= TEST_CASE_CNT; test_case++) {
			// 최대 덤프 수 입력
			int maxDump = Integer.parseInt(br.readLine());
			
			// 덤프 수 입력
			st = new StringTokenizer(br.readLine());
			List<Integer> dumpList = new ArrayList<>();
			while (true) {
				try {
					int score = Integer.parseInt(st.nextToken());
					dumpList.add(score);
				} catch (NoSuchElementException e) {
					break;
				}
			}
			// 덤프 수 정렬
			Collections.sort(dumpList);
			
			int maxHeight = calcMaxHeight(dumpList, maxDump);
			int minHeight = calcMinHeight(dumpList, maxDump);
			
			int result = (maxHeight > minHeight) ? (maxHeight - minHeight) : calcZeroOrOne(dumpList);  
			
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
	 * 높이가 높은 Dump부터 maxDump까지 dump를 뺐을 때, dump중에 최대 높이를 계산
	 * 
	 * @param dumpList dump의 배열, 오름차순 정렬되어있음
	 * @param maxDump dump 할수있는 최대 수
	 * @return maxDump만큼 dump를 뺐을 때, 최대 높이의 dump
	 */
	private static int calcMaxHeight(List<Integer> dumpList, int maxDump) {
		int currDumpCnt = 0;
		int range = 1;
		int maxHeight = dumpList.get(dumpList.size() - 1);
		for (int loop = dumpList.size() - 1; loop >= 1 ; loop--) {
			currDumpCnt += (dumpList.get(loop) - dumpList.get(loop - 1)) * range;
			maxHeight = dumpList.get(loop - 1);
			range++;
			if(currDumpCnt > maxDump)
				break;
		}
		
		if(currDumpCnt > maxDump) {
			int remainDump = currDumpCnt - maxDump;
			while(remainDump >= 0) {
				maxHeight++;
				remainDump -= range;
			}
		}
		return maxHeight;
	}
	
	/**
	 * 높이가 낮은 Dump부터 maxDump까지 채웠을 때, dump중에 최소 높이를 계산
	 * 
	 * @param dumpList dump의 배열, 오름차순 정렬되어있음
	 * @param maxDump dump 할수있는 수
	 * @return maxDump만큼 dump했을때, 최소 높이의 dump
	 */
	private static int calcMinHeight(List<Integer> dumpList, int maxDump) {
		int currDumpCnt = 0;
		int range = 1;
		int minHeight = dumpList.get(dumpList.get(0));
		for (int loop = 0; loop < dumpList.size() - 1; loop++) {
			currDumpCnt += (dumpList.get(loop + 1) - dumpList.get(loop)) * range;
			minHeight = dumpList.get(loop + 1);
			range++;
			if(currDumpCnt > maxDump)
				break;
		}
		
		if(currDumpCnt > maxDump) {
			int remainDump = currDumpCnt - maxDump;
			while(remainDump >= 0) {
				minHeight--;
				remainDump -= range;
			}
		}
		return minHeight;
	}
	
	/**
	 * (덤프 리스트의 총합) % (덤프의 수) == 0 이면 같은 높이로 정렬되므로 0을 리턴
	 * 아니라면 1을 리턴
	 * 
	 * @param dumpList 덤프 리스트
	 * @return 모든 dump가 같은 높이로 정렬되면 0, 아니면 1을 리턴
	 */
	private static int calcZeroOrOne(List<Integer> dumpList) {
		// TODO Auto-generated method stub
		int dumpSum = 0;
		for (Integer dump : dumpList) {
			dumpSum += dump;
		}
		
		if(dumpSum % dumpList.size() == 0)
			return 0;
		else
			return 1;
	}
}
