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
		int n = this.length();
		int high = n - 1;
		int low = 0;

		// find row with value
		while (low <= high){
			int mid = (high+low)/2;
			int temp = inspect(mid,0) - target;
			if(temp > 0){
				high = mid - 1;
			}else if(temp < 0){
				low = mid + 1;
			}else{
				return new int[] {mid,0};
			}
		}

		int row = high; // store row

		// reset values for binary search in row
		high = row;
		low = 1;
		while (low <= high){
			int mid = (high+low)/2;
			int temp = inspect(row,mid) - target;
			if(temp > 0){
				high = mid - 1;
			}else if(temp < 0){
				low = mid + 1;
			}else{
				return new int[] {row,mid};
			}
		}


		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
