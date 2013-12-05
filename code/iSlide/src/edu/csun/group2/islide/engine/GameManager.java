package edu.csun.group2.islide.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.engine.entity.TileManager;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable{

	public Texture gameTexture;
	public int size;
	public boolean running, solving;
	TileManager tileManager;
	
	ShapeRenderer renderer;
	
	public GameManager(int size, Texture imageTexture)
	{
		init(size , imageTexture);
	}
	private void init(int size, Texture imageTexture)
	{
		running = true;
		solving = false;
		this.size = size;
		int midpt1 =  0;//(Gdx.graphics.getWidth() - imageTexture.getWidth())/2;
		int midpt2 = 0;
		tileManager = new TileManager(midpt1, midpt2,size, imageTexture);
		
	
		
		renderer = new ShapeRenderer();
	}

	@Override
	public void update(long elapsedMillis) {
		if(running){
			tileManager.update(elapsedMillis);
			running = !tileManager.board.solved();
		}
		else{
			Gdx.app.exit();
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
