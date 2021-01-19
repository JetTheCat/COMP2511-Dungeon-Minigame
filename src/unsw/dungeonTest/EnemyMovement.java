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

public class EnemyMovement {
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
		// Moving while not invincible causes enemies to move closer
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(7, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(9, enemy2.getY());
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(6, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(8, enemy2.getY());
		// Enemies don't move if the move is not valid (walking into walls)
		Wall wall = null;
		for (Entity entity : tmp[2][3].getEntities()) {
			if (entity instanceof Wall) {
				wall = (Wall) entity;
			}
		}
		assertNotNull(wall);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		assertEquals(1, enemy1.getX());
		assertEquals(6, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(8, enemy2.getY());
		
		// Go pickup sword, still moving towards player
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(5, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(7, enemy2.getY());
		
		// Swinging sword also causes enemy to move closer
		player.swingSword(controller);
		controller.moveAllEnemies();
		assertEquals(1, enemy1.getX());
		assertEquals(4, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(6, enemy2.getY());
		
		// Picking up potion
		dungeon.checkInteraction(player.getX(), player.getY() - 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(3, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(5, enemy2.getY());
		// Any action after picking up potion causes them to run away
		player.swingSword(controller);
		controller.moveAllEnemies();
		assertEquals(1, enemy1.getX());
		assertEquals(4, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(6, enemy2.getY());
		dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		assertEquals(1, enemy1.getX());
		assertEquals(5, enemy1.getY());
		assertEquals(1, enemy2.getX());
		assertEquals(7, enemy2.getY());
	}
}
