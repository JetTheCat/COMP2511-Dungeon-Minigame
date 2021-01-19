package unsw.dungeonTest;

import unsw.dungeon.*;
import unsw.dungeonComposite.*;
import unsw.dungeonState.*;
import unsw.dynamicBehaviour.*;
import unsw.dynamicEntity.*;
import unsw.staticBehaviour.*;
import unsw.staticEntity.*;
import unsw.dungeonTest.*;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.*;

public class SwordTest {

	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.swordTestDungeon();
	JSONObject goals = new JSONObject();
	
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	
	@Test
	public void swordInteractionTest() {
		
		// Adds a dungeon goal to kill all enemies
		goals.put("goal", "enemies");
		dungeon.processJSONGoals(goals);
		
		List<StaticEntity> playerBackpack = player.getBackpack();
		Tile[][] tile = dungeon.getTiles();
		
		// Move player to pickup sword, should appear in player's backpack
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertTrue(player.isHoldingSword());
		assertTrue(playerBackpack.get(0) instanceof Sword);
		
		// Player swings sword to kill all 5 enemies, player sword should break
		// and be removed from the player's backpack
		player.swingSword(controller);
		controller.moveAllEnemies();
		player.swingSword(controller);
		controller.moveAllEnemies();
		player.swingSword(controller);
		controller.moveAllEnemies();
		player.swingSword(controller);
		controller.moveAllEnemies();
		player.swingSword(controller);
		controller.moveAllEnemies();
		
		assertFalse(player.isHoldingSword());
		assertEquals(0, playerBackpack.size());
		
		// Checks if enemy still exist in the tiles, 0 indicates enemy have been successfully killed and remove
		assertEquals(0, tile[1][3].getEntities().size());
		assertEquals(0, tile[1][4].getEntities().size());
		assertEquals(0, tile[1][5].getEntities().size());
		assertEquals(0, tile[1][6].getEntities().size());
		assertEquals(0, tile[1][7].getEntities().size());
		assertEquals(0, tile[1][8].getEntities().size());
		
		// Moves player to pickup a sword at near the end of the dungeon
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertTrue(player.isHoldingSword());
		assertTrue(playerBackpack.get(0) instanceof Sword);
		
		// Moves player to a second sword after picking up a sword, 
		// player should not be able to pickup this sword and should have 1 sword item 
		// in the backpack only
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertEquals(1, playerBackpack.size());
		
		// Check if we have completed dungeon by killing all enemies
		assertTrue(dungeon.checkGoal());
		
	}
}
