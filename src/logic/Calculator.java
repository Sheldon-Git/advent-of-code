package logic;

import data.Coordinate;
import data.CoordinateSystem;
import data.State;
import data.Vector;
import data.Wire;

public class Calculator {

	final CoordinateSystem cs;

	public Calculator() {
		// TODO determine CoordinateSystem constructor argument automatically by lengths of wires
		// TODO or just use Map instead of array
		cs = new CoordinateSystem(20);
	}

	public static int closestDistance(Wire wire1, Wire wire2) {
		Calculator calc = new Calculator();
		calc.applyWire(wire1, State.S1);
		calc.applyWire(wire2, State.S2);
		// calc.cs.print();
		return 0; // FIXME implement this
	}

	private void applyWire(Wire wire, State updateState) {
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
					cs.updateStateAt(x, y, updateState);
				}
			}
			if (point1.y > point2.y) {
				for (int y = point1.y; y >= point2.y; y--) {
					cs.updateStateAt(x, y, updateState);
				}
			}
		}
		if (point1.y == point2.y) {
			final int y = point1.y;
			if (point1.x < point2.x) {
				for (int x = point1.x; x <= point2.x; x++) {
					cs.updateStateAt(x, y, updateState);
				}
			}
			if (point1.x > point2.x) {
				for (int x = point1.x; x >= point2.x; x--) {
					cs.updateStateAt(x, y, updateState);
				}
			}
		}
	}

}
