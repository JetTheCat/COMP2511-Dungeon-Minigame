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

public class WrongKeyTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.doorDungeon(true);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		// Check the existence of a door, key and a wrong key. 
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
		
		assertTrue(door.getID() == key.getID());
		assertTrue(door.getID() != wrongKey.getID());
		
		// Pickup the wrong key
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getBackpack().size());
		assertTrue(player.isHoldingKey());
		assertTrue(player.isHolding(wrongKey));
		assertFalse(player.isHolding(key));
		assertEquals(1, tmp[2][3].getEntities().size());
		// Try to open the door with the wrong key
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertEquals(1, player.getBackpack().size());
		assertTrue(player.isHoldingKey());
		assertTrue(player.isHolding(wrongKey));
		assertEquals(2, player.getX());
		assertEquals(1, player.getY());
		
		// Try to pickup other key, doesn't stop them moving over it though
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getX());
		assertEquals(2, player.getY());
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getBackpack().size());
		assertTrue(player.isHoldingKey());
		assertTrue(player.isHolding(wrongKey));
		assertFalse(player.isHolding(key));
		assertEquals(1, player.getX());
		assertEquals(3, player.getY());
	}
}
