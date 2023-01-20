package voltorbFlip;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private int level;
	private int winCon;
	private boolean game = true;
	private int[][] grid = new int[5][5];
	private char[][] playerGrid = new char[5][5];
	private int[][] bombCounter = new int[2][5];

	public void playGame(int level) {
		if (level > 0 && level < 4) {
			int winCon = 0;
			this.level = level;
			boardSetUp();
			Scanner scan = new Scanner(System.in);
			int input;
			while (game == true && winCon != this.winCon) {
				printBoard(playerGrid);
				System.out.println(
						"Enter the row number(1-5) followed by column number(1-5), e.g. 15 would be row 1 column 5");
				input = scan.nextInt();
				winCon += updateBoard(input);
			}
			scan.close();
			if (game == false) {
				System.out.println("Game Over!");
			} else if (winCon == this.winCon) {
				System.out.println("Congratulations! You Won");
			}
			printBoard(grid);
		}
	}

	private void boardSetUp() {
		int bombs = (this.level * 2) + 4;
		winCon = 25 - bombs;
		ArrayList<Integer> a = new ArrayList<Integer>(5);

		initBoard();

		// bomb generation for each row
		while (bombs != 0) {
			// to make sure the number of bombs add up
			bombs = (this.level * 2) + 4;
			int value = bombs;
			a.clear();

			// generate bomb values for each row ranging from 0-3
			for (int i = 0; i < 5; i++) {
				if (bombs > 3) {
					value = (int) (Math.random() * 100) % 4;
				}
				a.add(value);
				bombs = bombs - value;
			}
		}
		// shuffle the bombs generated for each row
		for (int i = 0; i < 5; i++) {
			int select = (int) (Math.random() * 100) % (5 - i);
			bombCounter[0][i] = a.get(select);
			a.remove(select);
		}

		// based on the number of bombs in each row, randomly place them in their
		// respective row
		for (int i = 0; i < 5; i++) {
			int setBombs = 0;
			while (setBombs != bombCounter[0][i]) {
				int index = (int) (Math.random() * 100) % 5;
				if (grid[i][index] == 0) {
					grid[i][index] = 1;
					setBombs++;
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				bombCounter[1][j] += grid[i][j];
			}
		}
	}

	private void initBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grid[i][j] = 0;
				playerGrid[i][j] = '×';
			}
		}
	}

	private void printBoard(int[][] board) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(board[i][j] + "    ");
			}
			System.out.print("  " + bombCounter[0][i] + "\n---  ---  ---  ---  ---\n");
		}
		System.out.println("\n" + bombCounter[1][0] + "    " + bombCounter[1][1] + "    " + bombCounter[1][2] + "    "
				+ bombCounter[1][3] + "    " + bombCounter[1][4] + "\n");

	}

	private void printBoard(char[][] board) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(board[i][j] + "    ");
			}
			System.out.print("  " + bombCounter[0][i] + "\n---  ---  ---  ---  ---\n");
		}
		System.out.println("\n" + bombCounter[1][0] + "    " + bombCounter[1][1] + "    " + bombCounter[1][2] + "    "
				+ bombCounter[1][3] + "    " + bombCounter[1][4] + "\n");

	}

	private int updateBoard(int value) {
		if (value > 55 || value < 10 || value % 10 == 0 || value % 10 > 5) {
			System.out.println("Invalid Input");
			return 0;
		}

		if (playerGrid[(value / 10) - 1][(value % 10) - 1] == '×') {
			playerGrid[(value / 10) - 1][(value % 10) - 1] = (char) (grid[(value / 10) - 1][(value % 10) - 1] + '0');
			if (grid[(value / 10) - 1][(value % 10) - 1] == 1)
				game = false;
			return 1;
		}

		return 0;
	}
}