package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ContagionGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.height = ContagionGame.HEIGHT;
		config.width = ContagionGame.WIDTH;
		config.resizable = false;
		new LwjglApplication(new ContagionGame(), config);
	}
}
