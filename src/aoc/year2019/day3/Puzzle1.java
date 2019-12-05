package aoc.year2019.day3;

import java.io.IOException;
import java.io.InputStream;

import data.Wire;
import logic.Calculator;

public class Puzzle1 {

	public static void main(String[] args) throws Exception {
		String name = "example2";
		Wire wire1 = createWire(name, 1);
		Wire wire2 = createWire(name, 2);

		wire1 = Wire.createFrom("R8,U5,L5,D3");
		wire2 = Wire.createFrom("U7,R6,D4,L4");

		System.out.println("wire1=" + wire1);
		System.out.println("wire2=" + wire2);

		int closestDistance = Calculator.closestDistance(wire1, wire2);
		System.out.println("closestDistance=" + closestDistance);
	}

	private static Wire createWire(String name, int number) throws IOException {
		try (InputStream input = Puzzle1.class.getResource("/" + name + "/" + number + ".wire").openStream();) {
			return Wire.createFrom(input);
		}
	}

}
