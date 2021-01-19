package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 * Modified by Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.

	private String imageFile;
    private IntegerProperty x, y;
    private Integer ID;

    /**
     * Constructs an entity. They have an x and y coordinate and a unique identifier
     * @param x the x coordinate of the entity
     * @param y the y coordinate of the entity
     * @param id the unique identifier for the entity
     */
    public Entity(int x, int y, int id, String imageFile) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.imageFile = imageFile;
        this.ID = id;
    }

    public IntegerProperty x() {
        return x;
    }

	public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setX(int x) {
    	x().set(x);
    }
    
    public void setY(int y) {
    	y().set(y);
    }

    public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getImageFile() {
		return this.imageFile;
	}
}
