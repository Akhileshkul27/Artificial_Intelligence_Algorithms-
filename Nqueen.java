import java.util.*;

class Nqueen{
    public int boardSize;
    public List<Integer>solution;

   public Nqueen(int boardSize) {
        this.boardSize = boardSize;
        this.solution = new ArrayList<>();
   }
   public boolean isSafe(int row, int column){
       for(int i = 0;i<row;i++){
           int prevC = this.solution.get(i);
           if(column==prevC || Math.abs(prevC-column)==Math.abs(i-row)){
               return false;
           }
       }
       return true;
   }

   public void printSolution(){
       if(this.solution.isEmpty()){
           System.out.println("No solution found");
       }
       else {
           for (int r = 0; r < this.boardSize; r++) {
               for (int c = 0; c < this.boardSize; c++) {
                   if (c==this.solution.get(r)){
                       System.out.print("Q ");
                   }
                   else{
                       System.out.print("X ");
                   }
               }
               System.out.println();
           }
       }
   }
   public boolean solve(){
       return this.backtrack(0);
   }
   public boolean backtrack(int row){
       if(row==this.boardSize){
           return true;
       }
       else{
           for(int c = 0;c<this.boardSize;c++){
               if(this.isSafe(row,c)) {
                   this.solution.add(c);
                   if (this.backtrack(row + 1)) {
                       return true;
                   }
                   this.solution.remove(this.solution.size()-1);
               }
           }
           return false;
       }
   }

    public static void main(String[] args){
       Scanner sc =  new Scanner(System.in);
        System.out.println("Enter n queens(n): ");
        int n = sc.nextInt();
        Nqueen csp = new Nqueen(n);
        if(csp.solve()){
            System.out.println("Solution found!");
            csp.printSolution();
        }
        else{
            System.out.println("No solution found...........");
        }
    }
}