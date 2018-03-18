
public class PercolationDFSFast extends PercolationDFS {

	public PercolationDFSFast(int n) {
		super(n);
	}
	
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		super.open(row, col);
	}
	
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return super.isOpen(row, col);
	}
	
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		return super.isFull(row, col);
	}
	@Override
	protected void updateOnOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException("OUT OF BOUNDS");
		}
		if(inBounds(row,col)) {
			if(isFull(row,col)) {
				myGrid[row][col] = FULL;
				dfs(row - 1, col);
				dfs(row, col - 1);
				dfs(row, col + 1);
				dfs(row + 1, col);
			}
		}
		if(inBounds(row-1,col)) {
			if(isFull(row-1,col)) {
				myGrid[row][col] = FULL;
				dfs(row - 1, col);
				dfs(row, col - 1);
				dfs(row, col + 1);
				dfs(row + 1, col);
			}
		}
		if(inBounds(row,col-1)) {
			if(isFull(row,col-1)) {
				myGrid[row][col] = FULL;
				dfs(row - 1, col);
				dfs(row, col - 1);
				dfs(row, col + 1);
				dfs(row + 1, col);
			}
		}
		if(inBounds(row,col+1)) {
			if(isFull(row,col+1)) {
				myGrid[row][col] = FULL;
				dfs(row - 1, col);
				dfs(row, col - 1);
				dfs(row, col + 1);
				dfs(row + 1, col);
			}
		}
		if(inBounds(row+1,col)) {
			if(isFull(row+1,col)) {
				myGrid[row][col] = FULL;
				dfs(row - 1, col);
				dfs(row, col - 1);
				dfs(row, col + 1);
				dfs(row + 1, col);
			}
		}
	}
}

