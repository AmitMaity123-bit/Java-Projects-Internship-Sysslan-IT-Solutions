import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        char player = 'X';

        while (true) {

            printBoard();

            System.out.print("Player " + player + " choose position (1-9): ");
            int pos = sc.nextInt();

            if (!placeMark(pos, player)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (checkWinner()) {
                printBoard();
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Game Draw!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " | " +
                    board[i][1] + " | " + board[i][2]);
            if (i < 2) System.out.println("--+---+--");
        }
        System.out.println();
    }

    static boolean placeMark(int pos, char player) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == (char)(pos + '0')) {
                    board[i][j] = player;
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkWinner() {

        for (int i = 0; i < 3; i++) {

            if (board[i][0] == board[i][1]
                    && board[i][1] == board[i][2])
                return true;

            if (board[0][i] == board[1][i]
                    && board[1][i] == board[2][i])
                return true;
        }

        if (board[0][0] == board[1][1]
                && board[1][1] == board[2][2])
            return true;

        return board[0][2] == board[1][1]
                && board[1][1] == board[2][0];
    }

    static boolean isBoardFull() {

        for (char[] row : board) {
            for (char cell : row) {
                if (Character.isDigit(cell))
                    return false;
            }
        }
        return true;
    }
}