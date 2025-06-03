package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A043_SWEA_1940_가랏RC카 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A043_SWEA_1940_가랏RC카.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int cmdCnt = Integer.parseInt(br.readLine());
            int velocity = 0;
            int displacement = 0;
            for (int loop = 0; loop < cmdCnt; loop++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String token = st.nextToken();
                if(token.equals("1"))
                    velocity += Integer.parseInt(st.nextToken());
                if(token.equals("2"))
                    velocity -= Integer.parseInt(st.nextToken());
                if(velocity < 0)
                    velocity = 0;
                displacement += velocity;
            }
            int result = displacement;

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
