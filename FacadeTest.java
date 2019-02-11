package nl.sogyo.mancala;

import org.junit.Test;
import org.junit.Assert;

public class FacadeTest {
		
	@Test
    public void testIfStartBowlIsAssignedAStartBowl() {
    	Facade mancalaDomain = new Facade();
    	Assert.assertTrue(mancalaDomain.getStartBowl() instanceof Bowl);
    }
	
	@Test
	public void testIfBoardComplete() {
		Facade mancalaDomain = new Facade();
		Pit startBowl = mancalaDomain.getStartBowl();
		Assert.assertSame(startBowl, startBowl.navigateToPit(15));
	}
	
}
