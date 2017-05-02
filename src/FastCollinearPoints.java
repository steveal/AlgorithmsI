import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private Point[] pointArray;

	private ArrayList<LineSegment> segList = new ArrayList<LineSegment>();

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {

		this.pointArray = points.clone();

		Arrays.sort(pointArray);

		int len = pointArray.length;

		for (int i = 0; i < len; i++) {
			if (pointArray[i] == null) {
				throw new NullPointerException("Points contain null");
			}
		}
		for (int i = 0; i < len - 1; i++) {
			Point p = this.pointArray[i];

			if (p.compareTo(this.pointArray[i + 1]) == 0) {
				throw new IllegalArgumentException(
						"Points contain duplicate keys");
			}

		}

		if (len < 4) {
			return;
		}
		// store slope array
		Point[] otherPoint = new Point[len - 1];

		for (int i = 0; i < len; i++) {
			// point to slope array
			int k = 0;
			Point origin = pointArray[i];
			for (int j = 0; j < len; j++) {
				if (j == i) {
					continue;
				}
				otherPoint[k] = pointArray[j];
				k++;
			}

			Arrays.sort(otherPoint, origin.slopeOrder());
			int start = 0;

			int otherLength = len - 1;
			double currslope = origin.slopeTo(otherPoint[0]);
			int collinearPointCount = 1;
			Point min = null;
			Point max = null;
			for (int j = 1; j < otherLength; j++) {
				double tempSlope = origin.slopeTo(otherPoint[j]);
				if (tempSlope != currslope) {
					if (collinearPointCount >= 3 && min.compareTo(origin) == 0) {
						segList.add(new LineSegment(min, max));
					}
					start = j;
					currslope = origin.slopeTo(otherPoint[start]);
					collinearPointCount = 1;
					min = null;
					max = null;

				} else {
					if (min == null) {
						if (origin.compareTo(otherPoint[j - 1]) < 0) {
							max = otherPoint[j - 1];
							min = origin;
						} else {
							max = origin;
							min = otherPoint[j - 1];
						}
					}
					if (min.compareTo(otherPoint[j]) > 0)
						min = otherPoint[j];
					if (max.compareTo(otherPoint[j]) < 0)
						max = otherPoint[j];

					collinearPointCount++;
				}

			}
			if (collinearPointCount >= 3 && min.compareTo(origin) == 0) {
				segList.add(new LineSegment(min, max));
			}

		}

	}

	// the number of line segments
	public int numberOfSegments() {
		return this.segList.size();
	}

	// the line segments
	public LineSegment[] segments() {
		return segList.toArray(new LineSegment[segList.size()]);
	}

	public static void main(String[] args) {
		testOtherFast("rs1423.txt");
	}

	private static void testOtherFast(String filename) {

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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdOut.println(collinear.segments().length);
		StdDraw.show();
	}

}