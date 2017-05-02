

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class TestKdTree {
	public static void main(String[] args) {

//        String filename = "E:/Coursera/Algorithms Path I/testweek5/kdtree/circle10.txt";
		String filename = "E:/Coursera/Algorithms Path I/testweek5/kdtree/circle10k.txt";
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }
        StdDraw.enableDoubleBuffering();
        kdtree.draw();
        StdDraw.show();
        
        Point2D origin = new Point2D(.81, .3);
        Point2D near = kdtree.nearest(origin);
        
        StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(origin.x(),origin.y());

        StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.line(near.x(),near.y(),origin.x(),origin.y());
		System.out.println("origin: " + origin);
		System.out.println("near: " + near);
//		System.out.println(kdtree.count);
		
		StdDraw.show();
	}
}
