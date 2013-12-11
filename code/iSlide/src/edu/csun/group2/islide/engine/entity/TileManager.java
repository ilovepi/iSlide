package edu.csun.group2.islide.engine.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import edu.csun.group2.islide.iSlide;
import edu.csun.group2.islide.engine.GameBoard;
import edu.csun.group2.islide.engine.GameManager;
import edu.csun.group2.islide.global.GameInfo;
import edu.csun.group2.islide.interfaces.IRenderable;

public class TileManager implements IRenderable {

	public Texture tileTexture;
	public GameBoard board;
	public int tWidth;
	public int size;
	public boolean justTouched;
	public boolean touchEnabled;
	
	private int totalTiles;
	private BitmapFont font;
	private SlideTile[] tiles;
	private Vector3 unprojectionVector;
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
		init(size, texture, manager);
	}

	private void init(int size, Texture texture, GameManager manager) {
		this.gameManager = manager;
		this.size = size;
		this.tileTexture = texture;
		this.board = new GameBoard(size);

		this.tiles = new SlideTile[size * size];
		this.tWidth = texture.getWidth() / size;
		this.font = new BitmapFont();
		this.font.setColor(Color.BLACK);
		this.font.setScale(2, -2);
		this.touchEnabled = true;

		this.solveButton = new Rectangle(0, texture.getHeight() + 100, 400, 100);
		this.solveTexture = new Sprite(new Texture("data/solvebutton.png"));
		this.solveTexture.flip(false, true);
		this.solveTexture.setX(solveButton.x);
		this.solveTexture.setY(solveButton.y);

		this.totalTiles = size * size;
		for (int i = 0; i < totalTiles; i++) {
			int id = (int) board.ary.get(i);								//Map the value of the board based on current index
			Sprite passSprite;												//Create a Sprite here because the params may differ
			if (id != 0) {													//if it isn't the empty sprite
				passSprite = new Sprite(texture, (id % size) * tWidth,		//set the sprite = to a sub section of the texture based on which id
						(id / size) * tWidth, tWidth, tWidth);				//exists on the board at current index.
				passSprite.flip(iSlide.INVERTED_X, iSlide.INVERTED_Y);		//Flip the sprite to match the libgdx coordinate flip

				tiles[id] = (new SlideTile(((i % size) * tWidth), 			//We insert into the array at the current value of the board
						((i / size) * tWidth), tWidth, tWidth, id,			//Add any offsets as necessary
						passSprite));										//And set it's current position based on the board value 
			} else {
				passSprite = new Sprite(texture, 0, 0);						//Otherwise it if is the empty sprite
				passSprite.flip(iSlide.INVERTED_X, iSlide.INVERTED_Y);		//we create a tile with no texture
				tiles[id] = (new SlideTile(((i % size) * tWidth),
						((i / size) * tWidth), tWidth, tWidth, id,
						passSprite));
			}
		}
	}

	/**
	 * Update Loop
	 */
	@Override
	public void update(long elapsedMillis) {
		handleInput();
		updateTilePositions(elapsedMillis);
	}
	@Override
	public void draw(SpriteBatch spriteBatch) {
		for (SlideTile tile : tiles) {
			tile.draw(spriteBatch);
			font.draw(spriteBatch, tile.tile_id + "", tile.x, tile.y);
		}
		solveTexture.draw(spriteBatch);
	}
	
	/**
	 * Private helper method to clean up and modularize code
	 * 
	 * @param elapsedMillis
	 *            Time since last frame elapse
	 */
	private void updateTilePositions(long elapsedMillis) {
		for (int i = 0; i < (size * size); i++) {

			int id = (int) board.ary.get(i);
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[j].tile_id == id) {
					tiles[j].x = ((i % size) * tWidth);
					tiles[j].y = (((i / size)) * tWidth);
				}
				tiles[j].update(elapsedMillis);
			}

		}
	}
	/**
	 * Private helper method to clean up and modularize code
	 */
	private void handleInput()
	{
		if (touchEnabled) {
			for (int i = 0; i < totalTiles; i++) {
				if (tiles[board.ary.get(i)].tile_id == 0
						|| !GameInfo.getInstance().touching)
					continue;
				unprojectionVector = new Vector3(
						GameInfo.getInstance().touchRectangle.x,
						GameInfo.getInstance().touchRectangle.y, 0);				//To match Libgdx's coordinate system we have to
				GameInfo.getInstance().gameCamera.unproject(unprojectionVector);	//Unproject the Orthographic Camera and map to the screen
				Rectangle unprojectedRect = new Rectangle(unprojectionVector.x,
						unprojectionVector.y, 1, 1);
				Rectangle cRect = new Rectangle(tiles[board.ary.get(i)].x,
						tiles[board.ary.get(i)].y, tWidth, tWidth);
				if (GameInfo.getInstance().touching
						&& GameInfo.getInstance().touchRectangle != null
						&& unprojectedRect.overlaps(cRect) && !justTouched) {
					board.move(i);
					gameManager.score += 1;
					justTouched = true;
				} else if (GameInfo.getInstance().touching
						&& GameInfo.getInstance().touchRectangle != null
						&& unprojectedRect.overlaps(this.solveButton)
						&& !justTouched) {
					justTouched = true;
					this.gameManager.solving = true;
					gameManager.score = 0;
				}
			}
			if (!GameInfo.getInstance().touching) {
				justTouched = false;
			}
		}
	}
}
