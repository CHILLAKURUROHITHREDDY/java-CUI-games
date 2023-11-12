import java.util.Scanner;
import java.util.Random;

public class RPS{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		Random rad = new Random();
		int computerscore = 0, playerscore = 0, draw = 0;
		byte computer, player;

		System.out.println("Rock Paper Scissors game\n");
		
		// Game loop
		while(true){
			
			computer = (byte) (rad.nextInt(2)+1); // Generating random computer choice (1 to 3)
			System.out.print("Enter 0 to exit\nEnter 1 for rock\nEnter 2 for paper\nEnter 3 for scissor\nEnter your choice = ");
			player = in.nextByte();
			if(player == 0){
				break; // Exit the game loop
			}

			// Display computer's choice
			if(computer == 1){
				System.out.println("\nComputer choice = rock");
			}
			else if(computer == 2){
				System.out.println("\nComputer choice = paper");
			}
			else{
				System.out.println("\nCompter choice = scissor");
			}
			
			byte result = RPS.resultCheck(computer, player);

			// Update scores based on the result
			if(result == -1)
				computerscore++;
			else if(result == 0)
				draw++;
			else if(result == 1)
				playerscore++;
			else
				System.out.println("!!! Please enter correct choice !!!\n");
			
		}
		in.close(); // Close the Scanner

		// Display final scores
		System.out.println("\nComputer score = "+computerscore);
		System.out.println("Player score = "+playerscore);
		System.out.print("Draw score = "+draw);
	}

	// Method to check the result of the game 
	static byte resultCheck(int computer, int player) {
		
		if(computer == player){
			System.out.println("Match is draw\n");
			return 0;
		}
		else if(player == 1 && computer == 2 || player == 2 && computer == 3 || player == 3 && computer == 1){
			System.out.println("Computer won the match\n");
			return -1;
		}
		else if(player == 1 && computer == 3 || player == 2 && computer == 1 || player == 3 && computer == 2){
			System.out.println("Player won the match\n");
			return 1;
		}
		else if(computer == 1 && player == 2 || computer == 2 && player == 3 || computer == 3 && player == 1){
			System.out.println("Player won the match\n");
			return 1;
		}
		else if(computer == 1 && player == 3 || computer == 2 && player == 1 || computer == 3 && player == 2){
			System.out.println("Computer won the match\n");
			return -1;
		}
		
		return -2; // Indicates an unexpected result
	}
}
