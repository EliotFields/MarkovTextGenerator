package model;

import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextGenerator {
	
	
	/**Writes a block of random text following an initial string. Meant to be used with the results of a call to the "digest" method.
	 * 
	 * @throws IllegalArgumentException - if "length" is less than the order of "map"
	 * 
	 * @param s - The initial "seed" text to be extended
	 * @param map - A HashMap which will be used to generate words
	 * @param length - The number of words to be generated
	 * 
	 * @return A string of (length) words in the style of some previously processed body of text
	 */
//	
//	public String write(String s, HashMap<String, ArrayList<String>> map, int length) throws IllegalArgumentException {
//		String ret = "";
//
////		TO IMPLEMENT note: for values not found in map, use map.keys() and grab a key at random.
////		This SHOULD only occur once, and only if s is shorter than map's order  
////		How to ensure proper capitalization?
////		What to do if length is less than the map's order?
//		
//		int i = 0;
//		
//		ArrayList<String> keys = new ArrayList<String>();
//		Iterator<String> keyGet = map.keySet().iterator();
//		while (keyGet.hasNext()) {
//			keys.add(keyGet.next());
//		}
//		
//		int ord = 0;
//		Collections.shuffle(keys);
//		String ordTest = keys.get(0);
//		
//		Scanner sc = new Scanner(ordTest);
//		while (sc.hasNext()) {
//			ord++;
//			sc.next();
//		}
//		sc.close();
//		
//		if (ord > length) {
//			throw new IllegalArgumentException();
//		}
//		
//		ret += ordTest;
//		i += ord;
//		
//		String lastThree = "" + ordTest;
////		lastThree = lastThree.substring(lastThree.indexOf(" "), lastThree.length()); lop off first word
//		
//		while (i < length) {
//			ret += " " + map.get(lastThree);
//			
//		}
//		
//		return ret;
//	}
	
	
	/**Generates a HashMap representing the probability of some character in a text following each unique substring of a given length.
	 * 
	 * @param text - The text from which to generate a transition mapping
	 * @param order - The number of characters in each key. Higher order will result in a more "faithful" mapping
	 * @return A pairing of each unique substring of length (order) to an ArrayList of characters immediately following that substring.
	 */
	
	public HashMap<String, ArrayList<Character>> digest(String text, int order) {
		
		HashMap<String, ArrayList<Character>> matrix = new HashMap<String, ArrayList<Character>>();
		//for each unique substring of length (order), store an ArrayList of characters immediately following it
		//this feels space inefficient, but not sure how else to do it without sacrificing time efficiency
		
		String working = text + "";
		working.trim();
		
		if (order <= 0 || order >= working.length()) {
			throw new IllegalArgumentException();
		}
		//throw exception if order is < 1 (no key) or >= length of text (no next char to map)
		
		while (working.length() > order) {
			String str = working.substring(0, order);
			Character ch = working.charAt(order);
			
			if (!matrix.containsKey(str)) {
				ArrayList<Character> map = new ArrayList<Character>();
				map.add(ch);
				matrix.put(str, map);
			}
			//if str isn't already a key, add it
			else {
				matrix.get(str).add(ch);
			}
			//if str is already a key, add ch to its value

			working = working.substring(1, working.length());
			//cut the first char off of working string to move the loop forward
		}
		
		return matrix;
	}
	
	/**Generates a HashMap representing the probability of some character in a file following each unique substring of a given length.
	 * 
	 * @param filename - The file to be processed
	 * @param order - The number of characters in each key
	 * @return A pairing of each unique substring of length (order) to an ArrayList of characters immediately following that substring.
	 */
	public HashMap<String, ArrayList<Character>> digestFile(String filename, int order) {

		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(filename)));
		}
		catch (Exception e) {
			System.out.println("ERROR: Invalid file path");
		}
		
		return digest(text, order);
	}
}
