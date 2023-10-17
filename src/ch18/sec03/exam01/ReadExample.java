package ch18.sec03.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
	public static void main(String[] args) {
		try {
			// 데이터 출발지를 test1.db로 하는 바이트 입력 스트림 생성
			InputStream is = new FileInputStream("C:/Temp/test1.db");

			while (true) {
				// 1byte씩 읽기
				int data = is.read();

				// 파일 끝에 도달했을 경우
				if (data == -1) {
					break;
				}

				System.out.println(data);
			}

			// 입력 스트림을 닫고 사용 메모리 해제
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}