package com.capgemini.tictactoe;

import java.util.*;

public class TicTacToeGame {

	private static char[] board;

	TicTacToeGame() {
		board = new char[10];
	}

	private static char userChoice;
	private static char computerChoice;
	private static final Scanner scannerObject = new Scanner(System.in);
	private static boolean isFree,tossOutcome;
	private static int position;

	public static void main(String[] args) {

		board = initializeBoard();	
		tossOutcome = Toss();
		if (tossOutcome) {
			System.out.println("You won the toss play the first move");
			userChoice = chooseLetter();
			computerChoice = userChoice == 'X' ? 'O' : 'X';
			Arrays.fill(board, '#');
			showBoard();
			isFree = checkPosition();
			if (isFree) {
				makeMove();
				showBoard();
			}

		}

		else {
			computerChoice = 'X';
			userChoice = 'O';
		}
	}

	// method to initialize the board
	private static char[] initializeBoard() {
		char[] boardarr = new char[10];
		Arrays.fill(boardarr, ' ');
		return boardarr;
	}

	// method to choose the letter of user
	private static char chooseLetter() {
		System.out.println("Choose one of the inputs X or O");
		return scannerObject.nextLine().charAt(0);
	}

	// method to show the board
	private static void showBoard() {
		for (int i = 1; i < 10; i++) {
			System.out.print(board[i]);
			if (i % 3 == 0)
				System.out.println("");
			else
				System.out.print(" | ");
		}
	}

	// method to check position
	private static boolean checkPosition() {
		System.out.println("Enter the position from 1 to 9");
		position = scannerObject.nextInt();
		if (position <= 9 && position >= 1) {
			if (board[position] == '#') {
				return true;
			}
		}
		return false;
	}

	// method to make a move for user
	private static void makeMove() {
		board[position] = userChoice;
	}
	
	// method to toss for first move
	private static boolean Toss()
	{
		System.out.println("Choose Heads or Tails to get the chance to play first");
		String tossInput = scannerObject.nextLine();
		int randomValue = (int) Math.floor(Math.random() * 10) % 2;
		String tossRandom = "";
		if (randomValue == 0)
			tossRandom = "Heads";
		else
			tossRandom = "Tails";
		return tossRandom.equals(tossInput);
	}

}
