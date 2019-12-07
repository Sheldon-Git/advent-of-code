package aoc.year2019.day5;

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
		executeProgram(program);
		System.out.println("program=" + program);
	}

	private static void executeProgram(List<Integer> program) {
		int ip = 0;
		while (true) {
			ip = executeOperation(ip, program);
			if (ip < 0) {
				break;
			}
		}
	}

	private static int executeOperation(final int ip, List<Integer> program) {
		final int opCode = program.get(ip);
		switch (opCode) {
		case 99:
			return -1;
		case 1:
			executeOperationAdd(ip, program);
			return ip + 4;
		case 2:
			executeOperationMultiply(ip, program);
			return ip + 4;
		case 3:
			executeOperationInput(ip, program);
			return ip + 2;
		case 4:
			executeOperationOutput(ip, program);
			return ip + 2;
		default:
			throw new IllegalStateException("operation code " + opCode + " is not supported");
		}
	}

	private static void executeOperationOutput(int ip, List<Integer> program) {
		int sp = program.get(ip + 1);
		int value = program.get(sp);
		writeValue(value);
	}

	private static void writeValue(int value) {
		System.out.println("print " + value);
	}

	private static int readValue() {
		return 1;
	}

	private static void executeOperationInput(int ip, List<Integer> program) {
		int dp = program.get(ip + 1);
		program.set(dp, readValue());
	}

	private static void executeOperationMultiply(int ip, List<Integer> program) {
		int arg1 = program.get(program.get(ip + 1));
		int arg2 = program.get(program.get(ip + 2));
		int result = arg1 * arg2;
		int dp = program.get(ip + 3);
		program.set(dp, result);
	}

	private static void executeOperationAdd(int ip, List<Integer> program) {
		int arg1 = program.get(program.get(ip + 1));
		int arg2 = program.get(program.get(ip + 2));
		int result = arg1 + arg2;
		int dp = program.get(ip + 3);
		program.set(dp, result);
	}

	private static List<Integer> readProgram(String name) throws Exception {
		String input;
		try (InputStream stream = Puzzle.class.getResourceAsStream("/day5/" + name + ".int");
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
