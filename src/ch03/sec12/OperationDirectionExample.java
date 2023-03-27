package ch03.sec12;

public class OperationDirectionExample {
	public static void main(String[] args) {
		int var1 = 1;
		int var2 = 3;
		int var3 = 2;
		int result1 = var1 + var2 * var3;
		System.out.println("result1 = " + result1);

		int result2 = (var1 + var2) * var3; // 괄호 부분의 연산은 최우선순위를 갖는다
		System.out.println("result2 = " + result2);
	}
}