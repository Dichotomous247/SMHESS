import java.util.ArrayList;

public class ProposedBoard {
	
	// this object will hold the next move board information for the ChessGame Object. This Board will NOT be shared
	// it will be used to resolve collisions and undo moves

	
	private ArrayList[][] proposedBoard;
	
	public ProposedBoard() {
		// create a board of chess pieces
		proposedBoard = new ArrayList[8][8];		
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				proposedBoard[j][i] = new ArrayList<Piece>();
			}
		}
	}
	
	public void clearAll() {
		// empties the board
		for(int i=0;i<8;++i) {
			for(int j=0;j<8;++j) {
				proposedBoard[j][i].clear();
			}
		}
	}	
	
	public void clear(int x, int y) {
		// empties the location;
		proposedBoard[y][x].clear();
	}	
	
	public String printAll(ArrayList<Piece> pieces) {
		String s = "";
		for (Piece p : pieces) {
			if (p.getTeam() == Piece.Team.BLACK) {
				s += "B";
			} else {
				s += "W";
			}
			if (p.getType() == Piece.Type.PAWN) {
				s += "P";
			} else if (p.getType() == Piece.Type.ROOK) {
				s += "R";
			} else if (p.getType() == Piece.Type.KNIGHT) {
				s += "K";
			} else if (p.getType() == Piece.Type.BISHOP) {
				s += "B";
			} else if (p.getType() == Piece.Type.QUEEN) {
				s += "Q";
			} else if (p.getType() == Piece.Type.KING) {
				s += "+";
			} 
			
			s+=p.getID();
			s+=".";
		}

		return s;
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
				if (proposedBoard[i][j].size() == 0) { 
					line += "  |";
				} else {
					line += printAll(proposedBoard[i][j]);
					line = line.substring(0,line.length()-1) + "|";
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
	public ArrayList<Piece> getPieces(int x, int y) {
		return proposedBoard[y][x];
	}
	
	public int countPieces(int x, int y) {
		return proposedBoard[y][x].size();
	}

	public void add (int x, int y, Piece p) {
		proposedBoard[y][x].add(p);
	}	
	
}
