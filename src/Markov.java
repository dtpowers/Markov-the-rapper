import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Markov {

	// create hash table for every word that contains each word that follows it
	// piece together a sentence
	public static HashMap<String, ArrayList<String>> genWordTable(
			String sourceDest) throws IOException {
		File sourceText = new File(sourceDest);
		BufferedReader br = new BufferedReader(new FileReader(sourceText));
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		// words are separated by a space
		// for each word in file put word in map with key value of next word
		String line = br.readLine();

		while (line != null) {
			String[] words = line.split(" ");
			// create list of words
			
			for (int i = 0; i < words.length - 1; i++) {
				// if the word is already in the map, add next word to list
				if (result.containsKey(words[i])) {
					ArrayList<String> curValues = result.get(words[i]);
					curValues.add(TextFormater.formatText(words[i + 1]));

					result.put(TextFormater.formatText(words[i]), curValues);

				} else {
					// otherwise create new entry for word
					ArrayList<String> values = new ArrayList<String>();
					values.add(TextFormater.formatText(words[i + 1]));
					result.put(TextFormater.formatText(words[i]), values);
				}
				
			}
			// handle last word of line
			if(words.length != 0){
			ArrayList<String> values = new ArrayList<String>();
			values.add(" ");
			result.put(TextFormater.formatText(words[words.length-1]), values);
			}
			line = br.readLine();
		}

		br.close();
		return result;
	}

}
