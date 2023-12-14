
public class ChessGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessGameBoard b = new ChessGameBoard();

		for(int i=0; i< 20; i++) {
			System.out.println("BOARD MOVE " + i);
			System.out.print(b.printCurrentBoard());
			b.nextMoveAll();		
			System.out.println("NEXT MOVES");
			System.out.print(b.printProposedBoard());
			b.applyNextMoves();
		}
							
	}
	
}
