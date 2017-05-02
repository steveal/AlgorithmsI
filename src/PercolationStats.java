import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
   int trials;
   int n;
   double[] percent;
   double dMean;
   double dStddev;
   double confidenceLo;
   double confidenceHi;
   public PercolationStats(int n, int trials)    {
	   this.n = n;
	   this.trials = trials;
	   percent= new double[trials];
	   for(int i =0; i < this.trials; i++) {
		   Percolation p = new Percolation(n);
		   while(!p.percolates()) {
			   int row = StdRandom.uniform(1, n+1);
			   int col = StdRandom.uniform(1, n+1);
//			   System.out.println(row + "," + col);
			   p.open(row, col);
		   }
		   percent[i] = (double) p.numberOfOpenSites()/(n*n);
		   
//		   System.out.println(i);
	   }
	   
	   dMean = StdStats.mean(percent); 
	   dStddev =StdStats.stddev(percent); 
	   double value = 1.96* this.dStddev /Math.sqrt(this.trials);
	   confidenceLo = dMean - value;
	   confidenceHi = dMean + value;
   }
   public double mean() {
	  
	   return this.dMean;
   }
   
   public double stddev() {
	  
	  return this.dStddev;
   }
   
   public double confidenceLo() {
	   return confidenceLo;
   }
   public double confidenceHi() {
	   return confidenceHi;
   }
//
   public static void main(String[] args)   {
	   Stopwatch timer1 = new Stopwatch();
	   PercolationStats p = new PercolationStats(200,10000);
	   System.out.println(p.mean());
	   System.out.println(p.stddev());
	   System.out.println(p.confidenceLo());
	   System.out.println(p.confidenceHi());
	   double time1 = timer1.elapsedTime();
	   System.out.println(time1);
	   
   }
}