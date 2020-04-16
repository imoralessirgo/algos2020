package imoralessirgo.hw2;

/**
 * Building from Question 1, there are different questions to answer.
 * 
 * 1. What is the most frequently used word in the entire book?
 * 2. What are the top-ten most frequently used words in the entire book?
 * 3. How many words occur exactly once in the book?
 */
public class Q2 {

	static void mostFrequent() throws java.io.IOException {
		String mostFreq = "";
		int count = 0;
		int accumulatedCount = 0;
		int totalWords = 0;
		WordSymbolTable wl = new WordSymbolTable();
		for(int i = 1; i <= 45 ; i++) {
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			for (String word : te) {
				wl.increment(word);
				totalWords += 1;
			}
		}
		mostFreq = wl.mostFrequent();
		count = wl.count(mostFreq);
		double f = ((double)count/totalWords)*100;

		System.out.println(
				String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
						mostFreq, count, totalWords, f));

		System.out.println("The Top Ten words by frequency are:");
		accumulatedCount = count;
		System.out.println(String.format("%2d. %s (%d)", 1, mostFreq, count));
		wl.remove(mostFreq);
		for(int i = 1 ; i < 10; i++){
			mostFreq = wl.mostFrequent();
			count = wl.count(mostFreq);
			accumulatedCount += count;
			System.out.println(String.format("%2d. %s (%d)", i + 1, mostFreq, count));
			wl.remove(mostFreq);
		}
		System.out.println(String.format("These ten words represent %.2f%% of the total words in the book", ((double)accumulatedCount/totalWords)*100));

	}

	static void wordsUsedOnce() throws java.io.IOException {
		int numSingle = 0;
		WordSymbolTable wl = new WordSymbolTable();
		for(int i = 1; i <= 45 ; i++) {
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			for (String word : te) {
				wl.increment(word);
			}
		}
		WordSymbolTable.Node n = wl.getNode();
		if(n.count == 1){numSingle = 1;}
		while(n.next != null){
			n = n.next;
			if(n.count == 1){numSingle += 1;}
		}

		System.out.println(String.format("%d words are used exactly once", numSingle));
	}

	public static void main(String[] args) throws java.io.IOException {
		mostFrequent();
		wordsUsedOnce();
	}
}
