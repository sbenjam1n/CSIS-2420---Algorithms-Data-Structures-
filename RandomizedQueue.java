/*
 * Steven Benjamin
 * CSIS 2420
 * Assignment 2
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item>
{
    Item[] randeque;
    private int N;
    
   public RandomizedQueue()// construct an empty randomized queue
   {
       
   	randeque = (Item[]) new Object[2];
       
   }
   
   public boolean isEmpty() // is the queue empty?
   {
       
   	return randeque == null;
       
   }
   
   public int size()// return the number of items on the queue
   {
       
   	return N;
       
   }
   
   public void enqueue(Item item)// add the item
   {
	   
   	if (item == null) throw new java.lang.NullPointerException("Don't add null items.");
    
   	N++;
   	resize(size());
   	randeque[N-1] = item;
       
   }
   
   public Item dequeue()// delete and return a random item
   {
   	if(isEmpty()) throw new java.util.NoSuchElementException();
  	 
   	int random = StdRandom.uniform(N);
   	Item item = randeque[random];
  	 
   	if(random != N-1) randeque[random] = randeque[N-1];
   	randeque[N-1] = null;
   	N--;
  	 
   	resize(randeque.length);
   	return item;
       
   }
   
   public Item sample()// return (but do not delete) a random item
   {
   	if(isEmpty()) throw new java.util.NoSuchElementException();
       
   	return randeque[StdRandom.uniform(N)];
       
   }
   
   
   public Iterator<Item> iterator()// return an independent iterator over items in random order
   {
       
   	return new ListIterator();
       
   }
    	 
   
   private class ListIterator implements Iterator<Item>
   {
       
  	private int index;
  	private Item[] items = (Item[]) new Object[N];
 	 
  	public ListIterator()
  	{
  		for (int i=0; i < N;i++) {items[i] = randeque[i];}
	 
  		StdRandom.shuffle(items);
  	}
 	 

   	   
   	public boolean hasNext()  {  return index < N;}
   	public void remove()  	{ throw new UnsupportedOperationException();  }

   	public Item next()
   	{
       	if(!hasNext()) throw new java.util.NoSuchElementException();
      	 
       	Item  item = items[index++];
       	return item;

   	}
   }
   
   private void resize(int size)
   {
    if(N>0 && N == size/4) {size=size/2;}
    
    else if(N == size) {size=2*size;}
       
    
    Item[] resized = (Item[]) new Object[size];
    
	for(int i=0; i<size(); i++)
	{
    	resized[i] = randeque[i];
	}
       
	randeque = resized;
    
   }
   
   @SuppressWarnings("unchecked")
public static void main(String[] args)// unit testing
{
       
   
     RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    
     Integer[] s = new Integer[]{1,2,3,4,5,6,7,8,9};
     int k  = s.length;
    
 	for(int i=0; i<k; i++)
 	{
   	  q.enqueue(s[i]);
   	 
       }
	 
 	for(int i=0; i<k; i++)
 	{
   	  StdOut.println(q.dequeue());
       }
	 
   }
}
