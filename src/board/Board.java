package board;

import pieces.*;

public class Board {
	Piece[][] board = new Piece[8][8];
	char file[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}; 
	public Board() {
		populateBoard();
	}
	private void populateBoard() {
		board[0][0] = new Rook('w', 'R');
		board[0][1] = new Knight('w', 'N');
		board[0][2] = new Bishop('w', 'B');
		board[0][3] = new Queen('w', 'Q');
		board[0][4] = new King('w', 'K');
		board[0][5] = new Bishop('w', 'B');
		board[0][6] = new Knight('w', 'N');
		board[0][7] = new Rook('w', 'R');
		
		for(int i = 0; i < 8; i++) {
			board[1][i] = new Pawn('w', 'p');
		}
		
		board[7][0] = new Rook('b', 'R');
		board[7][1] = new Knight('b', 'N');
		board[7][2] = new Bishop('b', 'B');
		board[7][3] = new Queen('b', 'Q');
		board[7][4] = new King('b', 'K');
		board[7][5] = new Bishop('b', 'B');
		board[7][6] = new Knight('b', 'N');
		board[7][7] = new Rook('b', 'R');
		
		for(int i = 0; i < 8; i++) {
			board[6][i] = new Pawn('b', 'p');
		}
	}
	
	public Piece get(FileRank fr) {
		return board[indexOf("" + fr.file)][fr.rank - 1];
	}

	private int indexOf(String c) {
		for(int i = 0; i < file.length; i++) {
			if(c.charAt(0) == file[i]) return i;
		}
		return -1;
	}
	/**
	 * Prints the chessboard in the required format
	 */
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
				System.out.print(" ");
			}
			System.out.println(" " + (8 - i));
		}
		for(int i = 0; i < 8; i++) {
			System.out.print(" " + file[i] + " ");
		}
	}

}
