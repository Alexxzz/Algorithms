package week3;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || containsNull(points)) throw new NullPointerException();
        if (containsDuplicates(points)) throw new IllegalArgumentException();

        segments = new ArrayList<>();
        findSegments(points);
    }

    private void findSegments(Point[] points) {
        boolean[] usedPointsIdxs = new boolean[points.length];

        for (int i = 0; i < points.length; i++) {
            if (usedPointsIdxs[i]) continue;
            Point point1 = points[i];

            for (int j = 0; j < points.length; j++) {
                if (usedPointsIdxs[j]) continue;
                Point point2 = points[j];
                if (point1 == point2) continue;

                for (int k = 0; k < points.length; k++) {
                    if (usedPointsIdxs[k]) continue;
                    Point point3 = points[k];
                    if (point3 == point1 || point3 == point2) continue;

                    for (int l = 0; l < points.length; l++) {
                        if (usedPointsIdxs[l]) continue;
                        Point point4 = points[l];
                        if (point4 == point1 || point4 == point2 || point4 == point3) continue;

                        double s12 = point1.slopeTo(point2);
                        double s13 = point1.slopeTo(point3);
                        double s14 = point1.slopeTo(point4);

                        if (s12 == s13 && s13 == s14) {
                            LineSegment lineSegment = new LineSegment(point1, point2);
                            segments.add(lineSegment);

                            usedPointsIdxs[i] = true;
                            usedPointsIdxs[j] = true;
                            usedPointsIdxs[k] = true;
                            usedPointsIdxs[l] = true;
                        }
                    }
                }
            }
        }
    }

    private boolean containsDuplicates(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;;
                Point p2 = points[j];
                if (p.compareTo(p2) == 0) return true;
            }
        }
        return false;
    }

    private boolean containsNull(Point[] points) {
        for (Point p: points) if (p == null) return true;
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
}