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

public class EnemyBoulderInteraction {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.EnemyBoulderDungeon();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	
	@Test
	public void enemyBoulderTest() {
		
		Tile[][] tile = dungeon.getTiles();
		Enemy enemy = (Enemy) tile[1][5].getEntities().get(0);
		
		// Move player one tile down, enemy should move one tile up
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		assertEquals(1, enemy.getX());
		assertEquals(4, enemy.getY());
		
		// Move player up one tile again, enemy should not move and stay at
		// the same position because the boulder is blocking the enemy
		player.setDirection(Direction.UP);
		dungeon.checkInteraction(player.getX(), player.getY()-1, controller);
		assertEquals(1, enemy.getX());
		assertEquals(4, enemy.getY());
		
		
	}

}
