package aoc.year2019.day4;

import java.util.ArrayList;
import java.util.List;

public class Puzzle2 {

	private static final int MIN = 234208;
	private static final int MAX = 765869;

	public static void main(String[] args) {
		System.out.println(hasExactly2SameAdjacentDigits(123455));
		System.exit(1);
		List<Integer> result = new ArrayList<>();
		for (int i = MIN; i <= MAX; i++) {
			if (isValid(i)) {
				result.add(i);
			}
		}
		System.out.println(result.size());
	}

	private static boolean isValid(final int number) {
		boolean has2SameAdjacentDigits = hasExactly2SameAdjacentDigits(number);
		boolean hasNoDecreasingDigits = !hasDecreasingDigits(number);
		return has2SameAdjacentDigits && hasNoDecreasingDigits;
	}

	private static boolean hasExactly2SameAdjacentDigits(final int number) {
    ArrayList<Integer> group = new ArrayList<>();
    int currentNumber = number;
    do {
      int digit = currentNumber % 10 ;
      if (!group.isEmpty() && group.get(0).intValue() != digit) {
        if (group.size() == 2) {
          return true;
        }
        group.clear();
      }
      group.add(digit);
      currentNumber = currentNumber / 10;
    } while (currentNumber > 0);
    return group.size() == 2;
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

}
