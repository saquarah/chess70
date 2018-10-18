package pieces;

import board.FileRank;

public abstract class Piece {
	private char team;
	private char type;
	public Piece(char team, char type) {
		this.team = team;
		this.type = type;
	}
	
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
