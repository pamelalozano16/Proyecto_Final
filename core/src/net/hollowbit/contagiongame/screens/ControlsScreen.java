package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.ContagionGame;

public class ControlsScreen implements Screen {


	private static final int OPTION_BTN_HEIGHT = 123;
	private static final int OPTION_BTN_WIDTH = 267;
	private static final int OPTION_BTN_X = (ContagionGame.WIDTH *4/5) - (OPTION_BTN_WIDTH / 2);
	private static final int OPTION_BTN_Y = 50;
	private static final int LOGO_WIDTH = 400;
	private static final int LOGO_HEIGHT = 300;
	private static final int CONTROLS_WIDTH = 900;
	private static final int CONTROLS_HEIGHT = 500;
	private static final int CONTROLS_X = (ContagionGame.WIDTH / 2)-CONTROLS_WIDTH/2;
	private static final int CONTROLS_Y = (ContagionGame.HEIGHT/2)- CONTROLS_HEIGHT/2;


	ContagionGame game;

	Texture logo;
	Texture optionButtonActive;
	Texture optionButtonInactive;
	Texture controls;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;

	public ControlsScreen(ContagionGame game) {
		this.game = game;
		this.optionButtonActive = new Texture("exit_active.png");
		this.optionButtonInactive = new Texture("exit_inactive.png");
        backgroundTexture = new Texture("cuarto.png");
        backgroundSprite =new Sprite(backgroundTexture);
		controls = new Texture("controls.png");
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
		
		game.batch.draw(controls, CONTROLS_X, CONTROLS_Y, CONTROLS_WIDTH, CONTROLS_HEIGHT);
		

		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		if (OPTION_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (OPTION_BTN_X + OPTION_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - OPTION_BTN_Y)
				&& (game.HEIGHT - OPTION_BTN_Y - OPTION_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(optionButtonActive, OPTION_BTN_X + 100, OPTION_BTN_Y, OPTION_BTN_WIDTH - 40, OPTION_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new OptionScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(optionButtonInactive, OPTION_BTN_X + 50, OPTION_BTN_Y, OPTION_BTN_WIDTH, OPTION_BTN_HEIGHT);
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
