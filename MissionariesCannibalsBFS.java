import java.util.*;

public class MissionariesCannibalsBFS {

    public static class State {
        int missionariesLeft;
        int cannibalsLeft;
        int boat; // 0 for left bank, 1 for right bank
        State parent;

        public State(int missionariesLeft, int cannibalsLeft, int boat) {
            this.missionariesLeft = missionariesLeft;
            this.cannibalsLeft = cannibalsLeft;
            this.boat = boat;
        }
    }
    public static List<int[]> way(State goalState) {
        List<int[]> path = new ArrayList<>();
//        State current = goalState;
        while (goalState != null) {
            path.add(0, new int[]{goalState.missionariesLeft,goalState.missionariesLeft,goalState.boat});
            goalState = goalState.parent;
        }
        return path;
    }

    public static List<State> findValidMoves(State state) {
        List<State> validMoves = new ArrayList<>();
        int missionaries = state.missionariesLeft + state.boat * 2;
        int cannibals = state.cannibalsLeft + state.boat * 2;
        for (int m = 0; m <= 2 && m <= missionaries; m++) {
            for (int c = 0; c <= 2 && c <= cannibals; c++) {
                if (m + c >= 1 && m + c <= 2) {
                    int newMissionariesLeft = state.missionariesLeft - m;
                    int newCannibalsLeft = state.cannibalsLeft - c;
                    int newBoat = 1 - state.boat;
                    if (isValid(newMissionariesLeft, newCannibalsLeft) ) {
                        validMoves.add(new State(newMissionariesLeft, newCannibalsLeft, newBoat));
                    }
                }
            }
        }
        return validMoves;
    }
    public static boolean isValid(int missionaries, int cannibals) {
        return (missionaries == 0 || missionaries >= cannibals) &&
                (3 - missionaries == 0 || (3 - missionaries) >= (3 - cannibals));
    }
    public static List<int[]> solve() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        State initialState = new State(3, 3, 1);
        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.missionariesLeft == 0 && current.cannibalsLeft == 0 && current.boat == 1) {
                // Reached the goal state
                return way(current);
            }
            List<State> validMoves = findValidMoves(current);
            for (State move : validMoves) {
                if (!visited.contains(move)) {
                    queue.add(move);
                    visited.add(move);
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        List<int[]> solutionPath = solve();
        if (solutionPath != null) {
            for (int[] state : solutionPath) {
                System.out.println("Missionaries left: " + state[0] + ", Cannibals left: " + state[1] + ", Boat position: " + (state[2] == 0 ? "Right" : "Left"));
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
