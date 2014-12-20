import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TweetBot {
	public static HashMap<String, ArrayList<String>> wordMap;
	public TweetBot(String sampleText) throws IOException {
		wordMap = Markov.genWordTable(sampleText);
		String tweetTxt = generateTweetTxt();
		Twitter twitterBot = setup();
		System.out.print(tweetTxt);
		followBack(twitterBot);
		postTweet(twitterBot);
		
	}
	public static void followBack(Twitter bot){
		
	}
	public static void postTweet(Twitter bot){
		
	}
	public static Twitter setup() throws IOException{
		//configure bot settings
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(getConsumerKey("consumerKey.txt"));
		cb.setOAuthConsumerSecret(getConsumerKeySecret("consumerSec.txt"));
		cb.setOAuthAccessToken(getToken("accessToke.txt"));
		cb.setOAuthAccessTokenSecret(getTokenSec("accessTokeSec.txt"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
	public static String getConsumerKey(String dir) throws IOException{
		File key = new File(dir);
		String result = deserializeString(key);
		return result;
	}
	public static String getConsumerKeySecret(String dir) throws IOException{
		File key = new File(dir);
		String result = deserializeString(key);
		return result;
	}
	public static String getToken(String dir) throws IOException{
		File key = new File(dir);
		String result = deserializeString(key);
		return result;
	}
	public static String getTokenSec(String dir) throws IOException{
		File key = new File(dir);
		String result = deserializeString(key);
		return result;
	}
	public static String deserializeString(File file) throws IOException {
		int len;
		char[] chr = new char[4096];
		final StringBuffer buffer = new StringBuffer();
		final FileReader reader = new FileReader(file);
		try {
			while ((len = reader.read(chr)) > 0) {
				buffer.append(chr, 0, len);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
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
		tweet += " ";
		int tweetLen = tweet.length();
		Random randGen = new Random();
		boolean isDone = false;
		//until tweet is max len keep picking words
		
		while(tweet.length() < 140 && !isDone && wordMap.get(currWord) != null){
			
			int listSize = wordMap.get(currWord).size();
			int rand = randGen.nextInt(listSize);
			nextWord = wordMap.get(currWord).get(rand);
			tweetLen += nextWord.length();
			if(tweetLen > 140){
				isDone = true;
			}
			else{
				tweet += nextWord + " ";
				currWord = nextWord;
			}
			
		}
		return tweet;
	}

}
