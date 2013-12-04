package edu.csun.group2.islide.engine.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.engine.Board;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class TileManager implements IRenderable {

	Texture tileTexture;
	Board board;
	//ArrayList<SlideTile> tiles;
	SlideTile[] tiles;
	private Texture emptyTexture;
	int tWidth;
	int xOffSet, yOffSet;
	ShapeRenderer shapes;
	int size;
	boolean justTouched;
	BitmapFont font;
	String tileTouchedID;
	/**
	 * Instantiate New Tile Manager
	 * 
	 * @param size
	 *            Tile count for width/height
	 * @param texture
	 *            Image to use
	 */
	public TileManager(int size, Texture texture) {
		init(0, 0, size, texture);
	}

	/**
	 * Instantiate new Tile Manager
	 * 
	 * @param x
	 *            X origin of where the tiles show up
	 * @param y
	 *            Y origin of where the tiles show up
	 * @param size
	 *            Tile Count for width/height
	 * @param texture
	 *            image to use for puzzle
	 */
	public TileManager(int x, int y, int size, Texture texture) {
		init(x, y, size, texture);
	}

	private void init(int xOffSet, int yOffSet, int size, Texture texture) {
		this.size = size;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
		tileTexture = texture;
		board = new Board(size);
		//tiles = new ArrayList<SlideTile>();
		tiles = new SlideTile[size*size];
		tWidth = texture.getWidth() / size;
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2, -2);
		tileTouchedID = "";
		for (int i = 0; i < size*size; i++) {
			int id = (int) board.ary.get(i);// shouldn't this just be id = i
			Sprite passSprite;
			if (id != 0) {
				passSprite = new Sprite(texture, (id % size) * tWidth,
						(id / size) * tWidth, tWidth, tWidth);
				passSprite.flip(false, true);

				tiles[id] = (new SlideTile(((i % size) * tWidth) + xOffSet,
						((i / size) * tWidth) + yOffSet, tWidth, tWidth, id,
						passSprite));
			} else {
				passSprite = new Sprite(texture, 0, 0);
				passSprite.flip(false, true);
				tiles[id] = (new SlideTile(((i % size) * tWidth) + xOffSet,
						((i / size) * tWidth) + yOffSet, tWidth, tWidth, id,
						passSprite));
			}
		}
	}

	/**
	 * Update Loop
	 */
	@Override
	public void update(long elapsedMillis) {
		boolean moved = false;
		for (int i = 0; i < (size * size); i++) {
			if (tiles[board.ary.get(i)].tile_id == 0)
				continue;

			Rectangle cRect = new Rectangle(tiles[board.ary.get(i)].x, tiles[board.ary.get(i)].y,
					tWidth, tWidth);
			if (GameInfo.getInstance().touching
					&& GameInfo.getInstance().touchRectangle != null
					&& GameInfo.getInstance().touchRectangle.overlaps(cRect)
					&& !justTouched) {
				moved = board.move(i);
				tileTouchedID = tiles[board.ary.get(i)].tile_id + " index at: " + i;
				justTouched = true;
			}
		}
		if (!GameInfo.getInstance().touching) {
			justTouched = false;
		}
		if (true) {
			for (int i = 0; i < (size * size); i++) {
				
				int id = (int) board.ary.get(i);
				for (int j = 0; j < tiles.length; j++) {
					if (tiles[j].tile_id != id){
						
					}
					else {
						tiles[j].x = ((i % size) * tWidth) + xOffSet ;
						tiles[j].y = (((i / size)) * tWidth) + yOffSet;
					}
					tiles[j].update(elapsedMillis);
				}

			}
		}

	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		for (SlideTile tile : tiles) {
			tile.draw(spriteBatch);
			font.draw(spriteBatch, tile.tile_id + "", tile.x, tile.y + yOffSet);
			font.draw(spriteBatch, "Tile ID Touched = " + tileTouchedID, 300, 600);
			font.draw(spriteBatch, "Tile 3 x: " + tiles[3].x + " y: " + tiles[3].y, 300, 700);
		}

	}

	public void GlDraw() {

	}
}
