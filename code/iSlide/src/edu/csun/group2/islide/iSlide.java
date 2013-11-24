package edu.csun.group2.islide;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.global.Utility;

public class iSlide extends Game {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite testSprite;
	GameManager gameManager;
	private long startTime;
	private Rectangle glViewport;

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
		camera.position.set(w / 2, h / 2, 1);
		glViewport = new Rectangle(0, 0, w, h);
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(0, 0, 1, 1);

		// testSprite = new Sprite(new Texture("data/islidelogo.png"));

		gameManager = new GameManager(3, new Texture("data/testimg.jpg"));

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
		}
		Gdx.gl.glViewport((int) glViewport.x, (int) glViewport.y,
				(int) glViewport.width, (int) glViewport.height);

	}

	private void update(long elapsedMillis) {
		if (gameManager != null) {
			this.gameManager.update(elapsedMillis);
		}
	}

	private void draw(SpriteBatch spriteBatch) {
		if (spriteBatch != null) {
			spriteBatch.begin();
			{
				if (gameManager != null) {
					this.gameManager.draw(spriteBatch);
				}
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
