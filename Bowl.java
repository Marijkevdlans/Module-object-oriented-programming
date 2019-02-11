package nl.sogyo.mancala;

public class Bowl extends Pit {
	// Constructor(s)
	public Bowl(int instanceCount) {
		player = new Player();
		nextPit = new Bowl(++instanceCount, player, this);
	}
	
	public Bowl(int instanceCount, Player aPlayer, Bowl startBowl) {
		if (instanceCount == 6) {
			player = aPlayer;
			nextPit = new Kalaha(++instanceCount, aPlayer, startBowl);
		}
		else if (instanceCount == 13) {
			player = aPlayer;
			nextPit = new Kalaha(aPlayer, startBowl);
		}
		else if (instanceCount < 13) {
			player = aPlayer;
			nextPit = new Bowl(++instanceCount, aPlayer, startBowl);
		}
	}
		
	// Method(s)
	public void passStones(int nStones, Player playerOfPitChosen) {
		if (nStones > 1) {
			stoneCount++;
			nextPit.passStones(--nStones, playerOfPitChosen);
		}
		else if (nStones == 1) {
			chooseScenario(playerOfPitChosen);
		}
	}
	
	public void chooseScenario(Player playerOfPitChosen) {
		if (stoneCount > 0) {
			stoneCount++;
			player.changeTurn();
		}
		else if (stoneCount == 0 && player != playerOfPitChosen) {
			stoneCount++;
			player.changeTurn();
		}
		else if (stoneCount == 0 && player == playerOfPitChosen) {
			steal();
			player.changeTurn();
		}	
	}
	
	public void steal() {
		int nStonesToSteal = stealStones((findNumberOfStepsToKalaha(1) * 2)) + 1;
		findKalahaPlayerTurnIs(true).stoneCount += nStonesToSteal;
	}
	
	public int findNumberOfStepsToKalaha(int numberOfStepsToKalaha) {
		if (nextPit instanceof Bowl) {
			Bowl nextBowl = (Bowl) nextPit;
			return nextBowl.findNumberOfStepsToKalaha(++numberOfStepsToKalaha);
		}
		else {
			return numberOfStepsToKalaha;
		}
	}
	
	public int stealStones(int numberOfStepsToBowltoStealFrom) {
		if (numberOfStepsToBowltoStealFrom > 0) {
			return nextPit.stealStones(--numberOfStepsToBowltoStealFrom);
		}
		else {
			int nStonesToSteal = stoneCount;
			stoneCount = 0;
			return nStonesToSteal;
		}
	}
	
	public void endGame(int sumStonesBowls) {
		sumStonesBowls += stoneCount;
		stoneCount = 0;
		nextPit.endGame(sumStonesBowls);
	}
	
}
