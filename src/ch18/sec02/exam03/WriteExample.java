package ch18.sec02.exam03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream("C:/Temp/test3.db");

			byte[] array = { 10, 20, 30, 40, 50 };

			// 1번 인덱스부터 3개까지만 출력
			os.write(array, 1, 3);

			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}