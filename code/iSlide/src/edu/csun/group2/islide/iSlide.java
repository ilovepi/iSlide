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

import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.engine.InputHandler;
import edu.csun.group2.islide.global.GameInfo;

public class iSlide extends Game {
	
	public static final int PROJECTED_WIDTH = 480;
	public static final int PROJECTED_HEIGHT = 800;
	public static final boolean INVERTED_Y = true;
	public static final boolean INVERTED_X = false;
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private GameManager gameManager;
	private long startTime;
	private BitmapFont font;
	private int size;
	private String path;
	private boolean playSound;

	public iSlide(int puzzleSize, String path, boolean sound, boolean showNum) {
		init(puzzleSize, path, sound, showNum);
	}

	private void init(int puzzleSize, String imgPath, boolean sound, boolean showNum) {
		this.size = puzzleSize;
		this.path = imgPath;
		this.playSound = sound;
		GameInfo.getInstance().showNumbers = showNum;
	}

	@Override
	public void create() {
		camera = new OrthographicCamera(PROJECTED_WIDTH, PROJECTED_HEIGHT);
		camera.setToOrtho(INVERTED_Y, PROJECTED_WIDTH, PROJECTED_HEIGHT);
		camera.update();
		batch = new SpriteBatch();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		InputHandler input = new InputHandler();
		Gdx.input.setInputProcessor(input);
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2.0f);
		font.setScale(2, -2);

		if (this.path != "") {
			Bitmap bmp = BitmapFactory.decodeFile(path);
			Bitmap newBmp = Bitmap.createScaledBitmap(bmp, PROJECTED_WIDTH, PROJECTED_WIDTH, false);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			newBmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] bytes = stream.toByteArray();
			Pixmap p = new Pixmap(bytes, 0, bytes.length);
			Texture t = new Texture(p);
			gameManager = new GameManager(size, t, playSound);
		} else {
			gameManager = new GameManager(size, new Texture(
					"data/testimage.png"), playSound);
		}

		batch.setProjectionMatrix(camera.combined);
		GameInfo.getInstance().gameCamera = camera;

		startTime = System.currentTimeMillis();
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
	}

	private void update(long elapsedMillis) {
		if (gameManager != null)
			this.gameManager.update(elapsedMillis);
	}

	private void draw(SpriteBatch spriteBatch) {
		if (spriteBatch != null) {
			spriteBatch.begin();
			if (gameManager != null)
				this.gameManager.draw(spriteBatch);
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
