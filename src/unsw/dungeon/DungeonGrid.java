package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import unsw.dungeon.*;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.Boulder;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticEntity.StaticEntity;

/**
 * A grid for the entities in the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class DungeonGrid {

    private Integer width;
    private Integer height;
    private Tile tiles[][];
    private Integer id;

    /**
     * Constructs a dungeon grid.
     * A dungeon grid holds and entity's position within the dungeon.
     * Also creates all the tiles within the dungeon.
     * @param width the width of the grid
     * @param height the height of the grid
     * @param id the unique identifier of the dungeon
     */
    public DungeonGrid(Integer width, Integer height, Integer id) {
        this.width = width;
        this.height = height;
        this.id = id;
        this.tiles = new Tile[width][height];
        this.generateTiles(width, height);
    }
    
    /**
     * Local private function to aid the constructor
     * to fill tiles[][] with Tile objects
     * @param x the width of the Grid
     * @param y the height of the Grid
     */
    private void generateTiles(int x, int y) {
    	int tileid = 0;
    	
    	for(int i = 0; i < width; i++) {
    		for(int j = 0; j < height; j++) {
    			this.tiles[i][j] = new Tile(tileid);
    			tileid++;
    		}
    	}
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

	/** 
	 * Adds an entity to a specific tile
	 * @param obj the entity that is being added to the tile
	 * @param x the x coordinate of the tile the entity is being added to
	 * @param y the y coordinate of the tile the entity is being added to
	 */
    public void addEntity(Entity obj, Integer x, Integer y) {
        this.tiles[x][y].addEntity(obj);
    }

	/** 
	 * Removes an entity from a specific tile
	 * @param obj the entity that is being removed from the tile
	 * @param x the x coordinate of the tile the entity is being removed from
	 * @param y the y coordinate of the tile the entity is being removed from
	 */
    public void removeEntity(Entity obj, Integer x, Integer y) {
        this.tiles[x][y].removeEntity(obj);
    }

    /**
     * Method checks the DynamicEntity's interaction with all the entities in the tile they are moving into.
     * Method has different interactions depending on whether the entity will move or stay still.
     * @param player the DynamicEntity that is trying to move onto a new tile
     * @param x the x coordinate of the tile the entity is trying to move onto
     * @param y the y coordinate of the tile the entity is trying to move onto
     * @param controller the controller for the dungeon
     */
    public void playerInteraction(DynamicEntity player, Integer x, Integer y, DungeonController controller) {
    	ArrayList<Entity> entities = tiles[x][y].getEntities();
    	
    	int size = entities.size();
    	
    	int count = 0;
    	boolean isBoulder = false;
    	
        for (int i = 0; i < size; i++) {
        	Entity entity = entities.get(i);
        	if (entity instanceof DynamicEntity) {

        		DynamicEntity dEntity = (DynamicEntity) entity;
        		player.executeCheckInteraction(dEntity, x, y, controller);
        		if(entity instanceof Boulder && entity.getX() == x && entity.getY() == y) {
        			isBoulder = true;
        		}
        		count ++;
        		break;
        	}
		}
        
        if(!isBoulder) {
        	for (int i = 0; i < size; i++) {
            	Entity entity = entities.get(i);
            	if (entity instanceof StaticEntity) {
            		StaticEntity sEntity = (StaticEntity) entity;
            		sEntity.executeInteract(player, x, y, controller);
            		count ++;
            	}
    		}
        }
        
        

        // If no surrounding entities blocking player, move
        // them to the desired position
	    if (count == 0) {		
	    	player.executeMoveBehaviour(x,y, controller);
	    }
    }
    
    public Tile[][] getTiles(){
    	return tiles;
    }

}