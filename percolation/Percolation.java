/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


// Percolation class
// vitural n*n grid with 2 value 0 and n * n - 1 connected to top and bottom
public class Percolation {
    WeightedQuickUnionUF UF;
    int n; // n-by-n grid
    int openedSites[];   // 1 = open, 0 = block
    int numberEle;

    // creates n-by-n grid, with all sites initially blocked
    // value at 0 and n * n - 1 is open by default
    public Percolation(int n) {
        this.n = n;
        this.numberEle = n * n + 2;
        this.UF = new WeightedQuickUnionUF(this.numberEle);

        this.openedSites = new int[this.numberEle];
        for (int i = 0; i < this.numberEle; i++) {
            this.openedSites[i] = 0;
        }

        // default top and bottom is open
        this.openedSites[0] = 1;
        this.openedSites[this.numberEle - 1] = 1;
    }

    // opens the site (row, col) if it is not open already
    // open a site by union with adjacent open sites
    public void open(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }
        else {
            int value = this.row_col_to_value(row, col);
            this.openedSites[value] = 1;
            if (is_valid_and_open(row - 1, col)) {
                UF.union(value, value - this.n);
            }
            if (is_valid_and_open(row + 1, col)) {
                UF.union(value, value + this.n);
            }
            if (is_valid_and_open(row, col - 1)) {
                UF.union(value, value - 1);
            }
            if (is_valid_and_open(row, col + 1)) {
                UF.union(value, value + 1);
            }

            // open top and bottom if not open
            if (row == 1) {
                UF.union(0, value);
            }
            if (row == this.n) {
                UF.union(this.numberEle - 1, value);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }
        int value = this.row_col_to_value(row, col);
        return openedSites[value] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.n || col < 1 || col > this.n) {
            throw new IllegalArgumentException();
        }
        int value = this.row_col_to_value(row, col);
        return UF.connected(0, value);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < this.numberEle; i++) {
            count += this.openedSites[i];
        }
        // minus 2 for top and bottom virtual site is always open
        return count - 2;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(0, this.numberEle - 1);
    }

    // convert row and col to value, row, col >= 1
    private int row_col_to_value(int row, int col) {
        return this.n * (row - 1) + col;
    }

    // a position is valid if it is in the grid
    private boolean is_valid_position(int row, int col) {
        return row >= 1 && row <= this.n && col >= 1 && col <= this.n;
    }

    // a position is valid and open
    private boolean is_valid_and_open(int row, int col) {
        return is_valid_position(row, col) && isOpen(row, col);
    }

    public static void main(String[] args) {
        // n-by-n percolation system (read from command-line, default = 10)
        int n = 3;
        if (args.length == 1) n = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(n);
        perc.open(1, 1);
    }
}
