package chess;

import board.Board;
import board.FileRank;
import pieces.Piece;

public class Chess {
	public static void main(String[] args) {
		Board board = new Board();
		board.printBoard();
		board.move(new FileRank('a', 2), new FileRank('a', 4));
		board.move(new FileRank('e', 7), new FileRank('e', 4));
		board.printBoard();
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