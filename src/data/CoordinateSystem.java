package data;

import java.util.Arrays;

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

	private void print() {
		for (int y = max; y >= -max; y--) {
			for (int x = -max; x <= max; x++) {
				System.out.print(getValueAt(x, y) + " ");
			}
			System.out.println();
		}
	}

	private State getValueAt(int x, int y) {
		validateCoordinates(x, y);
		int j = x + max;
		int k = y + max;
		return data[j][k];
	}

	private void setValueAt(int x, int y, State state) {
		validateCoordinates(x, y);
		int j = x + max;
		int k = y + max;
		data[j][k] = state;
	}

	private void validateCoordinates(int x, int y) {
		if (x > max | x < -max) {
			throw new IllegalArgumentException("invalid x: " + x);
		}
		if (y > max | y < -max) {
			throw new IllegalArgumentException("invalid y: " + y);
		}
	}

}
