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

public class BoulderTest {

	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(false);
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test 
	public void boulderMovementTest() {
		
		Tile[][] tile = dungeon.getTiles();
		Boulder boulder = (Boulder) tile[8][5].getEntities().get(0);
		
		//Move Player next to a boulder
		player.setDirection(Direction.RIGHT);
		for(int i = 0; i < 4; i ++) {
			dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		}
		
		player.setDirection(Direction.DOWN);
		for(int i = 0; i < 4; i ++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}

		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		// Check if player is at the right position
		assertEquals(7, player.getX());
		assertEquals(5, player.getY());
		
		
		// Move boulder to the right, boulder & player position 
		// should be updated
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		assertEquals(8, player.getX());
		assertEquals(5, player.getY());
		assertEquals(9, boulder.getX());
		assertEquals(5, boulder.getY());
		
		// Move boulder up, boulder & player position 
		// should be updated
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		assertEquals(9, player.getX());
		assertEquals(5, player.getY());
		assertEquals(9, boulder.getX());
		assertEquals(4, boulder.getY());
		
		// Move boulder to the left, boulder & player position
		// should be updated
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		assertEquals(9, player.getX());
		assertEquals(4, player.getY());
		assertEquals(8, boulder.getX());
		assertEquals(4, boulder.getY());
		
		// Move boulder down, boulder & player position
		// should be updated
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertEquals(8, player.getX());
		assertEquals(4, player.getY());
		assertEquals(8, boulder.getX());
		assertEquals(5, boulder.getY());
		
		// Player to move the boulder to another boulder, 
		// the boulder being pushed should not move and stay at the same position
		// after trying to push towards another boulder
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);

		// Checks if boulder is not moving after blocked by another boulder,
		// and that player is also not moving forward as well if they can't push the boulder
		assertEquals(9, boulder.getX());
		assertEquals(2, boulder.getY());
		assertEquals(9, player.getX());
		assertEquals(3, player.getY());
		
		
	}
}
