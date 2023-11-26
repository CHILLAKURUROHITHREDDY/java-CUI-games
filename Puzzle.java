import java.util.Scanner;
import java.util.Random;

public class Puzzle{

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int size, move, steps = 0;
		System.out.println("Puzzle Game : \n");
		System.out.print("Enter size of the puzzle between 1 and 11 : ");
		size = in.nextInt(); // Puzzle size
		int[][] board = new int[size][size];
		
		if(size >= 2){
			
			// Board setup
			Puzzle.installation(board, size);
			
			// Game loop
			while(true){
				
				// Prints the board
				int currectCount = Puzzle.print(board, size);
				
				// Game over after puzzle completed 
				if(currectCount == (size*size)-1 && (board[0][0] == size*size || board[size-1][size-1] == size*size)){
					System.out.print("!!! congratulations you done it in "+steps+" steps !!!");
					in.close();
					break; // Exit Game loop
				}
				else{
					System.out.print("Enter number to move = ");
					move = in.nextInt();
					
					// To swap a number
					steps = steps+Puzzle.swap(board, size, move);
				}
			}
		}
		else {
			System.out.println("\nEnter more than 1");
		}
		
	}
	
	// Method to setup board
	static void installation(int[][] board, int size) {
		
		int row = 0, col = 0;
		boolean  flag;
		Random rad = new Random();
		
		while(true){
			flag = true;
			if(row == size)
				break;
			int temp = rad.nextInt((size*size))+1;
			
			// Checks temp present in board or not
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
					if(temp == (board[i][j])){
						flag = false;
					}
				}
			}
			if(flag){
				board[row][col] = temp;
				col++;
				if(col == size){
					col = 0;
					row++;
				}
			}
		}
		
	}
	
	// Method to print board and returns number of correct position
	static int print(int[][] board, int size) {
		
		int sum = 0, correctCount = 0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(board[i][j] < 10){
					System.out.print(" ");
				}
				if(board[i][j] != size*size){
					System.out.print(board[i][j]+"  ");
					if(sum < board[i][j]){
						sum = board[i][j];
						correctCount++;
					}
				}
				else{
					if(board[i][j] > 9){
						System.out.print(" ");
					}
				  System.out.print("   ");
				}
			}
			System.out.print("\n");
		}
		return correctCount;
	}
	
	// Method to swap number
	static short swap(int[][] board, int size, int move) {
		
		int nullRow = 0, nullCol = 0, numberRow = 0, numberCol = 0;
		
		// Checks position of empty and moving number 
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(move == board[i][j]){
					numberRow = i;
					numberCol = j;
				}
				if(size*size == board[i][j]) {
					nullRow = i;
					nullCol = j;
				}
			}
		}
		
		// Swaps number
		if(nullRow == numberRow-1 || nullRow == numberRow+1){
			if(nullCol == numberCol){
				int swap = board[nullRow][nullCol];
				board[nullRow][nullCol] = board[numberRow][numberCol];
				board[numberRow][numberCol] = swap;
				return 1;
			}
		}
		else if(nullCol == numberCol-1 || nullCol == numberCol+1) {
			if(nullRow == numberRow){
				int swap = board[nullRow][nullCol];
				board[nullRow][nullCol] = board[numberRow][numberCol];
				board[numberRow][numberCol] = swap;
				return 1;
				
			}
		}
		return 0;
		
	}
}
