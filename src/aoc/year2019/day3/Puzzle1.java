package aoc.year2019.day3;

import java.io.IOException;

public class Puzzle1 {

	public static void main(String[] args) throws Exception {
		Wire wire1 = createWire("Puzzle1.wire1.data");
		System.out.println("wire1=" + wire1);

		Wire wire2 = createWire("Puzzle1.wire2.data");
		System.out.println("wire2=" + wire2);
	}

	private static Wire createWire(String filename) throws IOException {
		return Wire.createFrom(Puzzle1.class.getResource(filename).getFile());
	}

}
