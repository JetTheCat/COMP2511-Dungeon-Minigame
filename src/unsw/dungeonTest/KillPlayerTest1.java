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

// This test is if a player and enemy is moving into the same tile at the same time
public class KillPlayerTest1 {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.enemyDungeon();
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		// Get enemies
		Enemy enemy1 = null;
		for (Entity entity : tmp[1][8].getEntities()) {
			if (entity instanceof Enemy) {
				enemy1 = (Enemy) entity;
			}
		}
		
		Enemy enemy2 = null;
		for (Entity entity : tmp[1][10].getEntities()) {
			if (entity instanceof Enemy) {
				enemy2 = (Enemy) entity;
			}
		}
		
		assertNotNull(enemy1);
		assertNotNull(enemy2);
		
		// Do actions that results in the enemy being 2 tiles away from the player
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		player.swingSword(controller);
		controller.moveAllEnemies();
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		// Enemy in close to player now
		assertEquals(player.getX(), enemy1.getX());
		assertEquals(player.getY() + 2, enemy1.getY());
		
		// Player and enemy move into the same tile, player dies
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(player.getX(), enemy1.getX());
		assertEquals(player.getY(), enemy1.getY());
		assertTrue(dungeon.getCurrentState() instanceof LoseDungeonState);
	}
}