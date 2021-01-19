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

public class PortalTest {
	DungeonBuilder builder = new DungeonBuilder();
	Dungeon dungeon = builder.buildDungeon(false);
	JSONObject goals = builder.buildDungeonGoals();
	List<ImageView> viewList = new ArrayList<ImageView>();
	
	DungeonController controller = new DungeonController(dungeon, viewList);
	Player player = controller.getPlayer();
	
	@Test
	public void testing() {
		Tile[][] tmp = dungeon.getTiles();
		ArrayList<Entity> portal1TileEntities = tmp[2][10].getEntities();
		ArrayList<Entity> portal2TileEntities = tmp[7][8].getEntities();
		checkPortalTiles(portal1TileEntities, portal2TileEntities);
		
		ArrayList<Entity> portal3TileEntities = tmp[2][6].getEntities();
		ArrayList<Entity> portal4TileEntities = tmp[8][10].getEntities();
		checkPortalTiles(portal3TileEntities, portal4TileEntities);
		
		//Move player to portal 1
		player.setDirection(Direction.DOWN);
		for (int i = 0; i < 9; i++) {
			dungeon.checkInteraction(player.getX(), player.getY()+1, controller);
		}
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		portal1TileEntities = tmp[2][10].getEntities();
		portal2TileEntities = tmp[7][8].getEntities();
		assertEquals(1, portal1TileEntities.size());
		assertEquals(2, portal2TileEntities.size());
		assertEquals(7, player.getX());
		assertEquals(8, player.getY());
		
		// Move player back through portal 2
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		portal1TileEntities = tmp[2][10].getEntities();
		portal2TileEntities = tmp[7][8].getEntities();
		assertEquals(2, portal1TileEntities.size());
		assertEquals(1, portal2TileEntities.size());
		assertEquals(2, player.getX());
		assertEquals(10, player.getY());
		
		// Move player to portal 3
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() , player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX() , player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX() , player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX() , player.getY() - 1, controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		portal3TileEntities = tmp[2][6].getEntities();
		portal4TileEntities = tmp[8][10].getEntities();
		assertEquals(1, portal3TileEntities.size());
		assertEquals(2, portal4TileEntities.size());
		assertEquals(8, player.getX());
		assertEquals(10, player.getY());
		
		// Move player back through portal 4
		dungeon.checkInteraction(player.getX() - 1, player.getY(), controller);
		dungeon.checkInteraction(player.getX() + 1, player.getY(), controller);
		portal3TileEntities = tmp[2][6].getEntities();
		portal4TileEntities = tmp[8][10].getEntities();
		assertEquals(2, portal3TileEntities.size());
		assertEquals(1, portal4TileEntities.size());
		assertEquals(2, player.getX());
		assertEquals(6, player.getY());
	}
	
	/**
	 * Method checks that the tile only contain one portal and that these portals 
	 * correspond to each others ID.
	 * @param portal1Tile the list of entities on the tile containing the first portal
	 * @param portal2Tile the list of entities on the tile containing the second portal
	 */
	private void checkPortalTiles(ArrayList<Entity> portal1Tile, ArrayList<Entity> portal2Tile) {
		// Count checks that there is only one portal in the tile
		int count = 0;
		Portal portal1 = null;
		Portal portal2 = null;
		for (Entity entity : portal1Tile) {
			count ++;
			if (entity instanceof Portal) {
				portal1 = (Portal) entity;
			}
			
		}
		assertNotNull(portal1);
		assertEquals(1, count);
		
		
		count = 0;
		for (Entity entity : portal2Tile) {
			count ++;
			if (entity instanceof Portal) {
				portal2 = (Portal) entity;
			}
			
		}
		assertNotNull(portal2);
		assertEquals(1, count);
		// Check ID is the same for both
		assertEquals(portal2.getID(), portal1.getID());
	}
	

}
