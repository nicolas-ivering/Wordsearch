package se.powerballad.wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearcher {

	private static Pattern linePattern = Pattern.compile("\\d+.*>(.*)");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		String includedChars = args[0];
		byte[] includedBytes = includedChars.getBytes();
		
		String bestMatchingWord = null;
		int mostMatchingChars = 0;
		//Init dictionary
		FileReader fr = new FileReader("/home/nicolas/dsso-1.29.txt");
		BufferedReader dic = new BufferedReader(fr);
		//for each line
		String line = null;
		while((line = dic.readLine()) != null) {
			//check if begins with number
			String matchingPart = getMatchingPart(line.toLowerCase());
			if(matchingPart !=  null) {
				//for each valid line get all words
				List<String> words = getWords(matchingPart);

				//for each word
				for (String word : words) {
					if(word.length() == includedChars.length()) {
						byte[] wordData = word.getBytes();
						int matches = calculateMatchingChars(includedBytes,
								wordData);
						if(matches == includedBytes.length) {
							System.out.println("Correct word:"  + word);
						}
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time:" + (endTime - startTime) / 1000f);
	}

	private static int calculateMatchingChars(byte[] includedBytes,
			byte[] wordData) {
		int matches = 0;
		for(int i = 0; i < includedBytes.length; i++) {
			boolean match = false;
			byte includedByte = includedBytes[i];
			for (int j = 0; j < wordData.length; j++) {
				byte wordByte = wordData[j];
				if(wordByte == includedByte) {
					matches++;
					wordData[j] = 0;
					match = true;
					break;
				}
			}
			if(!match) {
				break;
			}
		}
		return matches;
	}
			
	private static List<String> getWords(String matchingPart) {
		ArrayList<String> words = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(matchingPart, ":");
		while(tokens.hasMoreTokens()) {
			words.add(tokens.nextToken());
		}
		
		return words;
		
	}

	private static String getMatchingPart(String line) {
		
		Matcher m = linePattern.matcher(line);
		if(m.matches()) {
			return m.group(1);
		} else {
			return null;
		}
	}

}
