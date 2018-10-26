package pieces;

import board.FileRank;

public class Rook extends Piece {

	public Rook(char team, char type) {
		super(team, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
		// TODO Auto-generated method stub
		if(start.equals(end)) {
			return false;
		}
		if(start.getFile() == end.getFile()) {
			if(end.isBelow(start.getRank())) { // if the rook is moving down
				for(int i = start.getRank() - 1; i > end.getRank(); i--) { // iterate through to check for pieces in path
					if(Piece.getBoard().get(new FileRank(start.getFile(), i)) != null) { // if you find one set to false
						return false;
					}
				}
				if(Piece.getBoard().get(end) == null || Piece.getBoard().get(end).getTeam() != getTeam()) { // if the space is empty or 
					return true;																			// the rook is able to capture an enemy
				}
			} else if (end.isAbove(start.getRank())) {
				for(int i = start.getRank() + 1; i < end.getRank(); i++) {
					if(Piece.getBoard().get(new FileRank(start.getFile(), i)) != null) {
						return false;
					}
				}
				if(Piece.getBoard().get(end) == null || Piece.getBoard().get(end).getTeam() != getTeam()) {
					return true;
				}
			}
		} else if (start.getRank() == end.getRank()) {
			if(end.isToTheRightOf(start.getFile())) {
				for(int i = start.getFile() + 1; i < end.getFile(); i++) {
					char file = (char) i;
					if(Piece.getBoard().get(new FileRank(file, start.getRank())) != null) {
						return false;
					}
				}
				if(Piece.getBoard().get(end) == null || Piece.getBoard().get(end).getTeam() != getTeam()) {
					return true;
				}
			} else if (end.isToTheLeftOf(start.getFile())) {
				for(int i = start.getFile() - 1; i > end.getFile(); i--) {
					char file = (char) i;
					if(Piece.getBoard().get(new FileRank(file, start.getRank())) != null) {
						return false;
					}
				}
				if(Piece.getBoard().get(end) == null || Piece.getBoard().get(end).getTeam() != getTeam()) {
					return true;
				}
			}
		}
		return false;
	}

}
