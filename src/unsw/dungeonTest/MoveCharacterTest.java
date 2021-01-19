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


public class MoveCharacterTest {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(false);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void playerMovementTest() {
		
		int expectedX = player.getX();
		int expectedY = player.getY();
		
		// Player attempts to move left into a wall, should remain the same position
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
		// Player attempts to move up into a wall, should remain the same position
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
		// Player attempts to move right into a new movable tile, should update position
		expectedX = player.getX()+1;
		expectedY = player.getY();
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
		// Player attempts to move left back to its previous position, should update position
		expectedX = player.getX()-1;
		expectedY = player.getY();
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
		// Player attempts to move 3 tiles down, should update position
		expectedX = player.getX();
		expectedY = player.getY()+3;
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
		// Player attempts to move 3 tiles up back to starting position, should update position
		expectedX = player.getX();
		expectedY = player.getY()-3;
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		assertEquals(expectedX, player.getX());
		assertEquals(expectedY, player.getY());
		
	}

}
