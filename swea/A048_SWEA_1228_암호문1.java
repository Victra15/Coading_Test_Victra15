package 이용태;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class A048_SWEA_1228_암호문1 {
    private static final int T = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A048_SWEA_1228_암호문1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {
            int originPasswdLength = Integer.parseInt(br.readLine());
            String[] originPasswd = br.readLine().trim().split(" ");
            int cmdLength = Integer.parseInt(br.readLine());
            String[] cmd = br.readLine().trim().split("I");
            LinkedList<String> passwdList = new LinkedList<>();
            Collections.addAll(passwdList, originPasswd);

            for (int loop = 1; loop <= cmdLength; loop++) {
                String[] cmdArray = cmd[loop].trim().split(" ");
                int insertPos = Integer.parseInt(cmdArray[0]);
                int insertLength = Integer.parseInt(cmdArray[1]);
                passwdList.addAll(insertPos, Arrays.asList(cmdArray).subList(2, cmdArray.length));
            }

            passwdList.stream().limit(10).forEach(s->{sb.append(s).append(' ');});

            String result = sb.toString().trim();
            sb.setLength(0);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }
}
