public class Board extends ChessGameBoard {
	
	// this object will hold the Board information for the ChessGame Object. This Board will be shared with individual
	// pieces so that they can decide on their next moves.

	private Piece[][] board; // a board of chess pieces
	private int ID=100; // used to create a unique ID value for each piece
	
	// generate unique IDs
	private int getNewID() {
		ID++;
		return ID;
	}
	
	public Board() {
		// create a board of chess pieces
		board = new Piece[8][8];
		
		//add black pawns
		for(int i=0;i<8;++i) {
			board[1][i] = new Pawn(Piece.Team.BLACK,getNewID(),i,1);
		}
		//add the other pieces -- commented out x, y positions because they are misaligned with constructor. change if they were important but became obsolete
		board[0][0] = new Piece(Piece.Team.BLACK, Piece.Type.ROOK,getNewID());//,0,0);
		board[0][7] = new Piece(Piece.Team.BLACK, Piece.Type.ROOK,getNewID());//,7,0);
		board[0][1] = new Piece(Piece.Team.BLACK, Piece.Type.KNIGHT,getNewID());//,1,0);
		board[0][6] = new Piece(Piece.Team.BLACK, Piece.Type.KNIGHT,getNewID());//,6,0);
		board[0][2] = new Piece(Piece.Team.BLACK, Piece.Type.BISHOP,getNewID());//,2,0);
		board[0][5] = new Piece(Piece.Team.BLACK, Piece.Type.BISHOP,getNewID());//,5,0);
		board[0][3] = new Piece(Piece.Team.BLACK, Piece.Type.QUEEN,getNewID());//,3,0);
		board[0][4] = new Piece(Piece.Team.BLACK, Piece.Type.KING,getNewID());//,4,0);

		//add white pawns
		for(int i=0;i<8;++i) {
			board[6][i] = new Pawn(Piece.Team.WHITE,getNewID(),i,6);
		}
		//add the other pieces
		board[7][0] = new Piece(Piece.Team.WHITE, Piece.Type.ROOK,getNewID());//,0,7);
		board[7][7] = new Piece(Piece.Team.WHITE, Piece.Type.ROOK,getNewID());//,7,7);
		board[7][1] = new Piece(Piece.Team.WHITE, Piece.Type.KNIGHT,getNewID());//,1,7);
		board[7][6] = new Piece(Piece.Team.WHITE, Piece.Type.KNIGHT,getNewID());//,6,7);
		board[7][2] = new Piece(Piece.Team.WHITE, Piece.Type.BISHOP,getNewID());//,2,7);
		board[7][5] = new Piece(Piece.Team.WHITE, Piece.Type.BISHOP,getNewID());//,5,7);
		board[7][3] = new Piece(Piece.Team.WHITE, Piece.Type.QUEEN,getNewID());//,3,7);
		board[7][4] = new Piece(Piece.Team.WHITE, Piece.Type.KING,getNewID());//,4,7);

	}
	
	// print the current Board
	public String toString() {
		String line = "";
		String row = "";
		for(int k=0;k<8;k++) {
			row+="---";
		}
		row+="-\n";
		for(int i=0;i<8;++i) {
			line = "|";
			for(int j=0;j<8;++j) {
				if (board[j][i] == null) { 
					line += "  |";
				} else {
					if (board[j][i].getTeam() == Piece.Team.BLACK) {
						line += "B";
					} else {
						line += "W";
					}
					if (board[j][i].getType() == Piece.Type.PAWN) {
						line += "P";
					} else if (board[j][i].getType() == Piece.Type.ROOK) {
						line += "R";
					} else if (board[j][i].getType() == Piece.Type.KNIGHT) {
						line += "K";
					} else if (board[j][i].getType() == Piece.Type.BISHOP) {
						line += "B";
					} else if (board[j][i].getType() == Piece.Type.QUEEN) {
						line += "Q";
					} else if (board[j][i].getType() == Piece.Type.KING) {
						line += "+";
					} 
					line+="|";
				}
			}
			row+=line+"\n";
			for(int k=0;k<8;k++) {
				row+="---";
			}
			row+="-\n";
		}
		return row;
		
	}
	
	//returns the chess piece at those coordinates
	public Piece getPiece(int x, int y) {
		return board[y][x];
	}
	
	public void setPiece(int x, int y, Piece p) {
		// this is a holder for subclass
	}
	
	public Position getPositionByID(int ID) {
		for(int y=0;y<8;++y) {
			for(int x=0;x<8;++x) {		
				if ((board[y][x]!= null) && (board[y][x].getID() == ID)) {
					return new Position(x,y);
				}
			}	
		}
		return null;
	}
	
	
}
