package games;

import java.util.Scanner;
import java.util.Random;

public class Puzzle{

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int size, move, steps = 0;
		System.out.println("Puzzle Game : \n");
		System.out.println("Enter size of the puzzle less than 11");
		size = in.nextInt();
		int[][] array = new int[size][size];
		
		Puzzle.installation(size, array);
		while(true && size != 0){
			
			int currectCount = Puzzle.print(array, size);
			if(currectCount == (size*size)-1 && (array[0][0] == size*size || array[size-1][size-1] == size*size)){
				System.out.print("!!! congratulations you done it in "+steps+" steps !!!");
				in.close();
				break;
			}
			else{
				System.out.print("Enter number to move = ");
				move = in.nextInt();
				if(move == 0)
					break;
				else {
					steps = steps+Puzzle.swap(move, array, size);
				}
			}
		}
	}
	
	static void installation(int size, int[][] array) {
		
		int row = 0, col = 0;
		boolean  flag;
		Random rad = new Random();
		
		while(true){
			flag = true;
			if(row == size)
				break;
			int rad1 = rad.nextInt((size*size))+1;
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
					if(rad1 == (array[i][j])){
						flag = false;
					}
				}
			}
			if(flag){
				array[row][col] = rad1;
				col++;
				if(col == size){
					col = 0;
					row++;
				}
			}
		}
		
	}
	
	static int print(int[][] array, int size) {
		
		int sum = 0, correctCount = 0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(array[i][j] < 10){
					System.out.print(" ");
				}
				if(array[i][j] != size*size){
					System.out.print(array[i][j]+"  ");
					if(sum < array[i][j]){
						sum = array[i][j];
						correctCount++;
					}
				}
				else{
					if(array[i][j] > 9){
						System.out.print(" ");
					}
				  System.out.print("   ");
				}
			}
			System.out.print("\n");
		}
		return correctCount;
	}
	
	static short swap(int move, int[][] array, int size) {
		
		int nullRow = 0, nullCol = 0, numberRow = 0, numberCol = 0;
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(move == array[i][j]){
					numberRow = i;
					numberCol = j;
				}
				if(size*size == array[i][j]) {
					nullRow = i;
					nullCol = j;
				}
			}
		}
		if(nullRow == numberRow-1 || nullRow == numberRow+1){
			if(nullCol == numberCol){
				int swap = array[nullRow][nullCol];
				array[nullRow][nullCol] = array[numberRow][numberCol];
				array[numberRow][numberCol] = swap;
				return 1;
			}
		}
		else if(nullCol == numberCol-1 || nullCol == numberCol+1) {
			if(nullRow == numberRow){
				int swap = array[nullRow][nullCol];
				array[nullRow][nullCol] = array[numberRow][numberCol];
				array[numberRow][numberCol] = swap;
				return 1;
				
			}
		}
		return 0;
		
	}
}