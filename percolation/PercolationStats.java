/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double results[];

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.results = new double[trials]; // TODO : is this line necessary?
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                perc.open(row, col);
            }
            this.results[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(this.results) - (1.96 * StdStats.stddev(this.results) / Math.sqrt(
                this.results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(this.results) + (1.96 * StdStats.stddev(this.results) / Math.sqrt(
                this.results.length));
    }


    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 1000);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println(
                "95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
