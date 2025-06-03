package 이용태;

import java.io.*;

public class A018_SWEA_1216_회문2 {
    private static final int TEST_CASE_CNT = 10;
    private static final int STRING_MAP_SIZE = 100;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A018_SWEA_1216_회문2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int loop = 0; loop < TEST_CASE_CNT; loop++) {
            int testCase = Integer.parseInt(br.readLine());
            int maxPalLen = 0;
            String[] stringMap = new String[STRING_MAP_SIZE];
            for (int inputLoop = 0; inputLoop < STRING_MAP_SIZE; inputLoop++) {
                stringMap[inputLoop] = br.readLine();
                maxPalLen = findMaxPalLen(stringMap[inputLoop], maxPalLen);
            }

            String[] stringMapTrans = TransStringMap(stringMap);

            for (int strMapLoop = 0; strMapLoop < STRING_MAP_SIZE; strMapLoop++) {
                maxPalLen = findMaxPalLen(stringMapTrans[strMapLoop], maxPalLen);
            }

            sb.append('#').append(testCase).append(' ').append(maxPalLen).append("\n");
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findMaxPalLen(String s, int maxPalLen) {
        int palLen = s.length();
        StringBuilder sb = new StringBuilder();
        while(palLen > maxPalLen) {
            sb.append(s);
            sb.delete(palLen, s.length());
            for (int loop = 0; loop < s.length() - palLen; loop++) {
                if(isPal(sb.toString())){
                    return palLen;
                }
                sb.deleteCharAt(0);
                sb.append(s.charAt(palLen + loop));
                if(isPal(sb.toString()))
                    return palLen;
            }
            sb.setLength(0);
            palLen--;
        }

        return maxPalLen;
    }

    private static boolean isPal(String string) {
        String reversedString = new StringBuilder(string).reverse().toString();

        return reversedString.equals(string);
    }

    private static String[] TransStringMap(String[] stringMap) {
        String[] stringMapTrans = new String[STRING_MAP_SIZE];
        StringBuilder sb = new StringBuilder();
        for (int cLoop = 0; cLoop < STRING_MAP_SIZE; cLoop++) {
            for (int rLoop = 0; rLoop < STRING_MAP_SIZE; rLoop++) {
                sb.append(stringMap[rLoop].charAt(cLoop));
            }
            stringMapTrans[cLoop] = sb.toString();
            sb.setLength(0);
        }
        return stringMapTrans;
    }
}
