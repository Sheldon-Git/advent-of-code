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
		List<Integer> program = readProgram("main");
		System.out.println("program[begin]: " + program);
		executeProgram(program);
		System.out.println("program[end]: " + program);
	}

	private static void executeProgram(List<Integer> program) {
		int ip = 0;
		while (true) {
			// System.out.println("current program: " + program);
			ip = executeOperation(ip, program);
			if (ip < 0) {
				break;
			}
		}
	}

	private static int parseOpCode(int instructionHead) {
		if (instructionHead == 99) {
			return 99;
		}
		return instructionHead % 10;
	}

	private static int executeOperation(final int ip, List<Integer> program) {
		final int instructionHead = program.get(ip);
		final int opCode = parseOpCode(instructionHead);
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

	private static int getArgumentMode1(int instructionHead) {
		int result = instructionHead;
		if (result < 100) {
			return 0; // default
		}
		result /= 100;
		result = result % 10;
		return result;
	}

	private static int getArgumentMode2(int instructionHead) {
		int result = instructionHead;
		if (result < 1000) {
			return 0; // default
		}
		result /= 1000;
		result = result % 10;
		return result;
	}

	private static int getArgumentValue1(int ip, List<Integer> program) {
		int argMode = getArgumentMode1(program.get(ip));
		switch (argMode) {
		case 0:
			return program.get(program.get(ip + 1));
		case 1:
			return program.get(ip + 1);
		default:
			throw new IllegalStateException("invalid argument mode " + argMode);
		}
	}

	private static int getArgumentValue2(int ip, List<Integer> program) {
		int argMode = getArgumentMode2(program.get(ip));
		switch (argMode) {
		case 0:
			return program.get(program.get(ip + 2));
		case 1:
			return program.get(ip + 2);
		default:
			throw new IllegalStateException("invalid argument mode " + argMode);
		}
	}

	private static void executeOperationAdd(int ip, List<Integer> program) {
		int arg1 = getArgumentValue1(ip, program);
		int arg2 = getArgumentValue2(ip, program);
		int result = arg1 + arg2;
		int dp = program.get(ip + 3);
		program.set(dp, result);
	}

	private static void executeOperationMultiply(int ip, List<Integer> program) {
		int arg1 = getArgumentValue1(ip, program);
		int arg2 = getArgumentValue2(ip, program);
		int result = arg1 * arg2;
		int dp = program.get(ip + 3);
		program.set(dp, result);
	}

	private static void executeOperationInput(int ip, List<Integer> program) {
		int dp = program.get(ip + 1);
		program.set(dp, readValue());
	}

	private static void executeOperationOutput(int ip, List<Integer> program) {
		int sp = program.get(ip + 1);
		int value = program.get(sp);
		writeValue(value);
	}

	private static int readValue() {
		return 1;
	}

	private static void writeValue(int value) {
		System.out.println("print " + value);
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
