import java.util.ArrayList;

public class ChessGameBoard {	
	ProposedBoard proposedBoard; // game board with next moves including collisions	
	ProposedBoard black; // game board with next moves including collisions	
	ProposedBoard white; // game board with next moves including collisions	
	
	private Piece[][] board; // a board of chess pieces
	private int ID=100; // used to create a unique ID value for each piece
	
	Game game;
	
	enum Game {
	    RANDOM,
	    BATTLEROYALE
	}
	
	// generate unique IDs
	private int getNewID() {
		ID++;
		return ID;
	}
	
	public ChessGameBoard() {
		// create a board of chess pieces
		board = new Piece[8][8];
		
		//add black pawns
		for(int i=0;i<8;++i) {
			board[i][1] = new Pawn(Piece.Team.BLACK,getNewID(),1,i);
		}
		//add the other pieces
		board[0][0] = new Piece(Piece.Team.BLACK, Piece.Type.ROOK,getNewID());
		board[7][0] = new Piece(Piece.Team.BLACK, Piece.Type.ROOK,getNewID());
		board[1][0] = new Piece(Piece.Team.BLACK, Piece.Type.KNIGHT,getNewID());
		board[6][0] = new Piece(Piece.Team.BLACK, Piece.Type.KNIGHT,getNewID());
		board[2][0] = new Piece(Piece.Team.BLACK, Piece.Type.BISHOP,getNewID());
		board[5][0] = new Piece(Piece.Team.BLACK, Piece.Type.BISHOP,getNewID());
		board[3][0] = new Piece(Piece.Team.BLACK, Piece.Type.QUEEN,getNewID());
		board[4][0] = new Piece(Piece.Team.BLACK, Piece.Type.KING,getNewID());

		//add white pawns
		for(int i=0;i<8;++i) {
			board[i][6] = new Pawn(Piece.Team.WHITE,getNewID(),6,i);
		}
		//add the other pieces
		board[0][7] = new Piece(Piece.Team.WHITE, Piece.Type.ROOK,getNewID());
		board[7][7] = new Piece(Piece.Team.WHITE, Piece.Type.ROOK,getNewID());
		board[1][7] = new Piece(Piece.Team.WHITE, Piece.Type.KNIGHT,getNewID());
		board[6][7] = new Piece(Piece.Team.WHITE, Piece.Type.KNIGHT,getNewID());
		board[2][7] = new Piece(Piece.Team.WHITE, Piece.Type.BISHOP,getNewID());
		board[5][7] = new Piece(Piece.Team.WHITE, Piece.Type.BISHOP,getNewID());
		board[3][7] = new Piece(Piece.Team.WHITE, Piece.Type.QUEEN,getNewID());
		board[4][7] = new Piece(Piece.Team.WHITE, Piece.Type.KING,getNewID());

		proposedBoard = new ProposedBoard();		
		game = Game.BATTLEROYALE;		
		
	}
	
	// print the current Board
	public String printCurrentBoard() {
		String line = "";
		String row = "";
		for(int k=0;k<8;k++) {
			row+="---";
		}
		row+="-\n";
		for(int i=0;i<8;++i) {
			line = "|";
			for(int j=0;j<8;++j) {
				if (board[i][j] == null) { 
					line += "  |";
				} else {
					if (board[i][j].getTeam() == Piece.Team.BLACK) {
						line += "B";
					} else {
						line += "W";
					}
					if (board[i][j].getType() == Piece.Type.PAWN) {
						line += "P";
					} else if (board[i][j].getType() == Piece.Type.ROOK) {
						line += "R";
					} else if (board[i][j].getType() == Piece.Type.KNIGHT) {
						line += "K";
					} else if (board[i][j].getType() == Piece.Type.BISHOP) {
						line += "B";
					} else if (board[i][j].getType() == Piece.Type.QUEEN) {
						line += "Q";
					} else if (board[i][j].getType() == Piece.Type.KING) {
						line += "+";
					} 
					line+= "|";
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
		board[y][x] = p;
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
	
	
	
	
	public ChessGameBoard(Game g) {
	//	board = new Board();
		proposedBoard = new ProposedBoard();
		game = g;
	}	
	
	private ProposedBoard buildProposedBoard(Piece.Team t) {
		ProposedBoard teamBoard = new ProposedBoard();
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				Piece p = board[i][j];
				if (p != null && p.getTeam() == t) {
					if(p.getType() == Piece.Type.PAWN) {
						Pawn pawn = (Pawn) p;
						pawn.setNextPosition(this);
						teamBoard.add(pawn.getNextPosition().getX(),pawn.getNextPosition().getY(),p);
					}

					/*if(p.getType() == Piece.Type.ROOK){
						Rook rook = (Rook) p;
						rook.setNextPosition(this);
						teamBoard.add(rook.getNextPosition().getX(), rook.getNextPosition().getY(), p);
					}*/
					
				}
			}
		
		}
		return teamBoard;
	}

	private ProposedBoard nextMoveTeam(Piece.Team t) {
		ProposedBoard teamBoard = buildProposedBoard(t);
		while(checkConflicts(teamBoard)) {
			//resolveConflicts();
		}
		return teamBoard;
	
	}
	
	private ProposedBoard nextMoveRandomTeam(Piece.Team t) {		
		ProposedBoard teamBoard = buildProposedBoard(t);
		while(checkConflicts(teamBoard)) {
			resolveConflicts(teamBoard);
		}	
		return teamBoard;		
	}
	
	private boolean checkConflicts(ProposedBoard teamBoard) {
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				if(teamBoard.countPieces(i, j) > 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void resolveConflicts(ProposedBoard teamBoard) {
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				if(teamBoard.countPieces(j,i) > 1) {
					// collision has occurred, so process	
					
					// make a utility array to collect piece list 
					ArrayList<Piece> movers = new ArrayList<Piece>();  // pieces that moved to space
					Piece sitter = null; //piece that was in the space
					System.out.print("Collision on ("+ j + "," + i + ")!!");
					
					for(Piece t:teamBoard.getPieces(j,i)) {
						//check if player has moved
						System.out.print(t.getID() + ":");
						if ((getPositionByID(t.getID()).getX() != j) ||
								(getPositionByID(t.getID()).getY() != i)) {
							// then add them to the list
							System.out.print(" (moved) ");
							movers.add(t);
					} else {
							System.out.print(" (stayed) ");
							sitter = new Piece(t.getTeam(),t.getType(),t.getID());
						}
					}					
					System.out.println();
					teamBoard.clear(j,i);

					// if more than one person moved to the spot then send them all back
					if (movers.size() > 1) {
						// put everyone back to their previous positions
						for(Piece p : movers) {
							teamBoard.add(getPositionByID(p.getID()).getX(),getPositionByID(p.getID()).getY(),p);
						//	System.out.print(p.getID()+":");
							System.out.println(p.getID() + " sent back");
						}
						// leave any players in place
						if (sitter != null) 
							teamBoard.add(j,i,sitter);
					} else {
						teamBoard.add(j, i,movers.get(0));
						System.out.println(movers.get(0).getID() + " killed " + sitter.getID());
					}
					movers.clear();
					//System.out.println(" resolved");
				}
			}
		}		
	}
	
	private void combineBoards(ProposedBoard a, ProposedBoard b) {
		proposedBoard.clearAll();
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				for(Piece p: a.getPieces(i,j)) {
					proposedBoard.add(i, j, p);
				}
				for(Piece p: b.getPieces(i,j)) {
					proposedBoard.add(i, j, p);
				}
			}
		}
	}
	
	//moves all valid moves for players on both teams
	public void nextMoveAll() {
		black = nextMoveTeam(Piece.Team.BLACK);
		while(checkConflicts(black)) {
			resolveConflicts(black);
		}
		white = nextMoveTeam(Piece.Team.WHITE);
		while(checkConflicts(white)) {
			resolveConflicts(white);
		}
		
		combineBoards(black, white);
		black.clearAll();
		white.clearAll();

		while(checkConflicts(proposedBoard)) {
			resolveConflicts(proposedBoard);
		}
		
		//combine proposed boards
		//check and resolve conflicts
	}

	//moves one random move for each team 
	public void nextMoveRandom() {
		black = nextMoveRandomTeam(Piece.Team.BLACK);
		white = nextMoveRandomTeam(Piece.Team.WHITE);
		
		combineBoards(black, white);
		black.clearAll();
		white.clearAll();
		
		while(checkConflicts(proposedBoard)) {
			resolveConflicts(proposedBoard);
		}
		
	}	
	

	
	public void applyNextMoves() {
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				if(proposedBoard.getPieces(j,i).size() > 0) {
					Pawn p = (Pawn) proposedBoard.getPieces(j,i).get(0); // @mrH can you explain this it's rather cryptic
					p.setX(j);
					p.setY(i);
					board[i][j] = p;
					/*Rook r = (Rook) proposedBoard.getPieces(j, i).get(0);
					r.setX(j);
					r.setY(i);
					board[i][j] = r;*/
				} else {
					board[i][j] = null;
				}
			}
		}
		
	}
	
	public String printProposedBoard() {
		return proposedBoard.toString();
	}
	
	public String toString() {
		
		return "Current Board: \n\n" + printCurrentBoard() + " \n\nProposed Moves: \n\n" + proposedBoard.toString();
	}


}
