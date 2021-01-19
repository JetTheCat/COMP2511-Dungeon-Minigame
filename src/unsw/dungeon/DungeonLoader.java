package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.*;
import unsw.staticEntity.*;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(0, width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject jsonGoals = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i), i);
        }
        dungeon.processJSONGoals(jsonGoals);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json, int id) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        switch (type) {
        case "player":
        	int uniquePlayerId = 1337;
            Player player = new Player(dungeon, x, y, uniquePlayerId);
            dungeon.setPlayer(player);
            dungeon.addEntity(player);
            dungeon.addTileEntity(player, x, y);
            onLoad(player);
            break;
        case "wall":
            Wall wall = new Wall(x, y, id);
            dungeon.addEntity(wall);
            dungeon.addTileEntity(wall, x, y);
            onLoad(wall);
            break;
        // TODO Handle other possible entities
        case "boulder":
        	Boulder boulder = new Boulder(x, y, id);
        	dungeon.addEntity(boulder);
        	dungeon.addTileEntity(boulder, x, y);
        	onLoad(boulder);
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, id);
        	dungeon.addEntity(sword);
        	dungeon.addTileEntity(sword, x, y);
        	onLoad(sword);
        	break;
        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(x, y, id);
        	dungeon.addEntity(potion);
        	dungeon.addTileEntity(potion, x, y);
        	onLoad(potion);
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y, id);
        	dungeon.addEntity(treasure);
        	dungeon.addTileEntity(treasure, x, y);
        	onLoad(treasure);
        	System.out.println("x: "+ x + "y: "+ y);
    		break;
        case "portal":
        	int portalId = json.getInt("id");
        	Portal portal = new Portal(x, y, portalId);
        	dungeon.addEntity(portal);
        	dungeon.addTileEntity(portal, x, y);
        	onLoad(portal);
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x, y, id);
        	dungeon.addEntity(enemy);
        	dungeon.addTileEntity(enemy, x, y);
        	onLoad(enemy);
        	break;
        case "switch":
        	FloorSwitch switchObj = new FloorSwitch(x, y, id);
        	dungeon.addEntity(switchObj);
        	dungeon.addTileEntity(switchObj, x, y);
        	onLoad(switchObj);
        	break;
        case "key":
        	int doorKeyId = json.getInt("id");
        	Key key = new Key(x, y, doorKeyId);
        	dungeon.addEntity(key);
        	dungeon.addTileEntity(key, x, y);
        	onLoad(key);
        	break;
        case "door":
        	doorKeyId = json.getInt("id");
        	Door door = new Door(x, y, doorKeyId);
        	dungeon.addEntity(door);
        	dungeon.addTileEntity(door, x, y);
        	onLoad(door);
        	break;  
        case "exit":
        	Exit exit = new Exit(x, y, id);
        	dungeon.addEntity(exit);
        	dungeon.addTileEntity(exit, x, y);
        	onLoad(exit);
        	break;
        case "trap":
        	Trap trap = new Trap(x, y, id);
        	dungeon.addEntity(trap);
        	dungeon.addTileEntity(trap, x, y);
        	onLoad(trap);
        	break;
        }

    }

    public abstract void onLoad(Entity obj);

}
