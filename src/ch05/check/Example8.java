package ch05.check;

public class Example8 {
	public static void main(String[] args) {
		int[][] array = { { 95, 86 }, { 83, 92, 96 }, { 78, 83, 93, 87, 88 } };
		int sum = 0;
		double avg = 0.0;
		int count = 0;

		for (int[] intArr : array) {
			count += intArr.length;
			for (int num : intArr) {
				sum += num;
			}
		}

		avg = (double) sum / count;

		System.out.println("sum : " + sum);
		System.out.println("avg : " + avg);
	}
}