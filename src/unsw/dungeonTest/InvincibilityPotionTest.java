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

public class InvincibilityPotionTest {

	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.potionTestDungeon(false);
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void invincibilityPotionTest() {
		Tile[][] tile = dungeon.getTiles();
		InvincibilityPotion potion = (InvincibilityPotion) tile[1][2].getEntities().get(0);
		
		// Move player to pickup potion,
		// Check if player isInvincible() is set to true after picking it up
		// Also checks if potion has been added into backpack successfully
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		assertTrue(player.isInvincible());
		assertEquals(1, player.getBackpack().size());
	
		
		// Move player down 11 tiles, invincibility potion should wear off by then.
		// Check if player isInvincible() is set to false and potion is removed
		// from player's backpack
		for(int i = 0; i < 11; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}
		
		assertFalse(player.isInvincible());
		assertEquals(0, player.getBackpack().size());
		
	}
	
}
