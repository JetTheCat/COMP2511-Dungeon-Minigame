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

public class KillEnemyTest {
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
		
		// Go pickup sword, still moving towards player
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(7, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(9, enemy2.getY());
		
		// Go face enemy and swing sword
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, player.getX());
		assertEquals(4, player.getY());
		assertEquals(1, enemy1.getX());
		assertEquals(5, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(7, enemy2.getY());
		player.swingSword(controller);
		controller.moveAllEnemies();
		assertEquals(1, tmp[player.getX()][player.getY()].getEntities().size());
		assertEquals(0, tmp[player.getX()][player.getY() + 1].getEntities().size());
		assertFalse(enemy1.isAlive());
		assertTrue(enemy2.isAlive());
		
		// Pickup potion and destroy enemy
		for (int i = 0; i < 3; i ++) {
			dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		}
		assertTrue(player.isInvincible());
		for (int i = 0; i < 9; i ++) {
			dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		}
		assertFalse(enemy2.isAlive());
		assertFalse(enemy1.isAlive());
		assertFalse(dungeon.getCurrentState() instanceof LoseDungeonState);
	}
}
