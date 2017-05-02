import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Board {
    private int[] blocks;

    private int n;

    private int hamming = -1;

    private int manhattan = -1;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] oblocks) {
        n = oblocks.length;
        blocks = new int[n * n];
        int index = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                blocks[index++] = oblocks[row][col];
            }
        }
    }

    private Board(int[] oblocks, int on) {
        blocks = oblocks.clone();
        n = on;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        if (hamming == -1) {
            hamming = 0;
            int len = blocks.length;
            for (int i = 0; i < len - 1; i++) {
                if (blocks[i] != (i + 1)) {
                    hamming++;
                }
            }
        }

        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (manhattan == -1) {
            manhattan = 0;
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
        }

        return manhattan;
    }

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
    public Board twin() {
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
        return new Board(b, n);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;
        Board that = (Board) y;
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
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<Board>();
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
            Board b = new Board(swap(blocks.clone(), blankIndex - n, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankRow + 1) < n) {

            Board b = new Board(swap(blocks.clone(), blankIndex + n, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankCol - 1) >= 0) {
            Board b = new Board(swap(blocks.clone(), blankIndex - 1, blankIndex), n);
            neighbors.push(b);
        }
        if ((blankCol + 1) < n) {
            Board b = new Board(swap(blocks.clone(), blankIndex + 1, blankIndex), n);
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
        output.append(n + "\n");
        for (int i = 0; i < len; i++) {
            output.append(String.format("%" + n + "d",blocks[i]));
            if ((i + 1) % n == 0) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        // Board origin = new Board(new int[][] { { 1, 2 }, { 3, 0 } });
        Board origin = new Board(new int[][] {
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

        In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle3x3-31" + ".txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);

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