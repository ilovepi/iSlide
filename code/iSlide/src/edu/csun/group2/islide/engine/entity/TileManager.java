package edu.csun.group2.islide.engine.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.engine.Board;
import edu.csun.group2.islide.interfaces.IRenderable;

public class TileManager implements IRenderable {

	Texture tileTexture;
	Board board;
	ArrayList<SlideTile> tiles;
	private Texture emptyTexture;
	int tWidth;
	int xOffSet, yOffSet;
	
	public TileManager(int size, Texture texture) {
		init(0,0,size, texture);
	}
	public TileManager(int x, int y, int size, Texture texture)
	{
		init(x,y,size,texture);
	}
	private void init(int xOffSet, int yOffSet, int size, Texture texture)
	{
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
		tileTexture = texture;
		board = new Board(3);
		tiles = new ArrayList<SlideTile>();
		tWidth = texture.getWidth() / size;

		int len = board.ary.size();
		for (int i = 0; i < len; i++) {
			int id = (int) board.ary.get(i);
			if (id != 0) {
				tiles.add(new SlideTile(((i % size) * tWidth) + xOffSet, 
						((i / size)* tWidth) + yOffSet, tWidth, tWidth, id, new Sprite(texture,
						(id % size) * tWidth, (id / size) * tWidth, tWidth,
						tWidth)));
			} else {
				tiles.add(new SlideTile(((i % size) * tWidth) + xOffSet, ((i / size) * tWidth) + yOffSet, tWidth, tWidth, id, new Sprite(texture, 0, 0)));
			}
		}
	}
	@Override
	public void update(long elapsedMillis) {
		for (SlideTile tile : tiles) {
			tile.update(elapsedMillis);
		}

	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		for (SlideTile tile : tiles) {
			tile.draw(spriteBatch);
		}
	}
}
