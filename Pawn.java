
// This is inheritance. The current unit we are looking at, Unit 9.
// The Pawn is a Piece and is allowed to use all of the attibutes, constructors and methods of its parent

public class Pawn extends Piece {
	
	private boolean[][] possibleMoves = new boolean[8][8]; // 2D array of booleans indicating valid moves
	private Position nextPosition;	// the next move, as decided by your code in setNextPosition
	
	private int x;
	private int y;
	
	// constructor for a Pawn, which calls the Piece constructor using "super"
	public Pawn(Team c, int id, int x, int y) {
		super(c,Piece.Type.PAWN,id);
		this.x = x;
		this.y = y;		
		for(int i=0; i<8; ++i) {
			for(int j=0; j<8; ++j) {
				possibleMoves[i][j] = false;
			}
		}
		nextPosition = new Position(x,y);	
	}
	
	
	// utility method to output the possibleMoves 2D Array
	private void printPossibleMoves() {
		for(int i=0; i<8; ++i) {
			System.out.print("|");
			for(int j=0; j<8; ++j) {
				if(possibleMoves[j][i]) System.out.print("T|");
				else System.out.print("F|");
			}
			System.out.println();
		}
		
	}
	
	
	// overridden method. Makes a 2D array of booleans. False is no move is possible. True if possible to move there. 
	// I have coded the possible moves for a pawn.
	public boolean[][] getPossibleMoves(ChessGameBoard b) {
		int x = myX();
		int y = myY();
		//System.out.println("x:" + x + " y:"+y + " "+ getTeam());
		if(getTeam()==Team.BLACK && x<7) { 
			//if space in front is open
			if(b.getPiece(x+1,y)==null) {
				//System.out.println("x+1");
				possibleMoves[y][x+1] = true;
				//if in starting position and two spaces in front are open
				if(x==1 && b.getPiece(x+2,y)==null) {
					//System.out.println("x+2");
					possibleMoves[y][x+2] = true;			
				}
			} else //System.out.println(""+ b.getPiece(x+1,y).getID()) ;
			
			//if a diagonal kill is possible
			if((y>1 && b.getPiece(x+1,y-1)!=null && !isTeamate(b.getPiece(x+1,y-1)))) {
				possibleMoves[y-1][x+1] = true;	
			}
			//if a diagonal kill is possible
			if((y<7 && b.getPiece(x+1,y+1)!=null && !isTeamate(b.getPiece(x+1,y+1)))) {
				possibleMoves[y+1][x+1] = true;			
			}			
		}
		
		if(getTeam()==Team.WHITE && x>1) {
			//if space in front is open
			if(b.getPiece(x-1,y)==null) {
				possibleMoves[y][x-1] = true;
				//if in starting position and two spaces in front are open
				if(x==6 && b.getPiece(x-2,y)== null) {
					possibleMoves[y][x-2] = true;			
				}
			}
			//if a diagonal kill is possible
			if((y>1 && b.getPiece(x-1,y-1)!= null && !isTeamate(b.getPiece(x-1,y-1)))) {
				possibleMoves[y-1][x-1] = true;			
			}
			//if a diagonal kill is possible
			if((y<7 && b.getPiece(x-1,y+1)!= null && !isTeamate(b.getPiece(x-1,y+1)))) {
				possibleMoves[y+1][x-1] = true;			
			}			
		}
		
		//printPossibleMoves();
		//System.out.println();
		return possibleMoves;
	}
	
	// you need to code this based on the results of possibleMoves()
	public void setNextPosition(ChessGameBoard b) {
		
		// update the 2D array of possible moves
		possibleMoves = getPossibleMoves(b);
	
		int x = myX();
		int y = myY();
		int random_x = (int) (Math.random()*8);
		int random_y = (int) (Math.random()*8);
		int count = 10;
		//System.out.println("x:" + random_x + "y:" + random_y);
		while (!possibleMoves[random_y][random_x] && count > 0) {
			random_x = (int) (Math.random()*8);
			random_y = (int) (Math.random()*8);			
			//System.out.println("x:" + random_x + "y:" + random_y);
			count--;
		}
		if (count > 0) {
			x = random_x;
			y = random_y;
		}
		// set the next position
		nextPosition.setPosition(x,y);
	}
	
	public int myX() {
		return x;
	}
	
	public int myY() {
		return y;
	}	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}	
		
	
	
	
	public Position getPosition() {
		return new Position(x,y);
	}
	
	public Position getNextPosition() {
		return nextPosition;
	}	
	
	public boolean isStayingPut() {
		return getPosition() == nextPosition; 
	}
	

}
