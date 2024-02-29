import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size = 0;

    private class Node {
        private final Point2D point;
        private final boolean isVertical;
        private final RectHV rect;
        private Node left;
        private Node right;

        private Node(Point2D point, boolean isVertical, RectHV rect, Node left, Node right) {
            this.point = point;
            this.isVertical = isVertical;
            this.rect = rect;
            this.left = left;
            this.right = right;
        }
    }

    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // //add the point to the set (if it is not already in the set)
    // public void insert(Point2D p) {
    //     if (p == null) {
    //         throw new IllegalArgumentException();
    //     }
    //     if (root == null) {
    //         root = new Node(p, true, new RectHV(0.0, 0.0, 1.0, 1.0), null, null);
    //         size++;
    //         return;
    //     }
    //     insertRecursive(p, root);
    // }
    //
    // insert recursive a point into tree of nodeP
    // private void insertRecursive(Point2D p, Node nodeP) {
    //     if (nodeP.point.equals(p)) {
    //         return;
    //     }
    //     if (nodeP.isVertical) {
    //         if (p.x() < nodeP.point.x()) {
    //             if (nodeP.left == null) {
    //                 nodeP.left = new Node(p, false, new RectHV(nodeP.rect.xmin(), nodeP.rect.ymin(),
    //                                                            nodeP.point.x(), nodeP.rect.ymax()),
    //                                       null, null);
    //                 size++;
    //             }
    //             else {
    //                 insertRecursive(p, nodeP.left);
    //             }
    //         }
    //         else {
    //             if (nodeP.right == null) {
    //                 nodeP.right = new Node(p, false, new RectHV(nodeP.point.x(), nodeP.rect.ymin(),
    //                                                             nodeP.rect.xmax(),
    //                                                             nodeP.rect.ymax()), null, null);
    //                 size++;
    //             }
    //             else {
    //                 insertRecursive(p, nodeP.right);
    //             }
    //         }
    //     }
    //     else {
    //         if (p.y() < nodeP.point.y()) {
    //             if (nodeP.left == null) {
    //                 nodeP.left = new Node(p, true, new RectHV(nodeP.rect.xmin(), nodeP.rect.ymin(),
    //                                                           nodeP.rect.xmax(), nodeP.point.y()),
    //                                       null, null);
    //                 size++;
    //             }
    //             else {
    //                 insertRecursive(p, nodeP.left);
    //             }
    //         }
    //         else {
    //             if (nodeP.right == null) {
    //                 nodeP.right = new Node(p, true, new RectHV(nodeP.rect.xmin(), nodeP.point.y(),
    //                                                            nodeP.rect.xmax(),
    //                                                            nodeP.rect.ymax()),
    //                                        null, null);
    //                 size++;
    //             }
    //             else {
    //                 insertRecursive(p, nodeP.right);
    //             }
    //         }
    //     }
    //
    // }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new Node(p, true, new RectHV(0.0, 0.0, 1.0, 1.0), null, null);
            size++;
        }
        else {
            insertRecursive(p, root);
        }
    }

    private void insertRecursive(Point2D p, Node nodeP) {
        if (nodeP.point.equals(p)) {
            return;
        }

        boolean isLeftSubtree = isPointInLeftSubtree(p, nodeP);

        Node childNode = isLeftSubtree ? nodeP.left : nodeP.right;

        if (childNode == null) {
            Node newNode = createNode(p, nodeP, isLeftSubtree);
            if (isLeftSubtree) {
                nodeP.left = newNode;
            }
            else {
                nodeP.right = newNode;
            }
            size++;
        }
        else {
            insertRecursive(p, childNode);
        }
    }

    private boolean isPointInLeftSubtree(Point2D p, Node nodeP) {
        return nodeP.isVertical ? p.x() < nodeP.point.x() : p.y() < nodeP.point.y();
    }

    private Node createNode(Point2D p, Node parent, boolean isLeftChild) {
        double xmin = parent.rect.xmin();
        double ymin = parent.rect.ymin();
        double xmax = parent.rect.xmax();
        double ymax = parent.rect.ymax();

        if (parent.isVertical) {
            xmax = isLeftChild ? parent.point.x() : xmax;
            xmin = isLeftChild ? xmin : parent.point.x();
        }
        else {
            ymax = isLeftChild ? parent.point.y() : ymax;
            ymin = isLeftChild ? ymin : parent.point.y();
        }

        return new Node(p, !parent.isVertical, new RectHV(xmin, ymin, xmax, ymax), null, null);
    }


    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return containsRecursive(p, root);
    }

    // does the set contain point p?
    private boolean containsRecursive(Point2D p, Node nodeP) {
        if (nodeP == null) {
            return false;
        }
        if (nodeP.point.equals(p)) {
            return true;
        }
        if (nodeP.isVertical) {
            if (p.x() < nodeP.point.x()) {
                return containsRecursive(p, nodeP.left);
            }
            else {
                return containsRecursive(p, nodeP.right);
            }
        }
        else {
            if (p.y() < nodeP.point.y()) {
                return containsRecursive(p, nodeP.left);
            }
            else {
                return containsRecursive(p, nodeP.right);
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        drawRecursive(root);
    }

    private void drawRecursive(Node nodeP) {
        // draw current point
        StdDraw.setPenColor(StdDraw.BLACK);
        // TODO:
        // StdDraw.setPenRadius(0.02);
        // nodeP.point.draw();
        // StdDraw.textLeft(nodeP.point.x(), nodeP.point.y(), nodeP.point.toString());
        // System.out.println("drawing " + nodeP.point);


        // draw line of current point
        if (nodeP.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            // TODO:
            // StdDraw.setPenRadius(0.005);
            System.out.println("RectHV: " + nodeP.rect);
            StdDraw.line(nodeP.point.x(), nodeP.rect.ymin(), nodeP.point.x(), nodeP.rect.ymax());
        }
        else {
            // TODO:
            // StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(StdDraw.BLUE);
            System.out.println("RectHV: " + nodeP.rect);
            StdDraw.line(nodeP.rect.xmin(), nodeP.point.y(), nodeP.rect.xmax(), nodeP.point.y());
        }

        if (nodeP.left != null) drawRecursive(nodeP.left);
        if (nodeP.right != null) drawRecursive(nodeP.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }
        List<Point2D> pointInRects = new LinkedList<>();
        rangeRecursive(rect, root, pointInRects);
        return pointInRects;
    }

    // all points that are inside the rectangle (or on the boundary) in a tree that from a nodeP
    private void rangeRecursive(RectHV rect, Node currentNode, List<Point2D> pointInRects) {
        if (rect.contains(currentNode.point)) {
            pointInRects.add(currentNode.point);
        }
        if (currentNode.left != null && rect.intersects(currentNode.left.rect)) {
            rangeRecursive(rect, currentNode.left, pointInRects);
        }
        if (currentNode.right != null && rect.intersects(currentNode.right.rect)) {
            rangeRecursive(rect, currentNode.right, pointInRects);
        }
    }

    //
    // // a nearest neighbor in a nodeP, return
    // private Point2D nearestRecursive(Point2D pNearest, Point2D p, Node nodeP) {
    //     if (nodeP == null)
    //         return pNearest;
    //     if (nodeP.point.distanceSquaredTo(p) < pNearest.distanceSquaredTo(p)) {
    //         pNearest = nodeP.point;
    //     }
    //     if (nodeP.left != null && nodeP.left.rect.contains(p)) {
    //         pNearest = nearestRecursive(pNearest, p, nodeP.left);
    //         if (nodeP.right != null
    //                 && nodeP.right.rect.distanceSquaredTo(pNearest) < p.distanceSquaredTo(
    //                 pNearest)) {
    //             pNearest = nearestRecursive(pNearest, p, nodeP.right);
    //         }
    //     }
    //
    //     if (nodeP.right != null && nodeP.right.rect.contains(p)) {
    //         pNearest = nearestRecursive(pNearest, p, nodeP.right);
    //         if (nodeP.left != null
    //                 && nodeP.left.rect.distanceSquaredTo(pNearest) < p.distanceSquaredTo(
    //                 pNearest)) {
    //             pNearest = nearestRecursive(pNearest, p, nodeP.left);
    //         }
    //     }
    //
    //     return pNearest;
    // }
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }
        return nearestRecursive(root.point, p, root);
    }

    // pNearsest so far -> return pNearest in a nodeP (mean tree from nodeP)
    private Point2D nearestRecursive(Point2D minPoint, Point2D queryPoint, Node currentNode) {
        if (currentNode == null) {
            return minPoint;
        }
        // TODO:
        // System.out.println("currentNode: " + currentNode.point);
        // StdDraw.setPenColor(StdDraw.GREEN);
        // StdDraw.setPenRadius(0.04);
        // currentNode.point.draw();
        // StdDraw.show();

        double currentToQuery = currentNode.point.distanceSquaredTo(queryPoint);
        double minToQuery = minPoint.distanceSquaredTo(queryPoint);

        if (currentToQuery < minToQuery) {
            minPoint = currentNode.point;
        }

        boolean isLeftContainQueryPoint = currentNode.left != null
                && currentNode.left.rect.contains(queryPoint);
        boolean isRightContainQueryPoint = currentNode.right != null
                && currentNode.right.rect.contains(queryPoint);
        double distanceToLeft = (currentNode.left == null || isLeftContainQueryPoint) ?
                                Double.MAX_VALUE :
                                currentNode.left.rect.distanceSquaredTo(queryPoint);

        double distanceToRight = (currentNode.right == null || isRightContainQueryPoint) ?
                                 Double.MAX_VALUE :
                                 currentNode.right.rect.distanceSquaredTo(queryPoint);
        Node firstNode =
                (isLeftContainQueryPoint || (!isLeftContainQueryPoint && !isRightContainQueryPoint
                        && (distanceToLeft < distanceToRight))) ?
                currentNode.left :
                currentNode.right;
        Node secondNode = (firstNode == currentNode.left) ? currentNode.right : currentNode.left;

        if (firstNode != null && firstNode.rect.distanceSquaredTo(queryPoint) < minToQuery) {
            minPoint = nearestRecursive(minPoint, queryPoint, firstNode);
            minToQuery = minPoint.distanceSquaredTo(
                    queryPoint); // if not this line, minToQuery will be evaluated by minPoint before, TODO: NOTE
        }
        if (secondNode != null
                && secondNode.rect.distanceSquaredTo(queryPoint) < minToQuery) {
            minPoint = nearestRecursive(minPoint, queryPoint, secondNode);
        }
        return minPoint;
    }


    public static void main(String[] args) {

    }


}
