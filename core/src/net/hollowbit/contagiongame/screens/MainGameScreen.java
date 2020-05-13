package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ContagionGame;

public class MainGameScreen implements Screen {

	private static final int PLAY_BTN_HEIGHT = 62;
	private static final int PLAY_BTN_WIDTH = 134;
	private static final int PLAY_BTN_X = 15;
	private static final int PLAY_BTN_Y = ContagionGame.HEIGHT - 85;
	private static final int GAMEO_BTN_X = 15;
	private static final int GAMEO_BTN_Y =  PLAY_BTN_Y - PLAY_BTN_HEIGHT;
	private static final int DOOR_HEIGHT=160;
	private static final int DOOR_WIDTH=233;
	private static final int DOOR_X=ContagionGame.WIDTH-260;
	private static final int DOOR_Y=290;

	ContagionGame game;

	Texture pauseButtonActive;
	Texture pauseButtonInactive;
	Texture gameOverButtonActive;
	Texture gameOverButtonInactive;
	Texture door;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
    

	public MainGameScreen(ContagionGame game) {
		this.game = game;
		this.pauseButtonActive = new Texture("pause_active.png");
		this.pauseButtonInactive = new Texture("pause_inactive.png");
		this.gameOverButtonActive = new Texture("gameOver_active.png");
		this.gameOverButtonInactive = new Texture("gameOver_inactive.png");
<<<<<<< HEAD
        backgroundTexture = new Texture("cuarto2.png");
       // backgroundTexture = new Texture("cuarto.png");
=======
		this.door=new Texture("door.png");
       // backgroundTexture = new Texture("cuarto2.png");
        backgroundTexture = new Texture("cuarto.jpg");
>>>>>>> 8df94ce82fd98977762f69c52b9754b6116f5a0e
        backgroundSprite =new Sprite(backgroundTexture);
	}
	
	@Override
	public void show() {

	}

    public void renderBackground() {
    	backgroundSprite.setSize(game.WIDTH, game.HEIGHT);
    	backgroundSprite.setCenter(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        backgroundSprite.draw(game.batch);
    }
    void collisionImage(int GAMEO_BTN_X, int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT, Screen PauseScreen){
    	if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - GAMEO_BTN_Y)
				&& (game.HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
		//	game.batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y, PLAY_BTN_WIDTH-30, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(PauseScreen);//Changes to main menu screen
			}
			
		}
    }
    
	@Override
	public void render(float delta) {
		
		stateTime+=delta;
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		renderBackground();
		
		
		game.batch.draw(door, DOOR_X, DOOR_Y,  DOOR_WIDTH, DOOR_HEIGHT);
		collisionImage(DOOR_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT, new HallwayScreen(game));
		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		if (PLAY_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PLAY_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - PLAY_BTN_Y)
				&& (game.HEIGHT - PLAY_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(pauseButtonActive, PLAY_BTN_X + 30, PLAY_BTN_Y, PLAY_BTN_WIDTH-30, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new PauseScreen(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(pauseButtonInactive, PLAY_BTN_X, PLAY_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}
		
		
		if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - GAMEO_BTN_Y)
				&& (game.HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y, PLAY_BTN_WIDTH-30, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				game.setScreen(new GameOver(game));//Changes to main menu screen
			}
			
		} else {
			// No Collision 
			game.batch.draw(gameOverButtonInactive, GAMEO_BTN_X, GAMEO_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
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
