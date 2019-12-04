package logic;

import data.CoordinateSystem;
import data.State;
import data.Vector;
import data.Wire;

public class Calculator {

	final CoordinateSystem cs;

	public Calculator() {
		// TODO determine CoordinateSystem constructor argument automatically by lengths of wires
		cs = new CoordinateSystem(20);
	}

	public static int closestDistance(Wire wire1, Wire wire2) {
		Calculator calc = new Calculator();
		calc.applyWire(wire1, State.S1);
		calc.applyWire(wire2, State.S2);
		// calc.cs.print();
		return 0;  // FIXME implement this
	}

	private void applyWire(Wire wire, State updateState) {
		int x = 0;
		int y = 0;
		for (Vector vector : wire.getPath()) {
			switch (vector.direction) {
			case UP:
				applyVector(x, y + 1, x, y + vector.length, updateState);
				y += vector.length;
				break;
			case RIGHT:
				applyVector(x + 1, y, x + vector.length, y, updateState);
				x += vector.length;
				break;
			case DOWN:
				applyVector(x, y - 1, x, y - vector.length, updateState);
				y -= vector.length;
				break;
			case LEFT:
				applyVector(x - 1, y, x - vector.length, y, updateState);
				x -= vector.length;
				break;
			}
		}
	}

	private void applyVector(final int x1, final int y1, final int x2, final int y2, State updateState) {
		if (x1 != x2 && y1 != y2) {
			throw new IllegalStateException("x1=" + x1 + ";y1=" + y1 + ";x2=" + x2 + ";y2=" + y2);
		}
		if (x1 == x2) {
			final int x = x1;
			if (y1 < y2) {
				for (int y = y1; y <= y2; y++) {
					cs.updateStateAt(x, y, updateState);
				}
			}
			if (y1 > y2) {
				for (int y = y1; y >= y2; y--) {
					cs.updateStateAt(x, y, updateState);
				}
			}
		}
		if (y1 == y2) {
			final int y = y1;
			if (x1 < x2) {
				for (int x = x1; x <= x2; x++) {
					cs.updateStateAt(x, y, updateState);
				}
			}
			if (x1 > x2) {
				for (int x = x1; x >= x2; x--) {
					cs.updateStateAt(x, y, updateState);
				}
			}
		}
	}

}
