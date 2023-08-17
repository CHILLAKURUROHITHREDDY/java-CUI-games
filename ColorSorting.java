/*
 * CHILLAKURU ROHITH REDDY
 * 04/12/2022
 * It is the game to sort colors in the tube
 */
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class ColorSorting {

	public static void main(String[] args) {
		int row = 4,level = 1;
		@SuppressWarnings("unchecked")
		ArrayList<String>[] tubes = new ArrayList[50];
		String[] colors = {"RED","BLU","BLA","WHI","ORA","GRE","PIN","YEL","VOL","SKI"};
		System.out.println("Color Sorting Game : \n");
		ColorSorting.installation(row, level, tubes, colors);
	}
	
	static void installation(int row,int level,ArrayList<String>[] tubes,String[] colors) {
		Random rad = new Random();
		row = row+1;
		int[] numberoftimes = new int[row+2];
		@SuppressWarnings("unchecked")
		ArrayList<String>[] swap = new ArrayList[50];
		for(int i=0; i<row; i++) {
			numberoftimes[i] = 0;
		}
		for(int i=0; i<row+2; i++) {
			tubes[i] = new ArrayList<String>();
			swap[i] = new ArrayList<String>();
			for(int j=0; j<4 && i<row; j++) {
				while(true) {
					int index = rad.nextInt(row);
					if(numberoftimes[index] < 4) {
						tubes[i].add(colors[index]);
						swap[i].add(colors[index]);
						numberoftimes[index]++;
						break;
					}
				}
			}
		}
		
		ColorSorting.input(row, level, tubes, colors, swap);
	}
	
	static void input(int row,int level,ArrayList<String>[] tubes,String[] colors, ArrayList<String>[] swap) {
		Scanner scan = new Scanner(System.in);
		int push, pop;
		ColorSorting.print(row, tubes);
		while(true) {
			System.out.print("Enter 0 to restart game or\nEnter the pop tube number = ");
			pop = scan.nextInt()-1;
			if(pop == -1) {
				ColorSorting.restart(row,tubes,swap);
				ColorSorting.input(row,level,tubes,colors,swap);
			}
			if(pop <= row+2 && tubes[pop].size() != 0)
				break;
		}
		System.out.print("Enter the push tube number = ");
		push = scan.nextInt()-1;
		if(push <= row+2 && tubes[push].size() <= 3) {
			if(tubes[push].size() == 0 || tubes[pop].get(tubes[pop].size()-1) == tubes[push].get(tubes[push].size()-1)) {
				ColorSorting.pouring(pop, push, tubes);
				if(!end(row, tubes)) {
					ColorSorting.input(row, level, tubes, colors, swap);
				}
				else {
					ColorSorting.print(row, tubes);
					System.out.println("\nCONGRATS YOU SUCCESSFULLY COMPLETED LEVEL = "+level);
					System.out.print("Enter 1 to go next level \nEnter 0 to close \nEnter your choice = ");
					if(scan.nextInt() == 1) {
						level++;
						ColorSorting.installation(row, level, tubes, colors);
						ColorSorting.input(row, level, tubes, colors, swap);
					}
				}
			}
			else
				ColorSorting.input(row, level, tubes, colors, swap);
		}
		else {
			ColorSorting.input(row, level, tubes, colors, swap);
		}
		scan.close();
		
	}
	
	static void print(int row, ArrayList<String>[] tubes) {
		int index = 0, i, j;
		while(true) {
			for(i=3; i>=0; i--) {
				for(j=index; j<index+5 && j<row+2; j++) {
					try {
						System.out.print(tubes[j].get(i)+"\t");
					}catch(IndexOutOfBoundsException e) {
						System.out.print("   \t");
					}
					
				}
				System.out.println();
			}
			for(j=index; j<index+5 && j<row+2; j++)
				System.out.print(" "+(j+1)+" \t");
			System.out.println();
			index = index+5;
			if(j == row+2)
				break;
		}
		System.out.println();
		
	}
	
	static boolean end(int row, ArrayList<String>[] tubes) {
		
		boolean flag = true;
		int emptyCount = 0;
		for(int i=0; i<row+2; i++) {
			if(tubes[i].size() < 4)
				emptyCount++;
			else if(tubes[i].size() == 4) {
				String check = tubes[i].get(0);
				for(int j=1; j<4; j++) {
					if(check != tubes[i].get(j)) {
						flag = false;
						break;
					}
				}
			}
			if(emptyCount > 2) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	static void restart(int row, ArrayList<String>[] tubes, ArrayList<String>[] swap) {
		
		for(int i=0; i<row+2; i++) {
			tubes[i].clear();
			for(int j=0; j<4 && i<row; j++) {
				tubes[i].add(swap[i].get(j));
			}
		}
	}
	
	static void pouring(int pop, int push, ArrayList<String>[] tubes) {
		while(true) {
			if(tubes[push].size() == 0) {
				tubes[push].add(tubes[pop].get(tubes[pop].size()-1));
				tubes[pop].remove(tubes[pop].size()-1);
			}
			else if(tubes[pop].get(tubes[pop].size()-1) == tubes[push].get(tubes[push].size()-1) && tubes[push].size() <= 3) {
				tubes[push].add(tubes[pop].get(tubes[pop].size()-1));
				tubes[pop].remove(tubes[pop].size()-1);
				if(tubes[pop].size() == 0)
					break;
			}
			else
				break;
		}
	}
}
