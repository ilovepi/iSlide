package edu.csun.group2.islide.engine.entity;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.interfaces.IRenderable;

public class SlideTile implements IRenderable {

	Sprite sprite;
	int x, y, width,height;
	public com.badlogic.gdx.math.Rectangle rect;
	public int tileId;
	public SlideTile(int x, int y, int width, int height, Sprite sprite,int tileId)
	{
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rect = new Rectangle(x,y,width,height);
		this.tileId = tileId;
	}
	@Override
	public void update(long elapsedMillis) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
		
	}
	
}
