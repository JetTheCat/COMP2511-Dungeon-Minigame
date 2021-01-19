package unsw.staticBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Enemy;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.StaticEntity;
import unsw.staticEntity.Trap;

public class TrapBehaviour implements InteractBehaviour{

	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity,
			DungeonController controller) {
		Tile[][] tiles = controller.getTiles();
		Trap trap = (Trap) entity;
		if (movingEntity instanceof Player) {
			if (trap.isSet()) {
				controller.loseGame();
			} else {
				Player player = (Player) movingEntity;
				player.addToBackpack(trap);
				player.setHoldingTrap(true);
    			tiles[x][y].removeEntity(entity);
    			controller.removeFromTile(entity);
    			movingEntity.executeMoveBehaviour(x, y, controller);
				controller.modifyState("trap");
			}
		} else if (movingEntity instanceof Enemy) {
			if (trap.isSet()) {
				tiles[movingEntity.getX()][movingEntity.getY()].killEnemies(controller);
				tiles[x][y].removeEntity(entity);
    			controller.removeFromTile(entity);
				for(GoalObject curr : controller.getGoalList()) {
					if(curr.getGoal().equals("enemies")) {
						curr.decreaseState();
						controller.modifyState("enemies");
						break;
					}
				}
				if(controller.checkGoal()) {
					controller.winGame();
				}
			}
		}
	}

}
