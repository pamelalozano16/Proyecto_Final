package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ContagionGame;

public class MainMenuScreen implements Screen {

	ContagionGame game;
	Texture logo;
	private static final int LOGO_WIDTH = 300;
	private static final int LOGO_HEIGHT = 200;

	Texture playButtonActive;
	Texture playButtonInActive;

	public MainMenuScreen(ContagionGame game) {
		this.game = game;
		logo = new Texture("contagion.png");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		// Pinta el logo
		game.batch.draw(logo, (game.WIDTH / 2) - (LOGO_WIDTH / 2), game.HEIGHT - (LOGO_HEIGHT), LOGO_WIDTH,
				LOGO_HEIGHT);
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
