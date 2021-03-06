package week5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class KdTree implements UnitSquarePointSET {
    private Node root;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private int size;

        Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
            this.size = 1;
        }
    }

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();

        if (contains(p)) return;

        root = put(root, p, true, new RectHV(0, 0, 1, 1));
    }

    private Node put(Node h, Point2D p, boolean isVertical, RectHV rect) {
        if (h == null) return new Node(p, rect);

        RectHV lSubRect = new RectHV(rect.xmin(), rect.ymin(), h.p.x(), rect.ymax());
        RectHV rSubRect = new RectHV(h.p.x(), rect.ymin(), rect.xmax(), rect.ymax());

        RectHV bSubRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), h.p.y());
        RectHV tSubRect = new RectHV(rect.xmin(), h.p.y(), rect.xmax(), rect.ymax());

        RectHV lbSubRect = isVertical ? lSubRect : bSubRect;
        RectHV rtSubRect = isVertical ? rSubRect : tSubRect;

        int cmp = compare(p, h.p, isVertical);
        if (cmp < 0) h.lb = put(h.lb, p, !isVertical, lbSubRect);
        else if (cmp > 0) h.rt = put(h.rt, p, !isVertical, rtSubRect);
        else h.p = p;

        h.size = 1 + size(h.lb) + size(h.rt);
        return h;
    }

    private int compare(Point2D p, Point2D toP, boolean isVertical) {
        if (p.equals(toP)) return 0;

        int res;
        if (isVertical) res = Double.compare(p.x(), toP.x());
        else res = Double.compare(p.y(), toP.y());

        if (res == 0) res = 1;

        return res;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return get(root, p, true) != null;
    }

    private Point2D get(Node x, Point2D p, boolean isVertical) {
        if (x == null) return null;
        double cmp = compare(p, x.p, isVertical);
        if      (cmp < 0) return get(x.lb, p, !isVertical);
        else if (cmp > 0) return get(x.rt, p, !isVertical);
        else              return x.p;
    }

    // draw all points to standard draw
    public void draw()  {

    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)  {
        if (rect == null) throw new NullPointerException();

        ArrayList<Point2D> res = new ArrayList<>();

        collectPointsInRect(root, rect, res);

        return res;
    }

    private void collectPointsInRect(Node h, RectHV rect, ArrayList<Point2D> res) {
        if (h == null) return;

        if (rect.contains(h.p)) {
            res.add(h.p);
        }

        if (h.lb != null && rect.intersects(h.lb.rect)) collectPointsInRect(h.lb, rect, res);
        if (h.rt != null && rect.intersects(h.rt.rect)) collectPointsInRect(h.rt, rect, res);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        if (root == null) return  null;
        return nearest(root, p, root.p);
    }

    private Point2D nearest(Node x, Point2D p, Point2D n) {
        if (x == null) return n;

        double nearestDistance = n.distanceSquaredTo(p);
        if (nearestDistance >= x.rect.distanceSquaredTo(p)) {
            if (x.p.distanceSquaredTo(p) < nearestDistance) {
                n = x.p;
            }

            if (x.rt != null && x.rt.rect.contains(p)) {
                n = nearest(x.rt, p, n);
                n = nearest(x.lb, p, n);
            } else {
                n = nearest(x.lb, p, n);
                n = nearest(x.rt, p, n);
            }
        }

        return n;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        // initialize the two data structures with point from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        Iterable<Point2D> p = kdtree.range(new RectHV(0, 0, 1, 1));

        StdOut.println("brute.size() " + brute.size());
        StdOut.println("kdtree.size() " + kdtree.size());

        StdOut.println("brute.nearest() " + brute.nearest(new Point2D(1, 1)));
        StdOut.println("kdtree.nearest() " + kdtree.nearest(new Point2D(1, 1)));
    }
}
