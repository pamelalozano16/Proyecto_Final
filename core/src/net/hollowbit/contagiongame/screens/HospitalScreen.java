package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ContagionGame;

public class HospitalScreen implements Screen {

	private static final int PLAY_BTN_HEIGHT = 123;
	private static final int PLAY_BTN_WIDTH = 267;
	private static final int PLAY_BTN_X = (ContagionGame.WIDTH / 2) - (PLAY_BTN_WIDTH / 2);
	private static final int PLAY_BTN_Y = 50;
	private static final int PAUSE_BTN_HEIGHT = 62;
	private static final int PAUSE_BTN_WIDTH = 134;
	private static final int PAUSE_BTN_X = 70;
	private static final int PAUSE_BTN_Y = ContagionGame.HEIGHT - 65;
	private static final int LOGO_WIDTH = 800;
	private static final int LOGO_HEIGHT = 200;
	private static final int VIRUS_WIDTH=60;
	private static final int VIRUS_HEIGHT=73;
	private static final float VIRUS_SPEED=0.5f;

	ContagionGame game;

	Texture logo;
	TextureRegion[] doctor;
	Animation<TextureRegion> doctorAnimation;
	Texture playButtonActive;
	Texture playButtonInactive;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
    Texture pauseButtonActive;
    Texture pauseButtonInactive;
	private BitmapFont font;


	public HospitalScreen(ContagionGame game) {
		this.game = game;
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
        backgroundTexture = new Texture("hospitalInside.png");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("hospitalLogo.png");
		this.pauseButtonActive = new Texture("goback_active.png");
		this.pauseButtonInactive = new Texture("goback_inactive.png");
		font = new BitmapFont();
	}
	
	private void assignAnimations() {
		int column=5;
		int row=1;
		Texture virusT= new Texture("virus.png");
		TextureRegion[][] tmp = TextureRegion.split(virusT, virusT.getWidth()/5, virusT.getHeight());
		doctor = new TextureRegion[column*row];
		int index = 0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				doctor[index++]= tmp[i][j];
			}
		}
		doctorAnimation = new Animation<TextureRegion>(VIRUS_SPEED, doctor);
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
		
		// Pinta el logo
		game.batch.draw(logo, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT +50), LOGO_WIDTH,
				LOGO_HEIGHT);

		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		game.batch.draw(pauseButtonInactive,PAUSE_BTN_X, PAUSE_BTN_Y, PAUSE_BTN_WIDTH, PAUSE_BTN_HEIGHT);
		if (Gdx.input.justTouched()) {
			if (PAUSE_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PAUSE_BTN_X + PAUSE_BTN_WIDTH)
					&& Gdx.input.getY() <= (game.HEIGHT - PAUSE_BTN_Y)
					&& (game.HEIGHT - PAUSE_BTN_Y - PAUSE_BTN_HEIGHT < Gdx.input.getY())) {
					game.setScreen(new OutsideMainScreen(game));
				}

			}
		
		if (PLAY_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PLAY_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - PLAY_BTN_Y)
				&& (game.HEIGHT - PLAY_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(playButtonActive, PLAY_BTN_X + 50, PLAY_BTN_Y, PLAY_BTN_WIDTH - 40, PLAY_BTN_HEIGHT);
			
			//Collision mouse with Play Button for click
			if(Gdx.input.isTouched()) { //If it clicks it
				if(game.money<20) {
					font.setColor(Color.RED);
					font.getData().setScale(2, 2);
					font.draw(game.batch, "NO ALCANZA EL DINERO, REGRESA Y ESTUDIA EN LA COMPUTADORA", 100, 200);
				} else {
					game.money-=20;
					game.setScreen(new HospitalGame(game));//Changes to main menu screen
				}
			}
			
		} else {
			// No Collision 
			game.batch.draw(playButtonInactive, PLAY_BTN_X, PLAY_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}
		
		//Animation
		assignAnimations();
		TextureRegion currentFrame = doctorAnimation.getKeyFrame(stateTime, true);
		game.batch.draw(currentFrame, 50,400);
		game.batch.draw(currentFrame, 400,250);
		game.batch.draw(currentFrame, 600,300);
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
