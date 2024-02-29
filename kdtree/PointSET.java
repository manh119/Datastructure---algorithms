import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {
    private SET<Point2D> setPoints;

    // construct an empty set of points
    public PointSET() {
        setPoints = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return setPoints.isEmpty();
    }

    // number of points in the set
    public int size() {
        return setPoints.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        setPoints.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return setPoints.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : setPoints) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : setPoints) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (setPoints.isEmpty()) {
            return null;
        }
        Point2D nearest = setPoints.min();
        for (Point2D point : setPoints) {
            if (point.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
                nearest = point;
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET pointSet = new PointSET();
        // test 1 : isEmpty() and size()
        if (pointSet.isEmpty() && pointSet.size() == 0) {
            StdOut.println("Test 1 : Passed");
        }
        // test 2 : contain() and insert() and size(
        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4);
        Point2D p3 = new Point2D(0.2, 0.3);
        Point2D p4 = new Point2D(0.4, 0.7);
        Point2D p5 = new Point2D(0.9, 0.6);
        pointSet.insert(p1);
        pointSet.insert(p2);
        pointSet.insert(p3);
        pointSet.insert(p4);
        pointSet.insert(p5);
        pointSet.insert(p5);
        if (pointSet.size() == 5 && pointSet.contains(p1) && pointSet.contains(p2)
                && pointSet.contains(
                p3) && pointSet.contains(p4) && pointSet.contains(p5)) {
            StdOut.println("Test 2 : Passed");
        }
        else {
            StdOut.println("Test 2 : Failed");
        }
        
        // test 4 : test range()
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0); // contain all points
        Iterable<Point2D> it = pointSet.range(rect);
        boolean test4 = false;
        for (Point2D p : it) {
            if (p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4) || p.equals(p5)) {
                test4 = true;
            }
            else {
                test4 = false;
                break;
            }
        }
        if (test4) {
            StdOut.println("Test 4 : Passed");
        }
        else {
            StdOut.println("Test 4 : Failed");
        }

        // test 5 : test range(), react contain p1 , p2, p3
        RectHV rect1 = new RectHV(0.0, 0.0, 1, 0.4); // contain p1 p2 and p3
        Iterable<Point2D> it1 = pointSet.range(rect1);
        boolean test5 = false;
        for (Point2D p : it1) {
            if (p.equals(p1) || p.equals(p2) || p.equals(p3)) {
                test5 = true;
            }
            else {
                test5 = false;
                break;
            }
        }
        if (test5) {
            StdOut.println("Test 5 : Passed");
        }
        else {
            StdOut.println("Test 5 : Failed");
        }

        // test 6 : test nearest()
        Point2D pNearest1 = pointSet.nearest(new Point2D(0, 0)); // pNearest = p3
        if (pNearest1.equals(p3)) {
            StdOut.println("Test 6 : Passed");
        }
        else {
            StdOut.println("Test 6 : Failed, pNearest failed = " + pNearest1);
        }

        // test 7 : test nearest()
        Point2D pNearest2 = pointSet.nearest(new Point2D(1, 0)); // pNearest2 = p1
        if (pNearest2.equals(p1)) {
            StdOut.println("Test 7 : Passed");
        }
        else {
            StdOut.println("Test 7 : Failed, pNearest failed = " + pNearest2);
        }

        // test 8 : test nearest() - case point in the line
        Point2D pNearest3 = pointSet.nearest(new Point2D(0.4, 0.4)); // pNearest2 = p2
        if (pNearest3.equals(p2)) {
            StdOut.println("Test 8 : Passed");
        }
        else {
            StdOut.println("Test 8 : Failed, pNearest failed = " + pNearest3);
        }

        // test 9 : test nearest() - case point is contained by left, but nearest is right
        Point2D pNearest4 = pointSet.nearest(new Point2D(0.2, 0.41)); // pNearest4 = p3
        if (pNearest4.equals(p3)) {
            StdOut.println("Test 9 : Passed");
        }
        else {
            StdOut.println("Test 9 : Failed, pNearest failed = " + pNearest4);
        }
    }

}