package edu.csun.group2.islide.engine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.interfaces.IRenderable;


public class SlideTile implements IRenderable{

	public Sprite sprite;
	public int x, y, width, height, tile_id;
	public SlideTile(int x, int y, int width, int height, int id, Sprite sprite)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tile_id = id;
		this.sprite = sprite;
	}
	@Override
	public void update(long elapsedMillis) {
		this.sprite.setX(this.x);
		this.sprite.setY(this.y);
		
	}
	@Override
	public void draw(SpriteBatch spriteBatch) {
		this.sprite.draw(spriteBatch);
	}
	
}
