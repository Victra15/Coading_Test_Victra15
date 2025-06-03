
package 이용태;

import java.io.*;
import java.util.Arrays;

public class A031_SWEA_1859_백만장자프로젝트 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A031_SWEA_1859_백만장자프로젝트.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int[] marketPrices = new int[N + 1];

            int inputLoop = 1;
            for (String numStr : br.readLine().trim().split(" ")) {
                marketPrices[inputLoop++] = Integer.parseInt(numStr);
            }

            long result = getMaxProfit(marketPrices);

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    private static long getMaxProfit(int[] marketPrices) {
        int[] marketPriceSumArr = Arrays.copyOf(marketPrices, marketPrices.length);

        //누적합 배열 만들기
        Arrays.parallelPrefix(marketPriceSumArr, Integer::sum);

        long profitSum = 0;
        int prevMaxIdx = 0;
        int maxIdx = 0;
        while (maxIdx < marketPrices.length - 1) {
            for (int loop = maxIdx; loop < marketPrices.length; loop++) {
                if (marketPrices[loop] > marketPrices[maxIdx]) {
                    maxIdx = loop;
                }
            }
            profitSum += ((maxIdx - prevMaxIdx) * marketPrices[maxIdx]) - (marketPriceSumArr[maxIdx] - marketPriceSumArr[prevMaxIdx]);
            prevMaxIdx = maxIdx;
            maxIdx++;
        }

        return profitSum;
    }
}
