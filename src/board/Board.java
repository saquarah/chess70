package board;

import java.util.ArrayList;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board {
	private ArrayList<Piece> wPieces = new ArrayList<Piece>();
	private ArrayList<Piece> bPieces = new ArrayList<Piece>();
	private ArrayList<Pawn> pawns = new ArrayList<Pawn> ();
	Piece[][] board = new Piece[8][8]; // TODO CHANGE BACK TO PACKAGE ok did it
	private char files[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	
	static class Move {
		FileRank start;
		FileRank end;
		public Move(FileRank start, FileRank end) {
			this.start = start;
			this.end = end;
		}
		public String toString() {
			return "Start: " + start.file + "" + start.rank + "\n" + "End: " + end.file + "" + end.rank;
		}
	}
	/**
	 * 
	 * @return the ArrayList containing all white pieces still in the game.
	 */
	public ArrayList<Piece> getwPieces() {
		return wPieces;
	}
	/**
	 * Sets the ArrayList of white pieces.
	 * @param wPieces The new ArrayList of white pieces.
	 */
	public void setwPieces(ArrayList<Piece> wPieces) {
		this.wPieces = wPieces;
	}
	/**
	 * 
	 * @return the ArrayList containing all black pieces still in the game.
	 */
	public ArrayList<Piece> getbPieces() {
		return bPieces;
	}
	/**
	 * Sets the ArrayList of black pieces.
	 * @param bPieces The new ArrayList of black pieces.
	 */
	public void setbPieces(ArrayList<Piece> bPieces) {
		this.bPieces = bPieces;
	}
	/**
	 * 
	 * @return the ArrayList of all existing pawns in the game.
	 */
	public ArrayList<Pawn> getPawns() {
		return pawns;
	}
	/**
	 * Sets the ArrayList of all pawns.
	 * @param pawns The new ArrayList of pawns.
	 */
	public void setPawns(ArrayList<Pawn> pawns) {
		this.pawns = pawns;
	}
	/**
	 * Instantiates Board object filling it with chess pieces.
	 */
	public Board() {
		populateBoard();
	}
	private void populateBoard() { // This initializes the board with pieces
		board[0][0] = new Rook('b', 'R');
		board[0][1] = new Knight('b', 'N');
		board[0][2] = new Bishop('b', 'B');
		board[0][3] = new Queen('b', 'Q'); 
		board[0][4] = new King('b', 'K');
		board[0][5] = new Bishop('b', 'B');
		board[0][6] = new Knight('b', 'N');
		board[0][7] = new Rook('b', 'R');
		
		for(int i = 0; i < 8; i++) {
			Pawn p = new Pawn('b', 'p');
			board[1][i] = p;
			pawns.add(p);
		}
		
		for(int i = 0; i < 2; i++) { // adds all black pieces to array
			for(int j = 0; j < 8; j++) {
				Piece currentPiece = board[i][j];
				currentPiece.setLoc(new FileRank(files[j], 8 - i));
				bPieces.add(currentPiece);
				
			}
		}
		
		board[7][0] = new Rook('w', 'R');
		board[7][1] = new Knight('w', 'N');
		board[7][2] = new Bishop('w', 'B');
		board[7][3] = new Queen('w', 'Q');
		board[7][4] = new King('w', 'K');
		board[7][5] = new Bishop('w', 'B');
		board[7][6] = new Knight('w', 'N');
		board[7][7] = new Rook('w', 'R');
		
		for(int i = 0; i < 8; i++) {
			Pawn p = new Pawn('w', 'p');
			board[6][i] = p;
			pawns.add(p);
		}
		
		for(int i = 7; i > 5; i--) {
			for(int j = 0; j < 8; j++) {
				Piece currentPiece = board[i][j];
				currentPiece.setLoc(new FileRank(files[j], 8 - i));
				wPieces.add(currentPiece);
			}
		}
		Piece.setBoard(this);
		Piece.setWPieces(wPieces);
		Piece.setBPieces(bPieces);
	}

	/**
	 * Gets the chess piece at the given FileRank location
	 * @param fr The given FileRank
	 * @return The piece located at the given FileRank
	 */
	public Piece get(FileRank fr) {
		int file = indexOf("" + fr.file);
		int rank = 7 - (fr.rank - 1);
		return board[rank][file];
	}
	
	/**
	 * Puts the parameterized Piece on the given FileRank. If 
	 * the piece is null, it empties the FileRank space.
	 * @param fr - The FileRank being changed
	 * @param piece - The piece being put on the space
	 */
	public void set(FileRank fr, Piece piece) {
		int file = indexOf("" + fr.file);
		int rank = 7 - (fr.rank - 1);
		board[rank][file] = piece;
	}
	private int indexOf(String c) {
		for(int i = 0; i < files.length; i++) {
			if(c.charAt(0) == files[i]) return i;
		}
		return -1;
	}
	/**
	 * Places the piece at FileRank start on FileRank end, regardless of the
	 * legality of the move. Useful for placing the rook when the king is castling.
	 * @param start The FileRank where the piece is first located.
	 * @param end The FileRank of the destination of the piece.
	 */
	public void put(FileRank start, FileRank end) {
		Piece movingPiece = get(start);
		set(end, movingPiece);
		set(start, null);
		movingPiece.setLoc(end);
	}
	private King findKing(char team) {
		if(team == 'w') {
			for(int i = 0; i < wPieces.size(); i++) {
				if(wPieces.get(i) instanceof King) {
					return (King) wPieces.get(i);
				}
			}
		} else {
			for(int i = 0; i < bPieces.size(); i++) {
				if(bPieces.get(i) instanceof King) {
					return (King) bPieces.get(i);
				}
			}
		}
		return null; // theoretically shouldnt happen
	}
	private void otherMove(FileRank start, FileRank end) {
		if(get(start) != null) {
			if(get(start).isValidMove(start, end)) {
				Piece movingPiece = get(start);
				
//				Board phantomBoard = copyBoard();
//				phantomBoard.move(start, end); // this doesn't work bc recursion
//				if(phantomBoard.inCheck(movingPiece.getTeam())) {
//					Piece.setBoard(this);
//					return false;
//				}
//				Piece.setBoard(this);
//				King thisTeamsKing = findKing(movingPiece.getTeam());
				
//				if(underAttack(thisTeamsKing.getLoc(), thisTeamsKing.getTeam())) {
//					/*
//					 * okay so this is flawed because the movingPiece hasn't actually moved yet. so in the pawn ex
//					 * the pawn is at first protecting the king. what we need to do is make a phantom board again to simulate the move
//					 * using the same way it was done in the checkmate algorithm. and then use inCheck on that.
//					 */q2w22223333333333333333333333333333333333333333333333333333333333333w
//					return false;
//				}
				if(movingPiece instanceof King) {
	
//			    	if(underAttack(end, movingPiece.getTeam())) { // can't move a king to a place where it'll be check
//			    		return;
//			    	}
			//		System.out.println("in moving");
					if(start.getFile()=='e' && start.getRank()==1 && end.getFile()=='g' && end.getRank()==1) {
			//			System.out.println("In case1");
						move(new FileRank('h',1), new FileRank('f',1));
					}
					else if(start.getFile()=='e' && start.getRank()==8 && end.getFile()=='g' && end.getRank()==8) {
			//			System.out.println("In case2");
						move(new FileRank('h',8), new FileRank('f',8));
					}
					else if(start.getFile()=='e' && start.getRank()==1 && end.getFile()=='c' && end.getRank()==1) {
			//			System.out.println("In case3");
						move(new FileRank('a',1), new FileRank('d',1));
					}
					else if(start.getFile()=='e' && start.getRank()==8 && end.getFile()=='c' && end.getRank()==8) {
			//			System.out.println("In case4");
						move(new FileRank('a',8), new FileRank('d',8));
					}
					
				}
				set(start, null);
				if(movingPiece instanceof Pawn) { // This is to check if an en passant occurred
					Pawn movingPawn = (Pawn) movingPiece; // it's special bc en passant is the only move where you end not
					if (movingPawn.getEnPassantLoc() != null) { // on the same space as the piece you capped.
						Pawn target = (Pawn) get(movingPawn.getEnPassantLoc());
						if (target.getTeam() == 'w') {
							wPieces.remove(target);
						} else {
							bPieces.remove(target);
						}
						pawns.remove(target);
						set(movingPawn.getEnPassantLoc(), null);
						movingPawn.setEnPassantLoc(null);
					}
				}
				if(get(end) != null) { // this indicates a piece is getting cap'd
					// implement later
					Piece dyingPiece = get(end);
					if(dyingPiece instanceof Pawn) {
						pawns.remove(dyingPiece);
					}
					if(dyingPiece.getTeam() == 'w') {
						wPieces.remove(dyingPiece);
					} else {
						bPieces.remove(dyingPiece);
					}
				}
				set(end, movingPiece);
				movingPiece.setLoc(end);
				movingPiece.setHasMoved(true);
				updatePieces(movingPiece);
				return;
			}
		
		} else {
			return;
		}
	}
	/**
	 * Moves the piece from start to the end, capturing the piece at
	 * FileRank end, but only if the move is valid.
	 * @param start The FileRank where the piece starts.
	 * @param end The FileRank where the piece will end at, if the move is valid.
	 * @return true if the move is valid, false if otherwise.
	 */
	public boolean move(FileRank start, FileRank end) {
		if(get(start) != null) {
			if(get(start).isValidMove(start, end)) {
				Piece movingPiece = get(start);
				
				Board phantomBoard = copyBoard();
				phantomBoard.otherMove(start, end); // this doesn't work bc recursion
				if(phantomBoard.inCheck(movingPiece.getTeam())) {
					Piece.setBoard(this);
					return false;
				}
				Piece.setBoard(this);
				King thisTeamsKing = findKing(movingPiece.getTeam());
				
//				if(underAttack(thisTeamsKing.getLoc(), thisTeamsKing.getTeam())) {
//					/*
//					 * okay so this is flawed because the movingPiece hasn't actually moved yet. so in the pawn ex
//					 * the pawn is at first protecting the king. what we need to do is make a phantom board again to simulate the move
//					 * using the same way it was done in the checkmate algorithm. and then use inCheck on that.
//					 */q2w22223333333333333333333333333333333333333333333333333333333333333w
//					return false;
//				}
				if(movingPiece instanceof King) {
	
			    	if(underAttack(end, movingPiece.getTeam())) { // can't move a king to a place where it'll be check
			    		return false;
			    	}
			//		System.out.println("in moving");
					if(start.getFile()=='e' && start.getRank()==1 && end.getFile()=='g' && end.getRank()==1) {
			//			System.out.println("In case1");
						move(new FileRank('h',1), new FileRank('f',1));
					}
					else if(start.getFile()=='e' && start.getRank()==8 && end.getFile()=='g' && end.getRank()==8) {
			//			System.out.println("In case2");
						move(new FileRank('h',8), new FileRank('f',8));
					}
					else if(start.getFile()=='e' && start.getRank()==1 && end.getFile()=='c' && end.getRank()==1) {
			//			System.out.println("In case3");
						move(new FileRank('a',1), new FileRank('d',1));
					}
					else if(start.getFile()=='e' && start.getRank()==8 && end.getFile()=='c' && end.getRank()==8) {
			//			System.out.println("In case4");
						move(new FileRank('a',8), new FileRank('d',8));
					}
					
				}
				set(start, null);
				if(movingPiece instanceof Pawn) { // This is to check if an en passant occurred
					Pawn movingPawn = (Pawn) movingPiece; // it's special bc en passant is the only move where you end not
					if (movingPawn.getEnPassantLoc() != null) { // on the same space as the piece you capped.
						Pawn target = (Pawn) get(movingPawn.getEnPassantLoc());
						if (target.getTeam() == 'w') {
							wPieces.remove(target);
						} else {
							bPieces.remove(target);
						}
						pawns.remove(target);
						set(movingPawn.getEnPassantLoc(), null);
						movingPawn.setEnPassantLoc(null);
					}
				}
				if(get(end) != null) { // this indicates a piece is getting cap'd
					// implement later
					Piece dyingPiece = get(end);
					if(dyingPiece instanceof Pawn) {
						pawns.remove(dyingPiece);
					}
					if(dyingPiece.getTeam() == 'w') {
						wPieces.remove(dyingPiece);
					} else {
						bPieces.remove(dyingPiece);
					}
				}
				set(end, movingPiece);
				movingPiece.setLoc(end);
				movingPiece.setHasMoved(true);
				updatePieces(movingPiece);
				return true;
			}
			
		}
		return false;
	}
	/**
	 * Detects whether a FileRank is under attack, which means an enemy piece could reach it and capture
	 * a piece if it were occupied.
	 * @param fr The given FileRank
	 * @param team The character representing the team that would be under attack ('b' if black, 'w' if white)
	 * @return  Returns true if any piece from the team opposing the parameterized team
	 * could capture a piece on the parameterized team if it were on the 
	 * given FileRank. False if otherwise.
	 */
	public boolean underAttack(FileRank fr, char team) {
		ArrayList<Piece> enemyTeam;
		Piece dummyPiece = null;
		boolean usingDummy = false;
		if(team == 'w') { // if the team that has to move this turn is in check
			enemyTeam = bPieces;
			dummyPiece = new Pawn('w', 'd');
		} else {
			enemyTeam = wPieces;
			dummyPiece = new Pawn('b', 'd');
		}
		if(get(fr) == null) {
			usingDummy = true;
			dummyPiece.setLoc(fr);
			set(fr, dummyPiece);
		}
		for(int i = 0; i < enemyTeam.size(); i++) {
			Piece enemyPiece = enemyTeam.get(i);
//			if(enemyPiece instanceof Queen) {
//				enemyPiece.getBoard().printBoard();
//			}
			if(enemyPiece.isValidMove(enemyPiece.getLoc(), fr)) {
				if(enemyPiece instanceof Pawn) {
					Pawn enemyPawn = (Pawn) enemyPiece;
					if(enemyPawn.isCapture(enemyPiece.getLoc(), fr)) {
						set(fr, null); // get rid of dummy
						return true;
					}
				} else {
					if(usingDummy)
						set(fr, null); // get rid of dummy
					return true;
				}
			}
		}
		if(usingDummy)
			set(fr, null); // get rid of dum
		return false;
	}
	/**
	 * Detects if the parameterized team in chess
	 * is in check by testing if their king is vulnerable.
	 * @param team ('b' if black, 'w' if white) The team that is being tested for being in check
	 * @return true if the parameterized team is in check, false if not.
	 */
	public boolean inCheck(char team) {
		ArrayList<Piece> enemyTeam;
		Piece king = null;
		if(team == 'w') { // if the team that has to move this turn is in check
			enemyTeam = bPieces;
			for(int i = 0; i < wPieces.size(); i++) {
				if(wPieces.get(i) instanceof King) {
					king = wPieces.get(i);
					break;
				}
			}
		} else {
			enemyTeam = wPieces;
			for(int i = 0; i < bPieces.size(); i++) {
				if(bPieces.get(i) instanceof King) {
					king = bPieces.get(i);
					break;
				}
			}
		}
		for(int i = 0; i < enemyTeam.size(); i++) {
			Piece enemyPiece = enemyTeam.get(i);
//			if(enemyPiece instanceof Queen) {
//				enemyPiece.getBoard().printBoard();
//			}
			if(enemyPiece.isValidMove(enemyPiece.getLoc(), king.getLoc())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method detects whether or not the given team is in checkmate.
	 * @param team ('b' if black, 'w' if white)
	 * @return true if the parameterized team is in checkmate, false if there exists a move where
	 * their king is not in check.
	 */
	public boolean checkForCM(char team) {
		ArrayList<Piece> pieces;
		Piece king = null;
		if(team == 'w') { // if the team that has to move this turn is in check
			pieces = wPieces;
			
		} else {
			pieces = bPieces;
		}
		// first really: pick a piece for which we are generating 
		// First: generate a list of every legal move move
		
		

		ArrayList<Move> everyMove = new ArrayList<Move>();// make a list of every legal move on the real board
		for(int i = 0; i < pieces.size(); i++) { // for every piece
			
			Piece currentPiece = pieces.get(i);
			FileRank start = currentPiece.getLoc();
			
			for(char file: files) { // go through all possible files
				
				for(int rank = 1; rank <= 8; rank++) { // go through all possible ranks
					
					FileRank fr = new FileRank(file, rank);
					
					if(currentPiece.isValidMove(start, fr)) {
						everyMove.add(new Move(start, fr));
					}
				}
			}
			
			// here is where we test each of the moves in the list
			// if there is at least one example where the king on our team is not in check
			// then we can return false

		}
		
		for(Move move: everyMove) {
			Board phantomBoard = new Board();
			phantomBoard.wPieces = new ArrayList<Piece>();
			phantomBoard.bPieces = new ArrayList<Piece>();
			for(int i = 0; i < phantomBoard.board.length; i++) { // copy the real board
				for(int j = 0; j < phantomBoard.board.length; j++) {
					if(board[i][j] == null) {
						phantomBoard.board[i][j] = null;
						continue;
					}
					Piece p;
					p = board[i][j].clone();
					if(p.getTeam() == 'w') {
						phantomBoard.wPieces.add(p);
					} else {
						phantomBoard.bPieces.add(p);
					}
//					if(p instanceof Queen) {
//						System.out.println("queen");
//					}
					p.setLoc(new FileRank(p.getLoc().file, p.getLoc().rank));
					phantomBoard.board[i][j] = p;
				}
			}
			phantomBoard.move(move.start, move.end);
	//		System.out.println("PHAN Checking for checkmate on this Piece: " + phantomBoard.get(move.start));
	//		System.out.println();
//			if(move.start.getFile() == 'e' && move.start.getRank() == 8 && move.end.getFile() == 'f' && move.end.getRank() == 7) {
//				System.out.println("Debug place");
//			}
			if(!phantomBoard.inCheck(team)) {
		//		System.out.println("Phantom BOard");
		//		phantomBoard.printBoard();
				Piece.setBoard(this);
				return false;
			}
			
		}

		Piece.setBoard(this);
		return true;
	}
	
	/**
	 * If it is the turn after a pawn moved 2 squares on the other team, en passant can
	 * be performed.  This means, at the end of every turn, we must set every pawn's
	 * justMoved2Squares to false, unless the piece that just moved is a pawn that moved
	 * two squares.
	 * 
	 */
	private void updatePieces(Piece justMoved) {
		for(int i = 0; i < pawns.size(); i++) {
			if(pawns.get(i) != justMoved) {
				pawns.get(i).setJustMoved2Squares(false);
			}
		}
	}
	
	private Board copyBoard() {
		Board phantomBoard = new Board();
		phantomBoard.wPieces = new ArrayList<Piece>();
		phantomBoard.bPieces = new ArrayList<Piece>();
		for(int i = 0; i < phantomBoard.board.length; i++) { // copy the real board
			for(int j = 0; j < phantomBoard.board.length; j++) {
				if(board[i][j] == null) {
					phantomBoard.board[i][j] = null;
					continue;
				}
				Piece p;
				p = board[i][j].clone();
				if(p.getTeam() == 'w') {
					phantomBoard.wPieces.add(p);
				} else {
					phantomBoard.bPieces.add(p);
				}
//				if(p instanceof Queen) {
//					System.out.println("queen");
//				}
				p.setLoc(new FileRank(p.getLoc().file, p.getLoc().rank));
				phantomBoard.board[i][j] = p;
			}
		}
		return phantomBoard;
	}
	/**
	 * Prints the chessboard in the required format
	 */
	public void printBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					if((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
						System.out.print("  ");
					} else {
						System.out.print("##");
					}
				} else {
					System.out.print(board[i][j].name());
				}
				System.out.print(" ");
			}
			System.out.println(" " + (8 - i));
		}
		for(int i = 0; i < 8; i++) {
			System.out.print(" " + files[i] + " ");
		}
		System.out.println("\n");
	}
	private void clearBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}
}
