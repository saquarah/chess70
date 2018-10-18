package board;

import java.util.ArrayList;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {
	ArrayList<Piece> wPieces = new ArrayList<Piece>();
	ArrayList<Piece> bPieces = new ArrayList<Piece>();
	Piece[][] board = new Piece[8][8];
	private char file[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}; 
	/**
	 * Instantiates Board object filling it with chess pieces
	 */
	public Board() {
		populateBoard();
	}
	private void populateBoard() { // This initializes the board with pieces
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
		
		for(int i = 0; i < 2; i++) { // adds all white pieces to array
			for(int j = 0; j < 8; j++) {
				wPieces.add(board[i][j]);
			}
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
		
		for(int i = 7; i > 5; i--) {
			for(int j = 0; j < 8; j++) {
				bPieces.add(board[i][j]);
			}
		}
	}
	/**
	 * Gets the chess piece at the given FileRank location
	 * @param fr
	 * @return The piece located at the given FileRank
	 */
	public Piece get(FileRank fr) {
		int file = indexOf("" + fr.file);
		int rank = 7 - (fr.rank - 1);
		return board[rank][file];
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
