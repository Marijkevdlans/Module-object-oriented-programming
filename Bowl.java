package nl.sogyo.mancala;

public class Bowl extends Pit {
	// Constructor(s)
	public Bowl(int instanceCount) {
		player = new Player();
		nextBowl = new Bowl(++instanceCount, player, this);
	}
	
	public Bowl(int instanceCount, Player aPlayer, Bowl startBowl) {
		if (instanceCount == 5) {
			player = aPlayer;
			nextBowl = new Kalaha(++instanceCount, aPlayer, startBowl);
		}
		else if (instanceCount == 12) {
			player = aPlayer;
			nextBowl = new Kalaha(aPlayer, startBowl);
		}
		else if (instanceCount < 12) {
			player = aPlayer;
			nextBowl = new Bowl(++instanceCount, aPlayer, startBowl);
		}
	}
		
	// Method(s)
	public void passStones(int nStones, Player playerBowlChosen) {
		if (nStones > 1) {
			stoneCount++;
			nextBowl.passStones((nStones - 1), playerBowlChosen);
		}
		else if (nStones == 1) {
			chooseScenario(playerBowlChosen);
		}
	}
	
	public void chooseScenario(Player playerBowlChosen) {
		if (stoneCount > 0) {
			stoneCount++;
			player.changeTurn();
			checkIfEndOfGame();
		}
		else if (stoneCount == 0 && player == playerBowlChosen) {
			stoneCount++;
			steal();
			player.changeTurn();
			checkIfEndOfGame();
		}
	}
	
	public void steal() {
		int stepCount = 0;
		while (nextBowl instanceof Bowl) {
			stepCount++;
			nextBowl = nextBowl.nextBowl;
		}
		stoneCount = stoneCount + stealStones(this, (stepCount * 2) + 1);
		passStones(stoneCount);
	}
		
	public int stealStones(Pit aPit, int stepCountTimes2) {
		if (stepCountTimes2 > 0) {
			stealStones(nextBowl, stepCountTimes2--);
		}
		return aPit.stoneCount;
	}
}
