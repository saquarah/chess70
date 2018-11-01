package pieces;

import board.FileRank;
/**
 * This class represents the King piece, which can move up, down, left, right, and diagonal, but not 
 * for multiple spaces like the Queen, or it can castle.
 * @author Sarah Squillace, Nikita Zala
 *
 */
public class King extends Piece{
    /**
     * Instantiates a King object
     * @param team char ('b' if black, 'w' if white) representation of which color the King is.
     * @param type the char representation of which type the King is, in this case, 'K'
     */
    public King(char team, char type) {
        super(team, type);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean isValidMove(FileRank start, FileRank end) {
        // TODO Auto-generated method stub

        if(Piece.getBoard().get(end)!= null && Piece.getBoard().get(end).getTeam() == team) {
            //System.out.println("end is friend");
            return false;
        }
        
        //get start and end of file and rank
        char s_f = start.getFile();
        char e_f = end.getFile();
        int s_r = start.getRank();
        int e_r = end.getRank();

        
        if((Math.abs(s_f-e_f)<=1) && (Math.abs(s_r-e_r)<=1)) {
            return true;
        }
        
        //castling
        
        //TODO: call check method to see if king already in check then return false
        //THIS CANNOT HAPPEN HERE OR ELSE WE GET STACK OVERFLOW. LET'S SEE IF IT WORKS IN BOARD'S MOVE METHOD
//        if(Piece.getBoard().inCheck(this.team)) { // can't castle if the king is in check
//        	return false;
//        }
        
        //case 1: white king on e1 and Rook on h1
        
        if(s_f=='e' && e_f =='g' && s_r==1 && e_r==1 && Piece.getBoard().get(start).getTeam()=='w') {
        	FileRank f  = new FileRank('h',1);
        	FileRank b1  = new FileRank('f',1);
        	if(Piece.getBoard().underAttack(b1, this.team)) { // king can't pass over a space under attack
        		return false;
        	}
        	FileRank b2 = new FileRank('g',1);
        	
        	if(Piece.getBoard().get(f) instanceof Rook && Piece.getBoard().get(b1)==null && Piece.getBoard().get(b2)==null) {
        		//System.out.println("yes");
        		if(!Piece.getBoard().get(f).hasMoved() && !this.hasMoved()) {
        			return true;
        		}
        	}
        }
        
        //case 2: black king on e8 and Rook on h8
        else if(s_f=='e' && e_f =='g' && s_r==8 && e_r==8 && Piece.getBoard().get(start).getTeam()=='b') {
        	FileRank f  = new FileRank('h',8);
        	FileRank b1  = new FileRank('f',8);
        	if(Piece.getBoard().underAttack(b1, this.team)) {
        		return false;
        	}
        	FileRank b2 = new FileRank('g',8);
        	
        	if(Piece.getBoard().get(f) instanceof Rook && Piece.getBoard().get(b1)==null && Piece.getBoard().get(b2)==null) {
        		//System.out.println("yes");
        		if(!Piece.getBoard().get(f).hasMoved() && !this.hasMoved()) {
        			return true;
        		}
        	}
        }
      //Case3: white king on e1 and Rook on a1
        else if(s_f=='e' && e_f =='c' && s_r==1 && e_r==1 && Piece.getBoard().get(start).getTeam()=='w') {
        	FileRank f  = new FileRank('a',1);
        	FileRank b1  = new FileRank('b',1);
        	FileRank b2 = new FileRank('c',1);
        	FileRank b3 = new FileRank('d',1);
        	if(Piece.getBoard().underAttack(b3, this.team)) {
        		return false;
        	}
        	
        	if(Piece.getBoard().get(f) instanceof Rook && Piece.getBoard().get(b1)==null && Piece.getBoard().get(b2)==null && Piece.getBoard().get(b3)==null) {
        		//System.out.println("yes");
        		if(!Piece.getBoard().get(f).hasMoved() && !this.hasMoved()) {
        			return true;
        		}
        	}
        }
        
        //case 4: black king on e8 and Rook on a8
        else if(s_f=='e' && e_f =='c' && s_r==8 && e_r==8 && Piece.getBoard().get(start).getTeam()=='b') {
        	FileRank f  = new FileRank('a',8); // these are not meaningful variable names
        	FileRank b1  = new FileRank('b',8);
        	FileRank b2 = new FileRank('c',8);
        	FileRank b3 = new FileRank('d',8);
        	if(Piece.getBoard().underAttack(b3, this.team)) {
        		return false;
        	}
        	
        	if(Piece.getBoard().get(f) instanceof Rook && Piece.getBoard().get(b1)==null && Piece.getBoard().get(b2)==null && Piece.getBoard().get(b3)==null) {
        		//System.out.println("yes");
        		if(!Piece.getBoard().get(f).hasMoved() && !this.hasMoved()) {
        			return true;
        		}
        	}
        }
        return false;
    }
    
}
