import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean solvable;
    private final Stack<Board> solutionSteps;
    private final int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial Board cannot be null");
        }

        SearchNode initialNode = new SearchNode(initial, 0, null);
        SearchNode twinNode = new SearchNode(initial.twin(), 0, null);
        MinPQ<SearchNode> pq = new MinPQ<>();
        MinPQ<SearchNode> twinpq = new MinPQ<>();
        pq.insert(initialNode);
        twinpq.insert(twinNode);
        solutionSteps = new Stack<>();

        while (true) {
            SearchNode currentNode = pq.delMin();
            SearchNode twinCurrentNode = twinpq.delMin();

            if (currentNode.board.isGoal()) {
                solvable = true;
                moves = currentNode.moves;
                while (currentNode.previous != null) {
                    solutionSteps.push(currentNode.board);
                    currentNode = currentNode.previous;
                }
                break;
            }

            if (twinCurrentNode.board.isGoal()) {
                moves = -1;
                solvable = false;
                break;
            }

            // add neighbers to priority queue
            for (Board neighbor : currentNode.board.neighbors()) {
                if (currentNode.previous == null || !neighbor.equals(currentNode.previous.board)) {
                    pq.insert(new SearchNode(neighbor, currentNode.moves + 1, currentNode));
                }
            }

            for (Board neighbor : twinCurrentNode.board.neighbors()) {
                if (twinCurrentNode.previous == null || !neighbor.equals(
                        twinCurrentNode.previous.board)) {
                    twinpq.insert(
                            new SearchNode(neighbor, twinCurrentNode.moves + 1, twinCurrentNode));
                }
            }
        }
        solutionSteps.push(initial);

    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode previous;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        // compare the search nodes base move and manhattan distance
        public int compareTo(SearchNode that) {
            return Integer.compare(this.moves + this.board.manhattan(),
                                   that.moves + that.board.manhattan());

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solvable ? solutionSteps : null;
    }

    // test client (see below) 
    public static void main(String[] args) {
        Board initial = new Board(new int[][] { { 2, 1, 3 }, { 4, 5, 6 }, { 8, 7, 0 } });
        Solver solver = new Solver(initial);
        System.out.println(initial.twin());
        System.out.println("moves = " + solver.moves());
        System.out.println(solver.solution());

    }

}