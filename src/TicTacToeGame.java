import java.util.*;
public class TicTacToeGame {
	
	private static char[][] board;
	private static char userchoice;
	private static char computerchoice;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		initializeBoard();
		
		userchoice = chooseLetter();
		if(userchoice == 'X')
			computerchoice = 'O';
		else
			computerchoice = 'X';
		
	}
	
	private static void initializeBoard()
	{
		 board = new char[3][3];
	     for(int i=0;i<3;i++)
	     {
	    	 for(int j=0;j<3;j++)
		     {
	    	 board[i][j]=' ';
	     }
	     }
	}
	
	private static char chooseLetter()
	{
		System.out.println("Choose one of the inputs X or O");
		char choice = sc.nextLine().charAt(0);
		return choice;
	}

}
