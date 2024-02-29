/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *  RUN : java-algs4 BruteCollinearPoints input8.txt
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private int count = 0;
    private List<LineSegment> segments;

    // finds all line segments containing 4 point
    public BruteCollinearPoints(Point[] pointss) {
        if (pointss == null) {
            throw new IllegalArgumentException("points is null");
        }
        segments = new ArrayList<LineSegment>();
        checkDuplicatePoints(pointss);
        Point[] points = pointss.clone();

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])
                                && points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            // System.out.println(points[i] + " " + points[j] + " " + points[k] + " "
                            //                          + points[l]);
                            LineSegment segment = new LineSegment(points[i], points[l]);
                            segments.add(segment);
                            this.count++;
                        }
                    }
                }
            }
        }
    }

    private void checkDuplicatePoints(Point[] points) {
        for (int p = 0; p < points.length; p++) {
            if (points[p] == null) {
                throw new IllegalArgumentException("points[" + p + "] is null");
            }
            for (int j = p + 1; j < points.length; j++) {
                if (points[j] != null && points[p].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("points[" + p + "] == points[" + j + "]");
                }
            }
        }
    }

    ;

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        // System.out.println(segments.toArray(new LineSegment[count]));
        return segments.toArray(new LineSegment[count]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        In in = new In("input8.txt");

        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();

    }
}
