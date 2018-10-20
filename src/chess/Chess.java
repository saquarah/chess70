package chess;

import board.Board;
import board.FileRank;
import pieces.Piece;

public class Chess {
	public static void main(String[] args) {
		Board board = new Board();
		board.printBoard();
		System.out.println();

		// should probably put game loop in this class
	}
}
