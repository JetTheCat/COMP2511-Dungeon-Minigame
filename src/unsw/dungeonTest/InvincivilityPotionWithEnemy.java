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

public class InvincivilityPotionWithEnemy {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.potionTestDungeon(true);
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void invincibilityPotionEnemyTest() {
		Tile[][] tile = dungeon.getTiles();
		InvincibilityPotion potion = (InvincibilityPotion) tile[1][6].getEntities().get(0);
		Enemy enemy = (Enemy) tile[1][15].getEntities().get(0);
		
		// Move player to pickup potion,
		// Check if player isInvincible() is set to true after picking it up
		// Also checks if potion has been added into backpack successfully
		player.setDirection(Direction.DOWN);
		for(int i = 0; i < 5; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}
		
		assertTrue(player.isInvincible());
		assertEquals(1, player.getBackpack().size());
		//System.out.println("---------");
		
		int currX = enemy.getX();
		int currY = enemy.getY()+1;
		
		//System.out.println(currX + " " + currY);
		
		// Enemy realized player has potion on, move away from player
		// Checks whether enemy is correctly moving away from player
		for(int i = 0; i < 8; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
			assertEquals(currY, enemy.getY());
			if(currY == 15) {
				continue;
			}
			else {
				currY++;
			}
		}
		
		
		// Player has invincibility effect on, move player onto enemy
		// Enemy should die and be removed from the tile the player moved onto
		// Also checks if the only entity left in the tile is a player type
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertFalse(enemy.isAlive());
		assertTrue(player.isInvincible());
		assertSame(player, tile[player.getX()][player.getY()].getEntities().get(0));
		
		
	}

}
