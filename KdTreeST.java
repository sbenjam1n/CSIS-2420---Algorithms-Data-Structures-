//Steven Benjamin KDtrees 2420 Summer 2016

import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;


public class KdTreeST<Value> 
{
	private int size;
	private boolean dimension = true;//True if splitting x, left/right, 
							         //false if splitting y, above/below.
	
    private Queue<Point2D> inRange;
	
	
	private class Node //The node implementation suggested in the checklist.
	{		
		private Point2D p;      // the point    
		private RectHV rect;    // the axis-aligned rectangle corresponding to this node
	    private Node lb;        // the left/bottom subtree
	    private Node rt;        // the right/top subtree
	    private boolean d;

	    public Node(Point2D point, boolean dm) 
	    {
	           p = point;  
	           d = dm;
		}
	}
	
	private Node root; //The root node of the tree

   public boolean isEmpty() 
   {
      return root == null;
   }
   
   public int size() //Size gets incremented each time a node is added.
   {
      return size;
   }
   
   public void insert(Point2D p)
   {
	  if(isEmpty())
	  {
		  root = new Node(p, dimension);
		  root.rect = new RectHV(0,0,1,1); 
		  size++;
	  }
	  
	  else
	  {
		insert(p, root, root.d); 
	  }
   }
   
   
   private Node insert(Point2D p, Node node, boolean d) 
   {
	    if(node==null)//Stop and create the new node.
	    {
	       size++;
	       return new Node(p, d);
	    }
	    
	    double cmpr = compare(p, node.p, node.d);// Compare based on dimension.
	    
	    if(cmpr == 0)
	    {
	    	node.p = p;
	    	return node;
	    }
	    
	    else if(cmpr < 0)//If the compared dimension is less than node's, go left. 			   
	    {
	    	node.lb = insert(p, node.lb, rotate(node.d));
	    	
	    	node.lb.rect = node.d ? 
	    							new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax()) //close by x max if comparing x value.
	    						  : new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());//close by y max if comparing y value.
	    	return node;
	    }
	    
	    else//go right
	    {
	    	node.rt = insert(p,node.rt, rotate(node.d));
	    	
	    	node.rt.rect = node.d ? 
	                                new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax()) //close by x min if comparing x value.
				                  : new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());//close by y min if comparing y value.
					
	    	return node;
	    }
   }
   
   private double compare(Point2D a, Point2D b, boolean d)
   {
	   double i,j;
	   if(d==true){i = a.x(); j=b.x();} 
	   else { i = a.y(); j=b.y();}
	   
	   if(i>j)return 1;
	   if(i<j)return -1;
	   else return 0;
   }

   private boolean rotate(boolean d)
   {   
	   if(d==true) return false;
	   else return true;
   }
   
   public Value get(Point2D p)
   {
      return get(p, root);
   }
   

   private Value get(Point2D p, Node node)//Similar to the insert method in the way the comparisons move through the tree.
   {
	    if(node==null)
	    {
	       return null;//Returns null if the value hasn't been inserted.
	    }
	    
	    double cmpr = compare(p, node.p, node.d);
	    
	    if(cmpr==0) return (Value) node.p;
	    
	    else if(cmpr < 0) return get(p, node.lb);
	    
	    return get(p, node.rt);
   }
   
   // does the symbol table contain point p? 
   public boolean contains(Point2D p)
   {
      return get(p, root)!=null;
   }
   
   public Iterable<Point2D> range(RectHV rect) 
   { 

   	   inRange = new Queue<Point2D>();
	   findRange(rect, root);
	   
       return inRange;
       
   }
   
   private void findRange(RectHV rect, Node node) //Recursively check child nodes to see if the query rect intersects 
   								                  //the node's rectangle and adds it to the inRange queue.
   {
       if(rect.intersects(node.rect))
       {
    	   inRange.enqueue(node.p);
       }
       
       if(!rect.intersects(node.rect))
       {
    	   return;
       }
       
       if(node.lb != null)
       {
    	findRange(rect, node.lb);   
       }
       
       if(node.rt != null)
       {
       	findRange(rect, node.rt);    
       }
 
   }

   public Point2D nearest(Point2D p) 
   { 
	   //Look at each subtree to find the nearest point to the query point.
	   //Check the distance to each subtree and ignore the subtree with the larger distance.
   		return nearest(root, p);
   }

   
   private Point2D nearest(Node node, Point2D p)
   {
	   Point2D nearestPoint = root.p;
	   double distance = nearestPoint.distanceSquaredTo(p);
	   Point2D successor;//next possible nearest point.
	   
	   double cmpr = compare(p, node.p, node.d);
	   
	   if(cmpr < 0)//Left
	     {   
		   if(node.lb!=null)//If there is a left node, see if the next nearest point is in the left subtree.
		   {	
			   successor = nearest(node.lb, p);
			   if(successor.distanceSquaredTo(p) < distance)
			   {
				   nearestPoint = successor;
				   distance = successor.distanceTo(p);
			   }
		  }
	      
	      if(node.rt!=null)//Make sure the nearest point isn't in the right subtree.
		     if(node.rt.rect.distanceSquaredTo(p) < distance)
		     {
				 successor = nearest(node.rt, p);
				 if(successor.distanceSquaredTo(p) < distance)
				 {
					 nearestPoint = successor;
					 distance = successor.distanceTo(p);
				 }
		     }
           }  
	    
	   
	    else //Right
   	    {
			   if(node.rt!=null)//If there is a right node, see if the next nearest point is in the right subtree.
			   {	
				   successor = nearest(node.lb, p);
				   if(successor.distanceSquaredTo(p) < distance)
				   {
					   nearestPoint = successor;
					   distance = successor.distanceTo(p);
				   }
			  }
		    
		      if(node.rt!=null)//Make sure the nearest point isn't in the left subtree.
			     if(node.lb.rect.distanceSquaredTo(p) < distance)
			     {
					 successor = nearest(node.rt, p);
					 if(successor.distanceSquaredTo(p) < distance)
					 {
						 nearestPoint = successor;
						 distance = successor.distanceTo(p);
					 }
			     }
	           } 
	   
	      return nearestPoint;
   	     }   
  
   // public static void main(String[] args)  {}
   	 }
