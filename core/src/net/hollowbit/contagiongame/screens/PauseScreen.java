package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ContagionGame;

public class PauseScreen implements Screen {

	private static final int MENU_WIDTH = 400;
	private static final int MENU_HEIGHT = 500;
	private static final int MENU_X= (ContagionGame.WIDTH/2)-(MENU_WIDTH/2);
	private static final int MENU_Y=(ContagionGame.HEIGHT/2)-(MENU_HEIGHT/2);
	private static final int BTN_HEIGHT = 123;
	private static final int BTN_WIDTH = 267;
	private static final int BTN_X = MENU_X+45;
	private static final int SAVE_BTN_Y =ContagionGame.HEIGHT-MENU_Y-BTN_HEIGHT;;
	private static final int EXIT_BTN_Y =SAVE_BTN_Y-170;

	ContagionGame game;

	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture saveButtonActive;
	Texture saveButtonInactive;
	Texture menuBackground;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
    ShapeRenderer shapeRenderer;

	public PauseScreen(ContagionGame game) {
		this.game = game;
		this.saveButtonActive = new Texture("save_active.png");
		this.saveButtonInactive = new Texture("save_inactive.png");
		this.exitButtonActive = new Texture("exit_active.png");
		this.exitButtonInactive = new Texture("exit_inactive.png");
      // backgroundTexture = new Texture("cuarto2.png");
		menuBackground = new Texture("menuBackground.png");
        backgroundTexture = new Texture("cuarto.png");
        backgroundSprite =new Sprite(backgroundTexture);
        shapeRenderer=new ShapeRenderer();
        
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
		
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		renderBackground();
		
		game.batch.draw(menuBackground, MENU_X, MENU_Y, MENU_WIDTH, MENU_HEIGHT);
		
		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
*/		
		
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - SAVE_BTN_Y)
				&& (game.HEIGHT - SAVE_BTN_Y -BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(saveButtonActive, BTN_X + 30, SAVE_BTN_Y, BTN_WIDTH-30, BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				//game.setScreen(new PauseScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(saveButtonInactive, BTN_X, SAVE_BTN_Y,BTN_WIDTH, BTN_HEIGHT);
		} 
		
		
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - EXIT_BTN_Y)
				&& (game.HEIGHT - EXIT_BTN_Y -BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(exitButtonActive, BTN_X + 30, EXIT_BTN_Y, BTN_WIDTH-30, BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new MainMenuScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(exitButtonInactive, BTN_X, EXIT_BTN_Y,BTN_WIDTH, BTN_HEIGHT);
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
