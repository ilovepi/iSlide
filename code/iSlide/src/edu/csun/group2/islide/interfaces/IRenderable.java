package edu.csun.group2.islide.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderable {
	void update(long elapsedMillis);
	void draw(SpriteBatch spriteBatch);
	void GlDraw();
}
