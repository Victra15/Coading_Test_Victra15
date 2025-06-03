package 이용태;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A064_SWEA_1952_수영장 {
    private static final int ONE_DAY = 0;
    private static final int ONE_MONTH = 1;
    private static final int THREE_MONTH = 2;
    private static final int ONE_YEAR = 3;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A064_SWEA_1952_수영장.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int[] passPrice = new int[4];
            int[] accessDate = new int[12];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < passPrice.length; loop++) {
                passPrice[loop] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < accessDate.length; loop++) {
                accessDate[loop] = Integer.parseInt(st.nextToken());
            }

            // 이 위는 입력

            int result = getMinPrice(passPrice, accessDate, 0, 0); // 핵심로직

            // 이 아래는 출력

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }

    /**
     * month값과, currPrice를 이용해서 계산
     *
     * @param passPrice  이용권 가격 배열
     * @param accessDate  각 달에 이용한 일수
     * @param currPrice  현재까지 계산된 가격
     * @param month  현재까지 계산된 달수
     * @return 계산된 최솟값 return
     */
    private static int getMinPrice(int[] passPrice, int[] accessDate, int currPrice, int month) {
        int minPrice = Integer.MAX_VALUE; // 계산된 가격중 최솟값 계산

        if (month >= 12) // 기저 조건: month가 12라면 모든 달에대한 이용권 가격을 계산한 것이므로, 계산 결과 반환
            return currPrice;

        /*
         1년 이용권을 사용했을때 금액 계산 - (현재까지 계산된 금액 + 연 이용권 금액, 현재까지 계산한 달수 + 12)
         사실상 현재까지 계산된 금액은 0이고, 현재까지 계산한 달수는 0일 것이므로
         (0 + 연 이용권 금액, 0 + 12) 으로 봐도 무방하다.
         */
        if (month == 0)
            minPrice = Math.min(minPrice, getMinPrice(passPrice, accessDate, passPrice[ONE_YEAR], 12));


        if (month < 12) {
            /*
            현재 달에서 1달 이용했을때 금액 계산
            한달동안 이용한 금액 = (한달 이용권 금액과 한달 동안 일일 이용권으로 이용했을 때 금액중 작은거)
            (현재까지 계산된 금액 + 한달동안 이용한 금액, 현재까지 계산한 달수 + 1) 으로 계산
            */
            int monthPrice = Math.min(passPrice[ONE_MONTH], passPrice[ONE_DAY] * accessDate[month]);
            minPrice = Math.min(minPrice, getMinPrice(passPrice, accessDate, currPrice + monthPrice, month + 1));

            /*
            3달 이용권을 사용했을때 금액 계산 - (현재까지 계산된 금액 + 세달 이용권 금액, 현재까지 계산한 달수 + 3)
            예시: 5달차까지 낸 총 비용을 계산했을때, 300원 이었다면,
             세달 이용권을 사용한 후의 계산은 8달차까지 낸 총 비용이 되고,
             이는 300원 + 3달 이용권 금액
             */
            minPrice = Math.min(minPrice, getMinPrice(passPrice, accessDate, currPrice + passPrice[THREE_MONTH], month + 3));
        }
        return minPrice; //계산된 minPrice 반환
    }
}