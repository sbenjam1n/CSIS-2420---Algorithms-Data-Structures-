/*
 * Steven Benjamin
 * CSIS 2420
 */
import java.util.Comparator;



 



public class BinarySearchDeluxe 
{
	// Return the index of the first key in a[] that equals the search key, or -1 if no such key.
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) 
	{
		if (a == null || key == null || comparator == null) throw new java.lang.NullPointerException();
		if (a.length == 0) return -1;
		
		int getFirstIndex = binarySearch(a, key, comparator);
		if(getFirstIndex==-1) return -1;
		
        while(key.equals(a[getFirstIndex]) && getFirstIndex >= a.length) 
        { 
        	getFirstIndex--;
        }
        if(!(getFirstIndex==-1)) return getFirstIndex;
        else return -1;

	}

	// Return the index of the last key in a[] that equals the search key, or -1 if no such key.
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) 
	{
		if (a == null || key == null || comparator == null) throw new java.lang.NullPointerException();
		if (a == null || a.length == 0) return -1;
		
		int getLastIndex = binarySearch(a, key, comparator);
		if(getLastIndex==-1) return -1;
		
        while(key.equals(a[getLastIndex]) && getLastIndex < a.length) 
        { 
        	getLastIndex++;
        }
        
        if(!(getLastIndex==-1)) return getLastIndex;
        else return -1;
  
	}
	
    //Adapted from edu.princeton.cs.algs4.BinarySearch
    private static <Key> int binarySearch(Key[] a, Key key, final Comparator<Key> comparator) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {

        	// Key is in a[lo..hi] or not present.
        	int mid = lo + (hi - lo) / 2;
        	final int compare = comparator.compare(key, a[mid]);
        	
            if      (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

}
