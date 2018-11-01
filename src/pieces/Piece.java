package pieces;

import java.util.ArrayList;

import board.Board;
import board.FileRank;
/**
 * Abstract foundation for the chess pieces.
 * @author Sarah Squillace, Nikita Zala
 * 
 */
public abstract class Piece implements Cloneable{
	char team;
	private char type;
	static Board board; // This is the same board created for the game. It will be used to determine legal moves, etc.
	private static ArrayList<Piece> wPieces;
	private static ArrayList<Piece> bPieces;
	private boolean hasMoved;
	
	private FileRank loc;
	
	/**
	 * Instantiates a Piece object, setting its hasMoved boolean field to false.
	 * @param team the char representing the team of this piece ('b' for black, 'w' for white)
	 * @param type the char representing the type of this piece ('Q' for queen, 'p' for pawn, etc)
	 */
	public Piece(char team, char type) {
		this.team = team;
		this.type = type;
		hasMoved = false;
	}
	/**
	 * Sets this piece's location to the given FileRank
	 * @param loc The FileRank location of the piece.
	 */
	public void setLoc(FileRank loc) {
		this.loc = loc;
	}
	/**
	 * Gets the location of the piece on the chess board
	 * @return the FileRank representing the piece's location
	 */
	public FileRank getLoc() {
		return loc;
	}
	/**
	 * Sets the static Board that all pieces know
	 * @param newBoard The Board that is available to all pieces.
	 */
	public static void setBoard(Board newBoard) {
		board = newBoard;
	}
	/**
	 * Gets the board that is static for the Piece class, it is the same as the board being played
	 * in the game.
	 * @return the Board object referenced by the Piece class
	 */
	public static Board getBoard() {
		return board;
	}
	/**
	 * Sets the ArrayList of white pieces equal to the given ArrayList of pieces.
	 * @param an ArrayList of Piece objects.
	 */
	public static void setWPieces(ArrayList<Piece> pieces) {
		wPieces = pieces;
	}
	/**
	 * Gets the ArrayList of all white pieces in 
	 * @return an ArrayList of white Piece objects
	 */
	public static ArrayList<Piece> getWPieces() {
		return wPieces;
	}
	/**
	 * Sets the ArrayList of black pieces equal to the given ArrayList of pieces.
	 * @param an ArrayList of Piece objects
	 */
	public static void setBPieces(ArrayList<Piece> pieces) {
		bPieces = pieces;
	}
	/**
	 * 
	 * @return an ArrayList of black Piece objects
	 */
	public static ArrayList<Piece> getBPieces() {
		return bPieces;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	/**
	 * Determines if a piece has moved on the board since starting the game
	 * @return true if the piece has moved, false if not.
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	/**
	 * This method returns the char representing the team of this instance.
	 * @return 'b' if the Piece is on the black team, or 'w' if the piece
	 * is on the white time
	 */
	public char getTeam() {
		return team;
	}
	/**
	 * This string returns the name of the piece, the name being how the piece appears on the Board when
	 * it is printed. It is a combination of the char field team and the char field type, where a white pawn
	 * would appear as "wp" and a black queen would appear as "bQ"
	 * @return the String  representation of a Piece
	 */
	public String name() {
		return team + "" + type;
	}
	/**
	 * This shows a String representation of the Piece, showing the type, team, and FileRank location of 
	 * the piece
	 * @return the String representation of a Piece with its location as a FileRank
	 */
	public String toString() {
		String p = team + "" + type + ":  ";
		p += loc.getFile() + "" + loc.getRank();
		return p;
	}
	/**
	 * This method returns true if the move from start to end is a valid move for this Piece. It is an
	 * abstract method and its implementation differs depending on the type of piece.
	 * @param start the FileRank location of the piece that is moving
	 * @param end the FileRank location of where the piece aims to move.
	 * @return true if the move from start to end is valid by the rules of chess, false if not
	 */
	public abstract boolean isValidMove(FileRank start, FileRank end);
	
	/**
	 * This method clones a Piece into a separate object in memory with identical fields.
	 * @return A Piece object clone
	 */
	public Piece clone() {
		try {
			return (Piece) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
