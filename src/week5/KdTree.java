package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import sun.awt.util.IdentityArrayList;

public class KdTree implements UnitSquarePointSET  {
    private int size;
    private Node root;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        Node(Point2D p) {
            this.p = p;
        }
    }

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();

        root = put(root, p);

        size++;
    }

    private Node put(Node h, Point2D p) {
        if (h == null) return new Node(p);

        int cmp = p.compareTo(h.p);
        if (cmp < 0) h.lb = put(h.lb, p);
        else if (cmp > 0) h.rt = put(h.rt, p);
        else h.p = p;

        return h;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return get(p) != null;
    }

    private Point2D get(Point2D key)
    {
        Node x = root;
        while (x != null)
        {
            int cmp = key.compareTo(x.p);
            if (cmp < 0) x = x.lb;
            else if (cmp > 0) x = x.rt;
            else return x.p;
        }
        return null;
    }

    // draw all points to standard draw
    public void draw()  {

    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)  {
        if (rect == null) throw new NullPointerException();

        return new IdentityArrayList<>();
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();

        return null;
    }
}

