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

public class TreasureTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.treasureDungeon();
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		assertEquals(0, player.getBackpack().size());
		// Establish position of first treasure, boulder and enemy
		ArrayList<Entity> treasure1TileEntities = tmp[1][2].getEntities();
		assertEquals(1, treasure1TileEntities.size());
		int count = 0;
		for (Entity entity : treasure1TileEntities) {
			if (entity instanceof Treasure) {
				count ++;
			}
		}
		assertEquals(1, count);
		
		count = 0;
		Boulder boulder = null;
		for(Entity entity : tmp[1][4].getEntities()) {
			if (entity instanceof Boulder) {
				boulder = (Boulder) entity;
				count ++;
			}
		}
		assertEquals(1, boulder.getX());
		assertEquals(4, boulder.getY());
		assertNotNull(boulder);
		assertEquals(1, count);
		
		count = 0;
		Enemy enemy = null;
		for(Entity entity : tmp[1][7].getEntities()) {
			if (entity instanceof Enemy) {
				enemy = (Enemy) entity;
				count ++;
			}
		}
		assertEquals(1, enemy.getX());
		assertEquals(7, enemy.getY());
		assertNotNull(enemy);
		assertEquals(1, count);
		
		// Pickup treasure1
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getBackpack().size());
		count = 0;
		for (Entity entity : player.getBackpack()) {
			if (entity instanceof Treasure) {
				count ++;
			}
		}
		assertEquals(1, count);
		treasure1TileEntities = tmp[1][2].getEntities();
		assertEquals(1, treasure1TileEntities.size());
		count = 0;		// Only the player should be in the tile now
		for (Entity entity : treasure1TileEntities) {
			if (entity instanceof Player) {
				count ++;
			}
		}
		assertEquals(1, count);
		
		// Pickup treasure2
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(2, player.getBackpack().size());
		count = 0;
		for (Entity entity : player.getBackpack()) {
			if (entity instanceof Treasure) {
				count ++;
			}
		}
		assertEquals(2, count);
		
		// Trying to push boulder while treasure3 in the way, should not remove it
		Treasure treasure3 = null;
		for (Entity entity : tmp[1][5].getEntities()) {
			if (entity instanceof Treasure) {
				treasure3 = (Treasure) entity;
			}
		}
		assertNotNull(treasure3);
		assertEquals(1, player.getX(), 1);
		assertEquals(1, player.getY(), 3);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getX(), 1);
		assertEquals(1, player.getY(), 3);
		assertEquals(1, boulder.getX());
		assertEquals(4, boulder.getY());
		assertEquals(1, treasure3.getX());
		assertEquals(5, treasure3.getY());
		// Enemy should have not moved from its original position and not removed treasure
		assertEquals(1, enemy.getX());
		assertEquals(7, enemy.getY());
		Treasure treasure4 = null;
		for (Entity entity : tmp[1][6].getEntities()) {
			if (entity instanceof Treasure) {
				treasure4 = (Treasure) entity;
			}
		}
		assertNotNull(treasure4);
		assertEquals(1, treasure4.getX());
		assertEquals(6, treasure4.getY());
	}

	

}
