package week5;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class PointSET implements UnitSquarePointSET {
    private final SET<Point2D> set;

    // construct an empty set of points
    public PointSET() {
        set = new SET<>();
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
        if (p == null) throw new NullPointerException();
        set.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return set.contains(p);
    }

    // draw all points to standard draw
    public void draw()  {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);

        for(Point2D p: set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)  {
        if (rect == null) throw new NullPointerException();
        return () -> {
            ArrayList<Point2D> pointsInRange = new ArrayList<>();
            for (Point2D p: set) {
                if (rect.contains(p)) pointsInRange.add(p);
            }

            return pointsInRange.iterator();
        };
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        if (isEmpty()) return null;

        MinPQ<Point2D> pq = new MinPQ<>(set.size(), p.distanceToOrder());
        for (Point2D point: set) {
            pq.insert(point);
        }

        return pq.min();
    }
}
