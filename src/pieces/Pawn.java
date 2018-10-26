package pieces;

import board.FileRank;
/**
 * 
 * @author Sarah Squillace, Nikita Zala
 * Pawns cannot move backward. 
 * 
 * En Passant: Can only be done the turn after the pawn uses its 2 square
 * initial move. 
 */
public class Pawn extends Piece {
	boolean justMoved2Squares; // only true the turn after a pawn uses its 2 square move
	FileRank enPassantLoc;
	public Pawn(char team, char type) {
		super(team, type);
		justMoved2Squares = false;
		enPassantLoc = null;
		// TODO Auto-generated constructor stub
	}
	
	public FileRank getEnPassantLoc() {
		return enPassantLoc;
	}

	public void setEnPassantLoc(FileRank enPassantLoc) {
		this.enPassantLoc = enPassantLoc;
	}

	public boolean hasJustMoved2Squares() {
		return justMoved2Squares;
	}

	public void setJustMoved2Squares(boolean justMoved2Squares) {
		this.justMoved2Squares = justMoved2Squares;
	}

	@Override
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
	private boolean isCapture (FileRank start, FileRank end) {
		
		
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
