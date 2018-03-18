import java.util.*;
import java.awt.Point;

/**
 * Compute statistics on Percolation after performing T independent experiments on an N-by-N grid.
 * Compute 95% confidence interval for the percolation threshold, and  mean and std. deviation
 * Compute and print timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 * @author Josh Hug
 */

public class PercolationStats {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	private double[] myList;
	
	public PercolationStats(int N, int T) {
		if(N<=0 || T<=0) 
			throw new IllegalArgumentException("ILLEGAL ARGUMENT");
		
		double[] store = new double[T];
		myList = store;
		
		IUnionFind myFinder = new QuickUWPC();
		ArrayList<Point> myPtList = new ArrayList<>();
		
		for(int row=0; row<N; row++) {
			for(int col=0; col<N; col++) {
				Point pt = new Point(row,col);
				myPtList.add(pt);
			}
		}
		
		for(int i=0; i<T; i++) {
			IPercolate run = new PercolationUF(N, myFinder);
			Collections.shuffle(myPtList, ourRandom);
			for(Point p:myPtList) {
				run.open(p.x, p.y);
				if (run.percolates()) break;
			}
			myList[i] = new Double(run.numberOfOpenSites()) / new Double(N*N);
		}
	}
	
	public double mean() {
		return StdStats.mean(myList);
	}
	
	public double stddev() {
		return StdStats.stddev(myList);
	}
	
	public double confidenceLow() {
		return mean() - ((1.96*stddev()) / Math.sqrt(myList.length));
	}
	
	public double confidenceHigh() {
		return mean() + ((1.96*stddev()) / Math.sqrt(myList.length));
	}
	
	public static void main(String[] args) {
		double startTime = System.nanoTime();
		PercolationStats stats = new PercolationStats(20, 10);
		double endTime = System.nanoTime();
		double finalTime = (endTime-startTime)/1e9;
		System.out.printf("mean: %1.4f, time: %1.4f\n", stats.mean(),finalTime);
	}
}
