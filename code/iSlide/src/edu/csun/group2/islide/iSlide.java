package edu.csun.group2.islide;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.engine.GameInput;
import edu.csun.group2.islide.global.Utility;

public class iSlide implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture gameTexture;
	private Sprite sprite;
	private byte[] img;
	private float screenWidth, screenHeight;
	GameManager gameManager;
	private long startTime;
	private int nPuzzleSize;
	GameInput input;
	
	public iSlide()
	{
		init(3,null);
	}

	private void init(int nPuzzle, byte[] img)
	{
		
		 input = new GameInput();

		
	}
	@Override
	public void create() {
		Gdx.input.setInputProcessor(input);
		float w = screenWidth = Gdx.graphics.getWidth();
		float h = screenHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);

		//gameTexture = Utility.convertByteArrayToTexture(img);
		gameTexture = new Texture("data/testimage.png");
		gameManager = new GameManager();
		gameManager.setImage(gameTexture);
		
		startTime = System.currentTimeMillis();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		{
			update(System.currentTimeMillis() - startTime);
			draw(batch);
		}

	}

	private void update(long elapsedMillis) {
		if (gameManager != null && gameManager.running) {
			this.gameManager.update(elapsedMillis);
		}
	}

	private void draw(SpriteBatch spriteBatch) {
		spriteBatch.begin();
		{
			if (gameManager != null && gameManager.running) {
				this.gameManager.draw(spriteBatch);
			}
		}
		spriteBatch.end();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	public void gameOver()
	{

	}

	@Override
	public void resize(int width, int height) {
		
		float w = screenWidth = Gdx.graphics.getWidth();
		float h = screenHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h / w);
		batch.setProjectionMatrix(camera.combined);
	}
}
