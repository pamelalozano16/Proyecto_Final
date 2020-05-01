package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ContagionGame;


public class MainGameScreen implements Screen{
	
	public static final float SPEED = 120;
	
	Texture img;
	float x;
	float y;
	
	ContagionGame game;
	
	public MainGameScreen(ContagionGame game) {
		this.game = game;
	}

	@Override
	public void show () {
		img = new Texture("contagion.png");
	}

	@Override
	public void render (float delta) {

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			y+=SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			y-=SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x-=SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x+=SPEED * Gdx.graphics.getDeltaTime();
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		game.batch.begin();
		game.batch.draw(img, x, y, 300, 200);
		game.batch.end();
		
			
		
	}

	@Override
	public void resize (int width, int height) {

		
	}

	@Override
	public void pause () {

		
	}

	@Override
	public void resume () {

		
	}

	@Override
	public void hide () {

		
	}

	@Override
	public void dispose () {

		
	}

}
