package net.hollowbit.contagiongame.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	private static final int PAUSE_BTN_HEIGHT = 62;
	private static final int PAUSE_BTN_WIDTH = 134;
	private static final int PAUSE_BTN_X = 60;
	private static final int PAUSE_BTN_Y = ContagionGame.HEIGHT - 70;
	public Sound background_sound;
	public Sound laserSound;

	ContagionGame game;
	
	private boolean alive;
	Texture logo;
	TextureRegion[] virus;
	Texture youloose;
	Texture youwon;
	private int counter = 0;
	private int moveDown = 0;
	Animation<TextureRegion> virusAnimation;
	private ArrayList<Sprite> virusSprites = new ArrayList<Sprite>();
	private static final Texture virusTexture = new Texture("virusIndividual.png");
	Texture playButtonActive;
	Texture playButtonInactive;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
	private BitmapFont font;
	
	//BOTONES
	Texture pauseButtonActive;
	Texture pauseButtonInactive;
    
    //Laser
	public static Texture laser;
	public static Sprite laserSprite;
	private int LASER_X = -50;
	private int LASER_Y = 0;
	
	// PLAYER
	public static Texture player;
	public static Sprite playerSprite;
	private static final int PLAYER_H = 150;
	private static final int PLAYER_W = 100;
	private int PLAYER_X = (ContagionGame.WIDTH / 2) - (PLAYER_W / 2);
	private int PLAYER_Y = 0;
	private int direction =0;

	public HospitalGame(ContagionGame game) {
		this.game = game;
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
		this.pauseButtonActive = new Texture("goback_active.png");
		this.pauseButtonInactive = new Texture("goback_inactive.png");
		youloose = new Texture("youloose.png");
		youwon = new Texture("youwon.png");
        backgroundTexture = new Texture("hospitalInside.png");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("hospitalLogo.png");
		this.player = new Texture("doctorStill.png");
		this.laser = new Texture("laser-png-17.png");
		playerSprite = new Sprite(player);
		laserSprite = new Sprite(laser);
		playerSprite.setSize(PLAYER_W, PLAYER_H);
		laserSprite.setSize(10, 30);
		laserSprite.setPosition(LASER_X, LASER_Y);
		this.alive= true;
		font = new BitmapFont();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<5;j++) {
			Sprite virusSprite = new Sprite(virusTexture);
			virusSprite.setSize(100, 80);
			virusSprite.setPosition(80*j+50, 80*i+400);
			virusSprites.add(virusSprite);
			}
		}
		background_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/wii.mp3"));
		laserSound= Gdx.audio.newSound(Gdx.files.internal("sounds/laser2.mp3"));
		game.background_sound.dispose();
		long bs = background_sound.play();
		background_sound.setLooping(bs, true);
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
		

			movement(playerSprite);

			
			movementLaser(laserSprite);

			

			/* 
			 Gdx.input.getX() = mouse X coordinate
			 Gdx.input.getY() = mouse Y coordinate
			 */
			
			//Animation
			assignAnimations();
			
			//BUTTON GAME OVER
			
			game.batch.draw(pauseButtonInactive,PAUSE_BTN_X, PAUSE_BTN_Y, PAUSE_BTN_WIDTH, PAUSE_BTN_HEIGHT);
			if (Gdx.input.isTouched()) {
				if (PAUSE_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PAUSE_BTN_X + PAUSE_BTN_WIDTH)
						&& Gdx.input.getY() <= (game.HEIGHT - PAUSE_BTN_Y)
						&& (game.HEIGHT - PAUSE_BTN_Y - PAUSE_BTN_HEIGHT < Gdx.input.getY())) {
						game.setScreen(new OutsideMainScreen(game));
						background_sound.dispose();
					}

				}
			
			if(alive&&0<virusSprites.size()) {
				playerSprite.draw(game.batch);
				playerSprite.setPosition(PLAYER_X, PLAYER_Y);
				
				laserSprite.setPosition(LASER_X, LASER_Y);
				laserSprite.draw(game.batch);
				
			for (Sprite virus : virusSprites) {
				virus.draw(game.batch); // Pinta el virus
				counter++;
				if(counter==200) {
					if(moveDown ==0) {
						moveDown=1;
					}else {
						moveDown=0;
					}
					counter=0;
				}
				if(direction ==0) {
					virus.setPosition(virus.getX()+5, virus.getY()-moveDown);
				}else {
					virus.setPosition(virus.getX()-5, virus.getY()-moveDown);
				}
				 // Va cayendo a 7px

				if (virus.getX() <= 0) {
					// Si el virus toca el piso pierde una vida
					direction =0;
//					virusSprites.remove(virusSprites.indexOf(virus));
//					break;
				}
				if (virus.getX() >= ContagionGame.WIDTH-50) {
					// Si el virus toca el piso pierde una vida
					direction =1;
//					virusSprites.remove(virusSprites.indexOf(virus));
//					break;
				}
				if (Gdx.input.isKeyPressed(Keys.UP)) {
					LASER_X = PLAYER_X+50;
					LASER_Y = PLAYER_H-30;
					laserSound.play();
					long bs = background_sound.play();
				}
				if (laserSprite.getBoundingRectangle().overlaps(virus.getBoundingRectangle())) {
					// Si el cart toca la fruta gana score
					virusSprites.remove(virusSprites.indexOf(virus));
					LASER_X = -50;
					LASER_Y = 0;
					break;
				}
				if (playerSprite.getBoundingRectangle().overlaps(virus.getBoundingRectangle())) {
					// Si el cart toca la fruta gana score
					alive=false;
				}
		}
		} else if(!alive){
			//YOU LOOSE
			game.batch.draw(youloose, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT +100), LOGO_WIDTH,
					LOGO_HEIGHT);
		}else {
			//YOU WIN
			game.batch.draw(youwon, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT +100), LOGO_WIDTH,
					LOGO_HEIGHT);
			if(game.healthLevel<=80) {
				game.healthLevel+=20;
			}
			
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
	void movement(Sprite player) {
		// Wall Collision
		if (PLAYER_X < 0) {
			PLAYER_X = 0;
		}
		if (game.WIDTH - 100 < PLAYER_X) {
			PLAYER_X = game.WIDTH - 100;
		}

		// Movement
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			PLAYER_X -= 10;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			PLAYER_X += 10;
		}
	}
	
	void movementLaser(Sprite laser) {
		// Wall Collision
		LASER_Y+=10;
	}

}
