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

public class BoulderToBoulder {
	
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.BoulderInteractionDungeon();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	
	@Test
	public void boulderToBoulder() {
		Tile[][] tile = dungeon.getTiles();
		Boulder boulder1 = (Boulder) tile[1][2].getEntities().get(0);
		Boulder boulder2 = (Boulder) tile[1][3].getEntities().get(0);
		
		//Move player to push boulder, player & the boulders should not move
		//as the player is unable to push more than two boulders at once
		player.setDirection(Direction.DOWN);
		dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		
		assertEquals(1, player.getX());
		assertEquals(1, player.getY());
		assertEquals(1, boulder1.getX());
		assertEquals(2, boulder1.getY());
		assertEquals(1, boulder2.getX());
		assertEquals(3, boulder2.getY());
	}

}
