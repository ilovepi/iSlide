package edu.csun.group2.islide.engine;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.engine.entity.SlideTile;
import edu.csun.group2.islide.engine.entity.TileManager;
import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable{

	public Texture gameTexture;
	public int size;
	public boolean running;
	TileManager tileManager;

	public GameManager(int size, Texture imageTexture)
	{
		init(size , imageTexture);
	}
	private void init(int size, Texture imageTexture)
	{
		this.size = size;
		int midpt1 =  0;//(Gdx.graphics.getWidth() - imageTexture.getWidth())/2;
		int midpt2 = 0;
		tileManager = new TileManager(midpt1, midpt2,size, imageTexture);
	}

	@Override
	public void update(long elapsedMillis) {
		tileManager.update(elapsedMillis);
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		tileManager.draw(spriteBatch);
		
	}
	

	@Override
	public void GlDraw() {
		
	}
}
