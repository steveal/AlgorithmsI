import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation2 {

	private int n = 0;
	int[][] list = null;
	boolean[][] openlist = null;
	int count = 0;

	// create n-by-n grid, with all sites blocked
	public Percolation2(int n) {
		this.n = n;
		list = new int[n+1][n+1];
		openlist = new boolean[n+1][n+1];
		for (int row = 1; row <= n ; row++) {
			for (int col = 1; col <= n; col++) {
				list[row][col] = row * n + col;
				openlist[row][col] = false;
			}
		}
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		validate(row);
		validate(col);
		
		if (!isOpen(row, col)) {
			openlist[row][col] = true;
			//���			
			if((col-1)>=0 && isOpen(row,col-1) )  {
					list[row][col] = find(row,col-1);
			}
			//�ϰ�
			if((row-1)>=0 && isOpen(row-1,col) )  {
					list[row][col] = find(row-1,col);
			}
			//�ұ�
			if((col+1)<=n && isOpen(row,col+1) )  {
					list[row][col] = find(row,col+1);
			}
			//�±�
			if((row+1)<=n && isOpen(row,col) )  {
					list[row][col] = find(row+1,col);
			}
		} 
	}
	
	private int find(int row, int col)
	{
		int r = row;
		int c = col;
		while( (r*n+c) != list[row][col]) {
			int value = list[row][col];
			r = value /n;
			c = value %n;
		}
		return row*n + c;
	    
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		validate(row);
		validate(col);
		return openlist[row-1][col-1];
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		validate(row);
		validate(col);
		int value = list[row-1][col-1];
		//TODO
		return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return count;
	}

	// does the system percolate?
	public boolean percolates() { 
		for(int col = 1; col<= n ; col ++) {
			if(isOpen(n,col)) {
				return true;
			}
		}
		return false;
	}

	private void validate(int p) {
		int n = this.n;
		if ((p < 1) || (p > n))
			throw new IndexOutOfBoundsException("index " + p + " is not between 1 and " + n);
	}

	// test client (optional)
	  public static void main(String[] args)
	  {
	    int n = StdIn.readInt();
	    QuickUnionUF uf = new QuickUnionUF(n);
	    while (!(StdIn.isEmpty())) {
	      int p = StdIn.readInt();
	      int q = StdIn.readInt();
	      if (!(uf.connected(p, q)))
	      uf.union(p, q);
	      StdOut.println(p + " " + q);
	    }
	    StdOut.println(uf.count() + " components");
	  }
}