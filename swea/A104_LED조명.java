package 이용태;

import java.io.*;

public class A104_LED조명 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A104_LED조명.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] LEDSwitches = new boolean[N];
            String line = br.readLine();

            int inputIdx = 0;
            for (int loop = 0; loop < line.length(); loop++) {
                char c = line.charAt(loop);
                if (c != ' ') {
                    switch (c) {
                        case '0':
                            LEDSwitches[inputIdx++] = false;
                            break;
                        case '1':
                            LEDSwitches[inputIdx++] = true;
                            break;
                    }
                }
            }

            int result = findMinSwitchAction(LEDSwitches);
            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static int findMinSwitchAction(boolean[] ledSwitches) {
        int actionCnt = 0;
        for (int loop = 1; loop <= ledSwitches.length; loop++) {
            if(ledSwitches[loop - 1]) {
                for(int multLoop = 1; multLoop * loop <= ledSwitches.length; multLoop++) {
                    int index = multLoop * loop - 1;
                    ledSwitches[index] = !ledSwitches[index];
                }
                actionCnt++;
            }
        }
        return actionCnt;
    }
}
