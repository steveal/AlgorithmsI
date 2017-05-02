import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] pointArray;

    private ArrayList<LineSegment> segList = new ArrayList<LineSegment>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Points Array is Null");
        }
        else if (points.length <= 3) {
            this.pointArray = points;
            checkPointArray();
        }
        else {
            this.pointArray = points;
            checkPointArray();
            int length = this.pointArray.length;
            Point[] sortingPoint = new Point[4];
            for (int p = 0; p < length; p++) {
                for (int q = p + 1; q < length; q++) {
                    Point pPoint = pointArray[p];
                    Point qPoint = pointArray[q];
                    double pqSlope = pPoint.slopeTo(qPoint);
                    for (int k = q + 1; k < length; k++) {
                        Point kPoint = pointArray[k];
                        double pkSlope = pPoint.slopeTo(kPoint);
                        if (pqSlope != pkSlope) {
                            continue;
                        }
                        for (int r = k + 1; r < length; r++) {
                            Point rPoint = pointArray[r];
                            double prSlope = pPoint.slopeTo(rPoint);
                            if (pqSlope == prSlope) {
                                // to sort collinear points
                                sortingPoint[0] = pPoint;
                                sortingPoint[1] = qPoint;
                                sortingPoint[2] = rPoint;
                                sortingPoint[3] = kPoint;
                                Arrays.sort(sortingPoint);
                                segList.add(new LineSegment(sortingPoint[0], sortingPoint[3]));
                            }
                        }
                    }

                }
            }
        }

    }

    private void checkPointArray() {
        int len = this.pointArray.length;
        for (int i = 0; i < len; i++) {
            if (pointArray[i] == null) {
                throw new NullPointerException("Points contain null");
            }
        }
        for (int i = 0; i < len; i++) {
            Point p = this.pointArray[i];
            for (int j = i + 1; j < len; j++) {
                if (p.compareTo(this.pointArray[j]) == 0) {
                    throw new IllegalArgumentException("Points contain duplicate keys");
                }
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
}