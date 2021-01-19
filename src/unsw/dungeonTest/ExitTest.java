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

public class ExitTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(false);
	JSONObject goals = builder.buildTreasureExitGoal();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		dungeon.processJSONGoals(goals);
		assertFalse(dungeon.checkGoal());
		Tile[][] tmp = dungeon.getTiles();

		
		// Move player to pickup key and go through portal
		player.setDirection(Direction.DOWN);
		for (int i = 0; i < 9; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		
		// Move player to open the door and go to the tile right before the exit
		dungeon.checkInteraction(player.getX(), player.getY()- 1, controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertEquals(10, player.getX());
		assertEquals(9, player.getY());
		
		// Trying to exit while goals are not complete results in not passing through
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertFalse(dungeon.checkGoal());
		assertEquals(10, player.getX());
		assertEquals(9, player.getY());
		assertEquals(1, tmp[10][10].getEntities().size());

		// Still have to collect the treasure
		for (int i = 0; i < 7; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()- 1, controller);
		}
		assertFalse(dungeon.checkGoal());
		
		//Go back through exit, dungeon completed
		for (int i = 0; i < 8; i++) {
			dungeon.checkInteraction(player.getX(), player.getY() + 1, controller);
		}
		assertTrue(dungeon.checkGoal());
		assertTrue(dungeon.getCurrentState() instanceof WinDungeonState);
		

	}
}
