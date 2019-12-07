package aoc.year2019.day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {

	public static void main(String[] args) throws Exception {
		List<Integer> program = readProgram("example1");
		System.out.println("program=" + program);
	}

	private static List<Integer> readProgram(String name) throws Exception {
		String input;
		try (InputStream stream = Puzzle.class.getResourceAsStream("/day2/" + name + ".int");
				ReadableByteChannel channel = Channels.newChannel(stream);
				Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
				BufferedReader bReader = new BufferedReader(reader);) {
			input = bReader.readLine();
		}
		List<Integer> result = new ArrayList<>();
		for (String s : input.split(",")) {
			result.add(Integer.parseInt(s));
		}
		return result;
	}

}
