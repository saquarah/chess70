package pieces;

import board.Board;
import board.FileRank;

public abstract class Piece {
	char team;
	private char type;
	private static Board board; // This is the same board created for the game. It will be used to determine legal moves, etc.
	public Piece(char team, char type) {
		this.team = team;
		this.type = type;
	}
	public static void setBoard(Board newBoard) {
		board = newBoard;
	}
	public static Board getBoard() {
		return board;
	}
	/**
	 * 
	 * @return 'b' if the Piece is on the black team, or 'w' if the piece
	 * is on the white time
	 */
	public char getTeam() {
		return team;
	}
	
	public String toString() {
		return team + "" + type;
	}
	
	public abstract boolean isValidMove(FileRank start, FileRank end);
	
	public void move(FileRank start, FileRank end) {
		// can capture
	}
	
}
