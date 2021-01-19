package unsw.dungeonTest;


import unsw.dungeon.*;
import unsw.dungeonComposite.*;
import unsw.dungeonState.*;
import unsw.dynamicBehaviour.*;
import unsw.dynamicEntity.*;
import unsw.staticBehaviour.*;
import unsw.staticEntity.*;

import org.json.JSONArray;
import org.json.JSONObject;




import java.util.ArrayList;
import java.util.List;

public class DungeonBuilder {
	
	
	/**
	 * Function to build a complete dungeon filled with player intractable entities
	 * @param hasEnemy - boolean value to indicate whether to add enemies in a dungeon, true indicates add enemy
	 * @return Dungeon object that is complete for testing
	 */
	public Dungeon buildDungeon(boolean hasEnemy) {
		int id = 0;
		
		List<Entity> entList = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1, 12, 12);

		// Construct dungeon entities
		Player player = new Player(dungeon, 1, 1, id++);
		entList.add(player);
		dungeon.setPlayer(player);
		Sword sword = new Sword(6, 1, id++);
		entList.add(sword);
		Portal portal1 = new Portal(2, 10, id);
		entList.add(portal1);
		Portal portal2 = new Portal(7, 8, id++);
		entList.add(portal2);
		Portal portal3 = new Portal(2, 6 , id);
		entList.add(portal3);
		Portal portal4 = new Portal(8, 10 ,id++);
		entList.add(portal4);

		
		// Adds enemy to dungeon if read true 
		if(hasEnemy) {
			Enemy enemy = new Enemy(3, 6, id++);
			entList.add(enemy);
		}
		
		InvincibilityPotion potion = new InvincibilityPotion(5, 6, id++);
		entList.add(potion);
		Treasure treasure = new Treasure(10, 2, id++);
		entList.add(treasure);
		Key key = new Key(1, 10, id);
		entList.add(key);
		Door door = new Door(10, 8, id++);
		entList.add(door);
		Boulder boulder = new Boulder(8, 5, id++);
		entList.add(boulder);
		Boulder boulder2 = new Boulder(9, 1, id++);
		entList.add(boulder2);
		FloorSwitch floorSwitch = new FloorSwitch(10, 5, id++);
		entList.add(floorSwitch);
		Exit exit = new Exit(10, 10, id++);
		entList.add(exit);
		
		// Construct walls surrounding dungeon
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				if(i == 0 || i == 11) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
					continue;
				}
				else if(j == 0 || j == 11) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
				}
			}

		}
		
		
		// Adding custom walls in dungeon
		Wall wall = new Wall(2, 2, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(2, 3, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 3, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		
		wall = new Wall(2, 7, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 7, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(4, 7, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(2, 8, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 8, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(4, 8, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		
		wall = new Wall(6, 2, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(7, 2, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(8, 2, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		
		wall = new Wall(9, 8, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(9, 9, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(9, 10, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		
		
		
		// Add entities into dungeon
		for(Entity obj : entList) {
			dungeon.addEntity(obj);
			dungeon.addTileEntity(obj, obj.getX(), obj.getY());
		}
		

		
		return dungeon;
	}
	
	
	
	/**
	 * Function to build a dungeon layout suitable for testing player sword interaction against an enemy
	 * @return dungeon object that is fully customized for testing player sword interaction
	 */
	public Dungeon swordTestDungeon() {
 
        int id = 0;

        List<Entity> entList = new ArrayList<Entity>();
        Dungeon dungeon = new Dungeon(1, 3, 12);

        // Construct dungeon entities
        Player player = new Player(dungeon, 1, 1, id++);
        entList.add(player);
        dungeon.setPlayer(player);
        Sword sword = new Sword(1, 2, id++);
        entList.add(sword);
        Enemy enemy = new Enemy(1, 4, id++);
        entList.add(enemy);
        Enemy enemy2 = new Enemy(1, 5, id++);
        entList.add(enemy2);
        Enemy enemy3 = new Enemy(1, 6, id++);
        entList.add(enemy3);
        Enemy enemy4 = new Enemy(1, 7, id++);
        entList.add(enemy4);
        Enemy enemy5 = new Enemy(1, 8, id++);
        entList.add(enemy5);
        Sword sword2 = new Sword(1, 9, id++);
        entList.add(sword2);
        Sword sword3 = new Sword(1, 10, id++);
        entList.add(sword3);



        // Construct walls surrounding dungeon
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 12; j++) {
                if(i == 0 || i == 2) {
                    Wall wall = new Wall(i, j, id++);
                    dungeon.addTileEntity(wall, i, j);
                    continue;
                }
                else if(j == 0 || j == 11) {
                    Wall wall = new Wall(i, j, id++);
                    dungeon.addTileEntity(wall, i, j);
                }
            }
        }



        // Add entities into dungeon
        for(Entity obj : entList) {
            dungeon.addEntity(obj);
            dungeon.addTileEntity(obj, obj.getX(), obj.getY());
        }


        return dungeon;
    }
		
	/**
	 * Function to build a dungeon layout suitable for testing invincibility potion interaction against an enemy
	 * @return dungeon object that is fully customized for testing invincibility potion effect on Enemy entities
	 */
	public Dungeon potionTestDungeon(boolean hasEnemy) {
		int id = 0;
		
		List<Entity> entList = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1, 3, 17);

		// Construct dungeon entities
		Player player = new Player(dungeon, 1, 1, id++);
		entList.add(player);
		dungeon.setPlayer(player);
		
		if(hasEnemy) {
			Enemy enemy = new Enemy(1, 15, id++);
			entList.add(enemy);
			InvincibilityPotion potion = new InvincibilityPotion(1, 6, id++);
			entList.add(potion);
		}

		else {
			InvincibilityPotion potion = new InvincibilityPotion(1, 2, id++);
			entList.add(potion);
		}
		
		
		
		
		// Construct walls surrounding dungeon
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 17; j++) {
				
				if(i == 0 || i == 2) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
					continue;
				}
				else if(j == 0 || j == 16) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
				}
			}
		}
		
		
		
		// Add entities into dungeon
		for(Entity obj : entList) {
			dungeon.addEntity(obj);
			dungeon.addTileEntity(obj, obj.getX(), obj.getY());
		}
		

		return dungeon;
	}
	
	/**
	 * Method creates a dungeon for testing the interactions between the treasure
	 * and any moving entities
	 * @return dungeon suitable for testing treasure interactions
	 */
	public Dungeon treasureDungeon() {
		int id = 0;

	    List<Entity> entList = new ArrayList<Entity>();
	    Dungeon dungeon = new Dungeon(1, 3, 12);

	    // Construct dungeon entities
	    Player player = new Player(dungeon, 1, 1, id++);
	    entList.add(player);
	    dungeon.setPlayer(player);
	    Treasure treasure1 = new Treasure(1, 2, id++);
	    entList.add(treasure1);
	    Treasure treasure2 = new Treasure(1, 3, id++);
	    entList.add(treasure2);
	    Boulder boulder = new Boulder(1, 4, id++);
	    entList.add(boulder);
	    Treasure treasure3 = new Treasure(1, 5, id++);
	    entList.add(treasure3);
	    Treasure treasure4 = new Treasure(1, 6, id++);
	    entList.add(treasure4);
	    Enemy enemy = new Enemy(1, 7, id++);
	    entList.add(enemy);


	    // Construct walls surrounding dungeon
	    for(int i = 0; i < 3; i++) {
	        for(int j = 0; j < 12; j++) {
	            if(i == 0 || i == 2) {
	                Wall wall = new Wall(i, j, id++);
	                dungeon.addTileEntity(wall, i, j);
	                continue;
	            }
	            else if(j == 0 || j == 11) {
	                Wall wall = new Wall(i, j, id++);
	                dungeon.addTileEntity(wall, i, j);
	            }
	        }
	    }

	    // Add entities into dungeon
	    for(Entity obj : entList) {
	        dungeon.addEntity(obj);
	        dungeon.addTileEntity(obj, obj.getX(), obj.getY());
	    }


	    return dungeon;
	}
	
	
	/**
	 * Methods creates a dungeon to test enemy interaction with a boulder
	 * @return dungeon suitable for testing enemy interaction with boulders
	 */
	public Dungeon EnemyBoulderDungeon() {
		int id = 0;
		List<Entity> entList = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1, 3, 8);

		// Construct dungeon entities
		Player player = new Player(dungeon, 1, 1, id++);
		entList.add(player);
		dungeon.setPlayer(player);
		Boulder boulder = new Boulder(1, 3, id++);
		entList.add(boulder);
		Enemy enemy = new Enemy(1, 5, id++);
		entList.add(enemy);
		
		
		// Construct walls surrounding dungeon
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				
				if(i == 0 || i == 2) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
					continue;
				}
				else if(j == 0 || j == 7) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
				}
			}
		}
		
		// Add entities into dungeon
		for(Entity obj : entList) {
			dungeon.addEntity(obj);
			dungeon.addTileEntity(obj, obj.getX(), obj.getY());
		}
		

		return dungeon;
	}
	
	
	/**
	 * Method creates a dungeon suitable to test interaction between two boulders
	 * @return dungeon suitable for testing boulder interaction with another boulder
	 */
	public Dungeon BoulderInteractionDungeon() {
		int id = 0;
		List<Entity> entList = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1, 3, 6);

		// Construct dungeon entities
		Player player = new Player(dungeon, 1, 1, id++);
		entList.add(player);
		dungeon.setPlayer(player);
		Boulder boulder = new Boulder(1, 2, id++);
		entList.add(boulder);
		Boulder boulder2 = new Boulder(1, 3, id++);
		entList.add(boulder2);

		
		
		// Construct walls surrounding dungeon
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 6; j++) {
				
				if(i == 0 || i == 2) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
					continue;
				}
				else if(j == 0 || j == 5) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
				}
			}
		}
		
		// Add entities into dungeon
		for(Entity obj : entList) {
			dungeon.addEntity(obj);
			dungeon.addTileEntity(obj, obj.getX(), obj.getY());
		}
		

		return dungeon;
	}
	
	
	/**
	 * Method creates a dungeon suitable for testing the interaction between
	 * entities and doors.
	 * @param hasEnemy boolean value to indicate whether the dungeon will have enemies
	 * @return a dungeon for testing door interactions
	 */
	public Dungeon doorDungeon(boolean hasEnemy) {
		int id = 0;
		
		List<Entity> entList = new ArrayList<Entity>();
		Dungeon dungeon = new Dungeon(1, 12, 12);

		// Construct dungeon entities
		Player player = new Player(dungeon, 1, 1, id++);
		entList.add(player);
		dungeon.setPlayer(player);
		// Adds enemy to dungeon if read true 
		if(hasEnemy) {
			Enemy enemy = new Enemy(10, 9, id++);
			entList.add(enemy);
		}
		
		// Add door, keys and other entities.
		Key key = new Key(1, 10, id);
		entList.add(key);
		Door door = new Door(10, 8, id++);
		entList.add(door);
		door = new Door (3, 1, id);
		entList.add(door);
		key = new Key (1, 3, id++);
		entList.add(key);
		door = new Door(9,9, id);
		entList.add(door);
		key = new Key(2,3, id++);
		entList.add(key);
		Boulder boulder = new Boulder(6,1, id ++);
		entList.add(boulder);
		
		// Construct walls surrounding dungeon
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				
				if(i == 0 || i == 11) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
					continue;
				}
				else if(j == 0 || j == 11) {
					Wall wall = new Wall(i, j, id++);
					dungeon.addTileEntity(wall, i, j);
				}
			}
		}
		
		// Adding custom walls in dungeon
		Wall wall = new Wall(1, 4, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(2, 4, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 4, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 3, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(3, 2, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(9, 8, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(9, 10, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		wall = new Wall(10, 10, id++);
		dungeon.addTileEntity(wall, wall.getX(), wall.getY());
		
		// Add entities into dungeon
		for(Entity obj : entList) {
			dungeon.addEntity(obj);
			dungeon.addTileEntity(obj, obj.getX(), obj.getY());
		}
		

		
		return dungeon;
	}
	
	/**
	 * Method creates a dungeon suitable for testing enemy movement and behaviour
	 * @return a dungeon for testing enemy interactions
	 */
	public Dungeon enemyDungeon() {
		int id = 0;

        List<Entity> entList = new ArrayList<Entity>();
        Dungeon dungeon = new Dungeon(1, 3, 12);

        // Construct dungeon entities
        InvincibilityPotion potion = new InvincibilityPotion(1, 1, id++);
        entList.add(potion);
        
        Sword sword = new Sword(1, 2, id++);
        entList.add(sword);
        
        Player player = new Player(dungeon, 1, 3, id++);
        entList.add(player);
        dungeon.setPlayer(player);

        Enemy enemy = new Enemy(1, 10, id++);
        entList.add(enemy);

        Enemy enemy2 = new Enemy(1, 8, id++);
        entList.add(enemy2);

        // Construct walls surrounding dungeon
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 12; j++) {

                if(i == 0 || i == 2) {
                    Wall wall = new Wall(i, j, id++);
                    dungeon.addTileEntity(wall, i, j);
                    continue;
                }
                else if(j == 0 || j == 11) {
                    Wall wall = new Wall(i, j, id++);
                    dungeon.addTileEntity(wall, i, j);
                }
            }
        }



        // Add entities into dungeon
        for(Entity obj : entList) {
            dungeon.addEntity(obj);
            dungeon.addTileEntity(obj, obj.getX(), obj.getY());
        }


        return dungeon;
	}
	
	/**
	 * Function to construct a JSONObject containing preset dungeon goals
	 * @return JSONObject containing dungeon goals
	 */
	public JSONObject buildDungeonGoals() {
			// Test goal condition in JSONObject form
			// borrowed from 2511 assignment specs
			JSONObject enemies = new JSONObject();
			JSONObject treasureGoal = new JSONObject();
			enemies.put("goal","enemies");
			treasureGoal.put("goal", "treasure");
			
			JSONArray subgoal2 = new JSONArray();
			subgoal2.put(enemies);
			subgoal2.put(treasureGoal);
			
			JSONObject OR = new JSONObject();
			OR.put("goal", "OR");
			OR.put("subgoals", subgoal2);
			
			JSONObject exit1 = new JSONObject();
			exit1.put("goal", "exit");
			
			JSONArray subgoal1 = new JSONArray();
			subgoal1.put(exit1);
			subgoal1.put(OR);
			
			JSONObject goals = new JSONObject();
			goals.put("goal", "AND");
			goals.put("subgoals", subgoal1);

			//System.out.println("Goals: " + goals);

			
			return goals;
	}
	
	
	/**
	 * Function to construct a JSONObject containing the goal to collect all treasure AND reach
	 * the exit.
	 * @return JSONObject containing dungeon goals
	 */
	public JSONObject buildTreasureExitGoal() {
		JSONObject treasureGoal = new JSONObject();
		treasureGoal.put("goal", "treasure");
		JSONObject exit1 = new JSONObject();
		exit1.put("goal", "exit");
		JSONArray subgoal1 = new JSONArray();
		subgoal1.put(exit1);
		subgoal1.put(treasureGoal);
		JSONObject goals = new JSONObject();
		goals.put("goal", "AND");
		goals.put("subgoals", subgoal1);
		
		System.out.println("Goals: " + goals);
		
		return goals;
	}
}
