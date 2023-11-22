import java.util.Scanner;
import java.util.Random;

public class TicTacToe	{
	
	public static void main(String[] args)	{
		
		Scanner in = new Scanner(System.in);
		Random rad = new Random();
		char playersymbol = ' ', computersymbol = ' ';
		int player = 0, computer = 0, close = 0;
		boolean computerPlay, flag, finishCheck;
		char[][] gameBox = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		System.out.print("Enter noof players 1 or 2 = ");
		
		// Checking number of players
		if(in.nextInt() == 1)
			computerPlay = true;
		else
			computerPlay = false;
		
		if(computerPlay){
			
			// Setting computer symbol based on player symbol
			System.out.print("Enter your choice = ");
			playersymbol = in.next().charAt(0);
			if(playersymbol != 'X' && playersymbol != 'x'){
				computersymbol = 'X';
			}
			else{
				computersymbol = 'O';
			}
		}
		else if(!computerPlay){
			
			// Setting player 1 and player 2 symbols
			System.out.print("Enter player 1 choice = ");
			playersymbol = in.next().charAt(0);
			while(true){
				System.out.print("Enter player 2 choice = ");
				computersymbol = in.next().charAt(0);
				if(playersymbol != computersymbol)
					break;
			}
		}
		TicTacToe.print(gameBox); // Printing game box
		
		// Game loop
		while(close <= 9)	{
			flag = true;
			
			// Loop till correct position of player
			while(flag)	{
				if(computerPlay)
					System.out.print("Enter your place = ");
				else if(!computerPlay)
					System.out.print("Enter player 1 place = ");
				player = in.nextInt()-1;
				flag = TicTacToe.placeCheck(player, gameBox, playersymbol);
				if(!flag)
					TicTacToe.print(gameBox);
			}
			close++; // Number of iterations
			
			// Checks finished or not
			finishCheck = TicTacToe.finish(gameBox, playersymbol);
			if(finishCheck){
				if(computerPlay)
					System.out.print("\nPlayer won");
				else if(!computerPlay)
					System.out.print("\nPlayer 1 won");
				break;
			}
			
			// Terminates game
			else if(close == 9){
				System.out.print("\nMatch is draw");
				break;
			}
			
			flag = true;
			
			// Loop till correct position of computer or player 2
			while(flag){
				if(computerPlay){
					computer = rad.nextInt(8);
					flag = TicTacToe.placeCheck(computer, gameBox, computersymbol);
					if(!flag) {
						System.out.println("Computer choice = "+(computer+1));
						TicTacToe.print(gameBox);
					}
				}
				else if(!computerPlay){
					System.out.print("Enter player 2 place = ");
					computer = in.nextInt()-1;
					flag = TicTacToe.placeCheck(computer, gameBox, computersymbol);
					TicTacToe.print(gameBox);
				}
			}
			close++; // Number of iterations
			
			// Checks finished or not
			finishCheck = TicTacToe.finish(gameBox, computersymbol);
			if(finishCheck){
				if(computerPlay)
					System.out.print("\nComputer won");
				else if(!computerPlay)
					System.out.print("\nPlayer 2 won");
				break;
			}
			
			// Terminates game
			else if(close == 9){
				System.out.print("\nMatch is draw");
				break;
			}
		}
		in.close(); // close's Scanner
	}
	
	// Method to print
	static void print(char[][] gameBox){
		System.out.println("-------");
		for(int i=0;i<3;i++){
			System.out.print("|");
			for(int j=0;j<3;j++){
				System.out.print(gameBox[i][j]);
				System.out.print("|");
			}
			System.out.println("\n-------");
		}
	}
	
	// Method to check movement of the current player
	static boolean placeCheck(int number, char[][] gameBox, char symbol) {
		
		int row = 0, col = 0;
		if(number < 3){
			row = 0;
			col = number;
		}
		else if(number < 6){
			row = 1;
			col = number-3;
		}
		else if(number < 9){
			row = 2;
			col = number-6;
		}
		if(number < 9 && number >= 0 && gameBox[row][col] == ' '){
			gameBox[row][col] = symbol;
			return false;
		}
		return true;
	}
	
	// Method to check game completed or not
	static boolean finish(char[][] gameBox,char symbol){
		for(int i=0;i<3;i++){
			if(gameBox[i][0] == gameBox[i][1] && gameBox[i][1] == gameBox[i][2] && gameBox[i][2] == symbol){
				return true;
			}
			if(gameBox[0][i] == gameBox[1][i] && gameBox[1][i] == gameBox[2][i] && gameBox[2][i] == symbol){
				return true;
			}
		}
		if(gameBox[0][0] == gameBox[1][1] && gameBox[1][1] == gameBox[2][2] && gameBox[2][2] == symbol){
			return true;
		}
		if(gameBox[0][2] == gameBox[1][1] && gameBox[1][1] == gameBox[2][0] && gameBox[2][0] == symbol){
			return true;
		}
		return false;
	}
}
