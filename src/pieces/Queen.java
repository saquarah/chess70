package pieces;

import board.FileRank;

public class Queen extends Piece {

	public Queen(char team, char type) {
		super(team, type);
	}
	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
		return true;
	}

}
