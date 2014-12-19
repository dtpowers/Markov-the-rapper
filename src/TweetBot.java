import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


public class TweetBot {
	public static HashMap<String, ArrayList<String>> wordMap;
	public TweetBot(String sampleText) throws IOException {
		wordMap = Markov.genWordTable(sampleText);
		String tweetTxt = generateTweetTxt();
	}
	
	public static String getRandomWord(HashMap<String, ArrayList<String>> wordMap){
		Set<String> words = wordMap.keySet();
		int size = words.size();
		int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
		int i = 0;
		for(Object obj : words)
		{
		    if (i == item)
		        return obj.toString();
		    i = i + 1;
		}
		return null;
	
	}
	public static String generateTweetTxt(){
		//start by randomly selecting a first word
		String tweet = getRandomWord(wordMap);
		//current word, used to get next word.
		String currWord = tweet;
		String nextWord;
		int tweetLen = 0;
		Random randGen = new Random();
		boolean isDone = false;
		//until tweet is max len keep picking words
		while(isDone){
			int rand = randGen.nextInt(wordMap.get(currWord).size());
			nextWord = wordMap.get(currWord).get(rand);
			tweetLen += nextWord.length();
			if(tweetLen > 140){
				isDone = true;
			}
			else{
				tweet += nextWord;
				currWord = nextWord;
			}
			
		}
		return tweet;
	}

}
