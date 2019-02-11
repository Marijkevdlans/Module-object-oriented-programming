package nl.sogyo.mancala;

public abstract class Pit {
	// Instance variable(s) -> default access
	int stoneCount = 4;
	Player player;
	Pit nextPit;

	// Method(s)
	public Pit navigateToPit(int bowlChoicePlayer) {
		if (bowlChoicePlayer > 1) {
			return nextPit.navigateToPit(--bowlChoicePlayer);
		}
		else {
			return this;
		}
	}
	
	public int processPitChoice(int pitChoice) {
		Pit pitChosen = navigateToPit(pitChoice);
		if ((pitChosen instanceof Bowl) && (pitChosen.player.hasTurn() == true) && (pitChosen.stoneCount > 0)) {
			int stoneCountPitChosen = pitChosen.stoneCount;
			pitChosen.stoneCount = 0;
			pitChosen.nextPit.passStones(stoneCountPitChosen, pitChosen.player);
			return checkIfEndOfGame();
		}
		else {
			return 3;
		}
	}
	
	public abstract void passStones(int nStones, Player playerOfPitChosen);
	
	public abstract int stealStones(int numberOfStepsToBowltoStealFrom);
	
	public abstract void endGame(int sumStonesBowls);
			
	public int checkIfEndOfGame() {
		Pit startKalaha = findKalahaPlayerTurnIs(false);
		if (startKalaha.checkBoard(0) == 6) {
			findKalahaPlayerTurnIs(true).nextPit.endGame(0);
			return 2;
		}
		return 1;
	}
	
	public Pit findKalahaPlayerTurnIs(Boolean aBoolean) {
		if (this instanceof Kalaha && this.player.hasTurn() == aBoolean) {
			return this;
		}
		else {
			return nextPit.findKalahaPlayerTurnIs(aBoolean);
		}
	}
	
	public int checkBoard(int nConsecutiveEmptyBowls) {
		if (nextPit instanceof Bowl && nextPit.stoneCount == 0) {
			return nextPit.checkBoard(++nConsecutiveEmptyBowls);
		}
		else {
			return nConsecutiveEmptyBowls;
		}
	}

}
