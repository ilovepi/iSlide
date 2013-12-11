package edu.csun.group2.islide.engine;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.engine.entity.TileManager;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable {

	public Texture gameTexture;
	public int size;
	public boolean running, solving;
	TileManager tileManager;
	long prev_frame_millis;
	long solve_delay;
	ShapeRenderer renderer;
	boolean playSound;
	Sound clickSound;
	boolean justTouched;

	public GameManager(int size, Texture imageTexture, boolean playSound) {
		init(size, imageTexture, playSound);
	}

	private void init(int size, Texture imageTexture, boolean playSound) {
		this.playSound = playSound;
		running = true;
		solving = false;
		this.size = size;
		tileManager = new TileManager(size, imageTexture, this);
		prev_frame_millis = 0;
		solve_delay = 25;
		renderer = new ShapeRenderer();
		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.mp3"));
		justTouched = true;
	}

	@Override
	public void update(long elapsedMillis) {
		if (running && !solving) {
			if (playSound && GameInfo.getInstance().touching && justTouched) {
				clickSound.play();
				justTouched = false;
			} else if (!GameInfo.getInstance().touching){
				justTouched = true;
			}
			tileManager.update(elapsedMillis);
			running = !tileManager.board.solved();
		} else if (solving && running) {
			tileManager.touchEnabled = false;
			if (System.currentTimeMillis() - prev_frame_millis > solve_delay) {
				prev_frame_millis = System.currentTimeMillis();
				tileManager.board.solve();
				tileManager.update(elapsedMillis);
				running = !tileManager.board.solved();
			}
		} else {
			if (GameInfo.getInstance().touching)
				Gdx.app.exit();
		}

	}

	private void delay(long millis) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < millis) {
			continue;
		}
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		tileManager.draw(spriteBatch);
	} 
	@Override
	public void GlDraw() {

	}
}
