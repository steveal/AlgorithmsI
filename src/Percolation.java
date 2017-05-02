import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 
 * @author steve
 *
 */
public class Percolation {
	private WeightedQuickUnionUF uf = null;
	private boolean[] isopenlist;
	private int n;
	private int count = 0;

	public Percolation(int n) {
		
		this.n = n;
		
		int length = n * n + 2;
		uf = new WeightedQuickUnionUF(length);
		//n*n+1
		for(int i = 1;i <=n ; i++) {
			uf.union(n*n, mapping(1,i));
		}
		
		//n*n+2
		for (int i = 1; i <= n; i++) {
			uf.union(n * n + 1, mapping(n, i));
		}
		
		isopenlist = new boolean[length];
		for (int i = 0; i < length - 1; i++) {
			isopenlist[i] = false;
		}
		if(uf.connected(n*n, n*n+1)) {
			System.out.println("connected");
		}

	}

	public void open(int row, int col) {
		validate(row, col);
		if (!isOpen(row, col)) {
			isopenlist[mapping(row, col)] = true;
			count++;
			// 上边
			if ((row - 1) >= 1 && isOpen(row - 1, col)) {
				uf.union(mapping(row, col), mapping(row - 1, col));
			}
			// 左边
			if ((col - 1) >= 1 && isOpen(row, col - 1)) {
				uf.union(mapping(row, col), mapping(row, col - 1));
			}
			// 下边
			if ((row + 1) <= n && isOpen(row + 1, col)) {
				uf.union(mapping(row, col), mapping(row + 1, col));
			}
			// 右边
			if ((col + 1) <= n && isOpen(row, col + 1)) {
				uf.union(mapping(row, col), mapping(row, col + 1));
			}
		}

	}

	public boolean isOpen(int row, int col) {
		validate(row, col);
		return isopenlist[mapping(row, col)];
	}

	public boolean isFull(int row, int col) {
		validate(row, col);
//		for (int i = 1; i <= n; i++) {
//			if (uf.connected(mapping(row, col), mapping(1, i))) {
//				return true;
//			}
//		}
//		return false;
		return uf.connected(mapping(row,col), n*n);

	}

	public int numberOfOpenSites() {
		return this.count;
	}

	public boolean percolates() {
//		for (int i = 1; i <= n; i++) {
//			if (isFull(n, i)) {
//				return true;
//			}
//		}
//		return false;
		return uf.connected(n*n+1, n*n);
	}

	private int mapping(int row, int col) {
		return (row - 1) * n + col - 1;
	}

	private void validate(int row, int col) {
		int n = this.n;
		if ((row < 1) || (row > n))
			throw new IndexOutOfBoundsException("row " + row + " is not between 1 and " + n);

		if ((col < 1) || (col > n))
			throw new IndexOutOfBoundsException("col " + col + " is not between 1 and " + n);
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		Percolation uf = new Percolation(n);
		while (!(StdIn.isEmpty())) {
			int row = StdIn.readInt();
			int col = StdIn.readInt();
			uf.open(row, col);
		}
		System.out.println(uf.percolates());
	}
}