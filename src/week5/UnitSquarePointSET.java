package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public interface UnitSquarePointSET {
    // is the set empty?
    boolean isEmpty();

    // number of points in the set
    int size();

    // add the point to the set (if it is not already in the set)
    void insert(Point2D p);

    // does the set contain point p?
    boolean contains(Point2D p);

    // draw all points to standard draw
    void draw();

    // all points that are inside the rectangle
    Iterable<Point2D> range(RectHV rect);

    // a nearest neighbor in the set to point p; null if the set is empty
    Point2D nearest(Point2D p);
}