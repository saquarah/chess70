package pieces;

import java.util.ArrayList;

import board.Board;
import board.FileRank;

public abstract class Piece {
	char team;
	private char type;
	private static Board board; // This is the same board created for the game. It will be used to determine legal moves, etc.
	private static ArrayList<Piece> wPieces;
	private static ArrayList<Piece> bPieces;
	private ArrayList<Piece> teamPieces;
	private boolean hasMoved;
	
	private FileRank loc;
	
	public Piece(char team, char type) {
		this.team = team;
		this.type = type;
		hasMoved = false;
	}
	public void setLoc(FileRank loc) {
		this.loc = loc;
	}
	public FileRank getLoc() {
		return loc;
	}
	public static void setBoard(Board newBoard) {
		board = newBoard;
	}
	public static Board getBoard() {
		return board;
	}
	public static void setWPieces(ArrayList<Piece> pieces) {
		wPieces = pieces;
	}
	public static ArrayList<Piece> getWPieces() {
		return wPieces;
	}
	public static void setBPieces(ArrayList<Piece> pieces) {
		bPieces = pieces;
	}
	public static ArrayList<Piece> getBPieces() {
		return bPieces;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	public boolean hasMoved() {
		return hasMoved;
	}
	/**
	 * 
	 * @return 'b' if the Piece is on the black team, or 'w' if the piece
	 * is on the white time
	 */
	public char getTeam() {
		return team;
	}
	
	public String name() {
		return team + "" + type;
	}
	
	public String toString() {
		String p = team + "" + type + "\n";
		p += loc;
		return p;
	}
	
	public abstract boolean isValidMove(FileRank start, FileRank end);
	
	public void move(FileRank start, FileRank end) {
		// can capture
	}
	
}
