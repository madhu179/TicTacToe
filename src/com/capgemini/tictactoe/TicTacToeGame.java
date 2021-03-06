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
	private static boolean tossOutcome;
	private static int position;
	private static final int[][] WINNING_CONDITIONS = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 },
			{ 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };
	private static final int[] CORNOR_LIST = { 1, 3, 7, 9 };
	private static final int[] SIDES_LIST = { 2, 4, 6, 8 };
	private static ArrayList<Integer> userPositions = new ArrayList<Integer>();
	private static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		String choice;
		do {
			Random random = new Random();
			board = initializeBoard();
			Arrays.fill(board, '#');
			tossOutcome = tossForPlay();
			if (tossOutcome) {
				System.out.println("You won the toss play the first move");
				userChoice = chooseLetter();
				computerChoice = userChoice == 'X' ? 'O' : 'X';
				showBoard();
				System.out.println("Enter the position from 1 to 9");
				position = scannerObject.nextInt();
				if (checkPosition()) {
					makeMove(userChoice);
					userPositions.add(position);
					showBoard();
					System.out.println("");
				}

			}

			else {
				computerChoice = 'X';
				userChoice = 'O';
			}

			while (true) {
				position = getBestPosition();
				if (position == 10 || !checkPosition()) {
					position = getCorner();
					if (position == 10 || !checkPosition()) {
						position = 5;
						if (!checkPosition()) {
							position = getSide();
							if (position == 10 || !checkPosition()) {
								position = random.nextInt(9) + 1;
							}
						}
					}
				}
				if (checkPosition()) {
					makeMove(computerChoice);
					computerPositions.add(position);
					showBoard();
					break;
				}
			}

			while (true) {
				System.out.println("Enter the position from 1 to 9");
				position = scannerObject.nextInt();
				if (checkPosition()) {
					makeMove(userChoice);
					userPositions.add(position);
					showBoard();
					System.out.println("");
				}

				if (checkIfWon() == 1)
					if (userChoice == 'X') {
						System.out.println("User Won");
						break;
					} else {
						System.out.println("Computer Won");
						break;
					}
				else if (checkIfWon() == 2)
					if (userChoice == 'O') {
						System.out.println("User Won");
						break;
					} else {
						System.out.println("Computer Won");
						break;
					}
				if (checkIfBoardIsFull()) {
					System.out.println("Its a draw");
					break;
				}

				while (true) {
					position = getBestPosition();
					if (position == 10 || !checkPosition()) {
						position = getCorner();
						if (position == 10 || !checkPosition()) {
							position = 5;
							if (!checkPosition()) {
								position = getSide();
								if (position == 10 || !checkPosition()) {
									position = random.nextInt(9) + 1;
								}
							}
						}
					}
					if (checkPosition()) {
						makeMove(computerChoice);
						computerPositions.add(position);
						showBoard();
						System.out.println("");
						break;
					}
				}

				if (checkIfWon() == 1)
					if (userChoice == 'X') {
						System.out.println("User Won");
						break;
					} else {
						System.out.println("Computer Won");
						break;
					}
				else if (checkIfWon() == 2)
					if (userChoice == 'O') {
						System.out.println("User Won");
						break;
					} else {
						System.out.println("Computer Won");
						break;
					}

				if (checkIfBoardIsFull()) {
					System.out.println("Its a draw");
					break;
				}

			}
			System.out.println("Do you want to play another game : Y/N");
			scannerObject.nextLine();
			choice = scannerObject.nextLine();
		} while (choice.equals("Y"));

		System.out.println("Thank You");

	}

	// method to return a side
	private static int getSide() {
		for (int i = 0; i < 4; i++) {
			position = SIDES_LIST[i];
			if (checkPosition()) {
				return SIDES_LIST[i];
			}
		}
		return 10;
	}

	// method to return a corner
	private static int getCorner() {
		for (int i = 0; i < 4; i++) {
			position = CORNOR_LIST[i];
			if (checkPosition()) {
				return CORNOR_LIST[i];
			}
		}
		return 10;
	}

	// method to block the player win
	private static int getBestPosition() {
		int count = 0;
		int i = 0;
		int value = 8;
		for (i = 0; i < 8; i++) {
			for (int j = 0; j < userPositions.size(); j++) {
				for (int k = 0; k < 3; k++) {
					if (Integer.valueOf(WINNING_CONDITIONS[i][k]).equals(userPositions.get(j))) {
						count += 1;
					}
				}

			}
			if (count == 2) {
				for (int y = 0; y < 3; y++) {
					if (!userPositions.contains(Integer.valueOf(WINNING_CONDITIONS[i][y]))) {
						value = WINNING_CONDITIONS[i][y];
						position = value;
						if (checkPosition()) {
							return value;
						}
						break;
					}
				}

			} else
				count = 0;
		}
		return 10;
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
		if (position <= 9 && position >= 1) {
			if (board[position] == '#') {
				return true;
			}
		}
		return false;
	}

	// method to make a move for user
	private static void makeMove(char Letter) {
		board[position] = Letter;
	}

	// method to toss for first move
	private static boolean tossForPlay() {
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

	// method to check if won
	private static int checkIfWon() {
		for (int i = 0; i < WINNING_CONDITIONS.length; i++) {
			if (board[WINNING_CONDITIONS[i][0]] == board[WINNING_CONDITIONS[i][1]]
					&& board[WINNING_CONDITIONS[i][1]] == board[WINNING_CONDITIONS[i][2]])
				if (board[WINNING_CONDITIONS[i][0]] == 'X')
					return 1;
				else if (board[WINNING_CONDITIONS[i][0]] == 'O')
					return 2;
		}
		return 0;
	}

	// method to check if board is full
	private static boolean checkIfBoardIsFull() {
		int count = 0;
		for (int i = 1; i < board.length; i++)
			if (board[i] == '#')
				count += 1;
		return count == 0 ? true : false;
	}

}
