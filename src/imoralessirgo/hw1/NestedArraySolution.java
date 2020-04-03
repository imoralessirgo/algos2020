package imoralessirgo.hw1;

import algs.hw1.arraysearch.ArraySearch;
import algs.hw1.arraysearch.NestedArraySearch;

enum line{
	SOUTHEAST,
	WEST,
	NORTH
}

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class NestedArraySolution extends NestedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public NestedArraySolution(int[][] a) {
		super(a);
	}



	public int[] verticalSearch(int target, int[] lo, int[] hi){
		while (lo[0] <= hi[0]){
			int mid = (hi[0]+lo[0])/2;
			int temp = inspect(mid,hi[1]) - target;
			if(temp > 0){
				hi[0] = mid - 1;
			}else if(temp < 0){
				lo[0] = mid + 1;
			}else{
				return new int[] {mid,hi[1]};
			}
		}
		return null;
	}

	public int[] horizontalSearch(int target, int[] lo, int[] hi){
		while (lo[1] <= hi[1]){
			int mid = (hi[1]+lo[1])/2;
			int temp = inspect(hi[0],mid) - target;
			if(temp > 0){
				hi[1] = mid - 1;
			}else if(temp < 0){
				lo[1] = mid + 1;
			}else{
				return new int[] {hi[0],mid};
			}
		}
		return null;
	}

	public int[] diagonalSearch(int target, int[] hi, int[] lo){

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


	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();

		int level = -1;
		for(int i = 0, j= 0; ;i += 2, j++){
				int result = inspect(i,j) - target;
				if(result > 0 && level < 0){
					return null;
				}else if(result > 0){
					 break;
				}else if (result < 0){
					level++;
				}else{
					return new int[] {i,j};
				}
		}

		line l = null;
		int[] topCorner = {2*level,level};
		int[] bottomRight = {n-1-topCorner[1],n-1-topCorner[0]};
		int[] bottomLeft = {n-1-topCorner[1],topCorner[1]};

		int result = inspect(bottomRight[0],bottomRight[1]) - target;
		if(result > 0){
			 l = line.SOUTHEAST;
		}else if(result < 0){
			result = inspect(bottomLeft[0],bottomLeft[1]) - target;
			if(result > 0){
				l = line.WEST;
			}else if(result < 0){
				l = line.NORTH;
			}else{
				System.out.println("found " + target + " at [" + bottomLeft[0] + ", " + bottomLeft[1] + "]");
				return bottomLeft;
			}
		}else{
			System.out.println("found " + target + " at [" + bottomRight[0] + ", " + bottomRight[1] + "]");
			return bottomRight;
		}

		switch(l){
			case NORTH:
				return verticalSearch(target,
						new int[] {bottomLeft[0] - 1, bottomLeft[1]},
						new int[] {topCorner[0] + 1, topCorner[1]});
			case WEST:
				return horizontalSearch(target,
						new int[] {bottomRight[0], bottomRight[1] - 1},
						new int[] {bottomLeft[0], bottomLeft[1] + 1});
			case SOUTHEAST:
				return diagonalSearch(target,
						new int[] {topCorner[0] + 1,topCorner[1] + 1},
						new int[] {bottomRight[0] - 1, bottomRight[1] - 1});
			default:
				return null;
		}

	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = NestedArraySearch.create(13);
		new NestedArraySolution(ar).trial();
	}
}
