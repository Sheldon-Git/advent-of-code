package aoc.year2019.day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import aoc.year2019.day3.logic.Coordinate;
import aoc.year2019.day3.logic.CoordinateSystem;
import aoc.year2019.day3.logic.State;
import aoc.year2019.day3.logic.Wire;

public class Puzzle {

	private static final Comparator<Coordinate> COMPARATOR = new Comparator<Coordinate>() {
		@Override
		public int compare(Coordinate o1, Coordinate o2) {
			return Integer.compare(o1.getVectorLength(), o2.getVectorLength());
		}
	};

	public static void main(String[] args) throws Exception {
		String name = "main";
		Wire wire1 = createWire(name, 1);
		Wire wire2 = createWire(name, 2);

//		wire1 = Wire.createFrom("R8,U5,L5,D3");
//		wire2 = Wire.createFrom("U7,R6,D4,L4");

		System.out.println("wire1=" + wire1);
		System.out.println("wire2=" + wire2);

		CoordinateSystem cs = new CoordinateSystem();
		cs.applyWire(wire1, State.S1);
		cs.applyWire(wire2, State.S2);
		calculateClosestIntersection(cs);
		calculateShortestIntersection(cs);
	}

	private static void calculateShortestIntersection(CoordinateSystem cs) {
		List<Integer> intersectionWireLengths = new ArrayList<>();
		for (Coordinate intersection : cs.findCoordinatesWith(State.SN)) {
			Map<State, Integer> wireLengths = cs.getValueAt(intersection).getLengths();
			int wire1Length = wireLengths.get(State.S1);
			int wire2Length = wireLengths.get(State.S2);
			int wireCombinedLength = wire1Length + wire2Length;
			System.out.println("intersection " + intersection + " wire length: " + wireCombinedLength);
			intersectionWireLengths.add(wireCombinedLength);
		}
		Collections.sort(intersectionWireLengths);
		int shortestIntersection = intersectionWireLengths.get(0);
		System.out.println("shortestIntersection=" + shortestIntersection);
	}

	private static void calculateClosestIntersection(CoordinateSystem cs) {
		List<Coordinate> intersections = cs.findCoordinatesWith(State.SN);
		System.out.println("intersections=" + intersections);
		Coordinate closestIntersections = intersections.parallelStream().sorted(COMPARATOR).findFirst().get();
		System.out.println("closestIntersections=" + closestIntersections);
		int closestIntersection = closestIntersections.getVectorLength();
		System.out.println("closestIntersection=" + closestIntersection);
	}

	private static Wire createWire(String name, int number) throws IOException {
		try (InputStream input = Puzzle.class.getResource("/day3/" + name + "/" + number + ".wire").openStream();) {
			return Wire.createFrom(input);
		}
	}

}
