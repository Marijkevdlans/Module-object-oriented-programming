package nl.sogyo.mancala;

public abstract class Pit {
	// Instance variable(s)
	protected int stoneCount = 4;
	protected Player player;
	protected Pit nextBowl;

	// Method(s)
	public void processBowlChoice(int bowlChoicePlayer) {
		Pit bowlChosen = goToBowlPlayerChoice(bowlChoicePlayer);
		if (bowlChosen instanceof Bowl && bowlChosen.player.hasTurn() == true) {
			int stones = bowlChosen.stoneCount;
			bowlChosen.stoneCount = 0;
			bowlChosen.nextBowl.passStones(stones, bowlChosen.player);
			
		}
		else {
			/* Message to facade:
			 * Bowl chosen cannot be a kalaha;
			 * Bowl chosen should be on the player side.
			 */
		}
	}
	
	public Pit goToBowlPlayerChoice(int bowlChoicePlayer) {
		if (bowlChoicePlayer > 0) {
			return nextBowl.goToBowlPlayerChoice(bowlChoicePlayer - 1);
		}
		else {
			return this;
		}
	}
	
	public abstract void passStones(int nStones, Player playerBowlChosen);
	
	public void passStones(int nStones){
		if (this instanceof Bowl) {
			nextBowl.passStones(nStones);
		}
		else if (this instanceof Kalaha) {
			stoneCount = stoneCount + nStones;
		}
	}
	
	public void checkIfEndOfGame() {
		Pit startKalaha = goToKalahaPlayerTurnIs(false);
		if (startKalaha.checkBoard() == 6) {
			goToKalahaPlayerTurnIs(true).endGame(0);
		}
	}
	
	public int checkBoard() {
		int nEmptyBowls = 0;
		while (nextBowl instanceof Bowl && nextBowl.stoneCount == 0) {
			nEmptyBowls++;
			nextBowl = nextBowl.nextBowl;
		}
		return nEmptyBowls;
	}
	
	public Pit goToKalahaPlayerTurnIs(Boolean aBoolean) {
		if (this instanceof Kalaha && this.player.hasTurn() == aBoolean) {
			return this;
		}
		else {
			return nextBowl.goToKalahaPlayerTurnIs(aBoolean);
		}
	}
	
	public void endGame(int sumStonesBowls) {
		if (nextBowl instanceof Bowl) {
			sumStonesBowls = sumStonesBowls + nextBowl.stoneCount;
			nextBowl.endGame(sumStonesBowls);
		}
		else if (nextBowl instanceof Kalaha) {
			nextBowl.stoneCount = nextBowl.stoneCount + sumStonesBowls;
		}
	}
	
	public int getNumberOfPitsOfBoard(Pit startPit) {
		int boardCount = 1;
		while (nextBowl != startPit) {
			boardCount++;
			nextBowl = nextBowl.nextBowl;
		}
		return boardCount;
	}
}
