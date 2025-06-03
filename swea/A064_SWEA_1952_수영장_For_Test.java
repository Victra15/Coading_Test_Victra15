package 이용태;

import java.io.*;
import java.util.StringTokenizer;

public class A064_SWEA_1952_수영장_For_Test {
    private static final int ONE_DAY = 0;
    private static final int ONE_MONTH = 1;
    private static final int THREE_MONTH = 2;
    private static final int ONE_YEAR = 3;

    private static class Result{
        int minPrice;
        int[] minPricePlan = new int[12];

        Result(int minPrice, int[] minPricePlan){
            this.minPrice = minPrice;
            this.minPricePlan = minPricePlan.clone();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A064_SWEA_1952_수영장.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int[] passPrice = new int[4];
            int[] accessPlan = new int[12];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < 4; loop++) passPrice[loop] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int loop = 0; loop < 12; loop++) accessPlan[loop] = Integer.parseInt(st.nextToken());

            Result result = getMinPrice(passPrice, accessPlan, new Result(0, new int[12]), 0);
            for(int loop = 0; loop < result.minPricePlan.length; loop++){
                String passName = "";
                int monthPrice = 0;
                switch (result.minPricePlan[loop]) {
                    case ONE_DAY:
                        monthPrice = accessPlan[loop] * passPrice[ONE_DAY];
                        passName = "1일권";
                        break;
                    case ONE_MONTH:
                        passName = "1달권";
                        monthPrice = passPrice[ONE_MONTH];
                        break;
                    case THREE_MONTH:
                        passName = "3달권";
                        monthPrice = passPrice[THREE_MONTH];
                        break;
                    case ONE_YEAR:
                        passName = "1년권";
                        monthPrice = passPrice[ONE_YEAR];
                        break;
                }
                if(accessPlan[loop] == 0 && result.minPricePlan[loop] == ONE_DAY){
                    sb.append(loop+1).append("월 사용 x").append('\n');
                }
                else{
                    sb.append(loop+1).append("월 ").append(accessPlan[loop]).append("일 ").append(passName).append(" 사용=>").append(monthPrice).append('\n');
                }
            }
            sb.append("sum: ").append(result.minPrice).append('\n');
            sb.append("-------------------------------").append('\n');
            sb.append('#').append(testCase).append(' ').append(result.minPrice).append('\n').append('\n');
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
        }
        bw.close();
        br.close();
    }


    private static Result getMinPrice(int[] passPrice, int[] accessDate, Result currPlan, int month) {
        Result plans = new Result(Integer.MAX_VALUE, currPlan.minPricePlan);

        if (month >= 12)
            return currPlan;

        if (month == 0){
            Result newPlan = new Result(currPlan.minPrice + passPrice[ONE_YEAR], currPlan.minPricePlan);
            for(int loop = month; loop < month + 12; loop++){
                newPlan.minPricePlan[loop] = ONE_YEAR;
            }

            Result minPlan = getMinPrice(passPrice, accessDate, newPlan, month + 12);
            if(plans.minPrice > minPlan.minPrice){
                plans = minPlan;
            }
        }


        if (month < 12) {
            Result newPlan = new Result(currPlan.minPrice + passPrice[ONE_DAY] * accessDate[month], currPlan.minPricePlan);
            for(int loop = month; loop < month + 1; loop++){
                newPlan.minPricePlan[loop] = ONE_DAY;
            }
            Result minPlan = getMinPrice(passPrice, accessDate, newPlan, month + 1);
            if(plans.minPrice > minPlan.minPrice){
                plans = minPlan;
            }

            newPlan = new Result(currPlan.minPrice + passPrice[ONE_MONTH], currPlan.minPricePlan);
            for(int loop = month; loop < month + 1; loop++){
                newPlan.minPricePlan[loop] = ONE_MONTH;
            }
            minPlan = getMinPrice(passPrice, accessDate, newPlan, month + 1);
            if(plans.minPrice > minPlan.minPrice){
                plans = minPlan;
            }

            newPlan = new Result(currPlan.minPrice + passPrice[THREE_MONTH], currPlan.minPricePlan);
            for(int loop = month; loop < month + 3; loop++){
                if(loop >= minPlan.minPricePlan.length)
                    break;
                newPlan.minPricePlan[loop] = THREE_MONTH;
            }

            minPlan = getMinPrice(passPrice, accessDate, newPlan, month + 3);
            if(plans.minPrice > minPlan.minPrice){
                plans = minPlan;
            }
        }

        return plans;
    }
}