package 이용태;

class NotOddNumException extends Exception{
    @Override
    public String getMessage() {
        return "N이 홀수가 아닙니다.";
    }
}
public class A002_착한마름모만들기 {
    public static void main(String[] args) throws NotOddNumException {
        int N = 11;

        if (N % 2 == 0)
            throw new NotOddNumException();

        // rowLoop = 행 반복, colLoop = 열 반복
        for (int rowLoop = 0; rowLoop < N; rowLoop++) {
            for (int colLoop = 0; colLoop < N; colLoop++) {
                if (isPlusSign(N, rowLoop + 1, colLoop + 1))
                    System.out.print('+');
                else
                    System.out.print(' ');
            }
            System.out.println();
        }
    }

    /**
     * 행 위치와 열 위치를 입력받아 해당 위치가 마름모 영역인지 판단하는 함수
     *
     * @param N 정사각형 한 변의 길이
     * @param r 행 위치
     * @param c 열 위치
     * @return 해당 위치가 마름모 영역이면 true, 마름모 영역이 아니면 false
     */
    private static boolean isPlusSign(int N, int r, int c) {
        /*
         * N은 홀수이므로 N = 2n - 1 (n은 자연수)
         * 각 열의 중앙 위치 mid = n = (N / 2) + 1
         * 마름모 대각선 길이 N - 2 = 2(mid - 1) - 1
         * 각 열의 마름모 영역 계산
         * mid - range < c < mid + range ( range = (mid - 1) - |mid - r| )
         *
         * r = 1 일 때 range = 0;
         * r = 2 일 때, range = 1;
         * ...
         * r = mid 일 때, range = mid - 1;
         * ..
         * r = N = 2mid - 1 일 때 range = 0;
         */

        int mid = (N/2) + 1;
        int range = (mid - 1) - Math.abs(mid - r);
        if(c > mid - range && c < mid + range)
            return false;
        else
            return true;

    }
}
