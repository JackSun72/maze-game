package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MazeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

/**
 * The main class to launch desktop
 * @author Ethan
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("maze-game");
		config.setWindowedMode(960, 540);
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new MazeGame(), config);
	}
}
