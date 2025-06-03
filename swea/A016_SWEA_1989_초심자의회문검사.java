package 이용태;

import java.io.*;

public class A016_SWEA_1989_초심자의회문검사 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A016_SWEA_1989_초심자의회문검사.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase <= T; testCase++) {
            String sentence = br.readLine().trim();
            sb.append('#').append(testCase).append(' ').append(isPalindrome(sentence)).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        br.close();
        bw.close();
    }

    private static int isPalindrome(String sentence) {
        int leftIdx = sentence.length() - 1;
        int rightIdx = 0;

        while (rightIdx < leftIdx) {
            if (sentence.charAt(rightIdx++) != sentence.charAt(leftIdx--))
                return 0;
        }
        return 1;
    }
}
