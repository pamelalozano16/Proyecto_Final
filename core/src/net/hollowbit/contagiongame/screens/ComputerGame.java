package net.hollowbit.contagiongame.screens;

import com.badlogic.gdx.Gdx;
import java.util.Random;

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

public class ComputerGame implements Screen {

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
	TextureRegion[] doctor;
	Animation<TextureRegion> doctorAnimation;
	Texture playButtonActive;
	Texture playButtonInactive;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    float stateTime;
    
    //Game Wood
    Texture mainWood;
    Texture wood1;
    Texture wood2;
    Texture wood3;
    Texture wood4;
    boolean question=true;
    int questionNum=0;
    int counter =0;
	private BitmapFont font;
	int score =0;
	int correct=0;
	boolean help= false;
	
	//BOTONES
	Texture pauseButtonActive;
	Texture pauseButtonInactive;
	
	 String[] preguntas = {"Scientist believe that COVID-19 is spread primarly through:",
			 "What is a symtom that is commonly associated with the illness:",
			 "To keep yourself and others safe, you should:",
			 "To properly wash your hands you should wash them for at least ____ seconds:",
			 "You should use a sanitazier that has at least ____ percent alcohol",
			 "Which of the following isnt an acceptable thing to do according to the social distancing guidelines",
			 "Which people have higher chance to be at serious risk","Can you not present symtoms if you're infected",
			 "Do you need to wear a mask if you want to go outside even if you're healthy",
			 "What is COVID-19 mortality rate in Mexico:",
			 "Which was the first country to have 1 million people infected:",
			 "Where was the first recorded case of COVID-19",
			 "If you get infected can you recover?",
			 "What is the biggest measure countries have taken in order to prevent the spread of COVID-19:",
			 "Is being infected a death sentece?",
			 "What is the country with the biggest amount of confirmed cases:",
			 "As of may 2020, a cure for COVID-19 has been found:",
			 "If you're young and healthy you cant get infected"
			 };
	 String[][]repuestas= {{"Respiratory droplets that pass from person to person","Contact with infected animals","Food packaging and other contaminated surfaces","All of the above","0"},
			 {"Shortness of breath","Excessive sweating","Headache","Bleeding","0"},
			 {"Wash hands frequently","Clean and disenfect everything that is touched regularly","Avoid contact with people that are sick","All of the above","3"},
			 {"30","20","40","10","1"},
			 {"90","70","20","30","2"},
			 {"Walking the dog","Delivering the groceries to a loved one","Going to a group meeting or event, as long as no one touches","visiting the doctor for something that doesn’t have to do with COVID-19","2"},
			 {"Those with preexisting conditions like athsma","Adults 65 and older","Babies and young children, as long as no one touches","Both older adults and thos with preexisting conditions","3"},
			 {"Yes","No","","","0"},
			 {"Yes","No","","","0"},
			 {"30%","50%","1%","10%","3"},
			 {"China","Russia","USA","India","2"},
			 {"USA","China","Thailand","Spain","1"},
			 {"Yes","No","","","0"},
			 {"China","Russia","Thailand","USA","3"},
			 {"TRUE","False","","","1"},
			 {"TRUE","False","","","1"},
			 
			 };		

	public ComputerGame(ContagionGame game) {
		this.game = game;
		this.playButtonActive = new Texture("play_active.png");
		this.playButtonInactive = new Texture("play_inactive.png");
		this.pauseButtonActive = new Texture("goback_active.png");
		this.pauseButtonInactive = new Texture("goback_inactive.png");
        backgroundTexture = new Texture("compuBack.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
		logo = new Texture("computerLogo.png");
		
		this.mainWood = new Texture("wood2.png");
		this.wood1 = new Texture("wood3.png");
		this.wood2 = new Texture("wood3.png");
		this.wood3 = new Texture("wood3.png");
		this.wood4 = new Texture("wood3.png");
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(1, 1);
		
			
		
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
		
		if(counter <6) {
		game.batch.draw(mainWood, (game.WIDTH/2)-200, (game.HEIGHT/2)+100, 400, 200);
		
		game.batch.draw(wood1, (game.WIDTH/2)-400, ((game.HEIGHT/2)-100)-100, 300, 150);
		imageCollision2((game.WIDTH/2)-400, ((game.HEIGHT/2)-100)-100, 300, 150,"0");
		
		game.batch.draw(wood2, (game.WIDTH/2)-400, ((game.HEIGHT/2)+50)-100, 300, 150);
		imageCollision2((game.WIDTH/2)-400, ((game.HEIGHT/2)+50)-100, 300, 150,"1");
		
		game.batch.draw(wood3, (game.WIDTH/2)+100, ((game.HEIGHT/2)-100)-100, 300, 150);
		imageCollision2((game.WIDTH/2)+100, ((game.HEIGHT/2)-100)-100, 300, 150,"2");
		
		game.batch.draw(wood4, (game.WIDTH/2)+100, ((game.HEIGHT/2)+50)-100, 300, 150);
		imageCollision2((game.WIDTH/2)+100, ((game.HEIGHT/2)+50)-100, 300, 150,"3");
		
		if(correct==1) {
			font.draw(game.batch, "Correct",1000, (350)+250);
		}else if(correct ==2) {
			font.draw(game.batch, "Wrong Answer",1000, (350)+250);
		}
		if(question == true) {
			questionNum = getNextQuestion();
			question=false;
		}
	
		font.draw(game.batch, preguntas[questionNum],(600)-150, (350)+250);
		font.draw(game.batch, repuestas[questionNum][0] ,(650)-400, ((game.HEIGHT/2)-100));
		font.draw(game.batch, repuestas[questionNum][1],(650)-400, ((game.HEIGHT/2)+50));
		font.draw(game.batch, repuestas[questionNum][2] ,(650)+100, ((game.HEIGHT/2)-100));
		font.draw(game.batch, repuestas[questionNum][3] ,(650)+100, ((game.HEIGHT/2)+50));
		}else {
			System.out.println(score);
			if(!help) {
				game.money+=(score)*4;
				help=true;
			}

			font.draw(game.batch, "Score:"+ Integer.toString(score) +"/5",(600)-10, (game.HEIGHT/2)+100);
			game.batch.draw(pauseButtonInactive,(game.WIDTH/2)-100, (game.HEIGHT/2), 200, 100);
			if (Gdx.input.isTouched()) {
				if ((game.WIDTH/2)-50 <= Gdx.input.getX() && Gdx.input.getX() <= ((game.WIDTH/2) + 150)
						&& Gdx.input.getY() <= (game.HEIGHT - (game.HEIGHT/2))
						&& (game.HEIGHT - (game.HEIGHT/2) - 100 < Gdx.input.getY())) {
						game.setScreen(new MainGameScreen(game));
					}

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
	
	int getNextQuestion() {
		Random rand =new Random();
		int randomNum = rand.nextInt(((15-0)+1)+0);
		return randomNum;

	}
	void imageCollision2(int GAMEO_BTN_X, int GAMEO_BTN_Y, int PLAY_BTN_WIDTH, int PLAY_BTN_HEIGHT,String num) {
		if (GAMEO_BTN_X <= Gdx.input.getX() && Gdx.input.getX() <= (GAMEO_BTN_X + PLAY_BTN_WIDTH)
				&& Gdx.input.getY() <= (game.HEIGHT - GAMEO_BTN_Y)
				&& (game.HEIGHT - GAMEO_BTN_Y - PLAY_BTN_HEIGHT < Gdx.input.getY())) {
			// Collision mouse with Play Button for hover
			// game.batch.draw(gameOverButtonActive, GAMEO_BTN_X + 30, GAMEO_BTN_Y,
			// PLAY_BTN_WIDTH-30, PLAY_BTN_HEIGHT);

			// Collision mouse with Play Button for click
			if (Gdx.input.justTouched()) { // If it clicks it
				counter++;// Changes to main menu screen
				System.out.print(counter);
				if(num ==repuestas[questionNum][4]) {
					score++;
					correct=1;
				}else {
					correct=2;
				}
				question=true;
			}

		}
	}

}
