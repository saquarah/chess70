package chess;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import board.Board;
import board.FileRank;
import pieces.Piece;
public class Chess {
	
	public static void main(String[] args) {
//		testLoop();
//		Board board = new Board();
//		System.out.println("Real");
//		board.printBoard();
//		System.out.println("Pieces");
//		Piece.getBoard().printBoard();
//		board.move(new FileRank('e', 2), new FileRank('e', 4));
//		System.out.println("real");
//		board.printBoard();
//		System.out.println("pieces");
//		Piece.getBoard().printBoard();
//		board.move(new FileRank('f', 7), new FileRank('f', 5));
//		System.out.println("real");
//		board.printBoard();
//		System.out.println("pieces");
//		Piece.getBoard().printBoard();
//		board.move(new FileRank('d', 1), new FileRank('h', 5));
//		System.out.println("real");
//		board.printBoard();
//		System.out.println("pieces");
//		Piece.getBoard().printBoard();
//		
//		System.out.println(board.inCheck('b'));
//		System.out.println(board.checkForCM('b'));
		// if one of the moves while testing the pseudolegals captures a piece, i want to make sure the enemy piece
		// does not actually leave bPieces or wPieces
		// i could do this by checking if the end filerank is null and then if not, we add it back to whatever list it belongs to
//		testLoop();
		gameLoop();
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
//			if(currentTeam == 'b') {
//				Piece.getBoard().printBoard();;
//			}
			if(board.inCheck(currentTeam)) {
				System.out.println("Check");
			}
			System.out.print(team + "'s move: ");
			
			String input = sc.nextLine();
			// resign & draw detection should go here
			// or after tokenizer creation
			// e2 e4
			// f7 f6
			// d1 h5
			// c7 c6

			StringTokenizer st = new StringTokenizer(input, " ");
			
			
			// TODO
			// resign
			// draw
			// promotion
			// check
			// checkmate
			try {
				startStr = st.nextToken(); 
				frStart = getFileRank(startStr);
				
				endStr = st.nextToken();
				frEnd = getFileRank(endStr);
			} catch(NoSuchElementException e) {
				System.out.println("Invalid input");
				continue;
			}
			if(board.get(frStart) == null) {
				System.out.println("Error: no piece at given FileRank");
				continue;
			}
			if(board.get(frStart).getTeam() != currentTeam) {
				System.out.println("Error: cannot control a piece on the other team");
				continue;
			}
			// move:
			//e2 e4
			//f7 f5
			//d1 h5
			if(board.move(frStart, frEnd)) {
				illegalMove = false;
				if(team.equals("White")) {
					team = "Black";
					currentTeam = 'b';
				} else {
					team = "White";
					currentTeam = 'w';
				}
				
				if(board.checkForCM(currentTeam)) {
					gameOn = false;
				}
			} else {
				System.out.println("Illegal move, try again");
				illegalMove = true;
			}
		}
		sc.close();
		System.out.println(team + " wins");
	}
	
	private static boolean isResign(String input) {
		return false;
	}
	private static boolean isDrawQuestion(String input) {
		return false;
	}
	private static boolean isDrawAnswer(String input) {
		return false;
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
		board.put(new FileRank('g', 7), new FileRank('a', 3));
		board.put(new FileRank('h', 7), new FileRank('b', 3));
		board.put(new FileRank('h', 8), new FileRank('a', 4));
		while(true) {
			board.printBoard();
			if(board.inCheck('b')) {
				System.out.println("B is in check");
			}
			System.out.println("B in checkmate: " + board.checkForCM('b'));
			System.out.print("move: ");
			String input = sc.nextLine();
			StringTokenizer st = new StringTokenizer(input, " ");
			String startStr = null;
			FileRank frStart = null;
			String endStr = null;
			FileRank frEnd = null;
			try {
				startStr = st.nextToken(); 
				frStart = getFileRank(startStr);
				
				endStr = st.nextToken();
				frEnd = getFileRank(endStr);
			} catch(NoSuchElementException e) {
				System.out.println("Invalid input");
				continue;
			}
			System.out.println();
			board.move(frStart, frEnd);
		}
	}
}
//bR bN bB bQ bK bB bN bR  8
//## bp bp bp bp    ##     7
//bp ##    ##    ## wQ bp  6
//##    ##    ## bp ##     5
//   ##    ## wp ##    ##  4
//wp    ##    ##    ##     3
//   wp wp wp    wp wp wp  2
//wR wN wB    wK wB wN wR  1
// a  b  c  d  e  f  g  h 

// a1 translates to [7][0]
// so if a = 7, b = 6, c = 5, d = 4, e = 3, f = 2, g = 1, h = 0
// as for the rank
// 1 = 0, 2 = 1, 3 = 2, 4 = 3, 5 = 4, 6 = 5, 7 = 6, 8 =7
// so we can conclude that 