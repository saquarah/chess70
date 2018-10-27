package chess;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import board.Board;
import board.FileRank;
public class Chess {
	public static void main(String[] args) {
		gameLoop();
//		board.move(new FileRank('a', 2), new FileRank('a', 4));
//		board.printBoard();
//		board.move(new FileRank('a', 1), new FileRank('a', 3));
//		board.move(new FileRank('a', 3), new FileRank('h', 3));
//		board.printBoard();
//		board.printBoard();
//		board.move(new FileRank('a', 2), new FileRank('a', 4));
//		board.move(new FileRank('a', 4), new FileRank('a', 5));
//		board.move(new FileRank('b', 7), new FileRank('b', 5));
//		board.printBoard();
//		board.move(new FileRank('a', 5), new FileRank('b', 6));
//		board.printBoard();
		

		// should probably put game loop in this class
	}
	public static void gameLoop() {
		Board board = new Board();
		Scanner sc = new Scanner(System.in);
		String team = "White";
		char currentTeam = 'w';
		boolean gameOn = true;
		boolean illegalMove = false;
		String startStr;
		FileRank frStart;
		String endStr;
		FileRank frEnd;
		while(gameOn) {
			if(!illegalMove)
				board.printBoard();
			System.out.print(team + "'s move: ");
			
			String input = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(input, " ");
			// TODO
			// resign
			// draw
			// promotion
			try {
				startStr = st.nextToken();
				frStart = getFileRank(startStr);
				
				endStr = st.nextToken();
				frEnd = getFileRank(endStr);
			} catch(NoSuchElementException e) {
				System.out.println("Invalid input");
				continue;
			}
			
			if(board.get(frStart).getTeam() != currentTeam) {
				System.out.println("Error: cannot control a piece on the other team");
				continue;
			}
			
			if(board.move(frStart, frEnd)) {
				illegalMove = false;
				if(team.equals("White")) {
					team = "Black";
					currentTeam = 'b';
				} else {
					team = "White";
					currentTeam = 'w';
				}
				
//				if(board.checkForWin()) {
//					gameOn = false;
//				}
			} else {
				System.out.println("Illegal move, try again");
				illegalMove = true;
			}
		}
	}
	
	private static FileRank getFileRank(String input) {
		char file = input.charAt(0);
		int rank = Character.getNumericValue(input.charAt(1));
		return new FileRank(file, rank);
	}
	/**
	 * This is a loop used for debugging. It allows for pieces to be moved regardless of
	 * team or turn.
	 */
	private static void testLoop() {
		Board board = new Board();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			board.printBoard();
			System.out.print("Start: ");
			String start = sc.nextLine();
			System.out.print("End: ");
			String end = sc.nextLine();
			FileRank frStart = new FileRank(start.charAt(0), Character.getNumericValue(start.charAt(1)));
			FileRank frEnd = new FileRank(end.charAt(0), Character.getNumericValue(end.charAt(1)));
			System.out.println();
			board.move(frStart, frEnd);
		}
	}
}
// bR bN bB bQ bK bB bN bR  8
// bp bp bp bp bp bp bp bp  7
//    ##    ##    ##    ##  6
// ##    ##    ##    ##     5
//    ##    ##    ##    ##  4
// ##    ##    ##    ##     3
// wp wp wp wp wp wp wp wp  2
// wR wN wB wQ wK wB wN wR  1
//  a  b  c  d  e  f  g  h 