package edu.csun.group2.islide;

import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.engine.InputHandler;
import edu.csun.group2.islide.global.GameInfo;

public class iSlide extends Game {
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	GameManager gameManager;
	private long startTime;
	private Rectangle glViewport;
	private ShapeRenderer renderer;
	private BitmapFont font;
	Rectangle r2 ;
	public iSlide() {
		init(null, 3, null);
	}

	public iSlide(Context pContext, int nPuzzle) {
		init(pContext, nPuzzle, null);
	}

	public iSlide(Context pContext, int nPuzzle, byte[] img) {
		init(pContext, nPuzzle, img);
	}

	private void init(Context pContext, int nPuzzle, byte[] img) {

	}
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(true, w, h);
		camera.update();
		// camera.position.set(w / 2, h / 2, 1);
		glViewport = new Rectangle(0, 0, w, h);
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		InputHandler input = new InputHandler();
		Gdx.input.setInputProcessor(input);
		
		renderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2.0f);
		font.setScale(2, -2);
		// testSprite = new Sprite(new Texture("data/islidelogo.png"));
		gameManager = new GameManager(3, new Texture("data/testimage.png"));

		batch.setProjectionMatrix(camera.combined);
		startTime = System.currentTimeMillis();

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		{
			update(System.currentTimeMillis() - startTime);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			draw(batch);
		}if(GameInfo.getInstance().touchRectangle != null){
			r2 = GameInfo.getInstance().touchRectangle; 
		renderer.setColor(Color.BLACK);
		renderer.begin(ShapeType.Filled);
		renderer.rect(r2.x, r2.y, r2.width+5, r2.height + 5);
		renderer.end();
		}

		Gdx.gl.glViewport((int) glViewport.x, (int) glViewport.y,
				(int) glViewport.width, (int) glViewport.height);

	}

	private void update(long elapsedMillis) {
		if (gameManager != null) {
			this.gameManager.update(elapsedMillis);
		}
		if (GameInfo.getInstance().touching
				&& GameInfo.getInstance().touchRectangle != null) {
		
		}
	}

	private void draw(SpriteBatch spriteBatch) {
		if (spriteBatch != null) {
			spriteBatch.begin();
			{
				
				if (gameManager != null) {
					this.gameManager.draw(spriteBatch);
				}
				if(r2 != null)
				{
					
					font.draw(batch, "Touching At - X: " + r2.x + " Y: " + r2.y, 50, 900);
				}
				font.draw(spriteBatch, "isTouching? " + GameInfo.getInstance().touching, 50, 970);
			}
			spriteBatch.end();

			
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	public void gameOver() {

	}
}
