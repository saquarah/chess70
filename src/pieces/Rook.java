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
		if(start.getFile() == end.getFile()) {
			if(end.isToTheLeftOf(start.getFile())) {
				for(char i = start.getFile(); i > end.getFile(); i--) {
					if(Piece.getBoard().get(new FileRank(i, start.getRank())) != null) {
						return false;
					}
				}
				if(Piece.getBoard().get(end) == null || Piece.getBoard().get(end).getTeam() != getTeam()) {
					return true;
				}
			} else if (end.isToTheRightOf(start.getFile())) {
				for(char i = start.getFile(); i < end.getFile(); i++) {
					
				}
			}
		} else if (start.getRank() == end.getRank()) {
			if(end.isAbove(start.getRank())) {
				for(int i = start.getRank(); i < end.getRank(); i++) {
					
				}
			} else if (end.isBelow(start.getRank())) {
				for(int i = start.getRank(); i > end.getRank(); i--) {
					
				}
			}
		}
		return false;
	}

}
