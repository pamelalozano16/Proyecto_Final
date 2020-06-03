package net.hollowbit.contagiongame.screens;

import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.ContagionGame;

public class StoreGame implements Screen {

	ContagionGame game;
	public boolean start;
	public static Texture backgroundTexture;
	public static Texture youloose;
	public static Texture youwon;
	public static Texture instructions;
	public static Sprite backgroundSprite;
	float stateTime;
	public int score = 0;
	public int lives = 3;
	private BitmapFont font;
	
	//SOUNDS
	Sound background_sound;
	Sound drop;

	// PLAY BTN
	private static final int PLAY_BTN_HEIGHT = 123;
	private static final int PLAY_BTN_WIDTH = 267;
	private static final int PLAY_BTN_X = (ContagionGame.WIDTH / 2) - (PLAY_BTN_WIDTH / 2);
	private static final int PLAY_BTN_Y = 10;
	private static final int PAUSE_BTN_HEIGHT = 62;
	private static final int PAUSE_BTN_WIDTH = 134;
	private static final int PAUSE_BTN_X = 70;
	private static final int PAUSE_BTN_Y = ContagionGame.HEIGHT - 65;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture logo;
	private static final int LOGO_WIDTH = 800;
	private static final int LOGO_HEIGHT = 200;
	boolean cobrado=false;

	// PLAYER
	public static Texture player;
	public static Sprite playerSprite;
	private static final int PLAYER_H = 150;
	private static final int PLAYER_W = 100;
	private int PLAYER_X = (ContagionGame.WIDTH / 2) - (PLAYER_W / 2);
	private int PLAYER_Y = 50;
	
	//BOTONES
	Texture pauseButtonActive;
	Texture pauseButtonInactive;

	// CART
	public static Texture cart;
	public static Sprite cartSprite;
	private static final int CART_H = 60;
	private static final int CART_W = 80;

	// FRUITS
	private ArrayList<Sprite> fruitSprites = new ArrayList<Sprite>();
	TextureRegion[][] fruits;
	private int fruit_x;
	private float secs = 2;
	private float velocity = 6;

	// VIRUS
	private ArrayList<Sprite> virusSprites = new ArrayList<Sprite>();
	private int virus_x;
	private float virus_secs = 5;
	private static final Texture virusTexture = new Texture("virusIndividual.png");

	public StoreGame(final ContagionGame game) {
		this.game = game;
		this.start = false;
		
		//SOUNDS
		background_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/background_sound.mp3"));
		drop = Gdx.audio.newSound(Gdx.files.internal("sounds/drop.wav"));
		
		// FONT
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		font.getData().setScale(2, 2);
		
		//BUTTONS
		this.pauseButtonActive = new Texture("goback_active.png");
		this.pauseButtonInactive = new Texture("goback_inactive.png");
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
		
		//TEXTURES AND SPRITES
		backgroundTexture = new Texture("groceryStore.jpg");
		backgroundSprite = new Sprite(backgroundTexture);
		instructions = new Texture("marketgame_instructions.png");
		this.player = new Texture("playerIndividual.png");
		youloose = new Texture("youloose.png");
		youwon = new Texture("youwon.png");
		playerSprite = new Sprite(player);
		this.cart = new Texture("cart.png");
		cartSprite = new Sprite(cart);
		cartSprite.setSize(CART_W, CART_H);
		playerSprite.setSize(PLAYER_W, PLAYER_H);
		Texture fruitsT = new Texture("fruits.png");
		fruits = TextureRegion.split(fruitsT, fruitsT.getWidth() / 5, 200);
		logo = new Texture("marketLogo.png");

		// Genera frutas random cada 2 segundos
		Timer.schedule(new Task() {
			@Override
			public void run() {
				if (start) { // Si ya empezó el juego
					int column = 5;
					int row = 4;
					// Fruta random
					int fruitj = (int) (Math.random() * column);
					int fruiti = (int) (Math.random() * row);
					// Posicion random en x
					fruit_x = (int) (Math.random() * ContagionGame.WIDTH - 200) + 200;
					Sprite fruitSprite = new Sprite(fruits[fruiti][fruitj]);
					fruitSprite.setSize(60, 80);
					fruitSprite.setPosition(fruit_x, ContagionGame.HEIGHT);
					fruitSprites.add(fruitSprite);
				}
			}
		}, 0, secs);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				if (start) {
					// Aumenta la cantidad de frutas que se generan mientras mas tiempo pase
					secs -= (secs / 4);
					// System.out.println(secs);
					// Aumenta la velocidad con la que caen hasta 12
					if (velocity < 12) {
						velocity += 1;
					}
				}
			}
		}, 0, 20);
		// Genera frutas random cada 2 segundos
		Timer.schedule(new Task() {
			@Override
			public void run() {
				if (start) {
					// Posicion random en x
					virus_x = (int) (Math.random() * ContagionGame.WIDTH - 200) + 200;
					while (virus_x == fruit_x) {
						virus_x = (int) (Math.random() * ContagionGame.WIDTH - 200) + 200;
					}
					Sprite virusSprite = new Sprite(virusTexture);
					virusSprite.setSize(100, 80);
					virusSprite.setPosition(virus_x, ContagionGame.HEIGHT);
					virusSprites.add(virusSprite);
				}
			}
		}, 0, virus_secs);

		// SOUNDS
		game.background_sound.dispose();
		long bs = background_sound.play();
		background_sound.setLooping(bs, true);

	}

	@Override
	public void show() {

	}

	public void renderBackground() {
		backgroundSprite.setSize(game.WIDTH, game.HEIGHT);
		backgroundSprite.setCenter(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		backgroundSprite.draw(game.batch);
	}

	@Override
	public void render(float delta) {

		stateTime += delta;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		renderBackground();
		
		
		//BUTTON GAME OVER
		
		game.batch.draw(pauseButtonInactive,PAUSE_BTN_X, PAUSE_BTN_Y, PAUSE_BTN_WIDTH, PAUSE_BTN_HEIGHT);
		if (Gdx.input.isTouched()) {
			if (PAUSE_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (PAUSE_BTN_X + PAUSE_BTN_WIDTH)
					&& Gdx.input.getY() <= (game.HEIGHT - PAUSE_BTN_Y)
					&& (game.HEIGHT - PAUSE_BTN_Y - PAUSE_BTN_HEIGHT < Gdx.input.getY())) {
					game.setScreen(new OutsideMainScreen(game));
					background_sound.dispose();
					game.playBackgroundSound();
				}

			}
		

		if (!start) { // Todavia no empieza el juego entonces pinta el button Play
			game.batch.draw(logo, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT +50), LOGO_WIDTH,
					LOGO_HEIGHT);
			game.batch.draw(instructions, (game.WIDTH/2)-219, 120, 438, 320);
			boolean playbtn = game.buttonCollision(playButtonActive, playButtonInactive, PLAY_BTN_X, PLAY_BTN_Y,
					PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT, 50, 40);
			if (playbtn) { // Si le da click al boton empieza el juego
				start = true;
			}
		} else if(game.money<10&&!cobrado) {
			font.setColor(Color.RED);
			font.getData().setScale(2, 2);
			font.draw(game.batch, "NO ALCANZA EL DINERO, REGRESA Y ESTUDIA EN LA COMPUTADORA", 100, 300);
		} else if (0 < lives) { //Score amount to win
			if(!cobrado) {
				System.out.println("cobrado");
				game.money-=10;
				cobrado=true;
			}

			font.draw(game.batch, "Score: " + String.valueOf(score), 1000, 680);
			font.draw(game.batch, "Lives: " + String.valueOf(lives), 1000, 650);

			movement(playerSprite);
			playerSprite.draw(game.batch);
			playerSprite.setPosition(PLAYER_X, PLAYER_Y);

			cartSprite.draw(game.batch);
			cartSprite.setPosition(PLAYER_X, 60);
			
			// FRUITS
			for (Sprite fruit : fruitSprites) {
				fruit.draw(game.batch); // Pinta las frutas
				fruit.setPosition(fruit.getX(), fruit.getY() - velocity); // Aqui va cayendo

				if (cartSprite.getBoundingRectangle().overlaps(fruit.getBoundingRectangle())) {
					// Si el cart toca la fruta gana score
					fruitSprites.remove(fruitSprites.indexOf(fruit));
					score += 5;
					break;
				}
				if (fruit.getY() <= 0) {
					// Si la fruta toca el piso pierde una vida
					fruitSprites.remove(fruitSprites.indexOf(fruit));
					lives--;
					drop.play();
					break;
				}
			}

			// VIRUS
			for (Sprite virus : virusSprites) {
				virus.draw(game.batch); // Pinta el virus
				virus.setPosition(virus.getX(), virus.getY() - 7); // Va cayendo a 7px

				if (playerSprite.getBoundingRectangle().overlaps(virus.getBoundingRectangle())) {
					// Si el personaje toca el virus se acaba el juego
					lives = 0;
					background_sound.dispose();
					break;
				}
				if (virus.getY() <= 0) {
					// Si el virus toca el piso pierde una vida
					virusSprites.remove(virusSprites.indexOf(virus));
					break;
				}
			}
			

		} else{ 
			//YOU LOOSE
			game.batch.draw(youloose, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT +100), LOGO_WIDTH,
					LOGO_HEIGHT);
			if(game.hungerLevel<100&&25<=score) {
				game.hungerLevel+=20;
			}
			background_sound.dispose();

		};

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



public void buttonCollision(int GAMEO_BTN_X,
		int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT, Screen newScreen) {


}

};
