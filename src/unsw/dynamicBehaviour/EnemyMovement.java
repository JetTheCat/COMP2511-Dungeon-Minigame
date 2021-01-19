package unsw.dynamicBehaviour;

import java.util.ArrayList;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Entity;
import unsw.dungeon.Tile;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Enemy;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.Door;
import unsw.staticEntity.FloorSwitch;
import unsw.staticEntity.StaticEntity;
import unsw.staticEntity.Trap;

/**
 * The way an enemy moves towards a player
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class EnemyMovement implements DynamicMoveBehaviour {

	/**
	 * Moves the entity to a particular tile.
	 * The enemy tries to move closer to the player if they are not invincible,
	 * but runs away if the player is invincible.
	 * @param entity the entity that is moving
	 * @param x the x coordinate that the player wants to move to
	 * @param y the y coordinate that the player wants to move to
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void move(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		Player player = controller.getPlayer();
		Enemy enemy = (Enemy) entity;
		//System.out.println("player x = " + player.getX() + " player y = " + player.getY() + " enemy x = " + entity.getX() + " enemy y = " +  entity.getY());
		if (player.isInvincible()) {
			runAwayFromPlayer(player, enemy, controller);
		} else {
			moveToPlayer(player, enemy, controller);
			
		}

	}
	
	/**
	 * Method makes the enemy run away from the player
	 * @param player the player that is being run away from
	 * @param enemy the enemy that is running away
	 * @param controller the controller for the dungeon
	 */
	private void runAwayFromPlayer(Player player, Enemy enemy, DungeonController controller) {
		Tile[][] tile = controller.getTiles();
		if (player.getX() > enemy.getX()){
			if(checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)) {
				enemy.moveLeft(tile);
			} else if (player.getY() < enemy.getY() && checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)){
				enemy.moveDown(tile);
			} else if (checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)){
				enemy.moveUp(tile);		
			}
		}	
		else if (player.getX() < enemy.getX()){
			if(checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)) {
				enemy.moveRight(tile);
			} else if (player.getY() < enemy.getY() && checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)){
				enemy.moveDown(tile);
			} else if (checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)){
				enemy.moveUp(tile);		
			}
		} else if (player.getY() > enemy.getY()){
			if(checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)) {
				enemy.moveUp(tile);
			} else if (player.getX() < enemy.getX() && checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)){
				enemy.moveRight(tile);
			} else if (checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)){
				enemy.moveLeft(tile);		
			}
		} else if (player.getY() < enemy.getY()){
			if(checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)) {
				enemy.moveDown(tile);
			} else if (player.getX() < enemy.getX() && checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)){
				enemy.moveRight(tile);
			} else if (checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)){
				enemy.moveLeft(tile);	

			}
		}
		
	}

	/**
	 * Method makes the enemy move to the player
	 * @param player the player that is being moved towards
	 * @param enemy the enemy that is moving towards the player
	 * @param controller the controller for the dungeon
	 */
	private void moveToPlayer(Player player, Enemy enemy, DungeonController controller) {
		Tile[][] tile = controller.getTiles();
		if (player.getX() < enemy.getX()){
			if(checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)) {
				enemy.moveLeft(tile);
			} else if (player.getY() < enemy.getY() && checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)){
				enemy.moveUp(tile);		
			} else if (checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)){
				enemy.moveDown(tile);
			}
		}	
		else if (player.getX() > enemy.getX()){
			if(checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)) {
				enemy.moveRight(tile);
			} else if (player.getY() < enemy.getY() && checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)){
				enemy.moveUp(tile);		
			} else if (checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)){
				enemy.moveDown(tile);
			}
		} else if (player.getY() < enemy.getY()){
			if(checkBlockable(enemy.getX(), enemy.getY() - 1, controller, enemy)) {
				enemy.moveUp(tile);
			} else if (player.getX() < enemy.getX() && checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)){
				enemy.moveLeft(tile);		
			} else if (checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)){
				enemy.moveRight(tile);
			}
		} else if (player.getY() > enemy.getY()){
			if(checkBlockable(enemy.getX(), enemy.getY() + 1, controller, enemy)) {
				enemy.moveDown(tile);
			} else if (player.getX() < enemy.getX() && checkBlockable(enemy.getX() - 1, enemy.getY(), controller, enemy)){
				enemy.moveLeft(tile);		
			} else if (checkBlockable(enemy.getX() + 1, enemy.getY(), controller, enemy)){
				enemy.moveRight(tile);
			}
		}

		if (player.getX() == enemy.getX() && player.getY() == enemy.getY()){
			enemy.executeCheckInteraction(player, player.getX(), player.getY(), controller);
		}

	}
	
	/**
	 * Method checks if the enemy can move into the tile.
	 * @param x the x coordinate of the tile
	 * @param y the y coordinate of the tile
	 * @param tile a 2d array of all tiles
	 * @return true if the enemy can move onto the tile, false otherwise
	 */
	private boolean checkBlockable(int x, int y, DungeonController controller, DynamicEntity enemy) {
		Tile[][] tile = controller.getTiles();
		ArrayList<Entity> list = tile[x][y].getEntities();
		int size = list.size();
		int count = 0;
		for(Entity tmp : list) {
			if (tmp instanceof DynamicEntity) {
				if (tmp instanceof Player) {
					return true;
				}
			}
			count ++;
		}
		for(int i = 0; i < size; i ++) {
			Entity tmp = list.get(i);
			if (tmp instanceof StaticEntity) {
				if(tmp instanceof Door) {
					Door door = (Door) tmp;
					if(door.isOpened()) {
						return true;
					}
				} else if (tmp instanceof FloorSwitch) {
					return true;
				} else if (tmp instanceof Trap) {
					((StaticEntity) tmp).executeInteract(enemy, x, y, controller);
				}
			}
			count ++;
		}
		return count == 0;
	}
}
