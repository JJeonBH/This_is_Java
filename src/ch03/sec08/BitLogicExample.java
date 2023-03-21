package ch03.sec08;

public class BitLogicExample {
	public static void main(String[] args) {
		System.out.println("45 & 25 = " + (45 & 25)); // 비트 논리곱(&) 연산
		System.out.println("45 | 25 = " + (45 | 25)); // 비트 논리합(|) 연산
		System.out.println("45 ^ 25 = " + (45 ^ 25)); // 비트 배타적 논리합(^) 연산
		System.out.println("~45 = " + (~45)); // 비트 논리 부정(~) 연산
		System.out.println("---------------------------");

		byte receiveData = -120;

		// 방법1 : 비트 논리곱 연산으로 Unsigned 정수 얻기
		int unsignedInt1 = receiveData & 255;
		System.out.println(unsignedInt1);

		// 방법2 : 자바 API를 이용해서 Unsigned 정수 얻기
		int unsignedInt2 = Byte.toUnsignedInt(receiveData);
		System.out.println(unsignedInt2);

		int test = 136;
		byte btest = (byte) test;
		System.out.println(btest);
	}
}