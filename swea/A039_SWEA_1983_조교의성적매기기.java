package 이용태;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A039_SWEA_1983_조교의성적매기기 {
//    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A039_SWEA_1983_조교의성적매기기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        String[] gradeStr = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentCnt = Integer.parseInt(st.nextToken());
            int studentId = Integer.parseInt(st.nextToken());

            int[] totalScoreArray = new int[studentCnt];
            for (int loop = 0; loop < studentCnt; loop++) {
                totalScoreArray[loop] = getTotalScore(br);
            }
            int rank = 0;

            for (int loop = 0; loop < studentCnt; loop++) {
                if(totalScoreArray[loop] > totalScoreArray[studentId - 1]) {
                    rank++;
                }
            }

            int equalGradeStudentSize = studentCnt / 10;
            sb.append('#').append(testCase).append(' ').append(gradeStr[rank / equalGradeStudentSize]).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int getTotalScore(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int middleTestScore = Integer.parseInt(st.nextToken());
        int finalTestScore = Integer.parseInt(st.nextToken());
        int subjectScore = Integer.parseInt(st.nextToken());

        int totalScore = (middleTestScore * 35) + (finalTestScore * 45) + (subjectScore * 20);
        return totalScore;
    }
}
