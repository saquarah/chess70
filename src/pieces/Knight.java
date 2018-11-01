package pieces;

import board.FileRank;
/** This class represents Knight piece.
 * @author Sarah Squillace, Nikita Zala
 */

public class Knight extends Piece {
    /**
     * Instantiates a Knight object
     * @param team char ('b' if black, 'w' if white) representation of which color the Knight is.
     * @param type the char representation of which type the Piece is, in this case, 'N'
     */
    public Knight(char team, char type) {
        super(team, type);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    /**
     * Identifies legal moves of Knight
     * @param takes starting and ending position of move as parameter
     * return boolean whether or not it is a legal move for Knight
     */
    public boolean isValidMove(FileRank start, FileRank end) {
        // TODO Auto-generated method stub
        if(Piece.getBoard().get(end)!= null && Piece.getBoard().get(end).getTeam() == team) {
//            System.out.println("end is friend");
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
        
        
        /*
         if(Math.abs(e_f-s_f)==1 || Math.abs(e_f - s_f)==1){
         
         if((s_r+2 == e_r) || (e_r+2 == s_r))  {
         
         System.out.println("Is valid move");
         return true;
         }
         
         }*/
        
        if((Math.abs(s_f-e_f)==1 && Math.abs(s_r-e_r)==2) || (Math.abs(s_f-e_f)==2 && Math.abs(s_r-e_r)==1) ) {
            return true;
            
        }

        return false;
        
    }
    
}

