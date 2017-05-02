package mypackage;
import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver2 {
    private MinPQ<Node> openList;

    private ArrayList<Board2> closedList = new ArrayList<Board2>();

    private Node solutionEndNode = null;

    int count = 0;

    Stack<Board2> solution = null;

    // find a solution to the initial board (using the A* algorithm)
    public Solver2(Board2 initial) {
        if (initial == null) {
            throw new NullPointerException();
        }
        if (isSolvable()) {
            openList = new MinPQ<Node>(new NodeCompManhattan());
            Node intialNode = new Node(initial, null, 0);
            openList.insert(intialNode);
            while (!openList.isEmpty()) {
                Node min = openList.delMin();                
                closedList.add(min.curr);
                if (min.curr.isGoal()) {
                    solutionEndNode = min;
                    break;
                }
                else {
                    Iterable<Board2> iter = min.curr.neighbors();
                    for (Board2 b : iter) {
                        boolean exist = false;
                        for (Board2 existBoard : closedList) {
                            if (b.equals(existBoard)) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            Node next = new Node(b, min, min.move + 1);
                            openList.insert(next);
                        }
                        // if(!b.equals(min.prev)) {
                        // Node next = new Node(b,min,min.move + 1);
                        // openList.insert(next);
                        // }
                    }
                }
            }
            solution = new Stack<Board2>();
            Node n = solutionEndNode;
            count = n.move;
            while (n != null) {
                solution.push(n.curr);
                n = n.prev;
            }

            // solution.push(item);
        }
        else {
            solution = null;
            count = -1;
        }

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        // TODO
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return count;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board2> solution() {

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

        public Board2 curr;

        public Node prev;

        public int move;

        public Node(Board2 currBoard, Node prevNode, int move) {
            this.curr = currBoard;
            this.prev = prevNode;
            this.move = move;
        }

    }

    private class NodeCompHamming implements Comparator<Node> {

        @Override
        public int compare(Node b1, Node b2) {
            int b1pro = b1.curr.hamming() + b1.move;
            int b2pro = b1.curr.hamming() + b2.move;
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
            int b2pro = b1.curr.manhattan() + b2.move;
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
        In in = new In("G:/algs4onPC/testweek4/8puzzle/" + "puzzle3x3-15" + ".txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board2 initial = new Board2(blocks);

        // solve the puzzle
        Solver2 solver = new Solver2(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board2 board : solver.solution())
                StdOut.println(board);
        }
        
    }
    
    
}