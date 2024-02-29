/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class testKdTree {
    /**************** TEST FUNCTIONS ****************/


    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void testNearest(Point2D p, Point2D expected, KdTree kdTree, int testNumber) {
        Point2D pNearest = kdTree.nearest(p);
        if (pNearest.equals(expected)) {
            System.out.println(ANSI_GREEN + "Test " + testNumber + " : Passed" + ANSI_RESET);
        }
        else {
            System.out.println(
                    ANSI_RED + "Test " + testNumber + " : Failed, pNearest Failed = " + pNearest
                            + ", expected = " + expected + ANSI_RESET);
        }
    }

    private static void testRange(RectHV rect, Set<Point2D> expectedPoints, KdTree kdTree,
                                  int testNumber) {
        Iterable<Point2D> pointsInRect = kdTree.range(rect);

        List<Point2D> actualPoints = new ArrayList<>();
        for (Point2D actualPoint : pointsInRect) {
            actualPoints.add(actualPoint);
        }

        List<Point2D> expectedList = new ArrayList<>(expectedPoints);

        boolean testPassed = actualPoints.size() == expectedList.size() &&
                actualPoints.containsAll(expectedList) && expectedList.containsAll(actualPoints);

        if (testPassed) {
            System.out.println(ANSI_GREEN + "Test " + testNumber + " : Passed" + ANSI_RESET);
        }
        else {
            System.out.println(
                    ANSI_RED + "Test " + testNumber + " : Failed" + ANSI_RESET + "pointsInRect = "
                            + pointsInRect + ", expectedPoints = " + expectedPoints);
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        // test 1 : isEmpty() and size()
        if (kdTree.isEmpty() && kdTree.size() == 0) {
            System.out.println(ANSI_GREEN + "Test " + 1 + " : Passed" + ANSI_RESET);
        }
        // test 2 : contain() and insert() and size(
        Point2D A = new Point2D(0.7, 0.2);
        Point2D B = new Point2D(0.5, 0.4);
        Point2D C = new Point2D(0.2, 0.3);
        Point2D D = new Point2D(0.4, 0.7);
        Point2D E = new Point2D(0.9, 0.6);
        kdTree.insert(A);
        kdTree.insert(B);
        kdTree.insert(C);
        kdTree.insert(D);
        kdTree.insert(E);
        kdTree.insert(E);
        if (kdTree.size() == 5 && kdTree.contains(A) && kdTree.contains(B) && kdTree.contains(
                C) && kdTree.contains(D) && kdTree.contains(E)) {
            System.out.println(ANSI_GREEN + "Test " + 2 + " : Passed" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_RED + "Test " + 2 + " : Failed" + ANSI_RESET);
        }


        System.out.println("\n");
        System.out.println("----------TEST RANGE----------");
        testRange(new RectHV(0.0, 0.0, 1.0, 1.0), Set.of(A, B, C, D, E), kdTree, 1);
        testRange(new RectHV(0.0, 0.0, 1.0, 0.4), Set.of(A, B, C), kdTree, 2);
        testRange(new RectHV(0.54, 0.8, 0.65, 0.95), Set.of(), kdTree, 3);

        System.out.println("\n");
        System.out.println("----------TEST NEAREST---------");
        testNearest(new Point2D(0, 0), C, kdTree, 1);
        testNearest(new Point2D(1, 0), A, kdTree, 2);
        testNearest(new Point2D(0.4, 0.4), B, kdTree, 3);
        testNearest(new Point2D(0.2, 0.41), C, kdTree, 4);
        testNearest(new Point2D(0.706, 1), D, kdTree, 5);
        testNearest(new Point2D(0.214, 0.524), C, kdTree, 6);
        testNearest(new Point2D(0.79, 0.38), A, kdTree, 7);

        /************************ TEST 2 ************************/
        KdTree kdTree2 = new KdTree();

        // Given points
        Point2D A1 = new Point2D(0.372, 0.497);
        Point2D B1 = new Point2D(0.564, 0.413);
        Point2D C1 = new Point2D(0.226, 0.577);
        Point2D D1 = new Point2D(0.144, 0.179);
        Point2D E1 = new Point2D(0.083, 0.51);
        Point2D F1 = new Point2D(0.32, 0.708);
        Point2D G1 = new Point2D(0.417, 0.362);
        Point2D H1 = new Point2D(0.862, 0.825);
        Point2D I1 = new Point2D(0.785, 0.725);
        Point2D J1 = new Point2D(0.499, 0.208);

        // Insert points into kdTree2
        kdTree2.insert(A1);
        kdTree2.insert(B1);
        kdTree2.insert(C1);
        kdTree2.insert(D1);
        kdTree2.insert(E1);
        kdTree2.insert(F1);
        kdTree2.insert(G1);
        kdTree2.insert(H1);
        kdTree2.insert(I1);
        kdTree2.insert(J1);

        testNearest(new Point2D(0.99, 0.59), I1, kdTree2, 12);
    }
}
