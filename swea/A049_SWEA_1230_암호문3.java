package 이용태;

import java.io.*;
import java.util.*;

public class A049_SWEA_1230_암호문3 {
    private static final int T = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A049_SWEA_1230_암호문3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {
            int passwdLength = Integer.parseInt(br.readLine());
            String[] passwd = br.readLine().trim().split(" ");
            LinkedList<String> passwdList = new LinkedList<>(Arrays.asList(passwd));
            int cmdLength = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < cmdLength; loop++) {
                switch (st.nextToken().charAt(0)) {
                    case 'I':
                        int insertPosition = Integer.parseInt(st.nextToken());
                        int insertPasswdLength = Integer.parseInt(st.nextToken());
                        LinkedList<String> insertPasswd = new LinkedList<>();
                        for (int passwdLoop = 0; passwdLoop < insertPasswdLength; passwdLoop++) {
                            insertPasswd.addLast(st.nextToken());
                        }
                        passwdList.addAll(insertPosition, insertPasswd);
                        break;
                    case 'D':
                        int deletePosition = Integer.parseInt(st.nextToken());
                        int deleteLength = Integer.parseInt(st.nextToken());
                        for(int deleteLoop = 0; deleteLoop < deleteLength; deleteLoop++) {
                            passwdList.remove(deletePosition);
                        }

                        break;
                    case 'A':
                        int addPasswdLength = Integer.parseInt(st.nextToken());
                        LinkedList<String> addPasswd = new LinkedList<>();
                        for (int passwdLoop = 0; passwdLoop < addPasswdLength; passwdLoop++) {
                            addPasswd.addLast(st.nextToken());
                        }
                        passwdList.addAll(addPasswd);
                        break;

                }
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
