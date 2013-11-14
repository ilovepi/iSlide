package edu.csun.group2.islide.engine.entity;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.csun.group2.islide.engine.board.Board;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class TileManager implements IRenderable {

	public static final int TOUCH_OFFSET = 64;
	Texture tileTextureAtlas;

	Board board;

	public int size;
	public SlideTile[][] tiles;

	public int pixelWidth, pixelHeight;

	public TileManager(Texture texture, int size) {
		this.size = size;
		this.board = new Board(size);
		tiles = new SlideTile[size][size];

		pixelWidth = texture.getWidth() / size;
		pixelHeight = texture.getHeight() / size;
		int counter = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tiles[i][j] = new SlideTile(j * pixelWidth, i * pixelHeight,
						pixelWidth, pixelHeight, new Sprite(texture, j
								* pixelWidth, i * pixelHeight, pixelWidth,
								pixelHeight), counter);
				counter++;
			}
		}
		tiles[0][0] = new SlideTile(0, 0, pixelWidth, pixelHeight, new Sprite(new Texture("data/emptytexture.png"), 0, 0, pixelWidth, pixelHeight),0);
	}

	@Override
	public void update(long elapsedMillis) {

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (GameInfo.getInstance().touchPoint
						.intersects(tiles[i][j].rect) && tiles[i][j].tileId != 0) {
					if(GameInfo.getInstance().touchPoint.x > GameInfo.getInstance().touchedX + TOUCH_OFFSET)
					{
						board.moveTileRight(tiles[i][j]);
					}
					else if(GameInfo.getInstance().touchPoint.x < GameInfo.getInstance().touchedX - TOUCH_OFFSET)
					{
						board.moveTileLeft(tiles[i][j]);
					}
					else if(GameInfo.getInstance().touchPoint.y < GameInfo.getInstance().touchedY - TOUCH_OFFSET)
					{
						board.moveTileDown(tiles[i][j]);
					}
					else if(GameInfo.getInstance().touchPoint.y > GameInfo.getInstance().touchedY + TOUCH_OFFSET)
					{
						board.moveTileUp(tiles[i][j]);
					}
				}
			}
		}

	}

	@Override
	public void draw(SpriteBatch spriteBatch) {

		if (tiles != null) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					tiles[i][j].draw(spriteBatch);
				}
			}
		}

	}

}
