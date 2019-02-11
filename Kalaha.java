package nl.sogyo.mancala;

public class Kalaha extends Pit {
	// Constructor(s)
	public Kalaha(int instanceCount, Player aPlayer, Bowl startBowl) {
		player = aPlayer;
		stoneCount = 0;
		nextPit = new Bowl(++instanceCount, player.getOpponent(), startBowl);
	}
	
	public Kalaha(Player aPlayer, Bowl startBowl) {
		player = aPlayer;
		stoneCount = 0;
		nextPit = startBowl;
	}
	
	// Method(s)
	public void passStones(int nStones, Player playerBowlChosen) {
		if (playerBowlChosen != this.player) {
			nextPit.passStones(nStones, playerBowlChosen);
		}
		else if (playerBowlChosen == player && nStones > 1) {
			stoneCount++;
			nextPit.passStones(--nStones, playerBowlChosen);
		}
		else if (playerBowlChosen == player && nStones == 1) {
			stoneCount++;
		}
	}
	
	public int stealStones(int numberOfStepsToBowltoStealFrom) {
		return nextPit.stealStones(--numberOfStepsToBowltoStealFrom);
	}
	
	public void endGame(int sumStonesBowls) {
		stoneCount += sumStonesBowls;
	}
	
}
