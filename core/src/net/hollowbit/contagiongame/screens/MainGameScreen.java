package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.ContagionGame;

public class MainGameScreen implements Screen {

	// VARIABLES X, Y, WIDTH Y HEIGHT
	private static final int PLAY_BTN_HEIGHT = 62;
	private static final int PLAY_BTN_WIDTH = 134;
	private static final int PLAY_BTN_X = 15;
	private static final int PLAY_BTN_Y = ContagionGame.HEIGHT - 85;
	private static final int GAMEO_BTN_X = 15;
	private static final int GAMEO_BTN_Y = PLAY_BTN_Y - PLAY_BTN_HEIGHT;
	private static final int DOOR_HEIGHT = 160;
	private static final int DOOR_WIDTH = 233;
	private static final int DOOR_X = ContagionGame.WIDTH - 260;
	private static final int DOOR_Y = 290;
	private static final int PLAYER_H = 150;
	private static final int PLAYER_W = 100;
	private int PLAYER_X = (ContagionGame.WIDTH / 2) - (PLAYER_W / 2);
	private int PLAYER_Y = 200;

	ContagionGame game;

	ShapeRenderer shapeRenderer;
	Texture pauseButtonActive;
	Texture pauseButtonInactive;
	Texture gameOverButtonActive;
	Texture gameOverButtonInactive;
	public static Texture door;
	public static Sprite doorSprite;
	// PLAYER
	TextureRegion[] player;
	Animation<TextureRegion> playerStanding;
	Animation<TextureRegion> playerRight;
	Animation<TextureRegion> playerUp;
	Animation<TextureRegion> playerDown;
	Animation<TextureRegion> playerLeft;
	Sprite playerSprite;
	private static final float PLAYER_SPEED = 0.2f;

	// BACKGROUND
	public static Texture backgroundTexture;
	public static Sprite backgroundSprite;
	float stateTime;

	public MainGameScreen(ContagionGame game) {
		this.game = game;
		this.pauseButtonActive = new Texture("pause_active.png");
		this.pauseButtonInactive = new Texture("pause_inactive.png");
		this.gameOverButtonActive = new Texture("gameOver_active.png");
		this.gameOverButtonInactive = new Texture("gameOver_inactive.png");

		backgroundTexture = new Texture("cuarto2.png");
		// backgroundTexture = new Texture("cuarto.png");

		this.door = new Texture("door.png");
		doorSprite = new Sprite(door);
		// backgroundTexture = new Texture("cuarto2.png");
		backgroundTexture = new Texture("cuarto.jpg");

		backgroundSprite = new Sprite(backgroundTexture);
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

		doorSprite.draw(game.batch);
		doorSprite.setPosition(DOOR_X, DOOR_Y);
		doorSprite.setSize(DOOR_WIDTH, DOOR_HEIGHT);
		
		imageCollision(DOOR_X, DOOR_Y, DOOR_WIDTH, DOOR_HEIGHT, new HallwayScreen(game));
		buttonCollision(pauseButtonActive, pauseButtonInactive, PLAY_BTN_X, PLAY_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT, new PauseScreen(game));
		buttonCollision(gameOverButtonActive, gameOverButtonInactive, GAMEO_BTN_X, GAMEO_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT, new GameOver(game));


		assignPlayerAnimations();
		TextureRegion currentFrame = playerStanding.getKeyFrame(stateTime, true);

		// Walking
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			PLAYER_Y -= 7;
			currentFrame = playerDown.getKeyFrame(stateTime, true);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			PLAYER_Y += 7;
			currentFrame = playerUp.getKeyFrame(stateTime, true);
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			PLAYER_X -= 7;
			currentFrame = playerLeft.getKeyFrame(stateTime, true);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			PLAYER_X += 7;
			currentFrame = playerRight.getKeyFrame(stateTime, true);
		}

		// Animation
		
		game.batch.draw(currentFrame, PLAYER_X, PLAYER_Y, PLAYER_W, PLAYER_H);
		Rectangle playerRect = new Rectangle(PLAYER_X, PLAYER_Y, PLAYER_W, PLAYER_H);
		if(doorSprite.getBoundingRectangle().overlaps(playerRect)){
				    // gives true when sprite 2 intersects sprite1
			game.setScreen(new HallwayScreen(game));
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
	

	public void assignPlayerAnimations() {
		int column = 4;
		int row = 1;
		Texture playerT = new Texture("player.png");
		TextureRegion[][] tmp = TextureRegion.split(playerT, playerT.getWidth() / column, playerT.getHeight() / 4);
		int index=0;
		
		player = new TextureRegion[1*1];
			player[index++] = tmp[0][0];
		playerStanding = new Animation<TextureRegion>(PLAYER_SPEED, player);

		player = new TextureRegion[column * row];
		index = 0;
		for (int j = 0; j < column; j++) {
			player[index++] = tmp[0][j];
		}
		playerDown = new Animation<TextureRegion>(PLAYER_SPEED, player);
		
		index=0;
		player = new TextureRegion[column * row];
		for (int j = 0; j < column; j++) {
			player[index++] = tmp[1][j];
		}
		playerUp = new Animation<TextureRegion>(PLAYER_SPEED, player);

		index=0;
		player = new TextureRegion[column * row];
		for (int j = 0; j < column; j++) {
			player[index++] = tmp[2][j];
		}
		playerLeft = new Animation<TextureRegion>(PLAYER_SPEED, player);

		index=0;
		player = new TextureRegion[column * row];
		for (int j = 0; j < column; j++) {
			player[index++] = tmp[3][j];
		}
		playerRight = new Animation<TextureRegion>(PLAYER_SPEED, player);

	}
	
	public void buttonCollision(Texture gameOverButtonActive, Texture gameOverButtonInactive, 
			int GAMEO_BTN_X, int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT, Screen newScreen) {

		if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - GAMEO_BTN_Y)
				&& (game.HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			game.batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y, PLAY_BTN_WIDTH - 30, PLAY_BTN_HEIGHT);

			// Collision mouse with Play Button for click
			if (Gdx.input.isTouched()) { // If it clicks it
				game.setScreen(newScreen);// Changes to main menu screen
			}

		} else {
			// No Collision
			game.batch.draw(gameOverButtonInactive, GAMEO_BTN_X, GAMEO_BTN_Y, PLAY_BTN_WIDTH, PLAY_BTN_HEIGHT);
		}

	}
	

	void imageCollision(int GAMEO_BTN_X, int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT, Screen PauseScreen) {
		if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - GAMEO_BTN_Y)
				&& (game.HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			// game.batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y,
			// PLAY_BTN_WIDTH-30, PLAY_BTN_HEIGHT);

			// Collision mouse with Play Button for click
			if (Gdx.input.isTouched()) { // If it clicks it
				game.setScreen(PauseScreen);// Changes to main menu screen
			}

		}
	}


}
