import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;


public class TestWeek3Fast {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        testTimeComplex("input1000.txt");
//        testTimeComplex("input2000.txt");
//        testTimeComplex("input3000.txt");
//        testTimeComplex("input4000.txt");
//        testTimeComplex("input5000.txt");
//        testTimeComplex("input6000.txt");
//        testTimeComplex("input8000.txt");
//        testTimeComplex("input10000.txt");
//        testAndDraw("mytest.txt");
//        testAndDraw("input20.txt");
//        testAndDraw("hor.txt");
//        testAndDraw("input40.txt");
    }
    
    public static void testTimeComplex(String filename) {
        //20
          // read the n points from a file
          In in = new In("C:/Users/Administrator/Desktop/algs4/Week3 Pattern/collinear/" + filename);
          int n = in.readInt();
          Point[] points = new Point[n];
          for (int i = 0; i < n; i++) {
              int x = in.readInt();
              int y = in.readInt();
              points[i] = new Point(x, y);
          }

          Stopwatch watch = new Stopwatch(); 
          FastCollinearPointsOldVersion collinear = new FastCollinearPointsOldVersion(points);
          
          LineSegment[] seglist = collinear.segments();
          System.out.println(filename + "\t" + watch.elapsedTime());
          
//          for (LineSegment segment : seglist) {
//              StdOut.println(segment);
//          }
//          System.out.println(collinear.segmentsCount);

      }
    
    public static void testAndDraw(String filename) {
      //20
        // read the n points from a file
        In in = new In("C:/Users/Administrator/Desktop/algs4/Week3 Pattern/collinear/" + filename);
//        In in = new In("C:/Users/Administrator/Desktop/algs4/Week3 Pattern/ver.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPointsOldVersion collinear = new FastCollinearPointsOldVersion(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println(collinear.numberOfSegments());
        StdDraw.show();
    }
}
