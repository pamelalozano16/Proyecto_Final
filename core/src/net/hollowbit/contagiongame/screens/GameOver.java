package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ContagionGame;

public class GameOver implements Screen {


	private static final int PLAY_BTN_HEIGHT = 123;
	private static final int PLAY_BTN_WIDTH = 267;
	private static final int PLAY_BTN_X = (ContagionGame.WIDTH *4/5) - (PLAY_BTN_WIDTH / 2);
	private static final int PLAY_BTN_Y = 50;
	private static final int LOGO_WIDTH = 400;
	private static final int LOGO_HEIGHT = 300;
	private static final int GAMEO_WIDTH = 700;
	private static final int GAMEO_HEIGHT = 500;
	private static final int GAMEO_X = (ContagionGame.WIDTH / 2)-GAMEO_WIDTH/2;
	private static final int GAMEO_Y = (ContagionGame.HEIGHT/2)-250;


	ContagionGame game;

	Texture logo;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture gameOver;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;

	public GameOver(ContagionGame game) {
		this.game = game;
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
        backgroundTexture = new Texture("background.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("contagion.png");
		gameOver = new Texture("gameOver.png");
	}
	

	@Override
	public void show() {

	}

    public void renderBackground() {
    	backgroundSprite.setSize(game.WIDTH, game.HEIGHT);
    	backgroundSprite.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backgroundSprite.draw(game.batch);
    }
    
	@Override
	public void render(float delta) {
		
		stateTime+=delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		renderBackground();
		
		game.batch.draw(gameOver, GAMEO_X, GAMEO_Y, GAMEO_WIDTH, GAMEO_HEIGHT);
		
		// Pinta el logo
		game.batch.draw(logo, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT-60), LOGO_WIDTH,
				LOGO_HEIGHT);

		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		if (PLAY_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PLAY_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - PLAY_BTN_Y)
				&& (game.HEIGHT - PLAY_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(playButtonActive, PLAY_BTN_X + 100, PLAY_BTN_Y, PLAY_BTN_WIDTH - 40, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new MainMenuScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(playButtonInactive, PLAY_BTN_X + 50, PLAY_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}
		


		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
