package 이용태;

import java.io.*;

public class A017_SWEA_1213_String {
    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A017_SWEA_1213_String.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int loop = 0; loop < TEST_CASE_CNT; loop++) {
            int testCase = Integer.parseInt(br.readLine());
            String findString = br.readLine();
            String targetString = br.readLine();

            sb.append(targetString);
            int findIndex = sb.indexOf(findString);
            int findCnt = 0;
            while(findIndex != -1){
                findCnt++;
                findIndex = sb.indexOf(findString, findIndex + 1);
            }
            sb.setLength(0);
            sb.append('#').append(testCase).append(' ').append(findCnt).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
