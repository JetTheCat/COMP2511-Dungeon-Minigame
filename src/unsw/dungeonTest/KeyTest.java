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

public class KeyTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.doorDungeon(true);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		// Check the existence of a door, key, wrong key and enemy. 
		int count = 0;
		Door door = null;

		for (Entity entity : tmp[3][1].getEntities()) {
			if (entity instanceof Door) {
				count ++;
				door = (Door) entity;
			}
		}
		assertEquals(1, count);
		assertNotNull(door);
		assertFalse(door.isOpened());
		
		count = 0;
		Key key = null;
		for (Entity entity : tmp[1][3].getEntities()) {
			if (entity instanceof Key) {
				count ++;
				key = (Key) entity;
			}
		}
		assertEquals(1, count);
		assertNotNull(key);
		
		count = 0;
		Key wrongKey = null;
		for (Entity entity : tmp[2][3].getEntities()) {
			if (entity instanceof Key) {
				count ++;
				wrongKey = (Key) entity;
			}
		}
		assertEquals(1, count);
		assertNotNull(wrongKey);
		
		Enemy enemy = null;
		count = 0;
		for (Entity entity : tmp[10][9].getEntities()) {
			if (entity instanceof Enemy) {
				count ++;
				enemy = (Enemy) entity;
			}
		}
		assertEquals(1, count);
		assertNotNull(enemy);
		
		// Try to open door without key
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertFalse(player.isHoldingKey());
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		assertFalse(door.isOpened());
		
		
		// Pickup the correct key
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getBackpack().size());
		assertTrue(player.isHoldingKey());
		assertFalse(player.isHolding(wrongKey));
		assertTrue(player.isHolding(key));
		assertEquals(1, tmp[1][3].getEntities().size());
		
		// Open the door, player is now on the same tile as the door
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertEquals(2, tmp[3][1].getEntities().size());
		assertEquals(3, player.getX());
		assertEquals(1, player.getY());
		assertEquals(0, player.getBackpack().size());
		assertFalse(player.isHoldingKey());
		assertFalse(player.isHolding(key));
		assertTrue(door.isOpened());
		
		// Player can move back and forth through door without any issues
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		assertEquals(1, player.getX());
		assertEquals(1, player.getY());
		assertTrue(door.isOpened());
		
		// Enemy should not have moved from their original point
		assertEquals(10, enemy.getX());
		assertEquals(9, enemy.getY());
		
	}
}
