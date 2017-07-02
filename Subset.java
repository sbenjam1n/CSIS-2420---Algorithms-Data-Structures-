public class Subset 
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        int l = Integer.parseInt(args[0]);
        
        RandomizedQueue q = new RandomizedQueue();
        
        while(!StdIn.isEmpty()) 
        {
            q.enqueue(StdIn.readString());
        }
       
        for(int i=0;i<l;l++)
        {
            StdOut.println(q.dequeue());

        }
    }
}
