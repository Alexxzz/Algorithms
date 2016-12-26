package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments;
    private ArrayList<PointsPair> pointsPairs;

    private class PointsPair {
        private int firstPointIndex;
        private int lastPointIndex;

        PointsPair(int firstPointIndex, int lastPointIndex) {
            this.firstPointIndex = firstPointIndex;
            this.lastPointIndex = lastPointIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;

            if (this.getClass() == o.getClass()) {
                PointsPair that = (PointsPair) o;
                return firstPointIndex == that.firstPointIndex && lastPointIndex == that.lastPointIndex;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + firstPointIndex;
            result = 31 * result + lastPointIndex;
            return result;
        }
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();

        ArrayList<Point> pointArrayList = new ArrayList<>(Arrays.asList(points));
        if (pointArrayList.contains(null)) throw new NullPointerException();

        if (containsDuplicates(points)) throw new IllegalArgumentException();

        segments = new ArrayList<>();
        pointsPairs = new ArrayList<>();
        findSegments(pointArrayList);
    }

    private void findSegments(ArrayList<Point> points) {
        for (Point point1: points) {

            for (Point point2: points) {
                if (point1 == point2) continue;
                double s12 = point1.slopeTo(point2);

                for (Point point3: points) {
                    if (point3 == point1 || point3 == point2) continue;
                    double s13 = point1.slopeTo(point3);

                    for (Point point4: points) {
                        if (point4 == point1 || point4 == point2 || point4 == point3) continue;
                        double s14 = point1.slopeTo(point4);

                        if (s12 == s13 && s13 == s14) {
                            Point[] sortedSegmentPoints = {point1, point2, point3, point4};
                            Arrays.sort(sortedSegmentPoints, Point::compareTo);

                            Point startPoint = sortedSegmentPoints[0];
                            Point endPoint = sortedSegmentPoints[3];
                            LineSegment newSegment = new LineSegment(startPoint, endPoint);
                            boolean shouldAdd = shouldAddNewSegment(points.indexOf(startPoint), points.indexOf(endPoint));
                            if (shouldAdd) segments.add(newSegment);
                        }
                    }
                }
            }
        }
    }

    private boolean shouldAddNewSegment(int firstPointIndex, int lastPointIndex) {
        PointsPair pointsPair = new PointsPair(firstPointIndex, lastPointIndex);
        if (pointsPairs.contains(pointsPair)) return false;
        pointsPairs.add(pointsPair);
        return true;
    }

    private boolean containsDuplicates(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Point p2 = points[j];
                if (p.compareTo(p2) == 0) return true;
            }
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
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
        StdDraw.show();
    }
}