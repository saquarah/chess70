package board;

import pieces.*;

public class Board {
	Piece[][] board = new Piece[8][8];
	char file[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}; 
	public Board() {
		
	}
	
	public void printBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
						System.out.print("  ");
					} else {
						System.out.print("##");
					}
				} else {
					System.out.print(board[i][j]);
				}
			}
			System.out.println(" " + (8 - i));
		}
		for(int i = 0; i < 8; i++) {
			System.out.print(" " + file[i]);
		}
	}

}
