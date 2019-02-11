package nl.sogyo.mancala;

public class Player {
	// Instance variable(s)
	private boolean turn = true;
	private Player opponent;
	
	// Constructor(s)
	public Player() {
		opponent = new Player(this);
	}
	
	public Player(Player theOpponent) {
		turn = false;
		opponent = theOpponent;
	}
			
	// Method(s)
	public boolean hasTurn() {
		return turn;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	// 
	public void changeTurn() {
		opponent.turn = turn;
		turn = !turn;
	}
}
