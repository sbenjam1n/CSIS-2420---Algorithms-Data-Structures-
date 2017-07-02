/*
 * Steven Benjamin
 * CSIS 2420
 */

import java.util.Arrays;


public class Autocomplete 
{
  public Term[] terms;

  // Initialize the data structure from the given array of terms.
  public Autocomplete(Term[] terms) 
  {
	nullCheck(terms);
  	this.terms = terms;
  	Arrays.sort(terms);
  }

  //Return all terms that start with the given prefix, in descending order of weight.
  public Term[] allMatches(String prefix) 
  {
	nullCheck(prefix);
		  
  	Term pf = new Term(prefix, 0);
  	
  	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, pf, Term.byPrefixOrder(prefix.length()));
  	int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, pf, Term.byPrefixOrder(prefix.length()));
  	int index = firstIndex;
  	
  	nullCheck(firstIndex);
  	nullCheck(lastIndex);
		
  		Term[] matches = new Term[lastIndex - firstIndex + 1];

	    for (int i = 0; i < lastIndex - firstIndex; i++)
	    {
	        matches[i] = this.terms[index];
	        index++;
	    }
	    
		Arrays.sort(matches, Term.byReverseWeightOrder());
		return matches;

  }

  //Return the number of terms that start with the given prefix.
  public int numberOfMatches(String prefix) 
  {
	nullCheck(prefix);
	  
  	Term pf = new Term(prefix, 0);
  	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, pf, Term.byPrefixOrder(prefix.length()));
  	int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, pf, Term.byPrefixOrder(prefix.length()));
	
  	return firstIndex - lastIndex + 1;
		
  }

  
  private <T> void nullCheck(T t)
  {
	  	if (t == null) {throw new java.lang.NullPointerException();}
  }
  
  private <T> void nullCheck(T[] t)
  {
	  	if (t == null) {throw new java.lang.NullPointerException();} 
  }
  
  private void nullCheck(int t)
  {
	  	if (t == -1) {throw new java.lang.NullPointerException();} 
  }
  
//Testing
  public static void main(String[] args) {

  // read in the terms from a file
  String filename = args[0];
  In in = new In(filename);
  int N = in.readInt();
  Term[] terms = new Term[N];
  for (int i = 0; i < N; i++) {
      double weight = in.readDouble();       // read the next weight
      in.readChar();                         // scan past the tab
      String query = in.readLine();          // read the next query
      terms[i] = new Term(query, weight);    // construct the term
  }

  // read in queries from standard input and print out the top k matching terms
  int k = Integer.parseInt(args[1]);
  Autocomplete autocomplete = new Autocomplete(terms);
  while (StdIn.hasNextLine()) {
      String prefix = StdIn.readLine();
      Term[] results = autocomplete.allMatches(prefix);
      for (int i = 0; i < Math.min(k, results.length); i++)
          StdOut.println(results[i]);
  }
}
}
