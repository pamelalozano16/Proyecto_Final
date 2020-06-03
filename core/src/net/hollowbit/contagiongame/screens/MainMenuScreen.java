package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ContagionGame;

import utils.ReadandWrite;

public class MainMenuScreen implements Screen {

	
	private static final int BTN_HEIGHT = 123;
	private static final int BTN_WIDTH = 267;
	private static final int BTN_X = (ContagionGame.WIDTH / 2) - (BTN_WIDTH / 2);
	private static final int OPTION_BTN_Y = 100;
	private static final int NEW_BTN_Y = 225;
	private static final int RESUME_BTN_Y = 350;
	private static final int LOGO_WIDTH = 400;
	private static final int LOGO_HEIGHT = 300;
	private static final int VIRUS_WIDTH=60;
	private static final int VIRUS_HEIGHT=73;
	private static final float VIRUS_SPEED=0.5f;
	
	
	ContagionGame game;
	
	Texture logo;
	TextureRegion[] virus;
	Animation<TextureRegion> virusAnimation;
	Texture resumeButtonActive;
	Texture resumeButtonInactive;
	Texture newGameButtonActive;
	Texture newGameButtonInactive;
	Texture optionButtonActive;
	Texture optionButtonInactive;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;


	public MainMenuScreen(ContagionGame game) {
		this.game = game;
		this.resumeButtonActive = new Texture("resume_active.png");
		this.resumeButtonInactive = new Texture("resume_inactive.png");
		this.newGameButtonActive = new Texture("newG_active.png");
		this.newGameButtonInactive = new Texture ("newG_inactive.png");
		this.optionButtonActive = new Texture("options_active.png");
		this.optionButtonInactive = new Texture("options_inactive.png");
        backgroundTexture = new Texture("background.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("contagion.png");
		
		
	}

	
	private void assignAnimations() {
		int column=6;
		int row=1;
		Texture virusT= new Texture("virus.png");
		TextureRegion[][] tmp = TextureRegion.split(virusT, virusT.getWidth()/6, virusT.getHeight());
		virus = new TextureRegion[6*1];
		int index = 0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				virus[index++]= tmp[i][j];
			}
		}
		virusAnimation = new Animation<TextureRegion>(VIRUS_SPEED, virus);
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
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		renderBackground();
		
		// Pinta el logo
		game.batch.draw(logo, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT-60), LOGO_WIDTH,
				LOGO_HEIGHT);

		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		// Resume button
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - RESUME_BTN_Y)
				&& (game.HEIGHT - RESUME_BTN_Y - BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(resumeButtonActive, BTN_X + 50, RESUME_BTN_Y, BTN_WIDTH - 40, BTN_HEIGHT);
			ReadandWrite.Load("archivo.txt", game);
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new MainGameScreen2(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(resumeButtonInactive, BTN_X, RESUME_BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		}
		
		
		// New game button
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - NEW_BTN_Y)
				&& (game.HEIGHT - NEW_BTN_Y - BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(newGameButtonActive, BTN_X + 50, NEW_BTN_Y, BTN_WIDTH - 40, BTN_HEIGHT);
			game.healthLevel=100;
			game.hungerLevel=100;
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new MainGameScreen2(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(newGameButtonInactive, BTN_X, NEW_BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		}
		
		
		// Options button
		if (BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (BTN_X + BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - OPTION_BTN_Y)
				&& (game.HEIGHT - OPTION_BTN_Y - BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(optionButtonActive, BTN_X + 50, OPTION_BTN_Y, BTN_WIDTH - 40, BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new Options2Screen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(optionButtonInactive, BTN_X, OPTION_BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		}
		
		//Animation
		assignAnimations();
		TextureRegion currentFrame = virusAnimation.getKeyFrame(stateTime, true);

		game.batch.draw(currentFrame, 50,400);
		game.batch.draw(currentFrame, 400,250);
		game.batch.draw(currentFrame, 600,500);
		game.batch.draw(currentFrame, 900,300);

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
