import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    
    private boolean [] [] neighbors;
    private WeightedQuickUnionUF gridUf;
    
    private int length;
    private int cells;
    
    private int openCount;
    
    /**
    *
    * @param N , The number of cells in the grid
    */
    // create N­ by ­N grid, with all sites blocked
    public Percolation(int N)
    {
   	 neighbors  = new boolean [N] [N];
   	 
   	 //
   	 cells = N*N+2;
   	 length = N;
   	 
   	 gridUf = new WeightedQuickUnionUF(cells);
   	 
   	 for(boolean [] i : neighbors)
   	 { for(boolean j: i)
   	 {
   		 j = false;
   	 }}
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j) 
    {
   	 neighbors [i-1] [j-1] = true;
   	 openCount++;  
   	 
   	 if(i == 1)
   	 {
   		 gridUf.union(0, qfCell(i,j));
   		 //backwash.union(virtualTop, qfCell(i,j)); 
   		 //The backwash problem can be solved by either using a parallel uf object or tracking 
   		 //the state of each cell in a 2d boolean array, but my attempts at both were off and aren't included here.
   	 }
   	 
   	 if(i == length)
   	 {
   		 gridUf.union(cells-1, qfCell(i,j));
   	 }
   	 
   	 
   	 if((boundsCheck(i-1,j)))
   			 { if(isOpen(i-1,j))
   			 	{
   				 gridUf.union(qfCell(i-1 , j),qfCell(i , j));
   			 	}
   			 }
   	 if((boundsCheck(i+1,j))) if (isOpen(i+1,j)){gridUf.union(qfCell(i+1 , j),qfCell(i , j));}
   	 if((boundsCheck(i,j+1))) if (isOpen(i,j+1)){gridUf.union(qfCell(i , j+1),qfCell(i , j));}
   	 if((boundsCheck(i,j-1))) if (isOpen(i,j-1)){gridUf.union(qfCell(i , j-1),qfCell(i , j));}
    }
    
    private boolean boundsCheck(int i, int j)
    {
    	return ((i <= length && i > 0)&&(j <= length && j > 0));
    }
    
    // is site (row i, column j) open?
    boolean isOpen(int i, int j) 
    {
    	System.out.println(i+" "+j);

   	     return neighbors [i-1][j-1];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j)  
    {
   	 return neighbors [i][j];    
    }
    
    // does the system percolate?
    public boolean percolates()
    {
   	 return gridUf.connected(0 , cells-1);
    }
    
    //convert the grid coordinate to something the union method can use.
    private int qfCell(int i, int j)
    {
   	 return ((i-1)*length)+j;
    }


    public int numberOfOpenSites()
    {
   	 return openCount;
    }
    
}
