package logic;

import data.CoordinateSystem;
import data.Vector;
import data.Wire;

public class Calculator {

	public static int closestDistance(Wire wire1, Wire wire2) {
		// TODO determine CoordinateSystem constructor argument automatically by lengths of wires
		CoordinateSystem cs = new CoordinateSystem(10000);
		int x = 0;
		int y = 0;
		for (Vector v : wire1.getPath()) {
			switch (v.direction) {
			case UP:
				applyVector(v, x, y, x, y + v.length);
				y += v.length;
				break;
			case RIGHT:
				applyVector(v, x, y, x + v.length, y);
				x += v.length;
				break;
			case DOWN:
				applyVector(v, x, y, x, y - v.length);
				y -= v.length;
				break;
			case LEFT:
				applyVector(v, x, y, x - v.length, y);
				x -= v.length;
				break;
			}
			System.out.println(v + " -> [" + x + "," + y + "]");
			if (x < 0) {
				throw new RuntimeException("x < 0");
			}
			if (y < 0) {
				throw new RuntimeException("y < 0");
			}
		}
		// TODO Auto-generated method stub
		return 0;
	}

	private static void applyVector(Vector v, final int x1, final int y1, final int x2, final int y2) {
		if (x1 != x2 && y1 != y2) {
			throw new IllegalStateException("x1=" + x1 + ";y1=" + y1 + ";x2=" + x2 + ";y2=" + y2);
		}
		if (x1 == x2) {
			if (y1 < y2) {
			}
			if (y2 < y1) {
			}
		}
		if (y1 == y2) {
		}
	}

}
