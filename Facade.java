package nl.sogyo.mancala;
public class Facade {
	// Instance variable(s)
	private Pit startBowl;
		
	// Constructor(s)
	public Facade() {
		startBowl = new Bowl(1);
	}
	
	// Method(s)
	public Pit getStartBowl() {
		return startBowl;
	}
	
	public int[] processPitChoiceFromGUI(int bowlChoice) {
		int[] gameStatus = new int[16];
		
		if (bowlChoice > 0 && bowlChoice < 15) {
			gameStatus[15] = startBowl.processPitChoice(bowlChoice);
			// gameStatus[15] = 1 -> valid choice, not end game
			// gameStatus[15] = 2 -> valid choice, end game
			// gameStatus[15] = 3 -> invalid bowl choice
		}
		for (int i = 1; i <= 14; i++) {
			gameStatus[(i-1)] = startBowl.navigateToPit(i).stoneCount;
		}
		if (startBowl.player.hasTurn() == true) {
			gameStatus[14] = 0;
			// player 1 aan de beurt, player 2 geweest
		}
		else if (startBowl.player.hasTurn() == false) {
			gameStatus[14] = 1;
			// player 2 aan de beurt, player 1 geweest
		}
		return gameStatus;
	}
}
