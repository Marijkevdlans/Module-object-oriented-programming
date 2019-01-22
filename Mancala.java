package nl.sogyo.mancala;

public class Mancala {
	// Instance variable(s)
	private Pit startBowl;
		
	// Constructor(s)
	public Mancala() {
		startBowl = new Bowl(0);
	}
	
	// Method(s)
	public Pit getStartBowl() {
		return startBowl;
	}
		
	public static void main( String[] args ) {
     Mancala facade = new Mancala();
    }
}
