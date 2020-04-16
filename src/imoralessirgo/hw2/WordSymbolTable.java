package imoralessirgo.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 * 
 * This class will be used by Question Q2 on Homework2.
 */
public class WordSymbolTable {

	private Node node;
	private int size;
	private int wc;


	/** 
	 * Leave this Node class as is. While you don't need to make changes to this class,
	 * it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String     word;
		int        count;
		Node       next;

		Node(String w, int count) {
			this.word = w;
			this.count = count;
		}

		public boolean hasNext(){
			return next != null;
		}
	}

	/**
	 *
	 * @return initial node getter
	 */
	public Node getNode(){
		return this.node;
	}

	/**
	 *
	 * @param n
	 * @return initial node setter
	 */
	private Node setNode(Node n){
		this.size = 1;
		this.node = n;
		this.wc = 1;
		return this.node;
	}

	private void incrementSize(){
		this.size += 1;
	}

	private void incrementWC(){
		this.wc += 1;
	}


	public boolean isEmpty(){
		return this.node == null;
	}


	private void decrementSize(){
		this.size -= 1;
	}

	private void decrementWC(){
		this.wc -= 1;
	}

	/**
	 * Increase the count for given word.
	 * 
	 * Note: this might need to add the word in the first place. 
	 * 
	 * Returns TRUE if the word was newly added, otherwise FALSE
	 * 
	 * @param elt      element whose count has increased by 1.
	 */
	public boolean increment(String elt) {
		if (this.isEmpty()){ this.setNode(new Node(elt,1)); return true; }

		if(!this.contains(elt)){
			Node n = this.getNode();
			while(n.hasNext()){
				n = n.next;
			}
			n.next = new Node(elt,1);
			this.incrementSize();
			this.incrementWC();
			return true;
		}else{
			Node n = this.getNode();

			while(!elt.equals(n.word)){
				n = n.next;
			}
			n.count += 1;
			this.incrementSize();

		}
		return false;
	}

	/**
	 * Decrease the count for given word.
	 * 
	 * Note: this might need to remove the word once the count reaches zero.
	 * Returns TRUE if the word was removed, otherwise FALSE
	 * 
	 * @param elt      element whose count is to decrease by 1.
	 */
	public boolean decrement(String elt) {
		if (this.isEmpty()){ return false; }

		if(!this.contains(elt)){
			return false;
		}else{
			Node n = this.getNode();
			while(n.word != elt){
				n = n.next;
			}
			n.count -= 1;
			this.decrementSize();
			if(n.count == 0){
				n = n.next;
				this.decrementWC();
			}
		}
		return true;
	}

	/** Return number of words in the symbol table. */
	public int size() {
		return this.wc;
	}

	/** Return the accumulated counts of all words in the word table. */
	public int totalCounts() {
		return this.size;
	}

	/** Remove entire word from the word table. */
	public boolean remove (String elt) {
		if (this.isEmpty()){ return false; }
		if(!this.contains(elt)){
			return false;
		}else{
			Node n = this.getNode();
			while(!elt.equals(n.word)){
				n = n.next;
			}
			if(this.wc == 1){
				this.node = null;
				this.size = 0;
				this.wc = 0;
				return true;
			}
				this.size -= n.count;
				this.decrementWC();
				n.count = n.next.count;
				n.word = n.next.word;
				n.next = n.next.next;
		}
		return true;
	}

	/**
	 * Returns true if word exists in the WordSymbolTable; false otherwise.
	 * 
	 * @param elt      target element to seek.
	 */
	public boolean contains(String elt) {
		if(this.getNode() != null){
			Node n = this.getNode();
			while(!elt.equals(n.word)){
				if(n.next == null){ return false; }
				n = n.next;
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns the count for the word (or 0 if the word doesn't exist in the symbol table).
	 * 
	 * @param elt      target element to seek.
	 */
	public int count(String elt) {
		if (this.isEmpty()){ return 0; }
		if(!this.contains(elt)){
			return 0;
		}else{
			Node n = this.getNode();
			while(!elt.equals(n.word)){
				n = n.next;
			}
			return n.count;
		}
	}

	/** 
	 * Return a word whose repetition count is equal to the maximum in the Symbol table.
	 * 
	 * Note that there may be multiple words that have the maximum count, so this method 
	 * only needs to return one of them.
	 */
	public String mostFrequent() {
		int maxCount = 0;
		String mostFreq = "";
		if(this.getNode() != null){
			Node n = this.getNode();
			maxCount = n.count;
			mostFreq = n.word;
			while(n.next != null){
				n = n.next;
				if(n.count > maxCount){
					maxCount = n.count;
					mostFreq = n.word;
				}
			}
		}
		return mostFreq;
	}


	/** For debugging, return semicolon-separated string of (word,count) pairs. */
	public String elements() {
		String result = "";
		if(this.getNode() != null){
			Node n = this.getNode();
			if(wc == 1){ return "(" + this.node.word + "," + this.node.count + ")"; }
			while(n.next != null){
				result += "(" + n.word + "," + n.count + ")";
				n = n.next;
			}
			return result;
		}
		return "";
	}


	// you should not have to modify anything below. These are testing routines for you to check your work.
	// ----------------------------------------------------------------------------------------------------
	static void validate(Object o1, Object o2) {
		if (o1.equals(o2)) { return; }
		throw new RuntimeException(o1 + " doesn't equal " + o2);
	}

	// Once you have completed the implementation, you should be able to run this method and have
	// it complete without any runtime exceptions. While not an exhaustive test, this should be 
	// sufficient to help you uncover many of the boundary cases.
	public static void main(String[] args) {

		WordSymbolTable wl = new WordSymbolTable();
		validate(0, wl.size());
		validate("", wl.elements());           // empty word list must return ""
		validate(0, wl.count("nothing"));
		validate(false, wl.contains("this"));
		validate(true, wl.increment("test"));
		validate("(test,1)", wl.elements());       // no trailing or pre comma.
		validate(1, wl.count("test"));
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(1, wl.count("test"));
		validate(false, wl.increment("test"));       // when add TWICE, false is returned
		validate(2, wl.count("test"));

		validate(true, wl.remove("test"));     // can remove first element
		validate(false, wl.remove("test"));    // can't remove first empty
		validate(true, wl.increment("test"));
		validate(true, wl.increment("that"));
		validate(1, wl.count("test"));
		validate(false, wl.increment("that"));
		validate(2, wl.count("that"));
		validate(3, wl.totalCounts());
		validate(2, wl.size());
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("(that,2)", wl.elements());       // no trailing or pre comma.
		validate(true, wl.remove("that"));
	} 
}
