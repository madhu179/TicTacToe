
public class TicTacToeGame {
	
	private static char[][] board;

	public static void main(String[] args) {
		
		initializeBoard();
		
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

}
