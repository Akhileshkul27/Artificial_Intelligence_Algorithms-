import java.util.*;
class PuzzleNode {
    int[][] puzzle;
    int cost;
    int heuristic;
    PuzzleNode parent;
    public PuzzleNode(int[][] puzzle, int cost, int heuristic, PuzzleNode parent) {
        this.puzzle = puzzle;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }
}
public class astar_eight {
    public static void main(String[] args) {
        int[][] initialPuzzle = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };
        solvePuzzle(initialPuzzle);
    }
    public static void solvePuzzle(int[][] initialPuzzle) {
        int[][] goalPuzzle = {
                {1, 2, 3},
                {4, 6, 0},
                {7, 5, 8}
        };
        PuzzleNode initialNode = new PuzzleNode(initialPuzzle, 0, calculateHeuristic(initialPuzzle, goalPuzzle), null);
        Queue<PuzzleNode> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic));
        Set<PuzzleNode> visitedNodes = new HashSet<>();
        openSet.add(initialNode);
        visitedNodes.add(initialNode);

        while (!openSet.isEmpty()) {
            PuzzleNode currentNode = openSet.poll();

            if (Arrays.deepEquals(currentNode.puzzle, goalPuzzle)) {
                printSolution(currentNode);
                return;
            }
            // Generate possible moves
            int[][] moves = {
                    {-1, 0}, {1, 0}, {0, -1}, {0, 1}
            };

            for (int[] move:moves) {
                int newX = move[0] + findZeroX(currentNode.puzzle);
                int newY = move[1] + findZeroY(currentNode.puzzle);
                if (isValidMove(newX, newY)) {
                    int[][] newPuzzle = swap(currentNode.puzzle, findZeroX(currentNode.puzzle), findZeroY(currentNode.puzzle), newX, newY);
                    int newCost = currentNode.cost + 1;
                    int newHeuristic = calculateHeuristic(newPuzzle, goalPuzzle);
                    PuzzleNode newNode = new PuzzleNode(newPuzzle, newCost, newHeuristic, currentNode);
                    if (!openSet.contains(newNode)) {
                        openSet.add(newNode);
                    }
                }
            }
        }
        System.out.println("No solution found.");
    }
    public static void printSolution(PuzzleNode node) {
            for (int[] i : node.puzzle) {
                for (int j : i) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
    }

    public static int calculateHeuristic(int[][] currentPuzzle, int[][] goalPuzzle) {
        int heuristic = 0;
        for (int i = 0; i < currentPuzzle.length; i++) {
            for (int j = 0; j < currentPuzzle[i].length; j++) {
                if (currentPuzzle[i][j] != goalPuzzle[i][j]) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    public static int findZeroX(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    private static int findZeroY(int[][] puzzle) {
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1;
    }
    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }
    private static int[][] swap(int[][] puzzle, int x1, int y1, int x2, int y2) {
        int[][] newPuzzle = new int[3][3];
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                newPuzzle[i][j] = puzzle[i][j];
            }
        }
        int temp = newPuzzle[x1][y1];
        newPuzzle[x1][y1] = newPuzzle[x2][y2];
        newPuzzle[x2][y2] = temp;
        return newPuzzle;
    }
}
