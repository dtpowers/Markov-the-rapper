import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class TextFormater {
	
	//not used or tested, better solution below
	public static void formatTxtFile(File text) throws IOException{
		File output = new File("output.txt");
		PrintWriter write = new PrintWriter("output.txt");
		BufferedReader br = new BufferedReader(new FileReader(text));
		String line = br.readLine();
		//read each line, then write that line minus formating to new file
		while(line != null){
			String[] words = line.split(" ");
			//create list of words
			for(String word : words){
				//make lower case and remove non alphanumeric values
				String editedWord = word.toLowerCase();
				editedWord = editedWord.replaceAll("[^\\p{L}\\p{Nd}]+", "");
				write.printf(editedWord + " ");
			}
		}
		br.close();
		write.close();
	}
	
	public static String formatText(String word){
		//make all lower case and remove non alphanumeric chars
		String formatedWord = word;
		formatedWord = word.toLowerCase();
		formatedWord = formatedWord.replaceAll("[^\\p{L}\\p{Nd}]+", "");
		return formatedWord;
	}
}
