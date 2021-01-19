package unsw.dynamicEntity;

import unsw.dungeon.*;
import unsw.dynamicBehaviour.EnemyMovement;
import unsw.dynamicBehaviour.KillPlayer;
import unsw.dynamicBehaviour.LightMass;
import unsw.dynamicBehaviour.NoDirectionMove;

/**
 * A hostile NPC within the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Enemy extends DynamicEntity {
	
	private boolean isAlive;

	/**
	 * Constructs an enemy who will chase after and kill the player.
	 * Can also be killed by the player with a sword or invincibility potion.
     * @param x the x coordinate of the entity
     * @param y the y coordinate of the entity
     * @param id the unique identifier for the entity
	 */
	public Enemy(int x, int y, int ID) {
        super(x, y, ID, "/deep_elf_master_archer.png", new LightMass(), new KillPlayer(), new EnemyMovement()); 
        this.isAlive = true;
	}
	

	/**
	 * Moves the enemy up
	 * @param tile the tiles of the dungeon grid
	 */
	public void moveUp(Tile[][] tile) {
		int x = super.getX();
		int y = super.getY();
		
		tile[x][y].removeEnemies();
		y--;
		tile[x][y].addEntity(this);
		setX(x);
		setY(y);
	}
	
	/**
	 * Moves the enemy down
	 * @param tile the tiles of the dungeon grid
	 */
	public void moveDown(Tile[][] tile) {
		int x = super.getX();
		int y = super.getY();
		
		tile[x][y].removeEnemies();
		y++;
		tile[x][y].addEntity(this);
		setX(x);
		setY(y);
	}
	
	/**
	 * Moves the enemy left
	 * @param tile the tiles of the dungeon grid
	 */
	public void moveLeft(Tile[][] tile) {
		int x = super.getX();
		int y = super.getY();
		
		tile[x][y].removeEnemies();
		x--;
		tile[x][y].addEntity(this);
		setX(x);
		setY(y);
	}
	
	/**
	 * Moves the enemy right
	 * @param tile the tiles of the dungeon grid
	 */
	public void moveRight(Tile[][] tile) {
		int x = super.getX();
		int y = super.getY();
		
		tile[x][y].removeEnemies();
		x++;
		tile[x][y].addEntity(this);
		setX(x);
		setY(y);
	}

	/**
	 * Method executes move enemy, which tries to move the enemy close to the player.
	 * @player the player the enemy is trying to move closer towards
	 * @param dungeonController the controller for the dungeon
	 */
	public void moveEnemy(Player player, DungeonController dungeonController) {
		executeMoveBehaviour(player.getX(), player.getY(), dungeonController);
		
	}
	
	/**
	 * Method sets enemy isAlive state to false;
	 */
	public void killEnemy() {
		this.isAlive = false;
	}
	
	/**
	 * Get enemy live status
	 */
	public boolean isAlive() {
		return this.isAlive;
	}

}
