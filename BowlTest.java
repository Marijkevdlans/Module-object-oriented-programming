package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BowlTest {

	@Test
	public void testIfStartBowlhasFourStones() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(4, startBowl.stoneCount);
	}
	
	@Test
	public void testIfStartBowlIsAssignedAPlayer() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertTrue(startBowl.player instanceof Player);
	}
	
	@Test
	public void testIfStartBowlIsAssignedANextBwolThatIsAnotherBowl() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertNotSame(startBowl, startBowl.nextPit);
	}
	
	@Test
	public void testIfStartBowlAndNextBowlOfStartBowlHaveTheSamePlayer() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl.player, startBowl.nextPit.player);
	}
	
	@Test
	public void testIfNextPitOfBowlSixIsKalaha() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertTrue(startBowl.navigateToPit(6).nextPit instanceof Kalaha);
	}
	
	@Test
	public void testIfNextPitOfBowlThirteenIsKalaha() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertTrue(startBowl.navigateToPit(13).nextPit instanceof Kalaha);
	}
	
	@Test
	public void testIfBowlChoiceOneIncreasesStoneCountofBowlTwoANDFiveByOne() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.processPitChoice(1);
		Assert.assertEquals(5, startBowl.navigateToPit(2).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(5).stoneCount);
	}
	
	@Test
	public void testIfBowlChoiceOneDoesNotIncreaseStoneCountofBowlSixByOne() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.processPitChoice(1);
		Assert.assertEquals(4, startBowl.navigateToPit(6).stoneCount);
	}
	
	@Test
	public void testIfPlayerTurnIsSwoppedAfterProcessingOfBowlChoice() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.processPitChoice(1);
		Assert.assertFalse(startBowl.player.hasTurn());
		Assert.assertTrue(startBowl.player.getOpponent().hasTurn());	
	}
	
	@Test
	public void testIfPuttingTheLastStoneInAnEmptyBowlOfTheOpponentDoesNotActivatesStealing() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(9).stoneCount = 0;
		startBowl.processPitChoice(5);
		Assert.assertEquals(0, startBowl.navigateToPit(5).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(6).stoneCount);
		Assert.assertEquals(1, startBowl.navigateToPit(7).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(8).stoneCount);
		Assert.assertEquals(1, startBowl.navigateToPit(9).stoneCount);
	}
	
	@Test
	public void testIfPuttingTheLastStoneInAnEmptyBowlOfTheOwnPlayerActivatesStealing() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(5).stoneCount = 0;
		startBowl.processPitChoice(1);
		Assert.assertEquals(0, startBowl.navigateToPit(1).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(2).stoneCount);
		Assert.assertEquals(0, startBowl.navigateToPit(5).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(7).stoneCount);
		Assert.assertEquals(0, startBowl.navigateToPit(9).stoneCount);
	}
	
}
