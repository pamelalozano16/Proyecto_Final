package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ContagionGame;

public class Options2Screen implements Screen {

	
private static final int diff=120;
	
	private static final int MENU_WIDTH = 400;
	private static final int MENU_HEIGHT = 500;
	private static final int MENU_X= (ContagionGame.WIDTH/2)-(MENU_WIDTH/2);
	private static final int MENU_Y=(ContagionGame.HEIGHT/2)-(MENU_HEIGHT/2);
	private static final int BTN_HEIGHT = 123;
	private static final int BTN_WIDTH = 267;
	private static final int BTN_X = MENU_X+45;
	private static final int CONTROLS_BTN_Y =ContagionGame.HEIGHT-MENU_Y-BTN_HEIGHT;
	private static final int CREDITS_BTN_Y =CONTROLS_BTN_Y-diff;
	private static final int EXIT_BTN_Y = CREDITS_BTN_Y-diff;
	
	ContagionGame game;

	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture creditsButtonActive;
	Texture creditsButtonInactive;
	Texture controlsButtonActive;
	Texture controlsButtonInactive;
	Texture menuBackground;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
    ShapeRenderer shapeRenderer;
    
    public Options2Screen(ContagionGame game) {
		this.game = game;

		this.exitButtonActive = new Texture("exit_active.png");
		this.exitButtonInactive = new Texture("exit_inactive.png");
		this.controlsButtonActive = new Texture("controls_active.png");
		this.controlsButtonInactive = new Texture("controls_inactive.png");
		this.creditsButtonActive = new Texture("credits_active.png");
		this.creditsButtonInactive = new Texture("credits_inactive.png");
      // backgroundTexture = new Texture("cuarto2.png");
		menuBackground = new Texture("menuBackground.png");
        backgroundTexture = new Texture("background.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
        shapeRenderer=new ShapeRenderer();
        
	}
    
    
    public void renderBackground() {
    	backgroundSprite.setSize(game.WIDTH, game.HEIGHT);
    	backgroundSprite.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backgroundSprite.draw(game.batch);
    }
    
    
	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		stateTime+=delta;
		
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		renderBackground();
		
		game.batch.draw(menuBackground, MENU_X, MENU_Y, MENU_WIDTH, MENU_HEIGHT);
		

		// Controls
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - CONTROLS_BTN_Y)
				&& (game.HEIGHT - CONTROLS_BTN_Y -BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(controlsButtonActive, BTN_X + 50, CONTROLS_BTN_Y, BTN_WIDTH-40, BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new ControlsScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(controlsButtonInactive, BTN_X, CONTROLS_BTN_Y,BTN_WIDTH, BTN_HEIGHT);
		} ;
		
		// Credits
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - CREDITS_BTN_Y)
				&& (game.HEIGHT - CREDITS_BTN_Y -BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(creditsButtonActive, BTN_X + 50, CREDITS_BTN_Y, BTN_WIDTH-40, BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new CreditsScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(creditsButtonInactive, BTN_X, CREDITS_BTN_Y,BTN_WIDTH, BTN_HEIGHT);
		}; 
		
		// Exit
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - EXIT_BTN_Y)
				&& (game.HEIGHT - EXIT_BTN_Y -BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(exitButtonActive, BTN_X + 50, EXIT_BTN_Y, BTN_WIDTH-40, BTN_HEIGHT);
			
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
