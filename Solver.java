//Steven Benjamin 2420
import java.awt.List;
import java.util.ArrayList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver 
{	

	 private Board initial;
	 private ArrayList<Board> movesList = new ArrayList<Board>();
	 

	// find a solution to the initial board (using the A* algorithm)
		public Solver(Board initial)
		{
			this.initial=initial;
			MinPQ pq= new MinPQ(initial.byManhattan());
			
			if(initial.isGoal()){System.out.println(initial.toString() + "\n\n Solved in "+ moves() + "moves.\n");}
			
			else if(!initial.isSolvable()){}
		
			else 
			{
				System.out.println(initial.toString());
				
				if(!initial.isGoal())
				{
					pq = (MinPQ<Board>) initial.neighbors();
			
					Board next = (Board) pq.min();
				
					Solver nextBoard = new Solver(next);
				}
		    }         
		}
		
		// sequence of boards in a shortest solution
		public int moves()
		{
			int k = 0;
			
			Board b = initial;
			
			while(b.getPrevious()!=null)
			{
				k++;
				
				movesList.add(b);
				b = b.getPrevious();	
			}
			
			return k;
			
		}
		
		// sequence of boards in a shortest solution
	    public Iterable<Board> solution()
	    {
			return movesList;
	    }
	    
	    // solve a slider puzzle (given below) 
	    public static void main(String[] args)
	    {
	    	
	    	int[][] test = { { 0,  1,  2,  3}, 
	    			         { 5,  6,  7,  4 }, 
	    			         { 9, 10, 11,  8 },
	    			         {13, 14, 15, 12 } };

	    	Board b = new Board(test);
	    	
	    	Solver solve = new Solver(b);
	    	
	    }
	}
		

