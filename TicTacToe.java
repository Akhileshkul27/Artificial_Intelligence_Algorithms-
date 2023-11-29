import java.util.Scanner;
public class TicTacToe {
    private char[][] board;
    private String player;
    public TicTacToe(String player) {
        board = new char[3][3];
        this.player = player;
        initializeBoard();
    }
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    public void playGame() {
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard();
        while (!gameOver) {
            System.out.println("Player " + player + ", enter your move (row [1-3] column [1-3]): ");
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;
            if (isValidMove(row, col)) {
                board[row][col] = player.charAt(0);
                printBoard();
                if (checkWin()) {
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (boardFull()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    if(player=="x"){
                        player = "o";
                    }
                    else if (player=="o"){
                        player = "x";
                    }
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        System.out.println("Game Over!");
        sc.close();
    }
    private boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player.charAt(0) && board[i][1] == player.charAt(0) &&
                    board[i][2] == player.charAt(0))
                return true;
            if (board[0][i] == player.charAt(0) && board[1][i] == player.charAt(0) &&
                    board[2][i] == player.charAt(0))
                return true;
        }
        if (board[0][0] == player.charAt(0) && board[1][1] == player.charAt(0) &&
                board[2][2] == player.charAt(0))
            return true;
        if (board[0][2] == player.charAt(0) && board[1][1] == player.charAt(0) &&
                board[2][0] == player.charAt(0))
            return true;
        return false;
    }
    private boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-')
            return true;
        return false;
    }
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
    public static void main(String[] args) {
        String player = "x";
        TicTacToe ticTacToe = new TicTacToe(player);
        ticTacToe.playGame();
    }
}