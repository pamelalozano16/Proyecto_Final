package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.contagiongame.screens.MainGameScreen;
import net.hollowbit.contagiongame.screens.MainPlayScreen;

public class ContagionGame extends Game {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT= 700;
	
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainPlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}