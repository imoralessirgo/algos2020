package imoralessirgo.hw3;


/**
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {
	
	public static void main(String[] args) throws java.io.IOException {
		
		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST b = new BST();
		for(int i = 1; i <= 45 ; i++) {
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			for (String word : te) {

				if(b.get(word) != null){
					b.put(word, b.get(word) + 1);
				}else {
					b.put(word, 1);

				}
			}
		}
		
		System.out.println("Top ten most frequent words");
		for(int i = 0; i < 10 ; i++){
			String w = b.mostFrequent();
			System.out.println(w + "  " + b.get(w));
			b.delete(w);
		}


		
		int n = b.printUnique();
		System.out.println(n + " unique words.");
		 
	}
	
}
