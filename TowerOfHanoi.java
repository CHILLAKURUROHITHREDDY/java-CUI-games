import java.util.Scanner;
import java.util.ArrayList;

public class TowerOfHanoi {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int steps = 0;
		char send=' ', receive=' ';
		
		ArrayList<Integer> source = new ArrayList<Integer>();
		ArrayList<Integer> helper = new ArrayList<Integer>();
		ArrayList<Integer> destination = new ArrayList<Integer>();
		
		System.out.print("Enter size of tower = ");
		int size = scan.nextInt();
		for(int i=0; i<size; i++) {
			source.add(size-i);
		}
		
		while(true) {
			TowerOfHanoi.print(source, helper, destination, size);
			
			System.out.print("Enter sender pole = ");
			send = Character.toLowerCase(scan.next().charAt(0));
			System.out.print("Enter receiver pole = ");
			receive = Character.toLowerCase(scan.next().charAt(0));
			System.out.println();
			
			if(send == 'a' && receive == 'b')
				steps = steps + swap(source, helper);
			else if(send == 'a' && receive == 'c')
				steps = steps + swap(source, destination);
			else if(send == 'b' && receive == 'a')
				steps = steps + swap(helper, source);
			else if(send == 'b' && receive == 'c')
				steps = steps + swap(helper, destination);
			else if(send == 'c' && receive == 'a')
				steps = steps + swap(destination, source);
			else if(send == 'c' && receive == 'b')
				steps = steps + swap(destination, helper);
			
			if(TowerOfHanoi.finish(destination, size)) {
				TowerOfHanoi.print(source, helper, destination, size);
				System.out.println("!!! congratulations you done it in "+steps+" steps !!!");
				break;
			}
				
		}
		scan.close();
		

	}
	
	static void print(ArrayList<Integer> source, ArrayList<Integer> helper, ArrayList<Integer> destination, int size) {
		
		for(int i=0; i<size; i++) {

			try {
				System.out.print(source.get(size-i-1)+"\t");
			}catch(IndexOutOfBoundsException e) {
				System.out.print(" \t");
			}
			try {
				System.out.print(helper.get(size-i-1)+"\t");
			}catch(IndexOutOfBoundsException e) {
				System.out.print(" \t");
			}
			try {
				System.out.print(destination.get(size-i-1)+"\t");
			}catch(IndexOutOfBoundsException e) {
				System.out.print(" \t");
			}
			System.out.println();
		}
		System.out.println("\nA\tB\tC\n");
		
	}
	
	static byte swap(ArrayList<Integer> sender, ArrayList<Integer> receiver) {
		
		if(sender.size() != 0) {
			if(receiver.size() == 0) {
				receiver.add(sender.get(sender.size()-1));
				sender.remove(sender.size()-1);
				return 1;
			}
			else {
				int send = sender.get(sender.size()-1);
				int receive = receiver.get(receiver.size()-1);
				if(send < receive) {
					receiver.add(sender.get(sender.size()-1));
					sender.remove(sender.size()-1);
					return 1;
				}
			}
		}
		return 0;
	}
	
	static boolean finish(ArrayList<Integer> destination, int size) {
		
		int flag = 0;
		for(int i=0; i<size; i++) {
			try {
				if(destination.get(i) == size-i) {
					flag++;
				}
			}catch(IndexOutOfBoundsException e) {
				return false;
			}
		}
		if(flag == size)
			return true;
		return false;
	}

}
