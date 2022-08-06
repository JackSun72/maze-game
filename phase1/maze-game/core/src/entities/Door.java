package entities;

import config.GameConstants;
import entities.item.Item;
import geometry.Circle;
import geometry.Point;
import graphics.door.IDoorDrawer;
/** Represents a door
 * @author Ethan
 * @author Ian Curtis Ewing\
 * @author Daniel
 */
public class Door extends CollidableEntity {

    public Room room;
    public Door door;
    private IDoorDrawer doorDrawer;
    private IRoomContainer roomContainer;

    /**
     * Create a door
     * @param pos Position of the door.
     * @param doorDrawer Drawer of the door.
     * @param roomContainer Container of the door.
     */
    public  Door(Point pos, IDoorDrawer doorDrawer, IRoomContainer roomContainer){
        super(pos);
        this.doorDrawer = doorDrawer;
        this.roomContainer = roomContainer;
    }

    /**
     * Set the door.
     * @param door The target door.
     */
    public void setCorrespondingDoor(Door door){
        this.door = door;
    }

    /**
     * Set the room
     * @param room The target room
     */
    public void setRoom(Room room){
        this.room = room;
    }

    /**
     * Draw the door
     */
    public void draw(){
        doorDrawer.drawDoor(pos);
    }

    /**
     * Return the collision box of the door.
     * @return Circle the circle representation of the collision box.
     */
    public Circle getCollisionBox(){
        return new Circle(pos, GameConstants.DOOR_RADIUS);
    }

    /**
     * Inform other object being collided with the door
     * @param other the object being collided.
     */
    @Override
    public void informCollision(ICollidable other) {
        other.collideWith(this);
    }

    @Override
    public void collideWith(Merchant merchant) {

    }

    /**
     * Collide with the other player
     * @param player the player being collided
     */
    public void collideWith(Player player){
        roomContainer.setNewRoom(door.room);
    }

    /**
     * Collide with the other enemy
     * @param enemy the enemy being collided
     */
    @Override
    public void collideWith(Enemy enemy) {

    }

    /**
     * Collide with the other door
     * @param door the door being collided
     */
    @Override
    public void collideWith(Door door) {

    }

    /**
     * Collide with the bullet
     * @param bullet the bullet being collided
     */
    @Override
    public void collideWith(Bullet bullet) {

    }

    @Override
    public void collideWith(Item item) {

    }
}

