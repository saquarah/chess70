package pieces;

import board.FileRank;
/**
 * Representation of the Pawn piece in chess.
 * @author Sarah Squillace, Nikita Zala
 * 
 */
public class Pawn extends Piece {
	boolean justMoved2Squares; // only true the turn after a pawn uses its 2 square move
	FileRank enPassantLoc;
    /**
     * Instantiates a Pawn object
     * @param team char ('b' if black, 'w' if white) representation of which color the Pawn is.
     * @param type the char representation of which type the Piece is, in this case, 'p'
     */
	public Pawn(char team, char type) {
		super(team, type);
		justMoved2Squares = false;
		enPassantLoc = null;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the FileRank location of where a Pawn on the opposing team would land after
	 * performing En Passant.
	 * @return a FileRank of the square behind it
	 */
	public FileRank getEnPassantLoc() {
		return enPassantLoc;
	}
	/**
	 * Sets the location of the Pawn that this Pawn will capture when performing En Passant
	 * @param enPassantLoc the FileRank location of the Pawn that will be captured
	 */
	public void setEnPassantLoc(FileRank enPassantLoc) {
		this.enPassantLoc = enPassantLoc;
	}
	/**
	 * Returns a boolean for if the Pawn had moved two squares on the previous move, used for determining if
	 * En Passant can be performed.
	 * @return true if the Pawn moved 2 squares on the previous turn, false if not.
	 */
	public boolean hasJustMoved2Squares() {
		return justMoved2Squares;
	}
	/**
	 * Changes the boolean for if the Pawn had moved two squares on the previous move, used for determining if
	 * En Passant can be performed.
	 * @param boolean value to change whether the Pawn just moved.
	 */
	public void setJustMoved2Squares(boolean justMoved2Squares) {
		this.justMoved2Squares = justMoved2Squares;
	}

	@Override
    /**
     * Identifies legal moves of Pawn
     * @param takes starting and ending position of move as parameter
     * @return boolean whether or not it is a legal move for Pawn
     */
	public boolean isValidMove(FileRank start, FileRank end) {
		// they're so complicated >:v
		
		if (is2SquareMove(start, end)) { // COMPLETE
			justMoved2Squares = true;
			return true;
		}
		else if (isCapture(start, end)) { // also considers if capture is en passant COMPLETE
			return true;
		}
		else if (isRegularMove(start, end)) { // COMPLETE
			return true;
		}
		else {
			return false;
		}
	}
	private boolean isEnPassant (FileRank start, FileRank end) {
		if(Piece.getBoard().get(end) != null) return false;
		if(team == 'w') { // if white capping black
			if ((end.getRank() - start.getRank() == 1) && ( // it only moved one rank up
					end.getFile() - start.getFile() == -1 || // the file is one to the left of the start file
					end.getFile() - start.getFile() == 1 )) { // the file is one to the right of the start file
				enPassantLoc = new FileRank(end.getFile(), end.getRank() - 1); // save location of pawn to be cap'd
				Piece target = Piece.getBoard().get(enPassantLoc);
				if(target instanceof Pawn) {
					Pawn pTarget = (Pawn) target;
					if(pTarget.hasJustMoved2Squares()) {
						return true;
					}
				}
				
			}
		} else { // if black capping white
			if ((end.getRank() - start.getRank() == -1) && ( // it only moved one rank down
					end.getFile() - start.getFile() == -1 || // the file is one to the left of the start file
					end.getFile() - start.getFile() == 1 )) { // the file is one to the right of the start file
				enPassantLoc = new FileRank(end.getFile(), end.getRank() + 1);
				Piece target = Piece.getBoard().get(enPassantLoc);
				if(target instanceof Pawn) {
					Pawn pTarget = (Pawn) target;
					if(pTarget.hasJustMoved2Squares()) {
						return true;
					}
				}
				
			}
		}
		enPassantLoc = null;
		return false;
	}
	private boolean is2SquareMove (FileRank start, FileRank end) {
		if(!this.hasMoved()) {
			if(Piece.getBoard().get(end) != null) {
				return false;
			}
			if(team == 'w') { // if white capping black
				if (end.getRank() - start.getRank() == 2 && start.getFile() == end.getFile()) { // pawn moved 
					return true;
				}
			} else { // if black
				if (end.getRank() - start.getRank() == -2 && start.getFile() == end.getFile()) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Returns true if this Pawn will capture a Piece on the enemy team.
	 * @param start The FileRank representing the beginning of the move
	 * @param end The FileRank of where the pawn will end on
	 * @return true if this move qualifies as a capture, false if not
	 */
	public boolean isCapture (FileRank start, FileRank end) {
		
		
		if(isEnPassant(start, end)) {
			return true;
		}
		if(Piece.getBoard().get(end) == null) return false;
		
		if(team == 'w'  && Piece.getBoard().get(end).getTeam() != 'w') { // white capping black
			if((end.getRank() - start.getRank() == 1) && ( // it only moved one rank up
					end.getFile() - start.getFile() == -1 || // the file is one to the left of the start file
					end.getFile() - start.getFile() == 1 )) { // the file is one to the right of the start file
				return true;
			}
		} else if(team == 'b' && Piece.getBoard().get(end).getTeam() != 'b') { // black capping white
			if((end.getRank() - start.getRank() == -1) && ( // it only moved one rank down
					end.getFile() - start.getFile() == -1 || // the file is one to the left of the start file
					end.getFile() - start.getFile() == 1 )) { // the file is one to the right of the start file
				return true;
			}
			
		}
		return false;
	}
	private boolean isRegularMove (FileRank start, FileRank end) {
		if (Piece.getBoard().get(end) == null) { // pawns can only move onto empty spaces
			if(team == 'w') { // if pawn is white
				if(end.getRank() - start.getRank() == 1 && start.getFile() == end.getFile()) { // making sure the square is correct
					return true;
				}
			} else { // if pawn is black
				if(end.getRank() - start.getRank() == -1 && start.getFile() == end.getFile()) { // making sure the square is correct
					return true;
				}
			}
		}
		return false;

	}
}
