package aoc.year2019.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Wire {

	public List<Vector> path;

	private final Consumer<String> feedConsumer;

	public Wire() {
		path = new ArrayList<>();
		feedConsumer = new Consumer<String>() {
			@Override
			public void accept(String s) {
				getPath().add(new Vector(s));
			}
		};
	}

	public static Wire createFrom(File input) throws IOException {
		Wire result = new Wire();
		try (InputStream inputStream = new FileInputStream(input);
				ReadableByteChannel channel = Channels.newChannel(inputStream);
				Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(reader);) {
			bufferedReader.lines().forEach(result.feedConsumer);
		}
		return result;
	}

	public static Wire createFrom(String input) {
		Wire result = new Wire();
		Arrays.asList(input.split(",")).stream().forEach(result.feedConsumer);
		return result;
	}

	@Override
	public String toString() {
		return getPath().toString();
	}

	public List<Vector> getPath() {
		return path;
	}

}
