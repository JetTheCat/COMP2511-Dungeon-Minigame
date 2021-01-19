package unsw.dungeon;

import java.util.ArrayList;

import unsw.dynamicEntity.Enemy;

/**
 * A single tile that is a part of the dungeon grid.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Tile {
	private ArrayList<Entity> entities;
	private Integer ID;
	
	/**
	 * Constructs a tile. 
	 * A tile has a unique identifier and a list of entities that are on it.
	 * @param ID the unique identifier of the tile.
	 */
	public Tile(int ID) {
		this.ID = ID;
		this.entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public Integer getID() {
		return ID;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * Method checks if there is an enemy in the tile and removes it
	 * @return true if the enemy has been removed, false otherwise.
	 */
	public boolean removeEnemies() {
		boolean result = false;
		for (Entity entity : entities) {
			if (entity instanceof Enemy) {
				removeEntity(entity);
				result = true;
				break;
			}
		}
		return result;
	}
	

	/**
	 * Method checks if there is an enemy in the tile, removes and kill it
	 * @param controller 
	 * @return true if the enemy has been removed, false otherwise.
	 */
	public boolean killEnemies(DungeonController controller) {
		boolean result = false;
		for (Entity entity : entities) {
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				enemy.killEnemy();
				removeEntity(entity);
				controller.removeFromTile(entity);
				result = true;
				break;
			}
		}
		return result;
	}

}
