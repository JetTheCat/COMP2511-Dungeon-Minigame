package unsw.staticEntity;

import unsw.dynamicEntity.Player;
import unsw.staticBehaviour.DrinkPotion;

/**
 * A potion that will give the player invincibility
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class InvincibilityPotion extends StaticEntity {
	Integer duration;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * An invincibility potion can be drunk by the player and let's them
	 * kill enemies by walking over them. Lasts for a limited amount of steps.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public InvincibilityPotion(int x, int y, int ID) {
		super(x,y,ID, "/brilliant_blue_new.png", new DrinkPotion());
		this.duration = 10;
	}
	
	/**
	 * Sets the player to be invincible.
	 * @param player the player that is now invincible
	 */
	public void changeInvincibilityState(Player player) {
		player.addInvincible();
	}
	
	/**
	 * Reduces the duration of the invincibility effect.
	 * @param player the player that is invincible
	 */
	public void decreaseDuration(Player player) {
		if(duration > 0)
			duration--;
		if (duration == 0) {
			player.removeFromBackpack(this);
			player.removeInvincible();
		}
	}
	
	public Integer getRemainingDuration() {
		return this.duration;
	}
}
