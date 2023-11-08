package games;

import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

public class Chess {
	
    static String board[][] = {
    							{"r2","h2","b2","q1","k1","b1","h1","r1"},
    							{"p8","p7","p6","p5","p4","p3","p2","p1"},
    							{"  ","  ","  ","  ","  ","  ","  ","  "},
    							{"  ","  ","  ","  ","  ","  ","  ","  "},
    							{"  ","  ","  ","  ","  ","  ","  ","  "},
    							{"  ","  ","  ","  ","  ","  ","  ","  "},
    							{"P1","P2","P3","P4","P5","P6","P7","P8"},
    							{"R1","H1","B1","Q1","K1","B2","H2","R2"}
        					  };
    static LinkedList<String> white = new LinkedList<String>();
    static LinkedList<String> black = new LinkedList<String>();
    static LinkedList<String> caslingList = new LinkedList<String>();
    static LinkedList<String> powers = new LinkedList<String>();
    static Scanner scan = new Scanner(System.in);
    
    public chess() {
    	
    	white.addAll(Arrays.asList("K1","Q1","B1","B2","H1","H2","R1","R2","P1","P2","P3","P4","P5","P6","P7","P8"));
    	black.addAll(Arrays.asList("k1","q1","b1","b2","h1","h2","r1","r2","p1","p2","p3","p4","p5","p6","p7","p8"));
    	caslingList.addAll(Arrays.asList("K1", "k1", "R1", "R2", "r1", "r2"));
    	powers.addAll(Arrays.asList("Q2","B3","H3","R3","q2","b3","h3","r3"));
    	
    }
    public static void main(String args[]) {
    	
    	new chess();
    	instructions();
        white(white,black);
    }

    static void instructions() {
    	
    	System.out.println("Chess Game : \n");
        System.out.println("---Instructions---\n");
		System.out.println("It is a beta version");
		System.out.println("It does not supports the following functions");
		System.out.println("* check\n* check made\n* scale made\n* Back step\n");
        System.out.println("White is player 1");
        System.out.println("Black is player 2");
        System.out.print("Press enter to play game");
        scan.nextLine();
        
    }

    static void white(LinkedList<String> white,LinkedList<String> black) {
        printf();
        System.out.print("\nTime to play player 1\n");
        move(white,black,true);
		black(black,white);
    }

    static void black(LinkedList<String> black,LinkedList<String> white) {
        printf();
        System.out.print("\nTime to play player 2\n");
        move(black,white,false);
        white(white,black);
    }
    
    static void printf() {
    	System.out.print("\nPlayer 1 :\t\t\t\t\t");
    	System.out.println("  Player 2 :\n");
    	System.out.print(" ------------------------------------------------");
    	System.out.println("-----------------------------------------------");
    	System.out.print("|    1    2    3    4    5    6    7    8      ||");
    	System.out.println("     8    7    6    5    4    3    2    1\t|");
        int border=1;
        System.out.print("|  -----------------------------------------   ||");
        System.out.println("   -----------------------------------------\t|");
        for(int i=0; i<8; i++) {
            System.out.print("|"+border+" |");
            for(int j=0; j<8; j++) {
            	System.out.print(" "+board[i][j]+" |");
            }
            System.out.print("   || "+(9-border)+" |");
            border++;
            for(int j=7; j>=0; j--) {
            	System.out.print(" "+board[7-i][j]+" |");
            }
            System.out.print("\t|\n|  -----------------------------------------   ||");
            System.out.println("   -----------------------------------------\t|\t");
        }
    	System.out.print(" ------------------------------------------------");
    	System.out.println("-----------------------------------------------");
        
    }
    
    static void move(LinkedList<String> currentPlayer,LinkedList<String> oppsitePlayer,boolean player) {
    	
    	boolean moveResult = false, caslingStep = false;
        int presentRow=8,presentCol=8,moveRow=0,moveCol=0;
        char movePeice = ' ';
        String presentPeice = "";
        String temp;
        boolean flag = true;
        while(flag) {
            while(true) {
                System.out.print("Enter peice name = ");
                if(player)
                	presentPeice = scan.nextLine().toUpperCase();
                else
                	presentPeice = scan.nextLine().toLowerCase();
                presentPeice = presentPeice.replaceAll(" ", "").replaceAll("	", "");
                if(currentPlayer.contains(presentPeice))
                	break;
            }
            for(int i=0;i<8;i++) {
            	for(int j=0;j<8;j++) {
            		if(presentPeice.equals(board[i][j])) {
            			presentRow = i;
            			presentCol = j;
            		}
            	}
            }
            movePeice = board[presentRow][presentCol].charAt(0);
            
            while(true) {
                System.out.print("Enter move of row and column = ");
                moveRow = scan.nextInt()-1;
                moveCol = scan.nextInt()-1;
                scan.nextLine();
                
                if(moveRow < 8 && moveRow >= 0 && moveCol < 8 && moveCol >= 0) {
                	
                	if(board[moveRow][moveCol].charAt(0) != 'k' && board[moveRow][moveCol].charAt(0) != 'K') {
                		if(movePeice == 'K' && board[moveRow][moveCol].charAt(0) == 'R' && player) {
                    		if(caslingList.contains(board[presentRow][presentCol]) && caslingList.contains(board[moveRow][moveCol])){
                    			caslingStep = true;
                    			flag = false;
                    			break;
                    		}
                    	}
                		
                		else if(movePeice == 'k' && board[moveRow][moveCol].charAt(0) == 'r' && !player) {
                    		if(caslingList.contains(board[presentRow][presentCol]) && caslingList.contains(board[moveRow][moveCol])){
                    			caslingStep = true;
                    			flag = false;
                    			break;
                    		}
                    	}
                		else if(!currentPlayer.contains(board[moveRow][moveCol])) {
                    		flag = false;
                        	break;
                        }
                	}
                	else if(movePeice == 'R' && board[moveRow][moveCol].charAt(0) == 'K' && player) {
                		if(caslingList.contains(board[presentRow][presentCol]) && caslingList.contains(board[moveRow][moveCol])){
                			caslingStep = true;
                			flag = false;
                			break;
                		}
                	}
                	else if(movePeice == 'r' && board[moveRow][moveCol].charAt(0) == 'k' && !player) {
                		if(caslingList.contains(board[presentRow][presentCol]) && caslingList.contains(board[moveRow][moveCol])){
                			caslingStep = true;
                			flag = false;
                			break;
                		}
                	}
                	
                }
            }

        }
        
        if(movePeice=='H' || movePeice=='h') {
            moveResult = knight(presentRow, presentCol, moveRow, moveCol);
        }
        
        else if(movePeice=='P' || movePeice=='p') {
        	
        	moveResult = pawn(presentRow, presentCol, moveRow, moveCol, currentPlayer, oppsitePlayer, player);
        }
        
        else if(movePeice=='B' || movePeice=='b') {
            
            moveResult = daiagnal(presentRow, presentCol, moveRow, moveCol);
        }
        
        else if(movePeice=='R' || movePeice=='r') {
        	if(caslingStep)
        		moveResult = true;
        	moveResult = stright( presentRow, presentCol, moveRow, moveCol);
            if(moveResult)
            	caslingList.remove(board[presentRow][presentCol]);
        }
        
        else if(movePeice == 'Q' || movePeice == 'q') {
        	moveResult = daiagnal(presentRow, presentCol, moveRow, moveCol);
        	if(!moveResult)
        		moveResult = stright( presentRow, presentCol, moveRow, moveCol);
        }
        
        else if(movePeice=='K' || movePeice=='k'){
        	if(caslingStep)
        		moveResult = true;
        	moveResult = king(presentRow, presentCol, moveRow, moveCol);
    		if(moveResult)
    			caslingList.remove(board[presentRow][presentCol]);
        }
        
        if(moveResult && !caslingStep) {
        	
            board[moveRow][moveCol] = board[presentRow][presentCol];
            board[presentRow][presentCol] = "  ";
        }
        else if(stright(presentRow, presentCol, moveRow, moveCol) && caslingStep) {
        	temp = board[presentRow][presentCol];
            board[presentRow][presentCol] = board[moveRow][moveCol];
            board[moveRow][moveCol] = temp;
            caslingList.remove(board[presentRow][presentCol]);
            caslingList.remove(board[moveRow][moveCol]);
        }
        else
            move(currentPlayer,oppsitePlayer,player);
    }
   
    static boolean knight(int presentRow, int presentCol, int moveRow, int moveCol) {
    	
    	if(moveRow==presentRow+2 && moveCol==presentCol+1 || moveRow==presentRow+2 && moveCol==presentCol-1)
            return true;
        else if(moveRow==presentRow-2 && moveCol==presentCol+1 || moveRow==presentRow-2 && moveCol==presentCol-1)
        	return true;
        else if(moveCol==presentCol+2 && moveRow==presentRow+1 || moveCol==presentCol+2 && moveRow==presentRow-1)
        	return true;
        else if(moveCol==presentCol-2 && moveRow==presentRow+1 || moveCol==presentCol-2 && moveRow==presentRow-1)
        	return true;
    	return false;
    }
    
    static boolean pawn(int presentRow, int presentCol, int moveRow, int moveCol, LinkedList<String> currentPlayer, LinkedList<String> oppsitePlayer, boolean player) {
    	
    	if(presentRow==1 && !player || presentRow==6 && player) {
            if(moveRow==presentRow+1 && !player || moveRow==presentRow-1 && player) {
                if(board[moveRow][moveCol].equals("  ") && moveCol==presentCol)
                    return true;
                else if((moveRow==presentRow+1 && !player || moveRow==presentRow-1 && player) && (moveCol==presentCol-1 || moveCol==presentCol+1))
                  if(oppsitePlayer.contains(board[moveRow][moveCol]))
                	  return true;
            }
            if(moveRow==presentRow-2 && player || moveRow==presentRow+2 && !player) {
            	if((board[moveRow+1][moveCol].equals("  ") && player || board[moveRow-1][moveCol].equals("  ") && !player)&& board[moveRow][moveCol].equals("  ") && moveCol==presentCol)
                    return true;
            }
        }
        else if(moveRow==presentRow+1 && !player || moveRow==presentRow-1 && player) {
            if(moveRow==7 && !player || moveRow==0 && player) {
               
                System.out.println("Enter 1 for Queen");
                System.out.println("Enter 2 for Bishop");
                System.out.println("Enter 3 for knight");
                System.out.println("Enter 4 for Rook");
                System.out.print("Enter your choice = ");
                int choice = scan.nextInt()-1;
                scan.nextLine();
                if(player) {
                	String temp = powers.get(choice);
                	white.remove(board[presentRow][presentCol]);
                	board[presentRow][presentCol] = temp;
                	white.add(temp);
                	powers.set(choice, (temp.charAt(0) +""+(Integer.parseInt(temp.substring(1))+1)));
                	return true;

                }
                else if(!player) {
                	choice = choice + 4;
                	String temp = powers.get(choice);
                	black.remove(board[presentRow][presentCol]);
                	board[presentRow][presentCol] = temp;
                	black.add(temp);
                	powers.set(choice, (temp.charAt(0) +""+(Integer.parseInt(temp.substring(1))+1)));
                	System.out.println(powers.toString());
                	return true;
                }

            }
            else if(board[moveRow][moveCol].equals("  ") && moveCol==presentCol)
                return true;
            else if(moveCol==presentCol-1 || moveCol==presentCol+1) {
                for(int i=0; i<16; i++)
                    if(oppsitePlayer.contains(board[moveRow][moveCol]))
                        return true;
            }
        }
    	return false;
    }
    
    static boolean daiagnal(int presentRow, int presentCol, int moveRow, int moveCol) {
    	
    	if(presentRow<moveRow) {
            if(presentCol<moveCol && (moveRow-presentRow==moveCol-presentCol)) {
                for(int i=1; i<moveCol-presentCol; i++) {
                    if(!board[presentRow+i][presentCol+i].equals("  ")) {
                        return false;
                    }
                }
                return true;
            }
            else if(presentCol>moveCol && (moveRow-presentRow==presentCol-moveCol)) {
                for(int i=1; i<presentCol-moveCol; i++) {
                    if(!board[presentRow+i][presentCol-i].equals("  ")) {
                        return false;
                    }
                }
                return true;
            }
    	}
    	else if(presentRow>moveRow) {
    		if(presentCol<moveCol && (presentRow-moveRow==moveCol-presentCol)) {
    			for(int i=1; i<moveCol-presentCol; i++) {
    				if(!board[presentRow-i][presentCol+i].equals("  ")) {
    					return false;
    				}
    			}
    			return true;
    		}
    		else if(presentCol>moveCol && (presentRow-moveRow==presentCol-moveCol)) {
    			for(int i=1; i<presentCol-moveCol; i++) {
    				if(!board[presentRow-i][presentCol-i].equals("  ")) {
    					return false;
    				}
    			}
    			return true;
    		}
    	}
    	return false;
    }
    
    static boolean stright(int presentRow, int presentCol, int moveRow, int moveCol) {
    	
    	if(presentRow<moveRow && presentCol==moveCol) {
            for(int i=presentRow+1; i<moveRow; i++) {
                if(!board[i][presentCol].equals("  ")) {
                    return false;
                }
            }
            return true;
        }
        else if(presentRow>moveRow && presentCol==moveCol) {
            for(int i=presentRow-1; i>moveRow; i--) {
                if(!board[i][moveCol].equals("  ")) {
                    return false;
                }
            }
            return true;
        }
        else if(presentCol<moveCol && presentRow==moveRow) {
            for(int i=presentCol+1; i<moveCol; i++) {
                if(!board[moveRow][i].equals("  ")) {
                    return false;
                } 
            }
            return true;
        }
        else if(presentCol>moveCol && presentRow==moveRow) {
            for(int i=presentCol-1; i>moveCol; i--) {
                if(!board[moveRow][i].equals("  ")) {
                    return false;
                }
            }
            return true;
        }
    	return false;
    }
    
    static boolean king(int presentRow, int presentCol, int moveRow, int moveCol) {
    	
    	if(presentRow == moveRow && (presentCol == moveCol+1 || presentCol == moveCol-1)){
    		return true;
	}
	else if(presentCol == moveCol && (presentRow == moveRow+1 || presentRow == moveRow-1)){
		return true;
	}
	else if((presentRow == moveRow+1 || presentRow == moveRow-1) && (presentCol == moveCol+1 || presentCol == moveCol-1)){
		return true;
	}
    	return false;
    }
}
