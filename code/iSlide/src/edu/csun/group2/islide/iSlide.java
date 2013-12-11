package edu.csun.group2.islide;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	Rectangle r2;
	int size;
	String path;
	private boolean playSound;

	public iSlide(int puzzleSize, String path, boolean sound) {
		init(puzzleSize, path, sound);
	}

	private void init(int puzzleSize, String imgPath, boolean sound) {
		this.size = puzzleSize;
		this.path = imgPath;
		this.playSound = sound;
	}

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(480, 800);
		camera.setToOrtho(true, 480, 800);
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
		if (this.path != "") {
			Bitmap bmp = BitmapFactory.decodeFile(path);
			Bitmap newBmp = Bitmap.createScaledBitmap(bmp, 480, 480, false);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			newBmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] bytes = stream.toByteArray();
			Pixmap p = new Pixmap(bytes,0,bytes.length);
			Texture t = new Texture(p);
			gameManager = new GameManager(size,t, playSound);
		}
		else{
			gameManager = new GameManager(size,  new Texture("data/testimage.png"), playSound);
		}
		batch.setProjectionMatrix(camera.combined);
		startTime = System.currentTimeMillis();
		GameInfo.getInstance().gameCamera = camera;

	}

	@Override
	public void dispose() {
		batch.dispose();
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
		gameManager.GlDraw();
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
