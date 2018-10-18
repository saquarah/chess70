package pieces;

import board.FileRank;

public abstract class Piece {
	private char team;
	private char type;
	public Piece(char team, char type) {
		this.team = team;
		this.type = type;
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
