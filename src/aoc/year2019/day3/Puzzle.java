package aoc.year2019.day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

import logic.Coordinate;
import logic.CoordinateSystem;
import logic.State;
import logic.Wire;

public class Puzzle {

	private static final Comparator<Coordinate> COMPARATOR = new Comparator<Coordinate>() {
		@Override
		public int compare(Coordinate o1, Coordinate o2) {
			return Integer.compare(o1.getVectorLength(), o2.getVectorLength());
		}
	};

	public static void main(String[] args) throws Exception {
		String name = "example2";
		Wire wire1 = createWire(name, 1);
		Wire wire2 = createWire(name, 2);

		wire1 = Wire.createFrom("R8,U5,L5,D3");
		wire2 = Wire.createFrom("U7,R6,D4,L4");

		System.out.println("wire1=" + wire1);
		System.out.println("wire2=" + wire2);

		CoordinateSystem cs = new CoordinateSystem();
		cs.applyWire(wire1, State.S1);
		cs.applyWire(wire2, State.S2);
		System.out.println("closestDistance: " + closestDistance(cs));
	}

	private static Wire createWire(String name, int number) throws IOException {
		try (InputStream input = Puzzle.class.getResource("/" + name + "/" + number + ".wire").openStream();) {
			return Wire.createFrom(input);
		}
	}

	private static int closestDistance(CoordinateSystem cs) {
		List<Coordinate> preResult = cs.findCoordinatesWith(State.SN);
		System.out.println("preResult=" + preResult);
		Coordinate closestPoint = preResult.parallelStream().sorted(COMPARATOR).findFirst().get();
		System.out.println("closestPoint=" + closestPoint);
		return closestPoint.getVectorLength();
	}

}
