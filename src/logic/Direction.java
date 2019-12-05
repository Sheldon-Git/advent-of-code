package logic;

public enum Direction {

	UP('U'), RIGHT('R'), DOWN('D'), LEFT('L');

	private final char raw;

	private Direction(char c) {
		this.raw = c;
	}

	public static Direction valueOf(char c) {
		for (Direction d : values()) {
			if (d.raw == c) {
				return d;
			}
		}
		throw new IllegalArgumentException("Direction '" + c + "' unknown");
	}

}
