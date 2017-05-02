/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import mypackage.Board2;
import mypackage.Solver2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PuzzleChecker {

    public static void main(String[] args) {

        testBoardWitchCache("puzzle3x3-20");
//        testBoardWitchOutCache("puzzle3x3-25");
        
        // for each command-line argument
        // for (String filename : args) {
        //
        // // read in the board specified in the filename
        // In in = new In(filename);
        // int n = in.readInt();
        // int[][] tiles = new int[n][n];
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // tiles[i][j] = in.readInt();
        // }
        // }
        //
        // // solve the slider puzzle
        // Board initial = new Board(tiles);
        // Solver solver = new Solver(initial);
        // StdOut.println(filename + ": " + solver.moves());
        // }
    }

    public static void testBoardWitchCache(String filename) {

        In in = new In("G:/algs4onPC/testweek4/8puzzle/" + filename + ".txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Stopwatch sw = new Stopwatch();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        System.out.println("With Cache" + sw.elapsedTime());
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
        }
    }
    
    public static void testBoardWitchOutCache(String filename) {

        In in = new In("G:/algs4onPC/testweek4/8puzzle/" + filename + ".txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Stopwatch sw = new Stopwatch();
        Board2 initial = new Board2(blocks);

        // solve the puzzle
        Solver2 solver = new Solver2(initial);
        System.out.println("WithOut Cache" + sw.elapsedTime());
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board2 board : solver.solution())
//                StdOut.println(board);
        }
    }
}
