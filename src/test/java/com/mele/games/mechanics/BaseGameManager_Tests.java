package com.mele.games.mechanics;

import org.junit.Assert;
import org.junit.Test;

import com.mele.games.utils.GameException;


public class BaseGameManager_Tests {
	
	@Test
	public void testInputException() {
		IGameManager bgm = new TestGameManager(new TestGameInput(), null);
		
		try
		{
			bgm.startGame();
			Assert.fail("Exception not thrown.");
		} catch (GameException ge) {
			Assert.assertEquals("Input, renderer or game not set by game manager.", ge.getLocalizedMessage());
			Assert.assertEquals(EGameState.ERROR, bgm.state());
		}
		
		bgm = new TestGameManager(null, new TestGameRenderer());
		
		try
		{
			bgm.startGame();
			Assert.fail("Exception not thrown.");
		} catch (GameException ge) {
			Assert.assertEquals("Input, renderer or game not set by game manager.", ge.getLocalizedMessage());
			Assert.assertEquals(EGameState.ERROR, bgm.state());
		}
	}
	
	/*
	@Test
	public void testBasicCallPattern() {
		TestGameInput tgi = new TestGameInput();
		TestGameRenderer tgr = new TestGameRenderer();
		IGameManager bgm = null;
		TestGame game = null;
		try
		{	
			bgm = new TestGameManager(tgi, tgr);
			
			new Thread(bgm).start();
			
			// Pause the calling thread for a second and let the game manager thread do a bit of work.
			Thread.sleep(1000);
			
			game = (TestGame)((TestGameManager)bgm).game;
			Assert.assertNotNull(game);			
			
		} catch (GameException ge) {
			Assert.fail("Exception unexpected: " + ge.getLocalizedMessage());
		} catch (InterruptedException e) {
			Assert.fail("Exception unexpected: " + e.getLocalizedMessage());
		}
		
		Assert.assertEquals(true, tgi.getRunCount() > 0);
		Assert.assertEquals(true, tgi.isCommand());
		Assert.assertEquals(true, tgr.isRan());
		Assert.assertEquals(true, game.gameLoopCount > 0);
		Assert.assertEquals(0, game.commandCount);
		
		bgm.endGame();
		
		Assert.assertEquals(true, tgi.isKill());
		Assert.assertEquals(true, tgr.isKill());
	}
	
	
	@Test
	public void testKeyboardInput() {
		IGameInput tgi = new KeyboardInput();
		TestGameRenderer tgr = new TestGameRenderer();
		IGameManager bgm = null;
		TestGame game = null;
		try
		{	
			bgm = new TestGameManager(tgi, tgr);
			
			new Thread(bgm).start();
			
			// Pause the calling thread for a second and let the game manager thread do a bit of work.
			Thread.sleep(20000);
			
			game = (TestGame)((TestGameManager)bgm).game;
			Assert.assertNotNull(game);			
			
		} catch (GameException ge) {
			Assert.fail("Exception unexpected: " + ge.getLocalizedMessage());
		} catch (InterruptedException e) {
			Assert.fail("Exception unexpected: " + e.getLocalizedMessage());
		}
		
		bgm.endGame();
		
	}	
	*/
}
