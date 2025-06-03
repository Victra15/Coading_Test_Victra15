package 이용태;

import java.io.*;

public class A034_SWEA_1289_원재의메모리복구 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A034_SWEA_1289_원재의메모리복구.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String memoryStr = br.readLine();
            char currCh = '0';
            int memoryChangeCnt = 0;
            for (int loop = 0; loop < memoryStr.length(); loop++) {
                if (currCh != memoryStr.charAt(loop)) {
                    currCh = memoryStr.charAt(loop);
                    memoryChangeCnt++;
                }
            }

            int result = memoryChangeCnt;

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
