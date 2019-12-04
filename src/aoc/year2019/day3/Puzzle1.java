package aoc.year2019.day3;

import java.io.File;
import java.io.IOException;

import data.Wire;
import logic.Calculator;

public class Puzzle1 {

	public static void main(String[] args) throws Exception {
//		Wire mainWire1 = createWire("1.wire");
//		Wire mainWire2 = createWire("2.wire");
//
//		Wire test1Wire1 = Wire.createFrom("R75,D30,R83,U83,L12,D49,R71,U7,L72");
//		Wire test1Wire2 = Wire.createFrom("U62,R66,U55,R34,D71,R55,D58,R83");

//		Wire test2Wire1 = Wire.createFrom("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
//		Wire test2Wire2 = Wire.createFrom("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

		Wire test3Wire1 = Wire.createFrom("R8,U5,L5,D3");
		Wire test3Wire2 = Wire.createFrom("U7,R6,D4,L4");
		
		System.out.println("wire1=" + test3Wire1);
		System.out.println("wire2=" + test3Wire2);

		int closestDistance = Calculator.closestDistance(test3Wire1, test3Wire2);
		System.out.println("closestDistance=" + closestDistance);
	}

	private static Wire createWire(String filename) throws IOException {
		File file = new File(Puzzle1.class.getResource(filename).getFile());
		return Wire.createFrom(file);
	}

}
