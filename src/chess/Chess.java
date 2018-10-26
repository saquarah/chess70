package chess;

import board.Board;
import board.FileRank;
import pieces.Piece;
import java.util.Scanner;
public class Chess {
	public static void main(String[] args) {
		Board board = new Board();
		Scanner sc = new Scanner(System.in);
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
		// should probably put game loop in this class
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