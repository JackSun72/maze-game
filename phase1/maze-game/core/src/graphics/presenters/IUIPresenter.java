package graphics.presenters;

import graphics.presenters.IDrawble;

public interface IUIPresenter extends IDrawble {
    public void draw();

    public void updatePlayerShield(int playerShield);
    public void updateIsPlayerDead(boolean isPlayerDead);
    public void updatePlayerGold(int playerGold);
    public void updatePlayerHealth(int playerHealth);
    public void dispose();
}