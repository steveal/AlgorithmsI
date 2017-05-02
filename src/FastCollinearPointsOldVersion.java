import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.MergeX;

/**
 * 旧的版本，不知道怎么处理重复线段
 * @author Steve
 *
 */
public class FastCollinearPointsOldVersion {
    private Point[] pointArray;

    private ArrayList<LineSegment> segList = new ArrayList<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPointsOldVersion(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Points Array is Null");
        }
        else if (points.length <= 3) {
            this.pointArray = points;
            checkPointArray();
        }
        else {
            this.pointArray = points.clone();
            checkPointArray();
            int length = pointArray.length;
            // store slope array
            Point[] otherPoint = new Point[length - 1];

            for (int i = 0; i < length; i++) {
                // point to slope array
                int k = 0;
                Point origin = pointArray[i];
                for (int j = 0; j < length; j++) {
                    if (j == i) {
                        continue;
                    }
                    otherPoint[k] = pointArray[j];
                    k++;
                }

                MergeX.sort(otherPoint, origin.slopeOrder());
                // Arrays.sort(otherPoint, origin.slopeOrder());
                int start = 0;

                int otherLength = length - 1;
                double currslope = origin.slopeTo(otherPoint[0]);
                int collinearPointCount = 1;

                for (int j = 1; j < otherLength; j++) {
                    double tempSlope = origin.slopeTo(otherPoint[j]);
                    if (tempSlope != currslope) {
                        if (collinearPointCount >= 3) {
                            // to sort collinear points
                            sortAndAdd(segList, origin, otherPoint, collinearPointCount, start);
                        }
                        start = j;
                        currslope = origin.slopeTo(otherPoint[start]);
                        collinearPointCount = 1;

                    }
                    else {
                        collinearPointCount++;
                    }

                }
                if (collinearPointCount >= 3) {
                    sortAndAdd(segList, origin, otherPoint, collinearPointCount, start);
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

    private void sortAndAdd(ArrayList<LineSegment> segList, Point origin, Point[] otherPoint, int collinearPointCount,
        int start) {
        Point[] colliPoint = new Point[collinearPointCount + 1];
        for (int i = 0; i < collinearPointCount; i++, start++) {
            colliPoint[i] = otherPoint[start];
        }
        colliPoint[collinearPointCount] = origin;
        Arrays.sort(colliPoint);
        LineSegment seg = new LineSegment(colliPoint[0], colliPoint[collinearPointCount]);
        for (LineSegment s : segList) {
            if (s.toString().equals(seg.toString())) {
                return;
            }
        }
        segList.add(seg);
    }

}