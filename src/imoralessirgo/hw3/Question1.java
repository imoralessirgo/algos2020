package imoralessirgo.hw3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {


	public static void main(String[] args) {

		int T = 100;
		StdOut.println("N    MaxComp    MaxExch");
		for (int i= 16; i <= 4096; i*= 2) {

			int maxExch = 0;
			int maxComp = 0;
			for (int j = 0; j < T; j++) {
				Heap.constructHeap(getDataDomain(i));
				if (Heap.comp > maxComp) { maxComp = Heap.comp; }
				if (Heap.exch > maxExch) { maxExch = Heap.exch;	}
			}
			StdOut.printf("%d     %d      %d%n", i, maxComp, maxExch);
		}
	}

	static Double[] getDataDomain(int n) {
		Double[] arr = new Double[n];
		for (int i = 0; i < n; i++) {
			arr[i] = StdRandom.uniform();
		}
		return arr;
	}

}
