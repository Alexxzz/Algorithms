package week4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private SearchNode result = null;

    private class SearchNode {
        private final SearchNode previous;
        private final Board board;
        private final int moves;

        SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
        }

        int priority() {
            return board.manhattan() + moves;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();

        MinPQ<SearchNode> minPQ = getSearchNodes(initial);
        MinPQ<SearchNode> twinMinPQ = getSearchNodes(initial.twin());

        while (!minPQ.isEmpty()) {
            SearchNode node = minPQ.delMin();
            if (node.board.isGoal()) {
                result = node;
                break;
            }

            SearchNode twinNode = twinMinPQ.delMin();
            if (twinNode.board.isGoal()) break;

            processSearchNode(minPQ, node);
            processSearchNode(twinMinPQ, twinNode);
        }
    }

    private MinPQ<SearchNode> getSearchNodes(Board initial) {
        SearchNode head = new SearchNode(initial, null, 0);
        MinPQ<SearchNode> minPQ = new MinPQ<>(Comparator.comparingInt(SearchNode::priority));
        minPQ.insert(head);
        return minPQ;
    }

    private void processSearchNode(MinPQ<SearchNode> minPQ, SearchNode node) {
        for (Board b: node.board.neighbors()) {
            if (node.previous != null && b.equals(node.previous.board)) continue;
            minPQ.insert(new SearchNode(b, node, node.moves + 1));
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return result != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (result == null) return -1;
        return result.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> solution = new Stack<>();

        SearchNode node = result;
        while (node != null) {
            solution.push(node.board);
            node = node.previous;
        }

        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }
}
