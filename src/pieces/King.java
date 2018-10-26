package pieces;

import board.FileRank;

public class King extends Piece{
    
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
        
        //System.out.println(s_f);
        //System.out.println(s_r);
        //System.out.println(e_f);
        //System.out.println(e_r);
        
        
        if((Math.abs(s_f-e_f)<=1) && (Math.abs(s_r-e_r)<=1)) {
            return true;
        }
        else{
        System.out.println("Illegal move, try again");
        }
        return false;
    }
    
}
