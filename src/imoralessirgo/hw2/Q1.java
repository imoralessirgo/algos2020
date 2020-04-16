package imoralessirgo.hw2;

/**
 * For this question, you are to process the book "The Tale of Two Cities" as included in the repository.   
 * 
 * There are 45 chapters in the book, which I have extracted into separate files. I will admit that the  
 * transcription is quite awkward. For example, everything has been converted to lower case, and all punctuation 
 * marks have been removed. Some words are subdivided improperly, but this is what we have to work with!
 * 
 * The questions you are to answer are:
 * 
 * 1. Which chapter contains the most # of words in total, as returned by the TaleOfTwoCitiesExtractor
 * 2. Which chapter (of the 45) contains the most # of unique words? and how many unique words occur in
 *    that chapter.
 * 3. Which two distinct chapters share the most words in common? And how many words is that?
 * 
 * The definition of a word is given to you by the TaleOfTwoCitiesExtractor class, which provides an
 * Iterable interface to a given chapter. This object will return the words in a chapter, one at a time,
 * in the order they appear in the chapter.
 * 
 * Your first responsibility is to ensure that TaleOfTwoCitiesExtractor works in your location.
 */
public class Q1 {
	
	/** Complete this implementation. */
	static void largestChapter() throws java.io.IOException {
		int chapter = -1;
		int max = -1;
		for(int i = 1; i <= 45 ; i++){
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			int wc = 0;
			for(String word : te){
				wc++;
			}
			if(wc > max){
				max = wc;
				chapter = i;
			}
		}
		System.out.println(String.format("The chapter with the most number of words is %d with a total of %s", chapter, max));
	}
	
	/** Complete this implementation. */
	static void fewestUniqueWords() throws java.io.IOException {
		int chapter = -1;
		int minUnique = Integer.MAX_VALUE;
		for(int i = 1; i <= 45 ; i++){
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			WordList wl = new WordList();
			for(String word : te){
				wl.add(word);
			}
			if (wl.size() < minUnique){
				chapter = i;
				minUnique = wl.size();
			}
		}
		System.out.println(String.format("The chapter with the fewest number of unique words is %d with a total of %s", chapter, minUnique));
	}
	
	/** Complete this implementation. */
	static void totalUniqueWords() throws java.io.IOException {
		int totalUnique = 0;
		WordList wl = new WordList();
		for(int i = 1; i <= 45 ; i++) {
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			for (String word : te) {
				wl.add(word);
			}
		}
		totalUnique = wl.size();
		System.out.println(String.format("There are a total of %d unique words in the book.", totalUnique));
	}
	
	/** Complete this implementation. */
	static void twoChaptersShareMostInCommon() throws java.io.IOException {
		WordList[] arr = new WordList[45];
		int chapter1 = -1;
		int chapter2 = -1;
		int maxShared = -1;
		for(int i = 1; i <= 45 ; i++){
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			WordList wl = new WordList();
			for(String word : te){
				wl.add(word);
			}
			arr[i-1] = wl;
		}
		int num = 0;
		for(int i = 0; i < 44; i++){
			for(int j = i + 1; j < 45; j++) {
				if(maxShared < arr[j].size()){
					num = wordsInCommon(arr[i], arr[j]);
				}
				if(num > maxShared){
					maxShared = num;
					chapter1 = i+1;
					chapter2 = j + 1;
				}
			}
		}


		System.out.println(String.format("The two chapters that share the most words in common are chapters %d and %d with a total of %s words", chapter1, chapter2, maxShared));
	}

	static int wordsInCommon(WordList wl1, WordList wl2){
		WordList.Node n = wl1.getNode();
		int count = 0;
		while(n.next != null){
			if(wl2.contains(n.word)){count++;}
			n = n.next;
		}
		return count;
	}
	
	/** Leave this method alone. */
	public static void main(String[] args) throws java.io.IOException {
		largestChapter();
		fewestUniqueWords();
		totalUniqueWords();
		twoChaptersShareMostInCommon();
	}
}
