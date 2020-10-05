package com.capgemini.tictactoe;

import java.util.*;

public class TicTacToeGame {

	private static char[] board;
	
	TicTacToeGame()
	{
		board = new char[10];
	}
	private static char userChoice;
	private static char computerChoice;
	private static final Scanner SC = new Scanner(System.in);

	public static void main(String[] args) {

		board = initializeBoard();
		userChoice = chooseLetter();
		computerChoice = userChoice == 'X' ? 'O' : 'X';
	}

	private static char[] initializeBoard() {
		char[]boardarr = new char[10];
		Arrays.fill(boardarr,' ');
		return boardarr;
	}

	private static char chooseLetter() {
		System.out.println("Choose one of the inputs X or O");
		return SC.nextLine().charAt(0);
	}

}