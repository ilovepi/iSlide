package edu.csun.group2.islide.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import sun.io.Converters;

import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.engine.entity.TileManager;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable {

	public static final long SOLVE_DELAY = 25;

	public int size;
	public int score;

	public long prev_frame_millis;

	public boolean playSound;
	public boolean justTouched;
	public boolean running;
	public boolean solving;
	public boolean scoreRecorded;
	
	public Sound clickSound;
	public Texture gameTexture;

	private TileManager tileManager;

	public GameManager(int size, Texture imageTexture, boolean playSound) {
		init(size, imageTexture, playSound);
	}

	private void init(int size, Texture imageTexture, boolean playSound) {
		this.justTouched = true;
		this.running = true;
		this.solving = false;
		this.playSound = playSound;
		this.size = size;
		this.tileManager = new TileManager(size, imageTexture, this);
		this.prev_frame_millis = 0;
		this.score = 0;
		this.clickSound = Gdx.audio.newSound(Gdx.files
				.internal("data/click.mp3"));
		this.scoreRecorded = false;
	}

	@Override
	public void update(long elapsedMillis) {
		if (running && !solving) {// If game is running and solve button hasn't
									// been pressed
			playSound();
			tileManager.update(elapsedMillis);
			running = !tileManager.board.solved();
		} else if (solving && running) {
			tileManager.touchEnabled = false;
			if (stepThroughSolver())
				tileManager.update(elapsedMillis);
		} else {
			try {
				if (!scoreRecorded) {
					publishScore(this.score);
					scoreRecorded = true;
					Gdx.app.wait(2000);
				}
			} catch (Exception e) {
				//Couldn't Write To file??
				
			}
			if (GameInfo.getInstance().touching){
				Gdx.app.exit();
			}
		}
		
		if (GameInfo.getInstance().touching && justTouched) {
			justTouched = false;
		} else if (!GameInfo.getInstance().touching) {
			justTouched = true;
		}
	}

	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		tileManager.draw(spriteBatch);
	}

	private void publishScore(int score) throws IOException {
		if (score > 0) {
			String scoreString = score + "\t\t\t" + size + "X" + size + "\n";

			FileHandle fh = Gdx.files.external(GameInfo.HIGH_SCORE_PATH);
			fh.writeString(scoreString, true);
		}
	}
	
	/**
	 * Helper method to play sound
	 */
	private void playSound() {
		if (playSound && GameInfo.getInstance().touching && justTouched) {
			clickSound.play();
		}
	}
	/**
	 * Helper Method to Solve board
	 * @return True if a move was made
	 */
	private boolean stepThroughSolver() {
		if (System.currentTimeMillis() - prev_frame_millis > SOLVE_DELAY) {
			prev_frame_millis = System.currentTimeMillis();
			tileManager.board.solve();
			running = !tileManager.board.solved();
			return true;
		}
		return false;
	}
}
