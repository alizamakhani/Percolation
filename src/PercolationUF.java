/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which the open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 *
 */

public class PercolationUF implements IPercolate {
	public final int OUT_BOUNDS = -1;
	public boolean[][] myGrid;
	public IUnionFind myFinder;
	public int numOpenSites;
	private final int VTOP;
	private final int VBOTTOM;

	/**
	 * Constructs a Percolation object for a nxn grid that that creates
	 * a IUnionFind object to determine whether cells are full
	 */
	public PercolationUF(int size, IUnionFind finder) {
		// TODO complete PercolationUF constructor
		myGrid = new boolean[size][size];
		finder.initialize(size*size + 2);
		myFinder=finder;
		VTOP = size*size;
		VBOTTOM = size*size + 1;	
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	public int getIndex(int row, int col) {
		if(row>=myGrid.length || row<0 || col>=myGrid.length || col<0) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return myGrid.length*row + col;
	}

	@Override
	public void open(int i, int j) {
		if (i>=myGrid.length || i<0 || j>=myGrid.length || j<0) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		if (myGrid[i][j] != true) {
			myGrid[i][j] = true;
			numOpenSites+=1;
			if (i==0) myFinder.union(getIndex(i,j), VTOP);
			if (i==myGrid.length-1) myFinder.union(getIndex(i,j), VBOTTOM);
			connect(i,j);
		}
	}
	
	@Override
	public boolean isOpen(int i, int j) {
		if (i>=myGrid.length || i<0 || j>=myGrid.length || j<0) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return myGrid[i][j]==true;
	}

	@Override
	public boolean isFull(int i, int j) {
		if (i>=myGrid.length || i<0 || j>=myGrid.length || j<0) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return (myGrid[i][j] && myFinder.connected(getIndex(i,j), VTOP));
	}
	
	@Override
	public int numberOfOpenSites() {
		// TODO return the number of calls to open new sites
		return numOpenSites;
	}
	
	@Override
	public boolean percolates() {
		// TODO complete percolates
		return myFinder.connected(VTOP, VBOTTOM);
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int row, int col) {
		if (row-1 >= 0 && row-1 < myGrid.length && myGrid[row-1][col] == true)
			myFinder.union(getIndex(row,col), getIndex(row-1, col)); 
		if(col-1 >= 0 && col-1<myGrid.length && myGrid[row][col-1]==true)
			myFinder.union(getIndex(row,col), getIndex(row,col-1));
		if (row+1 >= 0 && row+1 < myGrid.length && myGrid[row+1][col] == true)
			myFinder.union(getIndex(row,col), getIndex(row+1, col)); 
		if(col+1 >= 0 && col+1<myGrid.length && myGrid[row][col+1]==true)
			myFinder.union(getIndex(row,col), getIndex(row,col+1));
	}

}
