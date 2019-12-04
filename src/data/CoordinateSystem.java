package data;

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
				System.out.print(getStateAt(x, y) + " ");
			}
			System.out.println();
		}
	}

	private State getStateAt(int x, int y) {
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

	private void updateStateAt(int x, int y, State updateState) {
		if (updateState == State.S0 || updateState == State.SN) {
			throw new IllegalArgumentException("invalid update state: " + updateState);
		}
		validateCoordinates(x, y);
		final int j = x + max;
		final int k = y + max;
		final State currentState = data[j][k];
		if (currentState == State.SN) {
			throw new IllegalStateException("current state is already " + currentState);
		}
		if (currentState == updateState) {
			throw new IllegalStateException(currentState + " == " + updateState);
		}
		data[j][k] = updateState;
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
