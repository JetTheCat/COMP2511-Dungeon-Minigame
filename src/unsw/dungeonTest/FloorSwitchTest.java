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

public class FloorSwitchTest {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(false);
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void floorSwitchTest() {
		Tile[][] tile = dungeon.getTiles();
		FloorSwitch fswitch = (FloorSwitch) tile[10][5].getEntities().get(0);
		Boulder boulder = (Boulder) tile[8][5].getEntities().get(0);
		
		// Move player to stand on a switch
		player.setDirection(Direction.DOWN);
		for(int i = 0; i < 5; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
	
		player.setDirection(Direction.UP);
		for(int i = 0; i < 4; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		}
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
	
		
		// Check if player is on same tile with the switch,
		// Check if switch tile also has added player entity onto it
		assertEquals(fswitch.getX(), player.getX());
		assertEquals(fswitch.getY(), player.getY());
		assertEquals(2, tile[fswitch.getX()][fswitch.getY()].getEntities().size());
		assertFalse(fswitch.isTriggered());
		
		// Get player to move boulder onto a switch, check if
		// boulder is correctly moved to the switch tile and the switch tile 
		// contains two entities, switch should also be in a triggered state
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		
		player.setDirection(Direction.LEFT);
		for(int i = 0; i < 3; i++) {
			dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		}
		
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		assertEquals(fswitch.getX(), boulder.getX());
		assertEquals(fswitch.getY(), boulder.getY());
		assertEquals(2, tile[fswitch.getX()][fswitch.getY()].getEntities().size());
		assertTrue(fswitch.isTriggered());
		
		// Get player to move the boulder out from the switch tile,
		// switch tile should be untriggered 
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		
		assertFalse(fswitch.isTriggered());
	}

}
