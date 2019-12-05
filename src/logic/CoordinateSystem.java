package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CoordinateSystem {

	private final Map<Integer, Map<Integer, CoordinateValue>> data;

	public CoordinateSystem() {
		data = new HashMap<>(999);
	}

	public CoordinateValue getValueAt(int x, int y) {
		Map<Integer, CoordinateValue> dataX = data.get(x);
		if (dataX == null) {
			dataX = new HashMap<>();
			data.put(x, dataX);
		}
		CoordinateValue dataY = dataX.get(y);
		if (dataY == null) {
			dataY = new CoordinateValue(State.S0);
			dataX.put(y, dataY);
		}
		return dataY;
	}

	public void updateStateAt(Coordinate point, State updateState, int length) {
		// System.out.println("updateStateAt[" + x + "," + y + "]");
		if (updateState == State.S0 || updateState == State.SN) {
			throw new IllegalArgumentException("invalid update state: " + updateState);
		}
		final CoordinateValue value = getValueAt(point.x, point.y);
		if (value.getState() == State.SN) {
			throw new IllegalStateException("current state is already " + value);
		}
		value.addLengthPerState(length, updateState);
		if (value.getState() == State.S0) {
			value.setState(updateState);
			return;
		}
		if (value.getState() != updateState) {
			value.setState(State.SN);
		}
	}

//	TODO reimplement/adjust
//	public void print() {
//		for (int y = max; y >= -max; y--) {
//			for (int x = -max; x <= max; x++) {
//				if (x == 0 && y == 0) {
//					System.out.print("ZZ ");
//				} else {
//					System.out.print(getStateAt(x, y) + " ");
//				}
//			}
//			System.out.println();
//		}
//	}

	public List<Coordinate> findCoordinatesWith(State state) {
		List<Coordinate> result = new ArrayList<>();
		for (Entry<Integer, Map<Integer, CoordinateValue>> entryX : data.entrySet()) {
			for (Entry<Integer, CoordinateValue> entryY : entryX.getValue().entrySet()) {
				if (entryY.getValue().getState() == state) {
					result.add(Coordinate.create(entryX.getKey(), entryY.getKey()));
				}
			}
		}
		return result;
	}

	public void applyWire(Wire wire, State updateState) {
		int x = 0;
		int y = 0;
		int wireLength = 0;
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
			applyVector(point1, point2, wireLength, updateState);
			wireLength += vector.length;
		}
	}

	private void applyVector(Coordinate point1, Coordinate point2, int length, State updateState) {
		if (point1.x != point2.x && point1.y != point2.y) {
			throw new IllegalStateException("point1=" + point1 + ";point2=" + point2);
		}
		if (point1.x == point2.x) {
			final int x = point1.x;
			if (point1.y < point2.y) {
				for (int y = point1.y; y <= point2.y; y++) {
					updateStateAt(Coordinate.create(x, y), updateState, ++length);
				}
			}
			if (point1.y > point2.y) {
				for (int y = point1.y; y >= point2.y; y--) {
					updateStateAt(Coordinate.create(x, y), updateState, ++length);
				}
			}
		}
		if (point1.y == point2.y) {
			final int y = point1.y;
			if (point1.x < point2.x) {
				for (int x = point1.x; x <= point2.x; x++) {
					updateStateAt(Coordinate.create(x, y), updateState, ++length);
				}
			}
			if (point1.x > point2.x) {
				for (int x = point1.x; x >= point2.x; x--) {
					updateStateAt(Coordinate.create(x, y), updateState, ++length);
				}
			}
		}
	}

}
