/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FastCollinearPoints {
    private List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }
        segments = new ArrayList<LineSegment>();
        checkDuplicatePoints(points);

        Point[] pointsCopy = points.clone();

        for (int i = 0; i < points.length; i++) {
            // make sure point A to point B is if colinear is include all point in middle
            Arrays.sort(pointsCopy);

            Arrays.sort(pointsCopy, points[i].slopeOrder());

            // check for 4 or more points is a line in a sorted array
            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                while (last < pointsCopy.length
                        && pointsCopy[p].slopeTo(pointsCopy[first]) == pointsCopy[p].slopeTo(
                        pointsCopy[last])) {
                    last++;
                }
                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                // find next
                first = last;
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


    public int numberOfSegments() {
        return this.segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[this.segments.size()]);
    }


    public static void main(String[] args) {
        // read the n points from a file
        // In in = new In(args[0]);
        In in = new In("input9.txt");

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
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
