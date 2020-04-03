package imoralessirgo.hw1;

import algs.hw1.arraysearch.DiagonalArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class DiagonalArraySolution extends DiagonalArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public DiagonalArraySolution(int[][] a) {
		super(a);
	}

	/**
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		int high = n - 1;
		int low = 0;

		if(target%(n-1) > 0 ) { return null; }

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

		if(high < 0) { high = 0;} // avoid going to row -1


		int row = high; //store the diagonal with the value
		int[] hi = {n-1,n-1-row};
		int[] lo = {row+1,1};

		while (lo[0] <= hi[0]){
			int[] mid = {(hi[0]+lo[0])/2,(hi[1]+lo[1])/2};
			int temp = inspect(mid[0],mid[1]) - target;
			if(temp > 0){
				hi[0] = mid[0] - 1;
				hi[1] = mid[1] - 1;
			}else if(temp < 0){
				lo[0] = mid[0] + 1;
				lo[1] = mid[1] + 1;
			}else{
				return new int[] {mid[0],mid[1]};
			}
		}

		return null;
	}

	/** Be sure that you call your class constructor. Do not modify this method. */
	public static void main (String args[]) {
		int[][] ar = DiagonalArraySearch.create(13);
		new DiagonalArraySolution(ar).trial();
	}
}
