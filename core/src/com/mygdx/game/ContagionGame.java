package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import net.hollowbit.contagiongame.screens.MainGameScreen;
import net.hollowbit.contagiongame.screens.MainPlayScreen;

public class ContagionGame extends Game {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT= 700;
	public int healthLevel =100;
	public int hungerLevel = 100;
	public ShapeRenderer shapeR;
	public SpriteBatch batch;

	
	@Override
	public void create () {
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				healthLevel-=10;//Health disminuye por 10 
				hungerLevel-=20; //Hunger disminuye por 20
			}
		}, 0, 300); //Cada 5 mins disminuye el hambre y la salud
		
		//healthLevel+=10;
		hungerLevel+=20;
		
		batch = new SpriteBatch();
		shapeR = new ShapeRenderer();
		this.setScreen(new MainPlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
	
	public boolean buttonCollision(Texture gameOverButtonActive, Texture gameOverButtonInactive, int GAMEO_BTN_X,
			int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT, int plus_x, int plus_width ) {
		
		if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (HEIGHT - GAMEO_BTN_Y)
				&& (HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y, PLAY_BTN_WIDTH - 30, PLAY_BTN_HEIGHT);

			// Collision mouse with Play Button for click
			if (Gdx.input.isTouched()) { // If it clicks it
				return true;// Changes to main menu screen
			}

		} else {
			// No Collision
			batch.draw(gameOverButtonInactive, GAMEO_BTN_X, GAMEO_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}
		return false;

}
	}
