import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;

public class Board 
{
	private final int N;
	private final int [][]blocks;
	private int nn;
	private int n ;
	private MinPQ<Board> pq = new MinPQ();
	private int[][] goal;
	private Board previous=null;
	private int zeroLoc;
	
	// construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) 
    {
    	n = blocks.length;//rows
    	
    	nn =  blocks[0].length;//columns
    	
    	N  = n * nn;//total blocks

    	this.blocks = blocks;

    	findGoal();
    	
    	isGoal();
    	
		pq.insert(this);
    	
    	pq.delMin().findNeighbors();
    }          
    


	// board size N   
    public int size()
    {
    	return N;
	
    }  
    
    // number of blocks out of place
    public int hamming()
    {
    	
    	
    	int d=0;
    	int index=1;
    	for (int i=0; i<n; i++)
    	{
    		for(int j=0; j<nn; j++)
    		{
    			if (blocks[i][j]==0)
    			{
    				continue;
    			}
    			
    			if (!(blocks[i][j]==index))
    			{
    				d++;
    			}
    			index++;
    		}
    	}
    	
    	return d;
	}         
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
     int d=0;

	 for (int i=0; i<n; i++)
    	{
    		for(int j=0; j<nn; j++)
    		{	
    			if (blocks[i][j]==0)
    			{
    				continue;
    			}
    			
    			if(goal[i][j]!=blocks[i][j])
    		    	d+= Math.abs(i - findGoal('r', blocks[i][j]))+Math.abs(j - findGoal('c', blocks[i][j]));
    		}
         }
	 
    	return d;
	}   
    
    //comparator for MinPQ, this might be backwards.
    public static Comparator<Board> byManhattan()
    {
    	return new Comparator<Board>()
        		{
					@Override
					public int compare(Board b1, Board b2)
					{
						if (b1.compareTo(b2)< 0) return 1;
						else if (b1.compareTo(b2)== 0)return 0;
						else return -1;
					}
        		};
    }
    
    //comparator for MinPQ, this might be backwards.
    protected int compareTo(Board b2)
    {
    	Integer b1b = manhattan();
    	Integer b2b = b2.manhattan();
    	
    	return b1b.compareTo(b2b);
	}

    //This is supposed to create an 2d array with the solution. 
	private void findGoal()
    {

    	
    		int k=1;
          	for (int i=0; i<n; i++)
        	{
        		for(int j=0; j<nn; j++)
        		{
        			goal = new int[n][nn];
        			goal[i][j]=k;
        			k++;
        		}
        	}	
    	
         
    	
    	goal[n-1][nn-1]=0;

    }
    
	//finds the row or column location of a value in blocks
    private int findGoal(char c, int r)
    {
      int k = 0;
		switch(c)
		{
		case'r':
          	for (int i=0; i<n; i++)
        	{
        		for(int j=0; j<nn; j++)
        		{
        			if(blocks[i][j]==r)
        			{
        				k=i;
        			}
        		}
        	}
		case 'c':	
          	for (int i=0; i<n; i++)
        	{
        		for(int j=0; j<nn; j++)
        		{
        			if(blocks[i][j]==r)
        			{
        				k=j;
        			}
        		}
		    }
		}
    	return k;
    }
    
    // is this board the goal board?
    public boolean isGoal() 
    {
    	if (hamming()==0)
    	{
    	 return true;
    	}
    	else
    	return false;
	}      
    
    // is this board solvable?//Check inversions
    public boolean isSolvable()
    {
    	int k=0;
      	for (int i=0; i<n; i++)
    	{
    		for(int j=0; j<nn; j++)
    		{
    			if (blocks[i][j]==0)
    			{
    				continue;
    			}
    			
    			if (goal[i][j]<blocks[i][j])
    			{
    				k++;
    				
    			}
    			
    		}
    	}; 
    	
    	if (N % 2 == 1) 
    	{
            return (k % 2 != 1);
    	}
    	
    	else if(N % 2 == 0) 
    	{

    		return ((k + zeroLoc) % 2 != 0);
    	}
    	
    	else return false;
    	
	} 
    

	public boolean equals(Object y) 
	{
		if (this == y)
			return true;
		if (y == null)
			return false;
		if (getClass() != y.getClass())
			return false;
		Board other = (Board) y;
		if (!Arrays.deepEquals(this.blocks, other.blocks))
			return false;
		return true;
	}       
    
    // all neighboring boards
    public Iterable<Board> neighbors()
    {
    	return pq;
    }    
    
    
    //Steven Benjamin 2420
    private void makeNeighbors(int n, int nn)
    {
     for (int i=0;i<=3; i++)
     {  
    	int[][] b = blocks;
        int zero = b[n][nn];
        int r=0;
        int c=0; 
        
        if(i==0){r=n+1; c=nn;}//up
        if(i==1){r=n-1; c=nn;}//down
        if(i==2){r=n; c=nn+1;}//right
        if(i==3){r=n; c=nn-1;}//left
        
	    if(inBounds(n,nn))
	    {
	    	b[n][nn] = b[r][c];
	    	b[r][c] = zero;
	    	
	    	Board brd = new Board(b);
	    	
	    	brd.setPrevious(this);
	    
    	    pq.insert(brd); 
	    }
      }
    }
    
    private void findNeighbors()
    {
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0; j <nn; j++)
    		{
    			if (blocks[i][j]==0)
    			{
    				zeroLoc=i;
    				makeNeighbors(i,j);
    			}
    		}
    	}
    }
    
    public void setPrevious(Board previous) 
    {
		this.previous = previous;
	}

    public Board getPrevious() 
    {
		return this.previous;
	}
    
	private boolean inBounds(int n, int nn) 
    {

        return n >= 0 && n < this.n && nn >= 0 && nn < this.nn;
    }
    
    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }              

	public static void main(String[] args) 
	{


	}


}
