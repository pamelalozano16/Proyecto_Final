package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ContagionGame;

public class HospitalGame implements Screen {

	private static final int PLAY_BTN_HEIGHT = 123;
	private static final int PLAY_BTN_WIDTH = 267;
	private static final int PLAY_BTN_X = (ContagionGame.WIDTH / 2) - (PLAY_BTN_WIDTH / 2);
	private static final int PLAY_BTN_Y = 50;
	private static final int LOGO_WIDTH = 800;
	private static final int LOGO_HEIGHT = 200;
	private static final int VIRUS_WIDTH=60;
	private static final int VIRUS_HEIGHT=73;
	private static final float VIRUS_SPEED=0.5f;

	ContagionGame game;

	Texture logo;
	TextureRegion[] virus;
	Animation<TextureRegion> virusAnimation;
	Texture playButtonActive;
	Texture playButtonInactive;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;

	public HospitalGame(ContagionGame game) {
		this.game = game;
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
        backgroundTexture = new Texture("hospitalInside.png");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("hospitalLogo.png");
	}
	
	private void assignAnimations() {
		int column=6;
		int row=1;
		Texture virusT= new Texture("virus.png");
		TextureRegion[][] tmp = TextureRegion.split(virusT, virusT.getWidth()/6, virusT.getHeight());
		virus = new TextureRegion[column*row];
		int index = 0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				virus[index++]= tmp[i][j];
			}
		}
		virusAnimation = new Animation<TextureRegion>(VIRUS_SPEED, virus);
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
		

		/* 
		 Gdx.input.getX() = mouse X coordinate
		 Gdx.input.getY() = mouse Y coordinate
		 */
		
		//Animation
		assignAnimations();
		TextureRegion currentFrame = virusAnimation.getKeyFrame(stateTime, true);
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
