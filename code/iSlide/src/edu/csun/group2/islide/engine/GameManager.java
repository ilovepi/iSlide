package edu.csun.group2.islide.engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.interfaces.IRenderable;

public class GameManager implements IRenderable{

	private Texture gameTexture;
	public boolean running;
	
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
		running = true;
	}
	private void stop()
	{
		
	}
	@Override
	public void update(long elapsedMillis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		// TODO Auto-generated method stub
		
	}
	
	
}
