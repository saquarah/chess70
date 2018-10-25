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
	boolean hasMoved; // false if the pawn has never moved
	boolean justMoved2Squares; // only true the turn after a pawn uses its 2 square move
	public Pawn(char team, char type) {
		super(team, type);
		hasMoved = false;
		justMoved2Squares = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
		// they're so complicated >:v
		if (is2SquareMove(start, end)) { // COMPLETE
			return true;
		}
		else if (isCapture(start, end)) { // also considers if capture is en passant
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
		return false;
	}
	private boolean is2SquareMove (FileRank start, FileRank end) {
		if(!hasMoved) {
			if(team == 'w') { // if white
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
		if(team == 'w') {
			if(end.getRank() - start.getRank() == 1) {
				
			}
		} else {
			
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
