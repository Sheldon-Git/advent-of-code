package aoc.year2019.day4;

public class Puzzle {

	private static final int MIN = 234208;
	private static final int MAX = 765869;

	public static void main(String[] args) {
		System.out.println("begin");
		for (int i = MIN; i <= MAX; i++) {
			if (isValid(i)) {
				System.out.println(i);
			}
		}
		System.out.println("end");
	}

	private static boolean isValid(int i) {
		boolean has2SameAdjacentDigits = has2SameAdjacentDigits(i);
		boolean hasNoDecreasingDigits = !hasDecreasingDigits(i);
		return has2SameAdjacentDigits && hasNoDecreasingDigits;
	}

	private static boolean hasDecreasingDigits(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean has2SameAdjacentDigits(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
