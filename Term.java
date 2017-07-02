/*
 * Steven Benjamin
 * CSIS 2420
 */

import java.util.Arrays;
import java.util.Comparator;


public class Term implements Comparable<Term> {
	
	private String query;
	private double weight;
	
    // Initialize a term with the given query string and weight.
    public Term(String query, double weight)
    {
        if (query == null) throw new java.lang.NullPointerException();
        if (weight < 0) throw new java.lang.IllegalArgumentException();
    	
    	this.query=query;
    	this.weight=weight;
    }
    
	@Override
	public int compareTo(Term that) 
	{
		return getQuery().compareTo(that.getQuery());
	}

    // Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder()
    {
    	return new Comparator<Term>()
        		{
    				@Override
    				public int compare(Term arg0, Term arg1) 
    				{
    					double a = arg0.getWeight();
    					double b = arg1.getWeight();
    					
    					//Returns 1 when b is greater than a so the weights are evaluated in descending order.
    					if (a < b) return 1;
    					else if (a == b)return 0;
    					else return -1;
    				}
        		};
    }
    


    // Compare the terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(final int r)
    {
    	if(r<0) throw new java.lang.IllegalArgumentException();
    	return new Comparator<Term>()
    		{
				@Override
				public int compare(Term arg0, Term arg1) 
				{	
					int prefix = arg0.query.length() > arg1.query.length() ? 
								 arg1.query.length() 
								:arg0.query.length();
								 
					if (r < prefix) prefix = r;//If r is less the the shortest string, it's okay to use as the search prefix.
					
					String a = arg0.query.substring(0, prefix);
					String b = arg1.query.substring(0, prefix);
					
					if (a.compareTo(b)< 0) return 1;
					else if (a.compareTo(b)== 0)return 0;
					else return -1;
				}
			};
    	
    	
    }
	

	public double getWeight() 
	{
		return weight;
	}
	
	public String getQuery() 
	{
		return query;
	}

/* Testing
	public static void main(String[] args) 
	{

		Term term1 = new Term("lon",19);
		Term term2 = new Term("lpn",20);
	
		System.out.println(Term.byPrefixOrder(4).compare(term1, term2));
		System.out.println(Term.byReverseWeightOrder().compare(term1, term2));

	}*/
}

