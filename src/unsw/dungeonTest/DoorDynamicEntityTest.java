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

public class DoorDynamicEntityTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.doorDungeon(true);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		Door door = null;
		for (Entity entity : tmp[3][1].getEntities()) {
			if (entity instanceof Door) {
				door = (Door) entity;
			}
		}

		Key key = null;
		for (Entity entity : tmp[1][3].getEntities()) {
			if (entity instanceof Key) {
				key = (Key) entity;
			}
		}
		
		Key key2 = null;
		for (Entity entity : tmp[2][3].getEntities()) {
			if (entity instanceof Key) {
				key2 = (Key) entity;
			}
		}
		
		Boulder boulder = null;
		for (Entity entity : tmp[6][1].getEntities()) {
			if (entity instanceof Boulder) {
				boulder = (Boulder) entity;
			}
		}
		assertNotNull(boulder);
		
		Enemy enemy = null;
		for (Entity entity : tmp[10][9].getEntities()) {
			if (entity instanceof Enemy) {
				enemy = (Enemy) entity;
			}
		}
		assertNotNull(enemy);
		
		// Pickup the correct key
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getBackpack().size());
		assertTrue(player.isHoldingKey());
		assertFalse(player.isHolding(key2));
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
		
		// Push boulder through the door
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(7, player.getX());
		assertEquals(1, player.getY());
		// Start pushing the boulder
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		// Boulder should be in the same position as the door
		assertEquals(3, boulder.getX());
		assertEquals(1, boulder.getY());
		assertEquals(3, door.getX());
		assertEquals(1, door.getY());
		assertEquals(2, tmp[3][1].getEntities().size());
		// Fully push boulder through door
		// Player should be in the door and the boulder fully pushed through
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		assertEquals(2, boulder.getX());
		assertEquals(1, boulder.getY());
		assertEquals(2, tmp[3][1].getEntities().size());
		

		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		assertEquals(1, boulder.getX());
		assertEquals(1, boulder.getY());
		// Pickup key2
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertTrue(player.isHolding(key2));

		// Go open the door containing an enemy
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		
		for (int i = 0 ; i < 6; i ++) {
			dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		}
		
		for (int i = 0 ; i < 8; i ++) {
			dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		}
		assertEquals(8, player.getX());
		assertEquals(9, player.getY());
		
		Door door2 = null;
		for (Entity entity : tmp[9][9].getEntities()) {
			if (entity instanceof Door) {
				door2 = (Door) entity;
			}
		}
		assertNotNull(door2);
		assertEquals(door2.getID(), key2.getID());
		assertTrue(player.isHolding(key2));
		
		// Player moves and opens door, enemy moves as well and kills player
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		assertEquals(3, tmp[9][9].getEntities().size());
		assertEquals(9, enemy.getX());
		assertEquals(9, enemy.getY());
		assertEquals(9, player.getX());
		assertEquals(9, player.getY());
		assertTrue(door2.isOpened());
		assertTrue(dungeon.getCurrentState() instanceof LoseDungeonState);
	}
}
