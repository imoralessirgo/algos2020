package imoralessirgo.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 * 
 * This class will be used by Question Q1 on Homework2.
 */
public class WordList {
	private Node node;
	private int size;

	/** 
	 * Leave this Node class as is. While you don't need to make changes to this class,
	 * it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String     word;
		Node       next;

		Node(String w) {
			this.word = w;
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
		return this.node;
	}

	private void incrementSize(){
		this.size += 1;
	}


	public boolean isEmpty(){
		return this.node == null;
	}


	private void decrementSize(){
		this.size -= 1;
	}

	/**
	 * If the given element doesn't exist in the set then update 
	 * the set and return true; otherwise return false. This means that
	 * adding a duplicate element to a set must return false.
	 * 
	 * @param elt      element to be added.
	 */
	public boolean add(String elt) {
		if (this.isEmpty()){ this.setNode(new Node(elt)); return true; }
		else if(!this.contains(elt)){
			Node n = this.getNode();
			while(n.hasNext()){
				n = n.next;
			}
			n.next = new Node(elt);
			this.incrementSize();
			return true;
		}
		return false;
	}

	/** Returns the number of elements in the set. */ 
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if the given element was in the set (and was removed) or 
	 * false if the given element did not belong to the set.
	 * @param elt      element to be removed.
	 */
	public boolean remove (String elt) {
		if(this.getNode() != null){
			Node n = this.getNode();
			while(n.word != elt){
				n = n.next;
				if(n.next == null){ return false; }

			}
			if(size == 1){

				this.node = null;
				this.size = 0;
				return true;
			}
			n.word = n.next.word;
			n.next = n.next.next;
			this.decrementSize();
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the element exists within the collection.
	 * @param elt      target element to be searched.
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

	/** For debugging, return comma-separated string of elements. */
	public String elements() {
		String result = "";
		if(this.getNode() != null){
			Node n = this.getNode();
			if(size == 1){ return this.node.word; }
			while(n.next != null){
				result += n.word;
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

		WordList wl = new WordList();
		validate(0, wl.size());
		validate("", wl.elements());           // empty word list must return ""
		validate(false, wl.contains("this"));
		validate(true, wl.add("test"));
		validate("test", wl.elements());       // no trailing or pre comma.
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(false, wl.add("test"));       // can't add twice
		validate(true, wl.remove("test"));     // can remove first element
		validate(false, wl.remove("test"));    // can't remove first empty
		validate(true, wl.add("test"));
		validate(true, wl.add("that"));
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("that", wl.elements());       // no trailing or pre comma.
		validate(true, wl.remove("that"));
	} 
}
