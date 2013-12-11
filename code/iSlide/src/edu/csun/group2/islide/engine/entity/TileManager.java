package edu.csun.group2.islide.engine.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.csun.group2.islide.engine.GameBoard;
import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class TileManager implements IRenderable {

	public Texture tileTexture;
	public GameBoard board;
	
	SlideTile[] tiles;
	
	public int tWidth;
	public int xOffSet; 
	public int yOffSet;
	public int size;
	public boolean justTouched;
	public boolean touchEnabled;
	public BitmapFont font;
	Vector3 unprojectionVector;
	
	private Rectangle solveButton;
	private Sprite solveTexture;
	private GameManager gameManager;
	

	/**
	 * Instantiate New Tile Manager
	 * 
	 * @param size
	 *            Tile count for width/height
	 * @param texture
	 *            Image to use
	 */
	public TileManager(int size, Texture texture, GameManager manager) {
		init(0, 0, size, texture, manager);
	}

	private void init(int xOffSet, int yOffSet, int size, Texture texture,
			GameManager manager) {
		this.gameManager = manager;
		this.size = size;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
		tileTexture = texture;
		board = new GameBoard(size);

		tiles = new SlideTile[size * size];
		tWidth = texture.getWidth() / size;
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2, -2);
		touchEnabled = true;

		solveButton = new Rectangle(0, texture.getHeight() + 100, 400, 100);
		solveTexture = new Sprite(new Texture("data/solvebutton.png"));
		solveTexture.flip(false, true);
		solveTexture.setX(solveButton.x);
		solveTexture.setY(solveButton.y);

		for (int i = 0; i < size * size; i++) {
			int id = (int) board.ary.get(i);
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
		if (touchEnabled) {
			for (int i = 0; i < (size * size); i++) {
				if (tiles[board.ary.get(i)].tile_id == 0
						|| !GameInfo.getInstance().touching)
					continue;
				unprojectionVector = new Vector3(
						GameInfo.getInstance().touchRectangle.x,
						GameInfo.getInstance().touchRectangle.y, 0);
				GameInfo.getInstance().gameCamera.unproject(unprojectionVector);

				Rectangle unprojectedRect = new Rectangle(unprojectionVector.x,
						unprojectionVector.y, 1, 1);
				Rectangle cRect = new Rectangle(tiles[board.ary.get(i)].x,
						tiles[board.ary.get(i)].y, tWidth, tWidth);

				if (GameInfo.getInstance().touching
						&& GameInfo.getInstance().touchRectangle != null
						&& unprojectedRect.overlaps(cRect) && !justTouched) {
					moved = board.move(i);
					justTouched = true;
				} else if (GameInfo.getInstance().touching
						&& GameInfo.getInstance().touchRectangle != null
						&& unprojectedRect.overlaps(this.solveButton)
						&& !justTouched) {
					justTouched = true;
					this.gameManager.solving = true;
				}

			}
			if (!GameInfo.getInstance().touching) {
				justTouched = false;
			}
		}
		if (true) {
			for (int i = 0; i < (size * size); i++) {

				int id = (int) board.ary.get(i);
				for (int j = 0; j < tiles.length; j++) {
					if (tiles[j].tile_id != id) {

					} else {
						tiles[j].x = ((i % size) * tWidth) + xOffSet;
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
		}
		solveTexture.draw(spriteBatch);
	}

	public void GlDraw() {

	}
}
