package 이용태;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class A023_SWEA_1217_거듭제곱 {
    private static final int TEST_CASE_CNT = 10;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/이용태/input/A023_SWEA_1217_거듭제곱.txt"));
        Scanner sc = new Scanner(System.in);

        for (int loop = 0; loop < TEST_CASE_CNT; loop++) {
            int testCase = sc.nextInt();
            int mantissa = sc.nextInt();
            int exponent = sc.nextInt();
            int result = calcExponent(mantissa, exponent);

            System.out.println("#" + testCase + " " + result);
        }
    }

    private static int calcExponent(int mantissa, int exponent) {
        if (exponent == 0) return 1;
        return mantissa * calcExponent(mantissa, exponent - 1);
    }
}
