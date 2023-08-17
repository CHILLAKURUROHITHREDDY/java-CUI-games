/* CHILLAKURU ROHITH REDDY
 * 02/11/2022
 * It is a number guessing game, which is based on following factors.
 * Range,Prime, Even and odd, Palindrome, largest and smallest.
 * 
 * KEY THAT ARE USED IN THIS :
 *      .length();(To find length)
 *      Integer.toString(int);(TO Convert int to string)
 *      using java.util.concurrent.TimeUnit;
 *           To sleep program : method 1
 *           TimeUtil.SECONDS.sleep();
 *           When we sleep we get InterruptedExceptoin
 */

package games;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NumberGuessing {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Random rad = new Random();
		int min = 1, max = 9, number, level = 1, steps, flag = 1;
		NumberGuessing.printf("---INSTRUCTIONS---\n");
		NumberGuessing.printf("Palindrome means a number which is same when it reversed\n");
		while(true) {
			
			number = rad.nextInt(max-min)+min;
			NumberGuessing.printf("\n---WELCOME LEVEL "+level+"---\n");
			NumberGuessing.printf("\nThe number in range between "+min+" to "+max+"\n");
			NumberGuessing.printInformation(number);
			
			steps = 1;
			while(true) {
				
				if(steps >= 10)
					NumberGuessing.printf("Enter 0 to view compter choices\n");
				
				NumberGuessing.printf("Enter your choices = ");
				
				int input = scan.nextInt();
				if(number > input)
					NumberGuessing.printf("Enter large number\n");
				else if(number < input)
					NumberGuessing.printf("Enter small number\n");
				
				if(input == 0 && steps >= 10) {
					NumberGuessing.printf("Computer cohies is "+number+"\n");
					printf("You won "+(level-1)+" levels\n");
					flag = 0;
					break;
				}
				else if(input == number) {
					NumberGuessing.printf("\n"+input+" is correct choices\n");
					NumberGuessing.printf("Steps you thaken to complete level "+level+" = "+steps+"\n\n");
					level++;
					min = min*10;
					max = (max*10)+9;
					break;
				}
				steps++;
			}
			
			if(flag == 0)
				break;
			else if(level == 10) {
				NumberGuessing.printf("\n---GAME IS OVER---\n");
				NumberGuessing.printf("---YOU COMPLETED ALL LEVELS---\n");
				break;
			}
			else{
				NumberGuessing.printf("Enter 0 to exit\nEnter 1 to continue\nEnter your choices = ");
				flag = scan.nextInt();
				if(flag == 0) {
					NumberGuessing.printf("You won "+(level-1)+" levels\n");
					break;
				}
			}
			
		}
		scan.close();
		
	}
	
	static boolean prime(int number) {
	
		if(number == 1)
			return false;
		for(int i=2;i<number;i++)
			if(number%i == 0)
				return false;
		return true;
	}
	
	static boolean palindrome(int number) {
		int revers = 0, orginal = number;
		while(true) {
			revers = revers+(orginal%10);
			orginal = orginal/10;
			revers = revers*10;
			if(orginal == 0)
				break;
		}
		if(revers/10 == number)
			return true;
		return false;
	}
	
	static void printInformation(int number) {
		
		String numString;
		int evenOdd;
		
		if(!prime(number))
			NumberGuessing.printf("It is not a prime\n");
		else
			NumberGuessing.printf("It is a prime\n");
		
		numString = Integer.toString(number);
		evenOdd = numString.charAt(0);
		
		if(evenOdd%2 == 0)
			NumberGuessing.printf("The starting digit is even\n");
		else
			NumberGuessing.printf("The starting digit is odd\n");
		
		evenOdd = numString.charAt(numString.length()-1);
		
		if(evenOdd%2 == 0)
			NumberGuessing.printf("The ending digit is even\n");
		else
			NumberGuessing.printf("The ending digit is odd\n");
		
		if(palindrome(number))
			NumberGuessing.printf("It is palindrome\n");
		else
			NumberGuessing.printf("It is not palindrome\n");
		
	}
	
	static void printf(String print) {
		try {
			for(int i=0;i<print.length();i++) {
				System.out.print(print.charAt(i));
				TimeUnit.MILLISECONDS.sleep(80);
			}
		}
		catch(InterruptedException e) {
			System.out.print(e);
		}
	}

}
