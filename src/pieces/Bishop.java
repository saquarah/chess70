package pieces;

import board.FileRank;
/**
 * Representation of the Bishop piece in chess.
 * @author Sarah Squillace, Nikita Zala
 * 
 */
public class Bishop extends Piece {
	  /**
     * Instantiates a Bishop object
     * @param team char ('b' if black, 'w' if white) representation of which color the Bishop is.
     * @param type the char representation of which type the King is, in this case, 'B'
     */
	public Bishop(char team, char type) {
		super(team, type);
		
	}

	@Override
	public boolean isValidMove(FileRank start, FileRank end) {
		
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
