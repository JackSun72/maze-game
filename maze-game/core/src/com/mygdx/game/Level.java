package com.mygdx.game;

import com.mygdx.game.Entities.Door;
import com.mygdx.game.Entities.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.geometry.Point;
import com.mygdx.game.graph.PlanarGraph;
import com.mygdx.game.graph.PlanarNode;
import com.mygdx.game.graph.TestGraphGenerator;
import com.mygdx.game.graphics.IPresenter;
import com.mygdx.game.graphics.door.IDoorDrawer;
import com.mygdx.game.graphics.level.ILevelDrawer;
import com.mygdx.game.graphics.room.IRoomDrawer;

import java.util.*;

public class Level implements IRoomContainer {
    private final Collection<Room> rooms;
    private Room currentRoom;
    private Player player;
    private final ILevelDrawer levelDrawer;
    private final Random rnd = new Random();
    private final int screenWidth;
    private final int screenHeight;
    public Level(IPresenter presenter, Player player, int screenWidth, int screenHeight){
        this.player = player;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        levelDrawer = presenter.getLevelDrawer();
        IDoorDrawer doorDrawer = presenter.getDoorDrawer();

        PlanarGraph levelLayout = new TestGraphGenerator().generate();
        Map<Set<PlanarNode>, Boolean> edges = getEdgeMap(levelLayout);
        Map<PlanarNode, Room> nodeToRoom = new HashMap<>();
        for(PlanarNode node: levelLayout){
            nodeToRoom.put(node, new Room(presenter, new RoomEntityManager()));
        }

        for (PlanarNode node: levelLayout) {
            for (PlanarNode neighbour: node.getNeighboors()){
                Set<PlanarNode> pair = new HashSet<>();
                pair.add(node);
                pair.add(neighbour);
                if(!edges.get(pair)){
                    Room r1 = nodeToRoom.get(node);
                    Room r2 = nodeToRoom.get(neighbour);

                    Door door1 = new Door(getRandomPointOnScreen(), doorDrawer, this  );
                    Door door2 = new Door(getRandomPointOnScreen(), doorDrawer, this);

                    door1.setRoom(r1);
                    door2.setRoom(r2);

                    door1.setDoor(door2);
                    door2.setDoor(door1);

                    r1.entityManager.addCollidableEntity(door1);
                    r2.entityManager.addCollidableEntity(door2);

                    edges.put(pair, true);
                }
            }
        }

        rooms = nodeToRoom.values();

        currentRoom = rooms.iterator().next();
        currentRoom.create(player, screenWidth, screenHeight);
    }
    public void setNewRoom(Room room){
        room.create(player, screenWidth, screenHeight);
        System.out.println("Room created!");
        currentRoom = room;
    }

    public void update(){
        currentRoom.update();
    }

    public void draw(){
        levelDrawer.drawLevel(currentRoom);
    }

    private Map<Set<PlanarNode>, Boolean> getEdgeMap(PlanarGraph levelLayout){
        Map<Set<PlanarNode>, Boolean> edges = new HashMap<>();
        for(PlanarNode n1: levelLayout){
            for(PlanarNode n2: levelLayout){
                Set<PlanarNode> pair = new HashSet<>();
                pair.add(n1);
                pair.add(n2);
                if(n1 != n2 && !edges.containsKey(pair) && n1.getNeighboors().contains(n2)){
                    edges.put(pair, false);
                }
            }
        }

        return edges;
    }

    private Point getRandomPointOnScreen(){
        return new Point(rnd.nextInt(0, screenWidth), rnd.nextInt(0, screenHeight));
    }
}