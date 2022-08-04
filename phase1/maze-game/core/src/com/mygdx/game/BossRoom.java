package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Boss;
import com.mygdx.game.Entities.Enemy;
import com.mygdx.game.Entities.Gun;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.geometry.Point;
import com.mygdx.game.graphics.IPresenter;

public class BossRoom extends Room{
    private IPresenter presenter;
    private Player player;
    private int screenWidth;
    private int screenHeight;

    private final IEntityManager entityManager = new EntityManager();

    public BossRoom(IPresenter presenter, Player player, int screenWidth, int screenHeight) {
        super(presenter, player, screenWidth, screenHeight, 0);
        Point bossPos = new Point((float) screenWidth/2,(float) screenHeight/2);
        Boss boss = new Boss(bossPos, presenter.getBossDrawer());
        player.addObserver(boss);
        entityManager.addCollidableEntity(boss);
//        Gun gun = new Gun(new Point(screenWidth / 2f, screenHeight / 2f), presenter.getGunDrawer(), presenter.getBulletDrawer());
//        boss.setGun(gun);
//        boss.setTarget(player.pos);

    }
    @Override
    public void update(){
        int i = 0;
        i = spawnEnemy(i);
        entityManager.update();
    }
    public int spawnEnemy(int i){
        if (i <= 100){i += 1;
        return i;}
        else {i = 0;
            Point enemy_pos = new Point(MathUtils.random(0, screenWidth), MathUtils.random(0, screenHeight));
            Enemy enemy = new Enemy(enemy_pos, presenter.getEnemyDrawer());
            player.addObserver(enemy);
            entityManager.addCollidableEntity(enemy);
            return i;
        }
    }
}
