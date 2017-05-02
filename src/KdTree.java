import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	private static boolean HorSplit = true;
	private static boolean VerSplit = false;
	private Node rootNode = null;
	private int size = 0;

	// construct an empty set of points
	public KdTree() {

	}

	// is the set empty?
	public boolean isEmpty() {
		return rootNode == null;
	}

	// number of points in the set
	public int size() {
		// return size(rootNode);
		return size;
	}

	/*
	 * private int size(Node node) { if (node == null) { return 0; } return 1 +
	 * size(node.leftSub) + size(node.rightSub); }
	 */

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) {
			return;
		}
		if (rootNode == null) {
			rootNode = new Node();
			rootNode.point = p;
			rootNode.splitWay = VerSplit;
			rootNode.rect = new RectHV(0L, 0L, 1L, 1L);
			size++;
		} else {
			put(rootNode, p);
		}

	}

	private void put(Node pNode, Point2D p) {
		if (pNode.splitWay == HorSplit) {
			if (p.y() >= pNode.point.y()) {
				if (pNode.rightSub != null) {
					put(pNode.rightSub, p);
				} else {
					if (p.x() == pNode.point.x()) {
						return;
					}
					Node node = new Node();
					node.point = p;
					node.splitWay = !pNode.splitWay;
					RectHV prect = pNode.rect;
					node.rect = new RectHV(prect.xmin(), pNode.point.y(),
							prect.xmax(), prect.ymax());
					pNode.rightSub = node;
					size++;
				}
			} else {
				if (pNode.leftSub != null) {
					put(pNode.leftSub, p);
				} else {
					Node node = new Node();
					node.point = p;
					node.splitWay = !pNode.splitWay;
					RectHV prect = pNode.rect;
					node.rect = new RectHV(prect.xmin(), prect.ymin(),
							prect.xmax(), pNode.point.y());
					pNode.leftSub = node;
					size++;
				}
			}
		} else {
			if (p.x() >= pNode.point.x()) {
				if (pNode.rightSub != null) {
					put(pNode.rightSub, p);
				} else {
					if (p.y() == pNode.point.y()) {
						return;
					}
					Node node = new Node();
					node.point = p;
					node.splitWay = !pNode.splitWay;
					RectHV prect = pNode.rect;
					node.rect = new RectHV(pNode.point.x(), prect.ymin(),
							prect.xmax(), prect.ymax());
					pNode.rightSub = node;
					size++;
				}
			} else {
				if (pNode.leftSub != null) {
					put(pNode.leftSub, p);
				} else {
					Node node = new Node();
					node.point = p;
					node.splitWay = !pNode.splitWay;
					RectHV prect = pNode.rect;
					node.rect = new RectHV(prect.xmin(), prect.ymin(),
							pNode.point.x(), prect.ymax());
					pNode.leftSub = node;
					size++;
				}
			}
		}
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null) {
			return false;
		}
		return contains(rootNode, p);
	}

	private boolean contains(Node node, Point2D p) {
		if (node == null) {
			return false;
		}
		if (node.rect.contains(p)) {
			boolean isEquals = node.point.equals(p);
			return isEquals || contains(node.leftSub, p)
					|| contains(node.rightSub, p);
		} else {
			return false;
		}

	}

	// draw all points to standard draw
	public void draw() {
		draw(rootNode);
	}

	private void draw(Node node) {
		if (node == null) {
			return;
		}
		RectHV rect = node.rect;
		Point2D point = node.point;
		// draw point;
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(point.x(), point.y());
		// draw line;
		StdDraw.setPenRadius();
		if (node.splitWay == VerSplit) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(point.x(), rect.ymin(), point.x(), rect.ymax());
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(rect.xmin(), point.y(), rect.xmax(), point.y());
		}

		draw(node.leftSub);
		draw(node.rightSub);

	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<Point2D>();
		get(rootNode, rect, stack);
		return stack;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (isEmpty()) {
			return null;
		}
		return nearest(rootNode, p, rootNode.point);
	}

	private Point2D nearest(Node node, Point2D origin, Point2D nearest) {
		if (node == null) {
			return null;
		}
		double distance = origin.distanceSquaredTo(node.point);
		double distanceNearest = origin.distanceSquaredTo(nearest);
		if (distance < distanceNearest) {
			nearest = node.point;
			distanceNearest = distance;
		}
		if (node.leftSub != null
				&& (distanceNearest > node.leftSub.rect
						.distanceSquaredTo(origin))) {
			Point2D pL = nearest(node.leftSub, origin, nearest);
			distance = origin.distanceSquaredTo(pL);
			if (distance < distanceNearest) {
				nearest = pL;
				distanceNearest = distance;
			}
		}
		if (node.rightSub != null
				&& (distanceNearest > node.rightSub.rect
						.distanceSquaredTo(origin))) {
			Point2D pR = nearest(node.rightSub, origin, nearest);
			distance = origin.distanceSquaredTo(pR);
			if (distance < distanceNearest) {
				nearest = pR;
				distanceNearest = distance;
			}
		}
		return nearest;

	}

	private void get(Node node, RectHV rect, Stack<Point2D> stack) {
		if (node != null && rect.intersects(node.rect)) {
			if (rect.contains(node.point)) {
				stack.push(node.point);
			}
			get(node.leftSub, rect, stack);
			get(node.rightSub, rect, stack);
		}
	}

	private class Node {
		public boolean splitWay;
		public Point2D point;
		public RectHV rect;
		public Node leftSub = null;
		public Node rightSub = null;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {
		KdTree tree = new KdTree();

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
		System.out.println("size: " + tree.size());
		p = new Point2D(.4, .7);
		tree.insert(p);
		System.out.println("size: " + tree.size());
		p = new Point2D(.9, .6);
		tree.insert(p);
		System.out.println("size: " + tree.size());
		p = new Point2D(.9, .6);
		tree.insert(p);

		System.out.println("isEmpty: " + tree.isEmpty());
		System.out.println("size: " + tree.size());
		// RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
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
		System.out.println("------------------");
	}
}