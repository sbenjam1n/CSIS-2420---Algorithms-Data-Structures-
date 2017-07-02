public class PercolationStats {
    private double experiments[];
    private int N;
   
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T)
    {
        experiments = new double[T];
        this.N = N;
        int t = 0;
        
        if (N <= 0)
        {
            throw new IllegalArgumentException();
        }
        if (T <= 0)
        {
            throw new IllegalArgumentException();
        }
        while (t < T)
        {
            Percolation percolation = new Percolation(N);
            boolean[][] grid = new boolean[N+1][N+1];
            int i = 0;
            while(true)
            {
                i++;
                if(true)
                {
                    int x = StdRandom.uniform(N) + 1;
                    int y = StdRandom.uniform(N) + 1;
                    if (grid[x][y]){
                        continue;
                }
                    
                else
                {
                        percolation.open(x, y);
                        grid[x][y] = true;
                        
                }
              }
                
                if (percolation.percolates())
                {
                    experiments[t] = (double)i / ((double)N * (double)N);
                    break;
                }
            }
            t++;
        }

    }
   
    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(experiments);
    }
   
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(experiments);
    }
   
    // returns lower bound of the 95% confidence interval
    public double confidenceLow()
    {
        return mean() - 1.96*stddev() / Math.sqrt(N);
    }
   
    // returns upper bound of the 95% confidence interval
    public double confidenceHigh()
    {
        return mean() + 1.96*stddev()/ Math.sqrt(N);
    }
   
    // test client, described below
    public static void main(String[] args)
    {	
    	System.out.println("Enter N:");
        int N = StdIn.readInt();
        System.out.println("Enter T:");
        int T = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(N, T);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval " + percolationStats.confidenceLow() + ", " + percolationStats.confidenceHigh());
    }
}
