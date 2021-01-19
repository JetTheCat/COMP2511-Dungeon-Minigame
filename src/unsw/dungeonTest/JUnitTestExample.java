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

public class JUnitTestExample {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(true);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	

	
	@Test
	public void testing() {
		dungeon.processJSONGoals(goals);
		assertFalse(dungeon.checkGoal());
		System.out.println(dungeon.showGoalList());
		
		int prevX = player.getX();
		int prevY = player.getY();
		System.out.println("Initial: x - " + prevX + " y - " + prevY);

		Tile[][] tmp = dungeon.getTiles();
		
		/*
		// Move player to pickup key
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		
		// Move player to portal
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		// Move player to push boulder to switch
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		// Move player to pickup treasure
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		// Move player to open door
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		// Move player to exit
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);*/
		
		

		// Move player to pickup sword
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		
		// Move player to destroy enemy
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		System.out.println(dungeon.getCurrentState());
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		player.setDirection(Direction.LEFT);
		player.swingSword(controller);
		
		/*
		// Move player to pickup potion
		player.setDirection(Direction.RIGHT);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()+1, player.getY(), controller);
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		// Move player to rekt enemy with invis
		player.setDirection(Direction.LEFT);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller);
		dungeon.checkInteraction(player.getX()-1, player.getY(), controller); */
		
		System.out.println(player.getBackpack() + " <<< Backpack");
		
		System.out.println(tmp[3][6].getEntities() + " <<< Entities");
		
		System.out.println("Final: x - " + player.getX() + " y - " + player.getY());
		//assertEquals(prevX, player.getX());
		//assertEquals(prevY, player.getY());
	}
	


}
