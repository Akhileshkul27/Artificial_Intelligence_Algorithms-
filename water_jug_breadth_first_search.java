import java.util.*;

public class water_jug_breadth_first_search {
    public static class State{
        int jugA;
        int jugB;
        State parent;
        public State(int jugA, int jugB, State parent){
            this.jugA = jugA;
            this.jugB = jugB;
            this.parent = parent;
        }
    }

    public static List<int[]> way(State goal) {
        List<int[]> path = new ArrayList<>();
        // Traverse the path and add states to the list
        while (goal != null) {
            path.add(0, new int[] { goal.jugA, goal.jugB });
            goal = goal.parent;
        }
        return path;
    }

    public static List<int[]> solution(int jugA, int jugB, int target){
        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(jugA, jugB, null));
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if(current.jugA == target) return way(current);
            if(!visited.contains(current)){
                //Pour water from jugA to jugB
                if(current.jugA > 0){
                    int AmountToPour = jugB - current.jugB;
                    if(current.jugA <= AmountToPour)
                    {
                        int newjugB = current.jugB + current.jugA;
                        int newjugA = 0;
                        queue.add(new State(newjugA, newjugB, current));
                    } else {
                        int newjugB = current.jugB + AmountToPour;
                        int newjugA = current.jugA - AmountToPour;
                        queue.add(new State(newjugA, newjugB, current));
                    }
                }
                //Pour water from jugB to jugA
                if(current.jugB > 0){
                    int AmountToPour = jugA - current.jugA;
                    if(current.jugB <= AmountToPour){
                        int newjugA = current.jugA + current.jugB;
                        int newjugB = 0;
                        queue.add(new State(newjugA, newjugB, current));
                    } else {
                        int newjugA = current.jugA + AmountToPour;
                        int newjugB = current.jugB - AmountToPour;
                        queue.add(new State(newjugA, newjugB, current));
                    }
                }
                //Empty jugA
                queue.add(new State(0, current.jugB, current));
                //Empty jugB
                queue.add(new State(current.jugA, 0, current));
                //fill jugA
                queue.add(new State(jugA, current.jugB, current));
                //filljugB
                queue.add(new State(current.jugA, jugB, current));
            }
        }
        return null;
    }
    public static void main(String[] args) {
        List<int[]> path = solution(4, 3, 2);
        for (int[] arr : path) {
            System.out.print(Arrays.toString(arr));
            System.out.print("->");
        }
        System.out.println("Goal");
    }
}