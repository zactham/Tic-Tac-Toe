//Zac Thamer
//Period 3
//10/27/15
//Creates a tic tac toe board and has the user play locally
import java.util.Scanner;
public class TicTacToe 
{
	//DESC: Checks to see if the user is trying to place a piece in a spot that has already been taken or in a position that isn't there
	// INPUT: gameBoard, row, column
	// OUTPUT: true if ok or false if taken
	public static boolean pieceCheck (String[][]gameBoard, int row, int column)
	{
		//Checks to make sure the row and columns are in the tictactoe board's range
		if ((row < 0 || row > 2 ) || (column < 0 || column > 2 ))
		{
			System.out.println("Invalid Position");
			return false;
		}

		//Checks to make sure the user isn't trying to place their piece in an already used location
		if (gameBoard[row][column].equalsIgnoreCase("X") || gameBoard[row][column].equalsIgnoreCase("O"))
		{
			System.out.println();
			System.out.println("There is already a game piece here.");
			return false;
		}


		else
			return true;
	}

	//DESC: Prints the gameBoard and assigns the player's current move into the board
	// INPUT: gameBoard, column1, row1, column2, row2, personPiece1, personPiece2
	// OUTPUT: none
	public static void table(String[][] gameBoard, int column1, int row1, int column2, int row2, String personPiece1, String personPiece2)
	{
		if (row1 > -1 && column1 > -1)
		{
			gameBoard[row1][column1] = personPiece1;
		}
		
		if (row2 > -1 && column2 > -1)
		{
			gameBoard[row2][column2] = personPiece2;
		}

		//Prints the tictactoe board
		for (int row = 0; row < 3; row++)
		{
			for (int column = 0; column < 3; column++)
			{
				System.out.print(gameBoard[row][column] + " ");
				if (column == 2)
				{
					System.out.println();
				}
			}
		}
	}

	//DESC: Checks all possible ways a user can win
	// INPUT: gameBoard, winner, gamePiece
	// OUTPUT: none
	public static boolean winnerCheck(String[][] gameBoard, String gamePiece)
	{
		int row = 0;
		int column = 0;

		for (row = 0; row < 3; row++)
		{
			//Horizontally
			if (gameBoard[row][0].equalsIgnoreCase(gamePiece) && gameBoard[row][1].equalsIgnoreCase(gamePiece) && gameBoard[row][2].equalsIgnoreCase(gamePiece))
			{
				System.out.println();
				System.out.println();
				System.out.println(gamePiece + " has won!!");
				return true;
			}
		}

		for (column = 0; column < 3; column++)
		{
			//Vertically
			if (gameBoard[0][column].equalsIgnoreCase(gamePiece) && gameBoard[1][column].equalsIgnoreCase(gamePiece) && gameBoard[2][column].equalsIgnoreCase(gamePiece))
			{
				System.out.println();
				System.out.println();
				System.out.println(gamePiece +  " has won!!");
				return true;
			}
		}

		//Diagonally Starting on the Left
		if (gameBoard[0][0].equalsIgnoreCase(gamePiece) && gameBoard[1][1].equalsIgnoreCase(gamePiece) && gameBoard[2][2].equalsIgnoreCase(gamePiece))
		{
			System.out.println();
			System.out.println();
			System.out.println(gamePiece +  " has won!!");
			return true;
		}

		//Diagonally Starting on the Right
		else if (gameBoard[0][2].equalsIgnoreCase(gamePiece) && gameBoard[1][1].equalsIgnoreCase(gamePiece) && gameBoard[2][0].equalsIgnoreCase(gamePiece))
		{
			System.out.println();
			System.out.println();
			System.out.println(gamePiece + " has won!!");
			return true;
		}
		return false;
	}

	//MAIN METHOD
	public static void main (String[]args)
	{
		String[][] gameBoard = new String[3][3];//Creates a 3x3 array
		int row1= -1;//Person 1's row
		int column1 = -1;//Person 1's column
		int row2 = -1;//Person 2's row
		int column2 = -1;//Person 2's column
		String personPiece1 = "#";//Person 1's gamePiece
		String personPiece2 = "#";//Person 2's gamePiece
		String defaultPiece = "#";
		Scanner input = new Scanner (System.in);
		boolean winner = false;

		int row = 0;
		int column = 0;

		//Creates the tictactoe game board
		for (row = 0; row < 3; row++)
		{
			for (column = 0; column < 3; column++)
			{
				gameBoard[row][column] = defaultPiece;
			}
		}

		//Calls table to print the table
		table(gameBoard, column1, row1, column2, row2, personPiece1, personPiece2);


		//PERSON 1
		//Makes sure they can at max enter their gamePiece 5 times
		for (int counter = 0; counter<5 && winner == false; counter++)
		{
			System.out.println();
			System.out.println();
			System.out.println("Person 1's Turn");
			if (counter == 0)
			{
				System.out.print("X or O: ");//Person 1 gets the right to decide which piece they want to be
				personPiece1 = input.next();


				//Checks to make sure they enter either X or O only as their piece
				while (!personPiece1.equalsIgnoreCase("X")&&!personPiece1.equalsIgnoreCase("O"))
				{
					System.out.println("Invalid Option");
					System.out.print("X or O: ");
					personPiece1 = input.next();
				}
			}



			do
			{
				System.out.println("Where do you want to place your piece?");//Asks the user for the piece location
				System.out.print("Row: ");//Row
				row1 = input.nextInt()-1;
				System.out.print("Column: ");//Column
				column1 = input.nextInt()-1;
			} 
			while (pieceCheck(gameBoard,row1,column1) == false);//Checks for duplicate location pieces, asks them again if so

			//Prints the board again
			table(gameBoard, column1, row1, column2, row2, personPiece1, personPiece2);

			winner = winnerCheck(gameBoard, personPiece1);//checks for a winner


			///////////////////

			//PERSON 2
			if (winner == false && counter < 4)//to make sure a winner hasn't yet been found
			{
				System.out.println();
				System.out.println();
				System.out.println("Person 2's Turn");

				if (personPiece1.equalsIgnoreCase("X"))//Assigns their game piece based off of the opposite of person 1's
				{
					System.out.println("You are O");
					personPiece2 = "O";
				}
				if (personPiece1.equalsIgnoreCase("O"))
				{
					System.out.println("You are X");
					personPiece2 = "X";
				}

				do 
				{
					System.out.println("Where do you want to place your piece?");//Asks the user for the piece location
					System.out.print("Row: ");//Row
					row2 = input.nextInt()-1;
					System.out.print("Column: ");//Column
					column2 = input.nextInt()-1;
				}
				while (pieceCheck(gameBoard,row2,column2) == false);

				//Prints the board again
				table(gameBoard, column1, row1, column2, row2, personPiece1, personPiece2);

				winner = winnerCheck(gameBoard, personPiece2);//checks for a winner

			}
		}
		input.close();
	}
}