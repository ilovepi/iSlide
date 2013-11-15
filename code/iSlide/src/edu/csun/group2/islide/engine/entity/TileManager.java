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

	public TileManager(int size, Texture texture) {
		emptyTexture = new Texture("data/gray_bg.png");// later abstract and
														// make invisible
		board = new Board(3);
		tiles = new ArrayList<SlideTile>();
		int tWidth = board.width;

		int len = board.ary.size();
		for (int i = 0; i < len; i++) {
			int id = (int) board.ary.get(i);
			if (id != 0) {

				tiles.add(new SlideTile((i % size) * tWidth, (i / size)
						* tWidth, tWidth, tWidth, id, new Sprite(tileTexture,
						(id % size) * tWidth, (id / size) * tWidth, tWidth,
						tWidth)));
			} else {
				tiles.add(new SlideTile((i % size) * tWidth, (i / size)
						* tWidth, tWidth, tWidth, id, new Sprite(emptyTexture,
						tWidth, tWidth)));
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
