package logic;

import java.util.Comparator;
import java.util.List;

import data.Coordinate;
import data.CoordinateSystem;
import data.State;
import data.Wire;

public class Calculator {

	private static final Comparator<Coordinate> COMPARATOR = new Comparator<Coordinate>() {
		@Override
		public int compare(Coordinate o1, Coordinate o2) {
			return Integer.compare(o1.getVectorLength(), o2.getVectorLength());
		}
	};

	public static int closestDistance(Wire wire1, Wire wire2) {
		// TODO determine CoordinateSystem constructor argument automatically by lengths of wires
		// TODO or just use Map instead of array
		CoordinateSystem cs = new CoordinateSystem(20);
		cs.applyWire(wire1, State.S1);
		cs.applyWire(wire2, State.S2);
		// calc.cs.print();

		List<Coordinate> preResult = cs.findCoordinatesWith(State.SN);
		System.out.println("preResult=" + preResult);
		Coordinate closestPoint = preResult.parallelStream().sorted(COMPARATOR).findFirst().get();
		System.out.println("closestPoint=" + closestPoint);
		return closestPoint.getVectorLength();
	}

}
