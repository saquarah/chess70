package chess;

import java.util.NoSuchElementException;

import java.util.Scanner;
import java.util.StringTokenizer;

import board.Board;
import board.FileRank;
import pieces.*;
/**
 * The main class that executes the game loop and is where the main method is located.
 * @author Sarah Squillace, Nikita Zala
 */
public class Chess {
	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
//		testLoop();
//		Board board = new Board();
//
//		board.move(new FileRank('e', 2), new FileRank('e', 4));
//		board.move(new FileRank('f', 7), new FileRank('f', 5));
//		board.move(new FileRank('d', 1), new FileRank('h', 5));
//		board.move(new FileRank('g', 1), new FileRank('f', 3));
//		board.move(new FileRank('f', 1), new FileRank('e', 2));
//		board.printBoard();
//		Piece.getBoard().printBoard();
//		board.move(new FileRank('e', 1), new FileRank('g', 1));a
//		board.printBoard();
//		System.out.println(board.inCheck('b'));
//		System.out.println(board.checkForCM('b'));
		// if one of the moves while testing the pseudolegals captures a piece, i want to make sure the enemy piece
		// does not actually leave bPieces or wPieces
		// i could do this by checking if the end filerank is null and then if not, we add it back to whatever list it belongs to
//		testLoop();
		gameLoop();
	}
	/**
	 * The method that starts the game loop for Chess.
	 */
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
		
		boolean whiteDrawQuestion = false;
		boolean blackDrawQuestion = false;
		boolean drawAns = false;
		boolean isResign = false;
		boolean isExplicitPromotion = false;
		char promoChar = 'd';
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
			isExplicitPromotion = isExplicitPromo(input);
			if(isExplicitPromotion) {
				String[] inputtokens = input.split(" ");
				promoChar = inputtokens[2].charAt(0);
			}
		//	System.out.println(promoChar);
			if(currentTeam == 'b') {
				drawAns = isDrawAnswer(input, whiteDrawQuestion);
				whiteDrawQuestion = false;
			} else {
				drawAns = isDrawAnswer(input, blackDrawQuestion);
				blackDrawQuestion = false;
			}
			if(currentTeam == 'w') {
				whiteDrawQuestion = isDrawQuestion(input);
			} else {
				blackDrawQuestion = isDrawQuestion(input);
			}
			if(drawAns) {
				break;
				
			}
			isResign = isResign(input);
			if(isResign) {
				if(currentTeam == 'w') {
					team = "Black";
				} else {
					team = "White";
				}
				break;
			}
			// resign & draw detection should go here
			// or after tokenizer creation
			// e2 e4
			// f7 f6
			// d1 h5
			// c7 c6
			System.out.println();
			StringTokenizer st = new StringTokenizer(input, " ");
			
			
			// TODO
			// resign
			// draw
			// promotion
			// check: done
			// checkmate: done
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
				illegalMove = true;
				System.out.println("Illegal move, try again");
				continue;
			}
			if(board.get(frStart).getTeam() != currentTeam) {
				illegalMove = true;
				System.out.println("Illegal move, try again");
				continue;
			}
			// move:
			//e2 e4
			//f7 f5
			//d1 h5
//			if(frStart.equals(new FileRank('f', 2)) && frEnd.equals(new FileRank('f', 3))) {
//				System.out.println("debug point");
//			}
			if(board.move(frStart, frEnd)) {
				Piece newPromoPiece = null;
				if(board.get(frEnd) instanceof Pawn) {
					Pawn pawn = (Pawn) board.get(frEnd);
					if(pawn.getTeam() == 'w') {
						if(frEnd.getRank() == 8) { // a promotion occurred
							switch(promoChar) {
								case 'd':
									newPromoPiece = (Piece) new Queen('w', 'Q');
									break;
								case 'Q':
									newPromoPiece = (Piece) new Queen('w', 'Q');
									break;
								case 'B':
									newPromoPiece = (Piece) new Bishop('w', 'B');
									break;
								case 'R':
									newPromoPiece = (Piece) new Rook('w', 'R');
									break;
								case 'N':
									newPromoPiece = (Piece) new Knight('w', 'N');
									break;
								default:
									newPromoPiece = (Piece) new Queen('w', 'Q');
							}
							newPromoPiece.setHasMoved(true);	 
							newPromoPiece.setLoc(frEnd);
							board.getwPieces().remove(board.get(frEnd)); // remove the pawn
							board.getPawns().remove(board.get(frEnd));
							board.set(frEnd, newPromoPiece);
							board.getwPieces().add(newPromoPiece);
							Piece.setBoard(board);
						}
					} else {
						if(frEnd.getRank() == 1) { // promo
								switch(promoChar) {
								case 'd':
									newPromoPiece = (Piece) new Queen('b', 'Q');
									break;
								case 'Q':
									newPromoPiece = (Piece) new Queen('b', 'Q');
									break;
								case 'B':
									newPromoPiece = (Piece) new Bishop('b', 'B');
									break;
								case 'R':
									newPromoPiece = (Piece) new Rook('b', 'R');
									break;
								case 'N':
									newPromoPiece = (Piece) new Knight('b', 'N');
									break;
								default:
									newPromoPiece = (Piece) new Queen('b', 'Q');
							}
							newPromoPiece.setHasMoved(true);
							newPromoPiece.setLoc(frEnd);
							board.getbPieces().remove(board.get(frEnd));
							board.getPawns().remove(board.get(frEnd));
							board.set(frEnd, newPromoPiece);
							board.getbPieces().add(newPromoPiece);
							Piece.setBoard(board);
						}
					}
				}
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
					if(team.equals("White")) {
						team = "Black";
					}
					else {
						team = "White";
					}
				}
			} else {
				System.out.println("Illegal move, try again");
				illegalMove = true;
			}
			promoChar = 'd';
		}
		if(drawAns) {
			// draw
			sc.close();
			return;
		}
		sc.close();
		System.out.println(team + " wins");
	}
	
	private static boolean isResign(String input) {
		if(input.equals("resign")) {
			return true;
		}
		return false;
	}
	private static boolean isDrawQuestion(String input) {
		String[] inputTokens = input.split(" ");
		if(inputTokens.length < 3) {
			return false;
		} else {
			if(inputTokens[2].equals("draw?")) {
				return true;
			}
		}
		return false;
	}
	private static boolean isDrawAnswer(String input, boolean drawQuestion) {
		if(drawQuestion && input.equals("draw")) {
			return true;
		}
		return false;
	}
	
	private static boolean isExplicitPromo(String input) {
		String[] inputTokens = input.split(" ");
		if (inputTokens.length >= 3) {
			if(inputTokens[2].length() > 1) {
				return false;
			} else {
				char ch = inputTokens[2].charAt(0);
				if(ch == 'N' || ch == 'Q' || ch == 'R' || ch == 'B') {
					return true;
				}
			}
		}
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

//a2 a3
//g7 g6
//b2 b3
//f8 h6
//c2 c3
//h6 g5
//d2 d3
//g5 h4
//f2 f3 should be illegal

// THIS ONE
//f2 f4
//e7 e5
//e1 f2
//e5 e4
//f2 f3

//e2 e4
//e7 e5
//g1 f3
//b8 c6
//f1 b5
//a7 a6
//b5 c6
//d7 c6
//e1 g1
//g8 e7
//d2 d4
//e5 d4
//f3 d4
//f7 f6
//a2 a4
//e7 g6
//c2 c4
//f8 e7
//b2 b3
//e8 g8
//d1 d3
//c6 c5
//d4 f5
//d8 d3
//b1 d2
//c8 f5
//e4 f5
//g6 f4
//d2 f3
//f4 e2
//g1 h1
//e2 g3
//h2 g3
//d3 f1
//h1 h2
//e7 d6
//c1 b2
//f1 f2
//a1 d1
//d6 g3
//h2 h1
//f2 b2
//d1 d8
//f8 d8
//f3 e5
//b2 c1