package mypackage;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board2{
    private int[] blocks;

    private int n;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board2(int[][] oblocks) {
        n = oblocks.length;
        blocks = new int[n * n];
        int index = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                blocks[index++] = oblocks[row][col];
            }
        }
    }

    private Board2(int[] oblocks, int on) {
        blocks = oblocks.clone();
        n = on;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    
    // number of blocks out of place
    public int hamming() {
        int hamming = 0;
        int len = blocks.length;
        for (int i = 0; i < len - 1; i++) {
            if (blocks[i] != (i + 1)) {
                hamming++;
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
        int len = blocks.length;
        for (int i = 0; i < len; i++) {
            if (blocks[i] != (i + 1) && blocks[i] != 0) {
                int value = blocks[i];
                int goalRow = (value - 1) / n;
                int goalCol = (value - 1) % n;
                int currRow = i / n;
                int currCol = i % n;
                manhattan += abs(currCol - goalCol) + abs(currRow - goalRow);
            }
        }
        return manhattan;
    }

    /**
     * 杩斿洖缁濆鍊紉值
     * 
     * @param x int
     * @return x 缁濆鍊�
     */
    private int abs(int x) {
        return x < 0 ? -x : x;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int len = blocks.length;
        for (int i = 0; i < len - 1; i++) {
            if (blocks[i] != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board2 twin() {
        int len = n * n;

        int blankIndex = 0;
        for (int i = 0; i < len; i++) {
            if (blocks[i] == 0) {
                blankIndex = i;
                break;
            }
        }
        int p = (blankIndex - 1) < 0 ? len - 1 : blankIndex - 1;
        int q = (blankIndex + 1) > len - 1 ? 0 : blankIndex + 1;
        int[] b = swap(blocks.clone(), p, q);
        return new Board2(b, n);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;
        Board2 that = (Board2) y;
        if (n != that.n) {
            return false;
        }
        int len = n * n;
        for (int i = 0; i < len; i++) {
            if (blocks[i] != that.blocks[i]) {
                return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board2> neighbors() {
        Stack<Board2> neighbors = new Stack<Board2>();
        int blankRow = 0;
        int blankCol = 0;
        int len = n * n;

        int blankIndex = 0;
        for (int i = 0; i < len; i++) {
            if (blocks[i] == 0) {
                blankRow = i / n;
                blankCol = i % n;
                blankIndex = i;
                break;
            }
        }

        if ((blankRow - 1) >= 0) {
            Board2 b = new Board2(swap(blocks.clone(), blankIndex - n, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankRow + 1) < n) {

            Board2 b = new Board2(swap(blocks.clone(), blankIndex + n, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankCol - 1) >= 0) {
            Board2 b = new Board2(swap(blocks.clone(), blankIndex - 1, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankCol + 1) < n) {
            Board2 b = new Board2(swap(blocks.clone(), blankIndex + 1, blankIndex), n);
            neighbors.push(b);
        }

        return neighbors;
    }

    private int[] swap(int[] blocks, int i, int j) {
        int temp = blocks[i];
        blocks[i] = blocks[j];
        blocks[j] = temp;
        return blocks;
    }

    // string representation of this board (in the output format specified
    // below)
    public String toString() {
        StringBuilder output = new StringBuilder();
        int len = blocks.length;
        output.append(n + "\n ");
        for (int i = 0; i < len; i++) {
            output.append(blocks[i]);
            if ((i + 1) % n == 0) {
                output.append("\n ");
            }
            else {
                output.append("  ");
            }
        }
        return output.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        // Board origin = new Board(new int[][] { { 1, 2 }, { 3, 0 } });
        Board2 origin = new Board2(new int[][] {
            {
                8, 1, 3
            }, {
                4, 0, 2
            }, {
                7, 6, 5
            }
        });
        System.out.println(origin);
        System.out.println("is equals : " + origin.equals(new int[][] {
            {
                8, 1, 3
            }, {
                4, 0, 2
            }, {
                7, 6, 5
            }
        }));
        System.out.println(origin.isGoal());
        System.out.println(origin.dimension());

        System.out.println("hamming: " + origin.hamming());
        System.out.println("manhattan: " + origin.manhattan());

        In in = new In("H:/algs4onPC/testweek4/8puzzle/" + "puzzle3x3-31" + ".txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board2 initial = new Board2(tiles);

        System.out.println(initial);
        System.out.println("---------------------------------");
        // for (Board b : initial.neighbors()) {
        // System.out.println(b);
        // }
        // System.out.println(initial.twin());

        System.out.println("---------------------------------");
        System.out.println(initial);

    }

}