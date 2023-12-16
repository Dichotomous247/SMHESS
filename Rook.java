public class Rook extends Piece {
    private boolean[][] possibleMoves = new boolean[8][8];
    private Position nextPosition;

    private int x;
    private int y;

    public Rook(Team team, int id, int x, int y){
        super(team, Piece.Type.ROOK, id);
        this.x = x;
        this.y = y;

        // initialize every value as an impossible move, we will change this later in the code
        for(int i=0; i<8; ++i) {
            for(int j=0; j<8; ++j) {
                possibleMoves[i][j] = false;
            }
        }

        nextPosition = new Position(x, y); // default nextposition
    }

    @Override public boolean[][] getPossibleMoves(ChessGameBoard b){

        checkMovesInDirection(b, 1, 0); // right
        checkMovesInDirection(b, -1, 0); // left
        checkMovesInDirection(b, 0, 1); // up
        checkMovesInDirection(b, 0, -1); // down


        printPossibleMoves();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
        return  possibleMoves;

    }

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

    public void setNextPosition(ChessGameBoard b){
        possibleMoves = getPossibleMoves(b);

        loop:
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(possibleMoves[i][j]){
                    nextPosition.setPosition(i, j);
                    break loop;
                }
            }
        }
    }

    void checkMovesInDirection(ChessGameBoard b, int xDir, int yDir){
        int i = myX() + xDir;
        int j = myY() + yDir;

        while(i >= 0 && i < 8 && j >= 0 && j < 8){
            if(b.getPiece(i, j) == null){
                possibleMoves[i][j] = true; // this square is empty
            } else if(!b.getPiece(i, j).isTeamate(this)){ // if this spot has a piece that is not a teammate, we can move there, but no further
                possibleMoves[i][j] = true;
                break;
            } else {
                // this spot has a piece and it is a teammate, break out
                break;
            }

            i += xDir;
            j += yDir;
        }
    }

    public int myX() { return this.x; }
    public int myY() { return this.y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public Position getPosition() { return new Position(x, y); }
    public Position getNextPosition() { return nextPosition; }
}
