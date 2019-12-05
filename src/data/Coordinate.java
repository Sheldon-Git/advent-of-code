package data;

public class Coordinate {

	public final int x;
	public final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Coordinate create(int x, int y) {
		return new Coordinate(x, y);
	}

	public int getVectorLength() {
		return Math.abs(x) + Math.abs(y);
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

}
