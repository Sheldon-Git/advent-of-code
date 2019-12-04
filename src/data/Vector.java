package data;

public class Vector {

	private final String raw;

	public final Direction direction;
	public final int length;

	public Vector(String raw) {
		this.raw = raw;
		this.direction = Direction.valueOf(raw.charAt(0));
		this.length = Integer.parseInt(raw.substring(1));
	}

	@Override
	public String toString() {
		return raw;
	}

}
