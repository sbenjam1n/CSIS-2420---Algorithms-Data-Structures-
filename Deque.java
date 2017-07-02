/*
 * Steven Benjamin
 * CSIS 2420
 * Assignment 2
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    
	private int N;     	// number of elements on queue
	private Node first;	// beginning of queue
	private Node last; 	// end of queue

    
	private class Node // helper linked list class
	{
   	 
    	private Item item;
    	private Node next;
    	private Node nextToLast;
   	 
	}
    
    
    public Deque()// construct an empty deque
   {
   	 
   	first = null;
   	last  = null;
   	N = 0;
       
   }                     	 
   
    public boolean isEmpty() // is the deque empty?
   {
   	 
   	return first == null;
       
   }         	 
    
   public int size() // return the number of items on the deque
   {
    
   	return N;    
       
   }                	 
   
   public void addFirst(Item item)// insert the item at the front
   {
   	if (item == null) throw new java.lang.NullPointerException("Don't add null items.");
       
   	Node oldfirst = first;
   	first = new Node();
   	first.item = item;
   	first.next = oldfirst;
  	 
   	if (isEmpty()) first = last;
  	 
   	N++;
       
   }    	 
   
   public void addLast(Item item)// insert the item at the end
   {
       
   	if (item == null) throw new java.lang.NullPointerException("Don't add null items.");
       
   	Node oldlast = last;
   	last = new Node();
   	last.item = item;
   	last.nextToLast=oldlast;

 	 
   	if (isEmpty()) first = last;
   	else oldlast.next = last;
  	 
   	N++;
       
   }    	 
   
   public Item removeFirst()  // delete and return the item at the front
   {
       
   	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
  	 
   	Item item = first.item;
   	first = first.next;
   	N--;
 	 
   	if (isEmpty()) last = null;   // to avoid loitering
 	 
   	return item;
       
   }        	 
   
   public Item removeLast()// delete and return the item at the end
   {
       
   	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
  	 
   	Item item = last.item;
   	last = last.nextToLast;
   	N--;
 	 
   	if (isEmpty()) last = null;   // to avoid loitering
 	 
   	return item;
       
   }          	 
   
   public Iterator<Item> iterator() // return an iterator over items in order from front to end
   {
       
   	return new ListIterator();
       
   }   	 
   
   private class ListIterator implements Iterator<Item>
   {
       
   	private Node current = first;

   	public boolean hasNext()  { return current != null;}
   	public void remove()  	{ throw new UnsupportedOperationException();  }

   	public Item next()
   	{
       	if (!hasNext()) throw new NoSuchElementException();
       	Item item = current.item;
       	current = current.next;
     	 
       	return item;
   	}
   }
   
   @SuppressWarnings("unchecked")
   public static void main(String[] args)// unit testing
   {
       
     Deque<Integer> q = new Deque<Integer>();
    
     Integer[] s = new Integer[]{1,2,3,4,5,6,7,8,9};
     int k  = s.length;
    
 	for(int i=0; i<k; i++)
 	{
   	  q.addFirst(s[i]);
    }
 	
 	for(int j : q)
 	{
   	  StdOut.println(q.removeFirst());
    }
	 
 	for(int i=0; i<k; i++)
 	{
   	  q.addLast(s[i]);
    }
 	
 	for(int j : q)
 	{
   	  StdOut.println(q.removeLast());
    }
	 
   }

}
