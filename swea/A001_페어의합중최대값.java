package 이용태;

import java.util.Arrays;

class NotEvenArrayException extends Exception {
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "선언된 배열의 크기가 짝수가 아닙니다.";
	}
}

public class A001_페어의합중최대값 {
	public static void main(String[] args) throws NotEvenArrayException {
		// 배열 선언, 입력값이 int 범위를 넘어서므로 long으로 선언
		long[] numArr = { 2, 19, 1, 90, 24, 51, 4, 77 };

		if (numArr.length % 2 == 1)
			throw new NotEvenArrayException();

		// 배열 정렬
		Arrays.sort(numArr);

		// 최대 페어 합 출력
		printMaxPairNum(numArr);
	}

	/**
	 * 숫자 배열에서 최대 페어 합을 출력하는 함수
	 *
	 * @param numArr 입력할 숫자 배열
	 */
	private static void printMaxPairNum(long[] numArr) {
		// 최대 페어 합을 저장할 변수 선언
		long maxPairSum = 0;

		for (int loop = 0; loop < numArr.length / 2; loop++) {
			// 페어의 합 계산
			long pairSum = numArr[loop] + numArr[numArr.length - 1 - loop];

			// 현재 maxPairSum보다 pairSum이 더 크면 maxPairSum을 pairSum값으로 초기화
			if (maxPairSum < pairSum)
				maxPairSum = pairSum;
		}
		System.out.println(maxPairSum);
	}

}
