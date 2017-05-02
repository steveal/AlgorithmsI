


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class TestWeek3 {

    public static void main(String[] args) {
//        testBrute("input6.txt");
//       testFastTiming("input1000.txt");
//       testFastTiming("input2000.txt");
//       testFastTiming("input4000.txt");
       testFastTiming("input8000.txt");
//        testBrute("input8.txt");
//        testFast("input20.txt");
//    	testBrute("equidistant.txt");
        testOtherFastTiming("input8000.txt");
//        testFastTiming("input8000.txt");
//        testBruteTiming("input8000.txt");
    	testOtherFast("mylinear.txt");
    }
    
    public static void testOtherFastTiming(String filename) {
        
        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        Stopwatch w = new Stopwatch();
        // print and draw the line segments
        FastCollinearPointsRightFromInternet collinear = new FastCollinearPointsRightFromInternet(points);
        
        System.out.println(w.elapsedTime());
    }
 
    public static void testFastTiming(String filename) {
        
        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        Stopwatch w = new Stopwatch();
        // print and draw the line segments
        FastCollinearPointsOldVersion collinear = new FastCollinearPointsOldVersion(points);
        
        System.out.println(w.elapsedTime());
    }
    
    public static void testBruteTiming(String filename) {
        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        Stopwatch w = new Stopwatch();
        // print and draw the line segments
        FastCollinearPointsOldVersion collinear = new FastCollinearPointsOldVersion(points);
        
        System.out.println(w.elapsedTime());
    }
    
    
    public static void testOtherFast(String filename) {

        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
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
        FastCollinearPointsRightFromInternet collinear = new FastCollinearPointsRightFromInternet(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(collinear.segments().length);
        StdDraw.show();
    }
    
    public static void testFast(String filename) {

        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
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
        StdOut.println(collinear.segments().length);
        StdDraw.show();
    }
    
    public static void testBrute(String filename) {

        // read the n points from a file
        
        In in = new In("E:/algs4/testweek3/collinear-test/" + filename);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(collinear.segments().length);
        StdDraw.show();
    }
}
