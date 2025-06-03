package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class A063_SWEA_7733_치즈도둑 {

	static class Position{
		int x;
		int y;

		public Position(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A063_SWEA_7733_치즈도둑.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int[][] cheeseMap = new int[N][N];
            for (int rowLoop = 0; rowLoop < N; rowLoop++) {
                String[] line = br.readLine().trim().split(" ");
                for (int colLoop = 0; colLoop < N; colLoop++) {
                    cheeseMap[rowLoop][colLoop] = Integer.parseInt(line[colLoop]);
                }
            }

            int result = getCheeseLoaf(cheeseMap);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getCheeseLoaf(int[][] cheeseMap) {
        int maxLoafCnt = 0;
        for (int day = 0; day <= 100; day++) {
            int loafCnt = getCheeseLoafatDay(cheeseMap, day);
            if (loafCnt == 0) break;
            maxLoafCnt = Math.max(maxLoafCnt, loafCnt);
        }
		return maxLoafCnt;
    }

	private static int getCheeseLoafatDay(int[][] cheeseMap, int day) {
		for(int rowLoop = 0; rowLoop < cheeseMap.length; rowLoop++) {
			for(int colLoop = 0; colLoop < cheeseMap.length; colLoop++) {
				if(cheeseMap[rowLoop][colLoop] == day)
					cheeseMap[rowLoop][colLoop] = 0;
			}
		}

		int loafCnt = 0;
		boolean[][] visited = new boolean[cheeseMap.length][cheeseMap[0].length];

		for(int rowLoop = 0; rowLoop < cheeseMap.length; rowLoop++) {
			for(int colLoop = 0; colLoop < cheeseMap.length; colLoop++) {
				if(!visited[rowLoop][colLoop] && cheeseMap[rowLoop][colLoop] != 0){
					loafCheck(cheeseMap, visited, new Position(rowLoop, colLoop));
					loafCnt++;
				}
			}
		}
		return loafCnt;
	}

	private static void loafCheck(int[][] cheeseMap, boolean[][] visited, Position start) {
		Stack<Position> visitStack = new Stack<Position>();
		visitStack.push(start);
		while (!visitStack.isEmpty()) {
			Position currPos = visitStack.pop();
			visit(visitStack,cheeseMap, visited, new Position(currPos.x + 1, currPos.y));
			visit(visitStack,cheeseMap, visited, new Position(currPos.x, currPos.y + 1));
			visit(visitStack,cheeseMap, visited, new Position(currPos.x - 1, currPos.y));
			visit(visitStack,cheeseMap, visited, new Position(currPos.x, currPos.y - 1));
		}
	}

	private static void visit(Stack<Position> visitStack, int[][] cheeseMap, boolean[][] visited, Position position) {
		try {
			if(!visited[position.x][position.y] && cheeseMap[position.x][position.y] != 0){
				visited[position.x][position.y] = true;
				visitStack.push(position);
			}

		}catch (ArrayIndexOutOfBoundsException e){

		}
	}
}