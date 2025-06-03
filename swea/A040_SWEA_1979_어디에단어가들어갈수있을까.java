package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A040_SWEA_1979_어디에단어가들어갈수있을까 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A040_SWEA_1979_어디에단어가들어갈수있을까.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int puzzleSize = Integer.parseInt(st.nextToken());
            int wordLength = Integer.parseInt(st.nextToken());
            String[][] puzzleMap = new String[puzzleSize][puzzleSize];
            for(int loop = 0;loop < puzzleMap.length; loop++){
                puzzleMap[loop] = br.readLine().trim().split(" ");
            }
            String[][] puzzleMapTrans = getTrans(puzzleMap);
            int result = 0;
            for(String[] line : puzzleMap){
                result += findWordSpace(line, wordLength);
            }

            for(String[] line : puzzleMapTrans){
                result += findWordSpace(line, wordLength);
            }

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findWordSpace(String[] line, int wordLength) {
        int wordSpaceCnt = 0;
        int spaceLength = 0;
        for(String space : line){
            if(space.equals("1")){
                spaceLength++;
            }
            else {
                if(spaceLength == wordLength){
                    wordSpaceCnt++;
                }
                spaceLength = 0;
            }
        }
        if(spaceLength == wordLength){
            wordSpaceCnt++;
        }

        return wordSpaceCnt;
    }

    private static String[][] getTrans(String[][] puzzleMap) {
        String[][] trans = new String[puzzleMap.length][puzzleMap[0].length];
        for(int i = 0; i < puzzleMap.length; i++){
            for(int j = 0; j < puzzleMap[0].length; j++){
                trans[i][j] = puzzleMap[j][i];
            }
        }
        return trans;
    }
}
