package aoc.year2019.day3.logic;

import java.util.HashMap;
import java.util.Map;

public class CoordinateValue {
	private State state;
	private final Map<State, Integer> lengths;

	public CoordinateValue(State state) {
		this.state = state;
		lengths = new HashMap<>();
		lengths.put(State.S1, 0);
		lengths.put(State.S2, 0);
	}

	public void addLengthPerState(int length, State state) {
		lengths.put(state, lengths.get(state) + length);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Map<State, Integer> getLengths() {
		return lengths;
	}

}
