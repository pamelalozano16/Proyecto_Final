package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.ContagionGame;

public class CreditsScreen implements Screen {

	private static final int PLAY_BTN_HEIGHT = 123;
	private static final int PLAY_BTN_WIDTH = 267;
	private static final int PLAY_BTN_X = (ContagionGame.WIDTH *4/5) - (PLAY_BTN_WIDTH / 2);
	private static final int PLAY_BTN_Y = 50;
	private static final int LOGO_WIDTH = 400;
	private static final int LOGO_HEIGHT = 300;
	private static final int GAMEO_WIDTH = 900;
	private static final int GAMEO_HEIGHT = 500;
	private static final int GAMEO_X = (ContagionGame.WIDTH / 2)-GAMEO_WIDTH/2;
	private static final int GAMEO_Y = (ContagionGame.HEIGHT/2)-300;


	ContagionGame game;

	Texture logo;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture credits;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;

	public CreditsScreen(ContagionGame game) {
		this.game = game;
		this.exitButtonActive = new Texture("exit_active.png");
		this.exitButtonInactive = new Texture("exit_inactive.png");
        backgroundTexture = new Texture("background.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("contagion.png");
		credits = new Texture("credits.png");
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
		
		game.batch.draw(credits, GAMEO_X, GAMEO_Y, GAMEO_WIDTH, GAMEO_HEIGHT);
		
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
			game.batch.draw(exitButtonActive, PLAY_BTN_X + 100, PLAY_BTN_Y, PLAY_BTN_WIDTH - 40, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new OptionScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(exitButtonInactive, PLAY_BTN_X + 50, PLAY_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}
		


		game.batch.end();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
