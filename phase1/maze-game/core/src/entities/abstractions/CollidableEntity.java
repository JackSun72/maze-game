package entities.abstractions;

import geometry.Circle;
import manager.IEntityManager;
import geometry.Point;

public abstract class CollidableEntity extends Entity implements ICollidable{

    /**
     * Create a collidable entity with the position
     * @param pos position of the entity
     */
    public CollidableEntity(Point pos) {
        super(pos);
    }

    /**
     * Create a collidable entity with coordinate (x,y)
     * @param x the horizontal coordinate of the entity
     * @param y the vertical coordinate of the entity
     */
    public CollidableEntity(float x, float y) {
        super(x, y);
    }
    public CollidableEntity(Point pos, boolean needToBeKilled){super(pos, needToBeKilled);}
    public CollidableEntity(float x, float y, boolean needToBeKilled){super(x,y,needToBeKilled);}

    abstract public Circle getCollisionBox();
    @Override
    public void removeSelf(IEntityManager entityManager){
        entityManager.removeEntity(this);
    }
}
