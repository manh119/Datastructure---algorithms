import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private int manhattan;
    private int hamming;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        int size = tiles.length;
        this.tiles = new int[size][size];
        this.manhattan = 0;
        this.hamming = 0;
        // goals[dimension() - 1][dimension() - 1] = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = (int) tiles[i][j];
                // cal manhattan
                if (tiles[i][j] != 0) {
                    int goalRow = (tiles[i][j] - 1) / size;
                    int goalCol = (tiles[i][j] - 1) % size;
                    manhattan += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
                // cal hamming
                int goalij = i * size + j + 1;
                if (tiles[i][j] != 0 && tiles[i][j] != goalij) {
                    hamming++;
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (getClass() != y.getClass()) {
            return false;
        }
        // check dimension() of y
        if (((Board) y).dimension() != dimension()) {
            return false;
        }
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != ((Board) y).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int num = 0;
        int colZero = 0, rowZero = 0;

        // find position of 0
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] == 0) {
                    colZero = j;
                    rowZero = i;
                    break;
                }
            }
        }

        // move 4 directions, each k is a direction

        List<Board> neighbors = new ArrayList<Board>();
        for (int k = 1; k <= 4; k++) {
            int colNewPos = colZero;
            int rowNewPos = rowZero;
            switch (k) {
                case 1:
                    rowNewPos = rowZero - 1;
                    break;
                case 2:
                    rowNewPos = rowZero + 1;
                    break;
                case 3:
                    colNewPos = colZero - 1;
                    break;
                case 4:
                    colNewPos = colZero + 1;
                    break;
            }

            if (rowNewPos >= 0 && rowNewPos < dimension() && colNewPos >= 0
                    && colNewPos < dimension()) {
                int[][] newBoard = new int[dimension()][dimension()];
                for (int i = 0; i < dimension(); i++) {
                    for (int j = 0; j < dimension(); j++) {
                        newBoard[i][j] = tiles[i][j];
                    }
                }
                newBoard[rowZero][colZero] = newBoard[rowNewPos][colNewPos];
                newBoard[rowNewPos][colNewPos] = 0;
                neighbors.add(new Board(newBoard));
            }
        }
        return neighbors;
    }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinBlocks = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                twinBlocks[i][j] = tiles[i][j];
            }
        }

        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            twinBlocks[0][0] = tiles[0][1];
            twinBlocks[0][1] = tiles[0][0];
        }
        else {
            twinBlocks[1][0] = tiles[1][1];
            twinBlocks[1][1] = tiles[1][0];
        }

        return new Board(twinBlocks);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board b = new Board(new int[][] { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } });
        System.out.println(b);
        System.out.println("Hamming should be 5 =" + b.hamming());
        System.out.println("Manhattan should be 10 = " + b.manhattan());
        for (Board board : b.neighbors()) {
            System.out.println(board);
        }
    }

}