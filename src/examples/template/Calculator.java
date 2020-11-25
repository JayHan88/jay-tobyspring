package examples.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

	public <T> T lineReadTemplate(String filepath, LineCallBack<T> callback, T initVal) throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public Integer calcSum(String filepath) throws IOException {
		LineCallBack<Integer> sumCallback = new LineCallBack<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, sumCallback, 0);
	}

	public Integer calcMultiply(String filepath) throws IOException {
		LineCallBack<Integer> multiplyCallback = new LineCallBack<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, multiplyCallback, 1);
	}

	public String concatenate(String filepath) throws IOException {
		LineCallBack<String> concatenateCallback = new LineCallBack<String>() {
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		return lineReadTemplate(filepath, concatenateCallback, "");
	}

	public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filepath));
			int result = callback.doSomethingWithReader(br);
			return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
