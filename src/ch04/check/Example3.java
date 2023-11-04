package ch04.check;

public class Example3 {
	public static void main(String[] args) {
		int sum = 0;
		int i;

		for (i = 1; i <= 100; i++) {
			if (i % 3 != 0) {
				continue;
			}
			sum += i;
		}

		System.out.println("1~" + (i - 1) + "까지의 정수 중에서 3의 배수의 총합 : " + sum);
	}
}