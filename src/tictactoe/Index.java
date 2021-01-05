/*!
 * tictactoe game
 *
 * Copyright(c) 2020 Imed Jaberi
 * MIT Licensed
 */

package tictactoe;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Index {
  // local varibles
  private static ArrayList<String> board = new ArrayList<String>();
  private static String turn = "X";

  // detect the winner
  private static String detectWinner() { 
    // check if exist a winner line
    for (byte index = 0; index < 9; index++) {
      if (board.contains(String.valueOf(index + 1))) { 
        break;
      }

      // For No Winner >> draw
      if (index == 8) { 
        return "draw"; 
      }

      String line = "";

      switch (index) {
        case 0: {
            line += board.get(0) + board.get(1) + board.get(2);
            break;
        }
        case 1: {
            line += board.get(3) + board.get(4) + board.get(5);
            break;
        }
        case 2: {
            line += board.get(6) + board.get(7) + board.get(8);
            break;
        } 
        case 3: {
            line += board.get(0) + board.get(3) + board.get(6);
            break;
        }  
        case 4: {
            line += board.get(1) + board.get(4) + board.get(7);
            break;
        }
        case 5: {
            line += board.get(2) + board.get(5) + board.get(8);
            break;
        }
        case 6: {
            line += board.get(0) + board.get(4) + board.get(8);
            break;
        }
        case 7: {
            line += board.get(2) + board.get(4) + board.get(6);
            break;
        }
      }

      // For X winner
      if (line.equals("XXX")) {
        return "X";
      }

      // For O winner
      if (line.equals("OOO")) {
        return "O";
      }
    }

    // enter the X Or O at the exact place on board 
    System.out.printf( "%s's turn; enter a cell number to place %s in:\n", turn, turn);

    return null;
  }

  // board initialization
  private static void initializer() {
    for (byte index = 0; index < 9; index++) {
      board.add(String.valueOf(index + 1)); 
    }

    System.out.println("================================");
    System.out.println("== Welcome to Tic Tac Toe 🪁 ==");
    System.out.println("================================");
  }

  // print the board inside the console
    /*
     ===============
     =|---|---|---|=
     =| 1 | 2 | 3 |=
     =|-----------|=
     =| 4 | 5 | 6 |=
     =|-----------|=
     =| 7 | 8 | 9 |=
     =|---|---|---|=
     ===============
    */
  private static void printBoard() {
    System.out.println("===============");
    System.out.println("=|---|---|---|=");
    System.out.printf("=| %s | %s | %s |= \n", board.get(0), board.get(1), board.get(2));
    System.out.println("=|-----------|=");
    System.out.printf("=| %s | %s | %s |= \n", board.get(3), board.get(4), board.get(5));
    System.out.println("=|-----------|=");
    System.out.printf("=| %s | %s | %s |= \n", board.get(6), board.get(7), board.get(8));
    System.out.println("=|-----------|=");
    System.out.println("===============");
  }

  // 
  private static void printWinner(String winner) {
    // If no one win or lose from both player x and O. 
    // then here is the logic to print "draw". 
    if (winner.equalsIgnoreCase("draw")) { 
        System.out.println("It's a draw! Thanks for playing."); 
    } 
    // For winner -to display Congratulations! message. 
    else { 
        System.out.printf(
        "Congratulations! %s's have won! Thanks for playing. \n",
        winner
        ); 
    } 
  }

  public static void main(String[] args) { 
    Scanner reader = new Scanner(System.in);
    String winner = null; 

    // Init Board
    initializer();
    // Load the Board inside the screen
    printBoard(); 

    System.out.println("X will play first. Enter a cell number to place X in:"); 

    while (winner == null) { 
      byte numInput; 
        
      // Number Validator + Exception handler.
      try { 
        numInput = reader.nextByte(10);
        if (!(numInput > 0 && numInput <= 9)) {
          System.out.println("Invalid input; re-enter cell number:");
          continue;
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input; re-enter cell number:");
        continue; 
      } 

      // check if the cell selected available
      if (!board.get(numInput - 1).equals(String.valueOf(numInput))) {
        System.out.println("Cell already taken; re-enter cell number:");
        continue;
      }

      // here the core of game >> logic to decide the turn 
      // between the two player ('X' and 'O').

      // replace the cell with the turn symbol
      board.set(numInput - 1, turn);
      // pass to next turn
      turn = turn == "X" ? "O" : "X";
      // re-print the bord with new values
      printBoard();
      // detect if exist winner with the current board state
      winner = detectWinner();
    }

    // log the result inside the console
    printWinner(winner);
  } 
}
