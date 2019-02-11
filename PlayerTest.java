package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void testIfPlayer1IsInstantiatedWithTurnIsTrue() {
		Player player1 = new Player();
		Assert.assertTrue(player1.hasTurn());
	}
	
	@Test
	public void testIfPlayer2IsInstantiatedWithTurnIsFalse() {
		Player player1 = new Player();
		Assert.assertFalse(player1.getOpponent().hasTurn());
	}
	
	@Test
	public void testIfPlayer1AndPlayer2AreDifferentPlayers() {
		Player player1 = new Player();
		Assert.assertNotSame(player1, player1.getOpponent());
	}
	
	@Test
	public void testIfOpponentPlayer2IsPlayer1() {
		Player player1 = new Player();
		Assert.assertSame(player1, player1.getOpponent().getOpponent());
	}
		
	@Test
	public void turnShouldBeChangedToOtherPlayer() {
		Player player1 = new Player();
		player1.changeTurn();
		Assert.assertFalse(player1.hasTurn());
		Assert.assertTrue(player1.getOpponent().hasTurn());
		player1.getOpponent().changeTurn();
		Assert.assertTrue(player1.hasTurn());
		Assert.assertFalse(player1.getOpponent().hasTurn());
	}
	
}
