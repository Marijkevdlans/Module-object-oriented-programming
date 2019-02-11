package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PitTest {
	
	@Test
	public void testIfBoardNavigatorNavigatesToCorrectPit() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl, startBowl.navigateToPit(1));
		Assert.assertSame(startBowl.nextPit, startBowl.navigateToPit(2));
		Assert.assertSame(startBowl.nextPit.nextPit.nextPit.nextPit, startBowl.navigateToPit(5));
	}
	
	@Test
	public void testIfPitChoiceIsKalahaIsNotProcessed() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(3, startBowl.processPitChoice(7));
		Assert.assertEquals(3, startBowl.processPitChoice(14));
	}
	
	@Test
	public void testIfOnlyPitChoiceOnPlayerSideIsProcessed() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(3, startBowl.processPitChoice(8));
		startBowl.player.changeTurn();
		Assert.assertEquals(3, startBowl.processPitChoice(1));
	}
	
	@Test
	public void testIfPitChoiceWithStoneCountIsZeroIsNotProcessed() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.stoneCount = 0; 
		Assert.assertEquals(3, startBowl.processPitChoice(1));
	}
	
	@Test
	public void testIfPitChoiceThatIsBowlOnPlayerSideWithMoreThanZeroStonesIsProcessed() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(1, startBowl.processPitChoice(1));
	}
	
	@Test
	public void testIfStoneCountPitChosenAfterProcessingIsZero() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.processPitChoice(1);
		Assert.assertEquals(0, startBowl.stoneCount);
	}
	
	@Test
	public void testIfKalahaPlayerTurnIsTrueIsFound() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl.navigateToPit(7), startBowl.findKalahaPlayerTurnIs(true));
		Assert.assertSame(startBowl.navigateToPit(14), startBowl.findKalahaPlayerTurnIs(false));
		startBowl.player.changeTurn();
		Assert.assertSame(startBowl.navigateToPit(14), startBowl.findKalahaPlayerTurnIs(true));
		Assert.assertSame(startBowl.navigateToPit(7), startBowl.findKalahaPlayerTurnIs(false));
	}
	
	@Test
	public void testIfCheckIfEndGameReturns2WhenEndGame() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(1).stoneCount = 0;
		startBowl.navigateToPit(2).stoneCount = 0;
		startBowl.navigateToPit(3).stoneCount = 0;
		startBowl.navigateToPit(4).stoneCount = 0;
		startBowl.navigateToPit(5).stoneCount = 0;
		startBowl.navigateToPit(6).stoneCount = 0;
		Assert.assertEquals(2, startBowl.checkIfEndOfGame());
	}
	
	@Test
	public void testIfCheckIfEndGameReturns1WhenNotEndGame() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(1).stoneCount = 0;
		startBowl.navigateToPit(2).stoneCount = 0;
		startBowl.navigateToPit(4).stoneCount = 0;
		startBowl.navigateToPit(5).stoneCount = 0;
		startBowl.navigateToPit(6).stoneCount = 0;
		Assert.assertEquals(1, startBowl.checkIfEndOfGame());
	}
		
	@Test
	public void testnConsecutiveBowlsCalculatedbyCheckBoardIsSix() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(1).stoneCount = 0;
		startBowl.navigateToPit(2).stoneCount = 0;
		startBowl.navigateToPit(3).stoneCount = 0;
		startBowl.navigateToPit(4).stoneCount = 0;
		startBowl.navigateToPit(5).stoneCount = 0;
		startBowl.navigateToPit(6).stoneCount = 0;
		Pit startKalaha = startBowl.findKalahaPlayerTurnIs(false);
		Assert.assertEquals(6, startKalaha.checkBoard(0));
	}
	
	@Test
	public void testIfAllStonesMoveToCorrectKalahasWhenEndGameActivated() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(1).stoneCount = 0;
		startBowl.navigateToPit(2).stoneCount = 0;
		startBowl.navigateToPit(3).stoneCount = 0;
		startBowl.navigateToPit(4).stoneCount = 0;
		startBowl.navigateToPit(5).stoneCount = 0;
		startBowl.navigateToPit(6).stoneCount = 0;
		startBowl.navigateToPit(7).stoneCount = 24;
		startBowl.findKalahaPlayerTurnIs(true).nextPit.endGame(0);
		Assert.assertEquals(24, startBowl.navigateToPit(14).stoneCount);
	}
	
}
	