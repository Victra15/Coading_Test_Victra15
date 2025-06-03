package 이용태;

import java.io.*;

public class A037_SWEA_1926_간단한369게임 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A037_SWEA_1926_간단한369게임.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int loop = 1; loop <= N; loop++) {
            sb.append(get369String(loop));
            if(loop < N)
                sb.append(' ');
        }
        sb.append('\n');
        bw.write(sb.toString());
        bw.flush();
        sb.setLength(0);
        bw.close();
        br.close();
    }

    private static String get369String(int num) {
        StringBuilder sb = new StringBuilder();
        int temp = num;
        while(temp != 0) {
            int digitNum = temp % 10;
            if(digitNum % 3 == 0 && digitNum != 0)
                sb.append('-');
            temp /= 10;
        }
        if(sb.length() == 0)
            sb.append(num);
        return sb.toString();
    }
}
