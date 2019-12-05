package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Wire {

	private List<Vector> path;

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

	public static Wire createFrom(InputStream input) throws IOException {
		try (ReadableByteChannel channel = Channels.newChannel(input);
				Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(reader);) {
			return createFrom(bufferedReader.readLine());
		}
	}

	public static Wire createFrom(String input) {
		Wire result = new Wire();
		Arrays.asList(input.split(",")).stream().forEach(result.feedConsumer);
		result.writeLock();
		return result;
	}

	private void writeLock() {
		path = Collections.unmodifiableList(path);
	}

	@Override
	public String toString() {
		return getPath().toString();
	}

	public List<Vector> getPath() {
		return path;
	}

}
