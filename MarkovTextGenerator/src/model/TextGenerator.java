package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextGenerator {
	
	
	/**Writes a block of random text following an initial string. Meant to be used with the results of a call to the "digest" method.
	 * 
	 * @param s - The initial "seed" text to be extended
	 * @param map - A HashMap which will be used to generate words
	 * @param length - The number of words to be generated
	 * 
	 * @return A string of (length) words in the style of some previously processed body of text
	 */
	
	public String write(String s, HashMap<String, ArrayList<String>> map, int length) {
		String ret = "";

//		TO IMPLEMENT note: for values not found in map, use map.values() and grab a key at random.
//		This SHOULD only occur once, and only if s is shorter than map's order  
//		How to ensure proper capitalization?
		
		return ret;
	}
	
	/**Takes a string and generates a HashMap linking each set of (order) words to an ArrayList of words which appear immediately following that set of words in the string.
	 * 
	 * @param text - The string to be processed.
	 * @param order - The number of words in each set. Must be greater than 0 and less than the number of words in text. SHOULD be a single-digit number
	 * 
	 * @return A HashMap of strings and ArrayList<String>s
	 */
	public HashMap<String, ArrayList<String>> digest(String text, int order) {
		
		HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
		ArrayList<String> textList = new ArrayList<String>();
		
		Scanner sc = new Scanner(text);
		while (sc.hasNext()) {
			textList.add(sc.next());
		}
		sc.close();
		
		if (order <= 0 || order >= textList.size()) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < textList.size() - order; i++) {
			String s = "";
			for (int j = 0; j < order; j++) {
				s = s + textList.get(i+j) + " ";
			}
			
			if (ret.containsKey(s)) {
				ret.get(s).add(textList.get(i+order));
			}
			else {
				ArrayList<String> al = new ArrayList<String>();
				al.add(textList.get(i+order));
				ret.put(s, al);
			}
		}
		
		return ret;
	}
	
	/**Takes a text file and generates a HashMap linking each set of (order) words contained in the file to an ArrayList of words which appear immediately following that set of words.
	 * 
	 * @param filename - The file containing the text to be processed. Must be in .txt format
	 * @param order - The number of words in each set. Larger numbers will result in fewer but more "sensible" mappings
	 * 
	 * @return A HashMap of strings and ArrayList<String>s
	 */
	public HashMap<String, ArrayList<String>> digestFile(String filename, int order) throws FileNotFoundException {
		HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
		
		File f = new File(filename);
		Scanner sc = new Scanner(f);
		String s = "";
		while (sc.hasNext()) {
			s = s + sc.next() + " ";
		}
		sc.close();
		ret = digest(s, order);
		
		return ret;
	}
}
