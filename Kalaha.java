package nl.sogyo.mancala;

public class Kalaha extends Pit {
	// Constructor(s)
	public Kalaha(int instanceCount, Player aPlayer, Bowl startBowl) {
		player = aPlayer;
		stoneCount = 0;
		nextBowl = new Bowl(++instanceCount, player.getOpponent(), startBowl);
	}
	
	public Kalaha(Player aPlayer, Bowl startBowl) {
		player = aPlayer;
		stoneCount = 0;
		nextBowl = startBowl;
	}
	
	// Method(s)
	public void passStones(int nStones, Player playerBowlChosen) {
		if (playerBowlChosen != this.player) {
			nextBowl.passStones(nStones, playerBowlChosen);
		}
		else if (playerBowlChosen == player && nStones > 1) {
			stoneCount++;
			nextBowl.passStones((nStones - 1), playerBowlChosen);
		}
		else if (playerBowlChosen == player && nStones == 1) {
			stoneCount++;
			checkIfEndOfGame();
		}
	}
}
