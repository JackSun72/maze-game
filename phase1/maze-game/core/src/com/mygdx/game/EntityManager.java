package com.mygdx.game;

import com.mygdx.game.Entities.CollidableEntity;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.ICollidable;


import java.util.ArrayList;
import java.lang.*;

/**
 * Represents an entity manager.
 * @author Ian
 * @author Jack Sun
 * @author Daniel
 * @author Ethan
 */
public class EntityManager implements IEntityManager{
    private final ArrayList<Entity> Entities;
    private final ArrayList<CollidableEntity> collidableEntities;
    private final ArrayList<Entity> toBeAddedEntities;
    private final ArrayList<Entity> toBeRemovedEntities;
    private final ArrayList<CollidableEntity> toBeAddedCollidable;
    private final ArrayList<CollidableEntity> toBeRemovedCollidable;

    private int gold = 0;



    /**
     * Create an entity manager
     */
    public EntityManager() {
        Entities = new ArrayList<>();
        collidableEntities = new ArrayList<>();
        toBeAddedEntities = new ArrayList<>();
        toBeRemovedEntities = new ArrayList<>();
        toBeAddedCollidable = new ArrayList<>();
        toBeRemovedCollidable = new ArrayList<>();
    }

    /**
     * Add a non-collidable entity to manager
     * @param ent an entity
     */
    public void addNonCollidableEntity (Entity ent) {toBeAddedEntities.add(ent);}

    /**
     * Add a collidable entity to manager
     * @param ent a collidable entity
     */
    public void addCollidableEntity (CollidableEntity ent) {
        toBeAddedCollidable.add(ent);
        toBeAddedEntities.add(ent);
    }
    /**
     * Remove a collidable entity from manager
     * @param ent a collidable entity
     */
    public void removeEntity (CollidableEntity ent) {
        toBeRemovedCollidable.add(ent);
        toBeRemovedEntities.add(ent);
    }

    /**
     * Remove an entity to manager
     * @param ent an entity
     */
    public void removeEntity (Entity ent) {
        toBeRemovedEntities.add(ent);
    }

    public boolean isFinished(){
        for (Entity entity: Entities){
            if (entity.needToBeKilled){
                return false;
            }
        }
        return true;
    }

    public void addGold(int gold){
        this.gold += gold;
    }
    public int getGold(){int value =gold;
        gold = 0;
        return value;}

    /**
     * Update the manager
     */
    @Override
    public void update(){
        for(Entity entity : Entities){
            if(entity.shouldBeRemoved()){
                entity.removeSelf(this);
            }
        }

        Entities.addAll(toBeAddedEntities);
        collidableEntities.addAll(toBeAddedCollidable);
        Entities.removeAll(toBeRemovedEntities);
        collidableEntities.removeAll(toBeRemovedCollidable);

        toBeAddedEntities.clear();
        toBeAddedCollidable.clear();
        toBeRemovedEntities.clear();
        toBeRemovedCollidable.clear();

        for(Entity entity: Entities){
            entity.update();
        }

        for(ICollidable e1: collidableEntities){
            for(ICollidable e2: collidableEntities){
                if(e1 == e2){
                    continue;
                }
                if(e1.getCollisionBox().intersects(e2.getCollisionBox())){
                    e1.informCollision(e2);}
            }
        }

    }

    /**
     * Draw the entities.
     */
    @Override
    public void draw() {
        for (Entity ent: Entities) {
            ent.draw();
        }
    }
}
