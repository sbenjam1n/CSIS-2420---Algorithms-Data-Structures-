//Steven Benjamin KDtrees 2420 Summer 2016

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> 
{

	private RedBlackBST table;
	
	// construct an empty symbol table of points 
    public PointST()
    {
    	
          table = new RedBlackBST<Point2D, Value>();
 
    }
   
    // is the symbol table empty? 
    public boolean isEmpty() 
    {
    	
       return table.isEmpty();
       
    }
    
    // number of points
    public int size() 
    {
    	
       return table.size();
       
    }
    
    // associate the value val with point p
    public void insert(Point2D p, Value v)
    {
    	
       table.put(p,v);
       
    }
    
    // value associated with point p 
    public Value get(Point2D p)
    {
    	
       return (Value) table.get(p);
       
    }
    
    // does the symbol table contain point p? 
    public boolean contains(Point2D p)
    {
    	
       return table.contains(p);
       
    }
    
     // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) 
    {
        Queue<Point2D> queue = new Queue<Point2D>();
        
        for (Point2D p : points()) 
        {
            if (rect.contains(p)) 
            {
                queue.enqueue(p);
            }
     }
        return queue;
    }

    // all points in the symbol table 
    public Iterable<Point2D> points()
    {
		return (Iterable<Point2D>) table;	
    }

    // a nearest neighbor to point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p) 
    {
        double distance = Double.MAX_VALUE;
        Point2D nearest = null;
        
        for (Point2D point : points()) 
        {
            if (p.distanceTo(point) < distance) 
            {
                distance = p.distanceTo(point);
                nearest = point;
            }

        }
        return nearest;
    }

   // public static void main(String[] args)  {}
}


