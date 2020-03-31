package imoralessirgo.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public RowOrderedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		// FIX ME: complete this method. Currently this causes an exception to be thrown
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
