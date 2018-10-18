package chess;

import board.Board;
import board.FileRank;

public class Chess {
	public static void main(String[] args) {
		Board board = new Board();
		board.printBoard();
		System.out.println();
		System.out.println(board.get(new FileRank('a', 1)));
		System.out.println(board.get(new FileRank('h', 5)));
		System.out.println(board.get(new FileRank('e', 8)));
	}
}
