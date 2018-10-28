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
	ArrayList<Piece> wPieces = new ArrayList<Piece>();
	ArrayList<Piece> bPieces = new ArrayList<Piece>();
	ArrayList<Pawn> pawns = new ArrayList<Pawn> ();
	public Piece[][] board = new Piece[8][8]; // TODO CHANGE BACK TO PACKAGE
	private char files[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	private int turn; // XXX may delete this field and its relating methods
	
	static class Move {
		FileRank start;
		FileRank end;
		public Move(FileRank start, FileRank end) {
			this.start = start;
			this.end = end;
		}
	}
	
	/**
	 * Instantiates Board object filling it with chess pieces.
	 * turn num
	 */
	public Board() {
		populateBoard();
		turn = 0;
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
	public int getTurn() {
		return turn;
	}
	/**
	 * Gets the chess piece at the given FileRank location
	 * @param fr
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
	 * @param start
	 * @param end
	 */
	public void put(FileRank start, FileRank end) {
		Piece movingPiece = get(start);
		set(end, movingPiece);
		set(start, null);
		movingPiece.setLoc(end);
	}
	/**
	 * Moves the piece from start to the end, capturing the piece at
	 * FileRank end.
	 * @param start
	 * @param end
	 */
	public boolean move(FileRank start, FileRank end) {
		if(get(start) != null) {
			if(get(start).isValidMove(start, end)) {
				Piece movingPiece = get(start);
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
			if(enemyPiece.isValidMove(enemyPiece.getLoc(), king.getLoc())) {
				return true;
			}
		}
		return false;
	}
	
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
		
		

		ArrayList<Move> everyMove = new ArrayList<Move>(); 									// make a list of every possible move
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
			for(int i = 0; i < phantomBoard.board.length; i++) { // copy the real board
				for(int j = 0; j < phantomBoard.board.length; j++) {
					Piece p = board[i][j].clone();
					p.setLoc(new FileRank(p.getLoc().file, p.getLoc().rank));
					phantomBoard.board[i][j] = p;
				}
			}
			phantomBoard.move(move.start, move.end);
			if(!inCheck(team)) {
				return false;
			}
			
		}

		
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
	/**
	 * This is just a method I created for debugging purposes, ignore.
	 * Should be deleted later.
	 */
	public void clearBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}
}
