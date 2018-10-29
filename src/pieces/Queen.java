package pieces;

import board.FileRank;

public class Queen extends Piece {

	public Queen(char team, char type) {
		super(team, type);
	}
	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
//		if(team == 'w') {
//		Piece.getBoard().printBoard();
//		System.out.println(team + " TEAM");
//		}
		if(inRooksPath(start, end)) {
			return true;
		} else if (inBishopsPath(start, end)) {
			return true;
		}
		return false;
	}
	private boolean inRooksPath(FileRank start, FileRank end) {
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
	private boolean inBishopsPath(FileRank start, FileRank end) {

		if(Piece.getBoard().get(end)!= null && Piece.getBoard().get(end).getTeam() == team) {
			//System.out.println("end is friend");
			return false;
		}
		char s_f = start.getFile();
		char e_f = end.getFile();
		int s_r = start.getRank();
		int e_r = end.getRank();
		int a = s_f-e_f;
		int b = s_r-e_r;
		if( Math.abs(a)!=Math.abs(b) ) {
			return false;
		}
		
		//this part is causing issue: 
		char t_f = (char) ((int)s_f);
		int t_r = s_r;
		
		//case 1
		if(a<0 && b<0) {
			t_f++;
			t_r++;
			while(t_f!=e_f && t_r!=e_r) {
				FileRank t = new FileRank(t_f,t_r);
				if(Piece.getBoard().get(t)!=null) {
					//System.out.println("false in case 1");
					//System.out.println(t_f);
					return false;
				}
				t_f++;
				t_r++;
				
			}
		}
		//case 2
		else if(a<0 && b>0) {
			t_f++;
			t_r--;
			while(t_f!=e_f && t_r!=e_r) {
				FileRank t = new FileRank(t_f,t_r);
				if(Piece.getBoard().get(t)!=null) {
					//System.out.println("false in case 2");
					return false;
				}
				t_f++;
				t_r--;
			}
		}
		//case 3
		else if(a>0 && b>0) {
			t_f--;
			t_r--;
			while(t_f!=e_f && t_r!=e_r) {
				FileRank t = new FileRank(t_f,t_r);
				//System.out.println(t);
				if(Piece.getBoard().get(t)!=null) {
//					System.out.println("false in case 3");
					return false;
				}
				t_f--;
				t_r--;
			}
		}
		//case 4
		else if(a>0 && b<0) {
			t_f--;
			t_r++;
			while(t_f!=e_f && t_r!=e_r) {
				FileRank t = new FileRank(t_f,t_r);
				if(Piece.getBoard().get(t)!=null) {
					//System.out.println("false in case 4");
					return false;
				}
				t_f--;
				t_r++;
			}
		}
		
		return true;
	}
}
