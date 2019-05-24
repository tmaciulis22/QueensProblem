/* Tautvydas Mačiulis 3gr. 2-a ADS užduotis, 15 var. */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int n, result = 0, maxResult = 0;
    public static int[][] maxBoard;
    public static ArrayList<int[][]> maxBoards = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter N: ");
        n = scanner.nextInt();
        scanner.close();
        calculateQueens(initializeBoard());
    }

    public static void calculateQueens(int[][] board){
        placeQueen(board, 0);
        for(int[][] b : maxBoards){
            printBoard(b);
        }
        System.out.println("Max amount of queens to place: " + maxResult);
    }

    public static boolean placeQueen(int[][] board, int col){
        if(col<n){
            for(int i = 0; i < n; i++){
                if(col>i){
                    continue;
                }
                if(isPossible(board, i, col)) {
                    board[i][col] = 1;
                    result++;
                    if(maxResult<result){
                        maxResult=result;
                        maxBoards.clear();
                        addMaxBoard(board);
                    }
                    else if(maxResult==result){
                        addMaxBoard(board);
                    }
                    if(placeQueen(board, col + 1)) return true;
                    board[i][col]=0;
                    result--;
                }
            }
        }
        return false;
    }

    public static void addMaxBoard(int[][] board){
        maxBoard = board.clone();
        for(int x = 0; x < board.length; x++) {
            maxBoard[x] = board[x].clone();
        }
        maxBoards.add(maxBoard);
    }

    public static boolean isPossible(int[][] board, int row, int col){
        for(int j = 0; j < col; j++){
            if(board[row][j]==1){
                return false;
            }
        }
        for(int i=row, j=col; i>=0 && j >= 0;i--, j--){
            if(board[i][j]==1){
                return false;
            }
        }
        for(int i=row, j=col; i<n && j >= 0;i++, j--){
            if(board[i][j]==1){
                return false;
            }
        }
        return true;
    }

    public static int[][] initializeBoard(){
        int[][] board = new int[n][];
        for (int i = 0; i < n; i++){
            board[i] = new int[i+1];
            for (int j = 0; j <= i; j++){
                board[i][j] = 0;
            }
        }
        return board;
    }

    public static void printBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 0) {
                    System.out.print("* ");
                }else {
                    System.out.print("Q ");
                }
            }
            System.out.println();
        }
    }
}
