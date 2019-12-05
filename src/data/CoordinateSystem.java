package data;

import java.util.ArrayList;
import java.util.List;

public class CoordinateSystem {

	private final int max;
	private final State[][] data;

	public CoordinateSystem(int max) {
		this.max = max;
		data = new State[1 + 2 * max][1 + 2 * max];
		init(State.S0);
	}

	private void init(State initialState) {
		for (int j = 0; j < data.length; j++) {
			for (int k = 0; k < data.length; k++) {
				data[j][k] = initialState;
			}
		}
	}

	public State getStateAt(int x, int y) {
		validateCoordinates(x, y);
		int j = x + max;
		int k = y + max;
		return data[j][k];
	}

	private void setStateAt(int x, int y, State state) {
		validateCoordinates(x, y);
		int j = x + max;
		int k = y + max;
		data[j][k] = state;
	}

	public void updateStateAt(int x, int y, State updateState) {
		// System.out.println("updateStateAt[" + x + "," + y + "]");
		if (updateState == State.S0 || updateState == State.SN) {
			throw new IllegalArgumentException("invalid update state: " + updateState);
		}
		final State currentState = getStateAt(x, y);
		if (currentState == State.SN) {
			throw new IllegalStateException("current state is already " + currentState);
		}
		if (currentState == updateState) {
			// just don't change the current state
			return;
		}
		if (currentState == State.S0) {
			setStateAt(x, y, updateState);
		} else {
			setStateAt(x, y, State.SN);
		}
	}

	private void validateCoordinates(int x, int y) {
		if (x > max | x < -max) {
			throw new IllegalArgumentException("invalid x: " + x);
		}
		if (y > max | y < -max) {
			throw new IllegalArgumentException("invalid y: " + y);
		}
	}

	public void print() {
		for (int y = max; y >= -max; y--) {
			for (int x = -max; x <= max; x++) {
				if (x == 0 && y == 0) {
					System.out.print("ZZ ");
				} else {
					System.out.print(getStateAt(x, y) + " ");
				}
			}
			System.out.println();
		}
	}

	public List<Coordinate> findCoordinatesWith(State state) {
		List<Coordinate> result = new ArrayList<>();
		for (int y = max; y >= -max; y--) {
			for (int x = -max; x <= max; x++) {
				if (getStateAt(x, y) == state) {
					result.add(Coordinate.create(x, y));
				}
			}
		}
		return result;
	}

	public void applyWire(Wire wire, State updateState) {
		int x = 0;
		int y = 0;
		for (Vector vector : wire.getPath()) {
			Coordinate point1;
			Coordinate point2;
			switch (vector.direction) {
			case UP:
				point1 = Coordinate.create(x, y + 1);
				point2 = Coordinate.create(x, y + vector.length);
				y += vector.length;
				break;
			case RIGHT:
				point1 = Coordinate.create(x + 1, y);
				point2 = Coordinate.create(x + vector.length, y);
				x += vector.length;
				break;
			case DOWN:
				point1 = Coordinate.create(x, y - 1);
				point2 = Coordinate.create(x, y - vector.length);
				y -= vector.length;
				break;
			case LEFT:
				point1 = Coordinate.create(x - 1, y);
				point2 = Coordinate.create(x - vector.length, y);
				x -= vector.length;
				break;
			default:
				throw new IllegalArgumentException("direction not supported: " + vector.direction);
			}
			applyVector(point1, point2, updateState);
		}
	}

	private void applyVector(Coordinate point1, Coordinate point2, State updateState) {
		if (point1.x != point2.x && point1.y != point2.y) {
			throw new IllegalStateException("point1=" + point1 + ";point2=" + point2);
		}
		if (point1.x == point2.x) {
			final int x = point1.x;
			if (point1.y < point2.y) {
				for (int y = point1.y; y <= point2.y; y++) {
					updateStateAt(x, y, updateState);
				}
			}
			if (point1.y > point2.y) {
				for (int y = point1.y; y >= point2.y; y--) {
					updateStateAt(x, y, updateState);
				}
			}
		}
		if (point1.y == point2.y) {
			final int y = point1.y;
			if (point1.x < point2.x) {
				for (int x = point1.x; x <= point2.x; x++) {
					updateStateAt(x, y, updateState);
				}
			}
			if (point1.x > point2.x) {
				for (int x = point1.x; x >= point2.x; x--) {
					updateStateAt(x, y, updateState);
				}
			}
		}
	}

}
