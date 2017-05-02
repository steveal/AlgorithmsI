import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
	private SET<Point2D> set = null;

	// construct an empty set of points
	public PointSET() {
		set = new SET<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return set.isEmpty();
	}

	// number of points in the set
	public int size() {
		return set.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		set.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return set.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for (Point2D p : set) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.01);
			StdDraw.point(p.x(), p.y());
		}
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<Point2D>();
		for (Point2D p : set) {
			if (rect.contains(p)) {
				stack.push(p);
			}
		}
		return stack;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (isEmpty()) {
			return null;
		}
		Point2D nearest = null;
		double nearestDistanceSquared = Double.MAX_VALUE;

		for (Point2D q : set) {
			double d = p.distanceSquaredTo(q);
			if (d < nearestDistanceSquared) {
				nearestDistanceSquared = d;
				nearest = q;
			}
		}
		return nearest;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {
		PointSET tree = new PointSET();

		System.out.println("isEmpty: " + tree.isEmpty());

		Point2D p = new Point2D(.7, .2);
		tree.insert(p);
		System.out.println("size: " + tree.size());
		p = new Point2D(.5, .4);
		tree.insert(p);
		p = new Point2D(.2, .3);
		tree.insert(p);
		p = new Point2D(.4, .7);
		tree.insert(p);
		p = new Point2D(.9, .6);
		tree.insert(p);

		StdDraw.enableDoubleBuffering();
		tree.draw();

		// Point2D origin = new Point2D(0.1,0.3);
		Point2D origin = new Point2D(0.7, 0.5);
		System.out.println("origin: " + origin);
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(origin.x(), origin.y());
		Point2D near = tree.nearest(origin);
		System.out.println("nearest: " + near);
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.line(near.x(), near.y(), origin.x(), origin.y());

		StdDraw.show();
	}
}