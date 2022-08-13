package game.entities.abstractions;

import manager.IEntityManager;
import geometry.Point;
import graphics.presenters.IDrawable;

/** Represents an entity
 * @author Ethan
 * @author Ian Curtis Ewing
 * @author Daniel
 */
public abstract class Entity implements IDrawable {
    public Point pos;
    public boolean needToBeKilled;

    /**
     * Create an entity
     * @param x the x-coordinate of the entity
     * @param y the x-coordinate of the entity
     */
    public Entity(final float x, final float y) {
        pos = new Point(x,y);
        this.needToBeKilled = false;
    }

    /**
     * Create an entity
     * @param pos the position of the entity
     */
    public Entity(final Point pos) {
        this.pos = pos;
        this.needToBeKilled = false;
    }
    public Entity(final Point pos, final boolean needToBeKilled){
        this.pos = pos;
        this.needToBeKilled = needToBeKilled;
    }
    public Entity(final float x, final float y, final boolean needToBeKilled){
        pos = new Point(x,y);
        this.needToBeKilled = needToBeKilled;
    }

    /**
     * Draw the entity.
     */
    @Override
    public abstract void draw();

    /**
     * Update the entity.
     */
    public void update() {

    }

    /**
     * Determine whether the entity should be removed.
     * @return it should not be removed.
     */
    public boolean shouldBeRemoved(){
        return false;
    }

    /**
     * This method exists to take advantage of overloading with the type of entity.
     */
    public void removeSelf(final IEntityManager entityManager){
        entityManager.removeEntity(this);
    }
}
