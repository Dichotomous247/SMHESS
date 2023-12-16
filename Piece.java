public class Piece {
	
	private Type type;  // PAWN, ROOK, etc...
	private Team colour; // BLACK, WHITE
	private int id;	// id value of piece
	private int kills; // stat for kills, updated by GameBoard
	private int moves; // stat for moves, updated by GameBoard
//	private Position position; //2 int array holding x and y coordinates

	// we can use enum instead of strings or ints to hold categories of items
	enum Team {
	    BLACK,
	    WHITE
	}
	
	enum Type {
		PAWN,
		ROOK,
		KNIGHT,
		BISHOP,
		QUEEN,
		KING
	  }
	
	// constructor needs colour, type, ID and coordinates
	
	
	public Piece(Team c, Type t, int id) {
		colour = c;
		type = t;
		this.id = id;
		kills = 0;
		moves = 0;
//		position = new Position(x,y);
	}
	
	public Type getType() {
		return type;
	}
	
	public Team getTeam() {
		return colour;
	}
	
	public int getID() {
		return id;
	}

	public int getKills() {
		return kills;
	}

	public int getMoves() {
		return moves;
	}
	
	public boolean isMe(Piece p) {
		return getID() == p.getID();
	}
	
	public boolean isTeamate(Piece p) {
		return getTeam() == p.getTeam();
	}
	
	public void setPiece(int x, int y, Piece p) {
		// override setPiece to hide it.
	}	
	
	protected boolean[][] getPossibleMoves(ChessGameBoard b) {
		// this implementation is overridden in subclasses
		boolean[][] moves = new boolean[8][8];
		return moves;
	}	
	
	
}
