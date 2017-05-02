import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 添加Node时候，对所有点遍历来判断是否访问了，不如对该Node之前所有节点遍历
 * 
 * @author Administrator
 */
public class Solver {

    private int moves = 0;

    private Stack<Board> solution = null;

    private boolean solvable = false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        MinPQ<Node> openList = new MinPQ<Node>(new NodeCompManhattan());
        Node intialNode = new Node(initial, null, 0, false);
        Node initalTwinNode = new Node(initial.twin(), null, 0, true);
        openList.insert(intialNode);
        openList.insert(initalTwinNode);
        while (!openList.isEmpty()) {
            Node min = openList.delMin();
            if (min.curr.isGoal()) {
                if (min.istwin) {
                    solvable = false;
                    moves = -1;
                    return;
                }
                else {
                    solvable = true;
                    solution = new Stack<Board>();
                    Node n = min;
                    moves = n.move;
                    while (n != null) {
                        solution.push(n.curr);
                        n = n.prev;
                    }
                    return;
                }

            }
            else {
                Iterable<Board> iter = min.curr.neighbors();
                for (Board b : iter) {
                    //想要将所有遍历过的节点过滤下，时间复杂度太大
                    // boolean exist = false;
                    // for (Board existBoard : closedList) {
                    // if (b.equals(existBoard)) {
                    // exist = true;
                    // break;
                    // }
                    // }
                    // if (!exist) {
                    // Node next = new Node(b, min, min.move + 1, min.istwin);
                    // openList.insert(next);
                    // }

                    if (min.prev == null) {
                        Node next = new Node(b, min, min.move + 1, min.istwin);
                        openList.insert(next);
                    }
                    else {
                        //尝试遍历父节点，看看有没有重复节点，貌似不划算，比较的太多，减少的重复节点太少
                        // boolean exist = false;
                        // Node ncomp = min.prev;
                        // while(ncomp != null) {
                        // cntJudge++;
                        // if(b.equals(ncomp.curr)) {
                        // exist = true;
                        // break;
                        // }else {
                        // ncomp = ncomp.prev;
                        // }
                        //
                        // }
                        // if(!exist) {
                        // Node next = new Node(b, min, min.move + 1, min.istwin);
                        // openList.insert(next);
                        // }

                        // cntJudge++;
                        if (!b.equals(min.prev.curr)) {
                            Node next = new Node(b, min, min.move + 1, min.istwin);
                            openList.insert(next);
                        }
                    }

                }
            }
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        return solution;

    }

    private void checkBlock(int[][] blocks) {
        if (blocks.length != blocks[0].length) {
            throw new IllegalArgumentException();
        }
        int len = blocks.length;
        for (int row = 0; row < len; row++) {
            if (blocks[row].length != len) {
                throw new IllegalArgumentException();
            }
        }
    }

    private class Node {

        public Board curr;

        public Node prev;

        public int move;

        public boolean istwin;

        public Node(Board currBoard, Node prevNode, int move, Boolean istwin) {
            this.curr = currBoard;
            this.prev = prevNode;
            this.move = move;
            this.istwin = istwin;
        }

        public String toString() {
            return "priority:" + (move + curr.manhattan()) + "\nmove:" + move + "\nmanhattan:" + curr.manhattan()
                + "\nistwin:" + istwin + "\n" + curr;
        }

    }

    private class NodeCompHamming implements Comparator<Node> {

        @Override
        public int compare(Node b1, Node b2) {
            int b1pro = b1.curr.hamming() + b1.move;
            int b2pro = b2.curr.hamming() + b2.move;
            if (b1pro < b2pro) {
                return -1;
            }
            else if (b1pro == b2pro) {
                return 0;
            }
            else {
                return 1;
            }
        }

    }

    private class NodeCompManhattan implements Comparator<Node> {

        @Override
        public int compare(Node b1, Node b2) {
            int b1pro = b1.curr.manhattan() + b1.move;
            int b2pro = b2.curr.manhattan() + b2.move;
            if (b1pro < b2pro) {
                return -1;
            }
            else if (b1pro == b2pro) {
                return 0;
            }
            else {
                return 1;
            }
        }

    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        // In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle2x2-unsolvable2" + ".txt");
        // In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle3x3-31" + ".txt");
        // In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle3x3-unsolvable1" + ".txt");
        // In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle4x4-45" + ".txt");
        In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle04" + ".txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }

}