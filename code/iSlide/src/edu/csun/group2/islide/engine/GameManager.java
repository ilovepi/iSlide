package edu.csun.group2.islide.engine;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.engine.entity.SlideTile;
import edu.csun.group2.islide.engine.entity.TileManager;
import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable{

	private Texture gameTexture;
	public boolean running;
	TileManager tileManager;

	public GameManager()
	{
		init();
	}
	private void init()
	{
		this.running = false;

	}
	public void setImage(Texture texture)
	{
		gameTexture = texture;
	}
	public void start()
	{
		tileManager = new TileManager(gameTexture, 3);
		running = true;
	}
	private void stop()
	{
		
	}
	@Override
	public void update(long elapsedMillis) {
		if(running)
		{
			tileManager.update(elapsedMillis);
		}
		
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		if(running)
		{
			tileManager.draw(spriteBatch);
		}
		
	}
	
	
}
