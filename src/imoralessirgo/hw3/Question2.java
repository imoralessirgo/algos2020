package imoralessirgo.hw3;

import algs.hw3.InstrumentedSeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.IOException;


/**

This question explores the different structures that result from using binary search trees and separate
chaining hash table to support the Symbol Table API, which allows you to associate a (key, value) pair
for future retrieval.

Once again, using the Tale Of Two Cities data set, modify this program to produce the following table.
This question is based on the number of comparisons needed to locate each key in the symbol table.

  * For Binary Search Trees, the depth of a node reflects the distance from the root, and this depth
    is one value smaller than the total number of comparisons needed to locate that node in the tree
  * The same is true of AVL trees
  * For Separate Chaining Hash Symbol Tables, each of the buckets stores its size, and each of these
    will contribute to the overall values.
    
For example, If there were five (key, value) pairs stored in the symbol table implemented as above 
as follows (note that only the keys are shown).

      BST                          AVL                  HT

      B                             C                [0] -> E, A, C
     / \                           / \               [1]
    A   C                         B   E              [2] -> B
         \                       /   /               [3]
          D                     A   D                [4] -> D
           \                                         [5]
            E

 Then # of comparisons to find each one is (note that hashing doesn't count as a compare):
 
    A - 2                       A - 3                A - 2
    B - 1                       B - 2                B - 1
    C - 2                       C - 1                C - 3
    D - 3                       D - 3                D - 1
    E - 4                       E - 2                E - 1

Thus average is:

   12.5/5 = 2.5                 11/5 = 2.2           8/5 = 1.6

Your task is to complete this experiment using the Tale Of Two Cities data set to record the count of
each word in a symbol table. You will have to use put (key, value) properly. That is, for the first 
occurrence of a given word, w, you would put (w, 1) -- hint: how would you determine this using the existing API? 
For each subsequent occurrence of a given word, just increment the value associated with w by calling put() with a
count that is one greater. With the above data set, this is what you would output

N      1,   2,   3,   4  
#BST   1,   2,   1,   1
#AVL   1,   2,   2  
#HT    3,   1,   1

AVG. BST Depth:2.5
AVG. AVL Depth:2.2
AVG. HT  Depth:11.6

- - - - - - - - - - - - - - - - - BELOW IS THE SAMPLE OUTPUT YOU ARE TO MATCH - - - - - - - - - - - - - - - - - - - -

There are 10650 unique words.
The Height of the BST is 30
The Height of the AVL is 15

N      1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  21,  22,  23,  24,  25,  26,  27,  28,  29
#BST   1,   2,   4,   8,  15,  30,  57,  98, 159, 252, 383, 549, 740, 886, 997,1085,1053,1024, 955, 793, 583, 391, 273, 157,  79,  37,  20,  10,   7,   2
#AVL   1,   2,   4,   8,  16,  32,  64, 128, 256, 512,1020,1944,2904,2583,1066, 110
#HT 2035,1977,1820,1549,1204, 867, 557, 331, 166,  92,  40,  11,   1

AVG. BST Depth:15.509671361502347
AVG. AVL Depth:11.716338028169014
AVG. HT Depth:3.6068544600938965

*/
public class Question2 {
	public static void main(String[] args) throws IOException {
		BST b = new BST();
		AVL avl = new AVL();
		InstrumentedSeparateChainingHashST<String, Integer> hashST = new InstrumentedSeparateChainingHashST<>();
		
		// Now process the data from Tale Of Two Cities and use these three different symbol tables 
		// to build up the frequency of each word. When you are done, there should be a total
		// of 10,650 unique keys in each of these symbol tables, and the Integer counts would be the 
		// frequency of occurrence.
		// FILL IN HERE...


		for(int i = 1; i <= 45 ; i++) {
			TaleOfTwoCitiesExtractor te = new TaleOfTwoCitiesExtractor(i);
			for (String word : te) {

				if(hashST.contains(word)){
					avl.put(word, b.get(word) + 1);
					b.put(word, b.get(word) + 1);
					hashST.put(word,hashST.get(word) + 1);
				}else {
					b.put(word, 1);
					avl.put(word, 1);
					hashST.put(word, 1);
				}
			}
		}


		System.out.println("There are " + b.size() + " unique words.");
		System.out.println("The Height of the BST is " + b.height());
		System.out.println("The Height of the AVL is " + avl.root.height);

		SeparateChainingHashST<Integer, Integer> bCollection = b.collect();
		SeparateChainingHashST<Integer, Integer> avlCollection  = avl.collect();



		// Here is where you will generate output that looks like ....
		System.out.print("N   ");
		for (int n = 1; n <= 30; n++) {
			System.out.print(String.format("%4d,", n));
		}
		System.out.println();

		double bCount = 0;
		double avlCount = 0;
		double hashCount = 0;

		// now output a row for each of the #BST, #AVL, #HT
		System.out.print("BST ");
		for (int n = 0; n < b.height(); n++) {
			System.out.print(String.format("%4d,", bCollection.get(n)));
			bCount +=  bCollection.get(n)*(n+1);
		}
		System.out.println();
		System.out.print("AVL ");
		for (int n = 0; n < avl.root.height; n++) {
			System.out.print(String.format("%4d,", avlCollection.get(n)));
			avlCount +=  avlCollection.get(n)*(n+1);
		}
		System.out.println();


		int i = 0;
		SeparateChainingHashST<Integer,Integer> htMap = new SeparateChainingHashST<>();
		boolean flag = true;
		int count = 0;
		while(flag) {
			flag = false;
			for(int j = 0; j < hashST.m; j++){
				if(hashST.st[j].size() > i){
					count++;
					flag = true;
				}
			}
			i++;
			htMap.put(i,count);
			count = 0;
		}

		System.out.print("HT  ");
		for (int k = 1; k < htMap.size(); k++) {
			System.out.print(String.format("%4d,", htMap.get(k)));
			hashCount +=  htMap.get(k) * k;
		}
		System.out.println();




		System.out.println();
		System.out.println("AVG. BST Depth: ... " + bCount/b.size());
		System.out.println("AVG. AVL Depth: ... " + avlCount/b.size());
		System.out.println("AVG. HT Depth: ... " + hashCount/b.size());
		
	}
}
