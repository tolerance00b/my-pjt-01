package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	public int add(String num) {
		if (num == null || num.isEmpty()) {
			return 0;
		}

		return Integer.parseInt(num);
	}

	public int addComma(String num) {
		String[] numbers = num.split(",");
		int returnNum = 0;

		for (int i = 0; i < numbers.length; i++) {
			returnNum += Integer.parseInt(numbers[i]);
		}

		return returnNum;
	}

	public int addColon(String num) {
		String[] numbers = num.split(":");
		int returnNum = 0;

		for (int i = 0; i < numbers.length; i++) {
			returnNum += Integer.parseInt(numbers[i]);
		}

		return returnNum;
	}

	public int addCustom(String num) {
		int returnNum = 0;
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(num);
		m.find();
		String customDelimeter = m.group(1);
		String[] tokens = m.group(2).split(customDelimeter);
		if (!tokens[0].isEmpty()) {
			for (int i = 0; i < tokens.length; i++) {
				returnNum += Integer.parseInt(tokens[i]);
			}
		}
		return returnNum;

	}

	public boolean addMinus(String num) {

		System.out.println(Integer.parseInt(num) < 0);
		if (Integer.parseInt(num) < 0) {
			throw new RuntimeException();
		}

		return true;
	}

}
