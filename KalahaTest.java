package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class KalahaTest {

	@Test
	public void testIfKalaha7HasZeroStones() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(0, startBowl.navigateToPit(7).stoneCount);
	}
	
	@Test
	public void testIfPlayerBowlSixAndPlayerKalahaAreEqual() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl.navigateToPit(6).player, startBowl.navigateToPit(7).player);
	}
	
	@Test
	public void testIfPlayerOfPitEightIsOpponentOfPlayerKalaha() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl.navigateToPit(7).player.getOpponent(), startBowl.navigateToPit(8).player);
	}
	
	@Test
	public void testIfKalaha14HasZeroStones() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertEquals(0, startBowl.navigateToPit(14).stoneCount);
	}
	
	@Test
	public void testIfPlayerBowlThirteenAndPlayerKalahaAreEqual() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl.navigateToPit(13).player, startBowl.navigateToPit(14).player);
	}
	
	@Test
	public void testIfNextPitOfKalahaIsStartBowl() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl, startBowl.navigateToPit(14).nextPit);
	}
	
	@Test
	public void testIfStoneIsAddedToKalahaOfOwnPLayerButNottoKalahaOfOpponent() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.navigateToPit(6).stoneCount = 8;
		startBowl.processPitChoice(6);
		Assert.assertEquals(0, startBowl.navigateToPit(6).stoneCount);
		Assert.assertEquals(1, startBowl.navigateToPit(7).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(8).stoneCount);
		Assert.assertEquals(0, startBowl.navigateToPit(14).stoneCount);
		Assert.assertEquals(5, startBowl.navigateToPit(1).stoneCount);
	}
	
	@Test
	public void testIfTurnIsNotChangedWhenTheLastStoneIsAddedToKalahaOfPlayerTurnIsTrue() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		startBowl.processPitChoice(3);
		Assert.assertEquals(1, startBowl.navigateToPit(7).stoneCount);
		Assert.assertTrue(startBowl.player.hasTurn());
	}
	
}
