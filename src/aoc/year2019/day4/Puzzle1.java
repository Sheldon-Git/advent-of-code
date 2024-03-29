package aoc.year2019.day4;

import java.util.ArrayList;
import java.util.List;

public class Puzzle1 {

	private static final int MIN = 234208;
	private static final int MAX = 765869;

	public static void main(String[] args) {
		List<Integer> result = new ArrayList<>();
		for (int i = MIN; i <= MAX; i++) {
			if (isValid(i)) {
				result.add(i);
			}
		}
		System.out.println(result.size());
	}

	private static boolean isValid(final int number) {
		boolean has2SameAdjacentDigits = has2SameAdjacentDigits(number);
		boolean hasNoDecreasingDigits = !hasDecreasingDigits(number);
		return has2SameAdjacentDigits && hasNoDecreasingDigits;
	}

	private static boolean hasDecreasingDigits(final int number) {
		int currentNumber = number;
		int previousDigit = number % 10;
		while (currentNumber > 0) {
			int digit = currentNumber % 10;
			if (digit < previousDigit) {
				return true;
			}
			previousDigit = digit;
			currentNumber = currentNumber / 10;
		}
		return false;
	}

	private static boolean has2SameAdjacentDigits(final int number) {
		int previousDigit = number % 10;
		int currentNumber = number / 10;
		while (currentNumber > 0) {
			int digit = currentNumber % 10;
			if (digit == previousDigit) {
				return true;
			}
			previousDigit = digit;
			currentNumber = currentNumber / 10;
		}
		return false;
	}

}
