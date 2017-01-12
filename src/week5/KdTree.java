package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

public class KdTree implements UnitSquarePointSET  {
    private int size;
    private Node root;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
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

        root = put(root, p, true, new RectHV(0, 0, 1, 1));
    }

    private Node put(Node h, Point2D p, boolean isVertical, RectHV rect) {
        if (h == null) {
            size++;
            return new Node(p, rect);
        }

        RectHV lSubRect = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
        RectHV rSubRect = new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());

        RectHV bSubRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        RectHV tSubRect = new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());

        RectHV lbSubRect = isVertical ? lSubRect : bSubRect;
        RectHV rtSubRect = isVertical ? rSubRect : tSubRect;

        int cmp = compare(p, h.p, isVertical);
        if (cmp < 0) {
            h.lb = put(h.lb, p, !isVertical, lbSubRect);
        } else if (cmp > 0) {
            h.rt = put(h.rt, p, !isVertical, rtSubRect);
        } else {
            h.p = p;
        }

        return h;
    }

    private int compare(Point2D p, Point2D toP, boolean isVertical) {
        double res;
        if (isVertical) res = p.x() - toP.x();
        else res = p.y() - toP.y();

        return res > 0 ? 1 : (res < 0 ? -1 : 0);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();
        return get(p) != null;
    }

    private Point2D get(Point2D p)
    {
        boolean isVertical = true;
        Node x = root;
        while (x != null)
        {
            int cmp = compare(p, x.p, isVertical);
            isVertical = !isVertical;
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

        ArrayList<Point2D> res = new ArrayList<>();

        collectPointsInRect(root, rect, res);

        return res;
    }

    private void collectPointsInRect(Node h, RectHV rect, ArrayList<Point2D> res) {
        if (h == null) return;

        if (rect.contains(h.p)) res.add(h.p);

        if (h.lb != null && rect.intersects(h.lb.rect)) collectPointsInRect(h.lb, rect, res);
        if (h.rt != null && rect.intersects(h.rt.rect)) collectPointsInRect(h.rt, rect, res);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();

        return null;
    }
}

