/******************************************************************************
 *  Compilation:  javac RangeSearchVisualizer.java
 *  Execution:    java RangeSearchVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Also draw all of the points in the rectangle
 *  the user selects by dragging the mouse.
 *
 *  The range search results using the brute-force algorithm are drawn
 *  in red; the results using the kd-tree algorithms are drawn in blue.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class RangeSearchVisualizer {

    public static void main(String[] args) {
        KdTree kdTree2 = new KdTree();
        //
        // // Given points
        // Point2D A1 = new Point2D(0.372, 0.497);
        // Point2D B1 = new Point2D(0.564, 0.413);
        // Point2D C1 = new Point2D(0.226, 0.577);
        // Point2D D1 = new Point2D(0.144, 0.179);
        // Point2D E1 = new Point2D(0.083, 0.51);
        // Point2D F1 = new Point2D(0.32, 0.708);
        // Point2D G1 = new Point2D(0.417, 0.362);
        // Point2D H1 = new Point2D(0.862, 0.825);
        // Point2D I1 = new Point2D(0.785, 0.725);
        // Point2D J1 = new Point2D(0.499, 0.208);


        // A  0.875 1.0
        //       B  0.25 0.5
        //       C  0.125 0.625
        //       D  0.0 0.875
        //       E  0.75 0.125
        Point2D A1 = new Point2D(0.875, 1.0);
        Point2D B1 = new Point2D(0.25, 0.5);
        Point2D C1 = new Point2D(0.125, 0.625);
        Point2D D1 = new Point2D(0.0, 0.875);
        Point2D E1 = new Point2D(0.75, 0.125);
        // Insert points into kdTree2
        kdTree2.insert(A1);
        kdTree2.insert(B1);
        kdTree2.insert(C1);
        kdTree2.insert(D1);
        kdTree2.insert(E1);


        // draw the points

        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        kdTree2.draw();

        Point2D queryPoint = new Point2D(0.8, 0.87);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.02);
        queryPoint.draw();
        StdDraw.show();


        testKdTree.testNearest(queryPoint, A1, kdTree2, 12);

        // draw the react each point


        // draw the query rectangle
        // RectHV rect = new RectHV(0.54, 0.8, 0.65, 0.95);
        // StdDraw.setPenColor(StdDraw.BLACK);
        // StdDraw.setPenRadius();
        // rect.draw();


        // draw the range search results for kd-tree in blue
        //


    }
}
