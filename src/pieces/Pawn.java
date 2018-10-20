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
	boolean hasMoved;
	boolean justMoved2Squares;
	public Pawn(char team, char type) {
		super(team, type);
		hasMoved = false;
		justMoved2Squares = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
		if(Piece.getBoard().get(end).getTeam() == team) {
			return false;
		}
		if(team == 'w') { // if white (can only move down)
			
		} else { // if black (can only move up)
			
		}
		
		return false;
	}

}
