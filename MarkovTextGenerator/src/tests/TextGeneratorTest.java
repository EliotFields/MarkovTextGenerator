package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import model.*;

import java.util.HashMap;
import java.util.ArrayList;

public class TextGeneratorTest {
	
	TextGenerator _gen;
	String _inputString;
	
	@Before
	public void before1() {
		_gen = new TextGenerator();
		_inputString = "This is a test string. The function which this string is supposed to test is functioning correctly. Tests are a testament to thoughtful and functional development practice.";
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void digestFailShort() {
		int ord = 0;
		_gen.digest(_inputString, ord);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void digestFailLong() {
		int ord = _inputString.length();
		_gen.digest(_inputString, ord);
	}
	
	@Test
	public void digestTest() {
		int ord = 4;
		HashMap<String, ArrayList<Character>> map = _gen.digest(_inputString, ord);
		int mapSize = map.size();
		int maxArray = map.get("test").size();

		assertEquals(mapSize, 136);	//ngl I cheated for this one. Counting is hard
		assertEquals(maxArray, 3);
	}
	
}
