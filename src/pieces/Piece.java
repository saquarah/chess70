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
