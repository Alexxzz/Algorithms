package week3;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
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
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        if (containsDuplicates(points)) throw new IllegalArgumentException();

        ArrayList<Point> pointArrayList = new ArrayList<>(Arrays.asList(points));
        if (pointArrayList.contains(null)) throw new NullPointerException();

        segments = new ArrayList<>();
        pointsPairs = new ArrayList<>();

        findSegments(pointArrayList);
    }

    private void findSegments(ArrayList<Point> pointArrayList) {
        ArrayList<Point> sortedPoints = new ArrayList<>(pointArrayList);

        for (Point point: pointArrayList) {
            sortedPoints.sort(point.slopeOrder());

            ArrayList<Point> collinearPoints = new ArrayList<>();

            double slopeOfCollinear = Double.MAX_VALUE;
            for (Point sortedPoint: sortedPoints) {
                if (sortedPoint == point) continue;

                double slopeToPoint = point.slopeTo(sortedPoint);
                if (slopeToPoint != slopeOfCollinear) {
                    if (collinearPoints.size() >= 3) break;
                    slopeOfCollinear = slopeToPoint;
                    collinearPoints.clear();
                }
                collinearPoints.add(sortedPoint);
            }

            collinearPoints.add(point);
            collinearPoints.sort(Point::compareTo);
            int collinearPointsCount = collinearPoints.size();
            if (collinearPointsCount >= 4) {
                Point startPoint = collinearPoints.get(0);
                Point endPoint = collinearPoints.get(collinearPointsCount - 1);
                if (shouldAddNewSegment(pointArrayList.indexOf(startPoint), pointArrayList.indexOf(endPoint))) {
                    LineSegment segment = new LineSegment(startPoint, endPoint);
                    segments.add(segment);
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
}
