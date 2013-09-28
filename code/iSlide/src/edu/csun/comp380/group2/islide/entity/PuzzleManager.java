package edu.csun.comp380.group2.islide.entity;

import java.util.ArrayList;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import edu.csun.comp380.group2.islide.util.GameConst;

public class PuzzleManager {

	private int columns, rows;
	private int width, height;
	private int tileWidth, tileHeight;
	private int heightOffset;
	private String assetFilename;
	private BitmapTextureAtlas puzzleTextureAtlas;
	private BaseGameActivity activity;
	private ArrayList<SlideTile> tiles;

	public PuzzleManager(int pWidth, int pHeight, int pColumns, int pRows,
			BitmapTextureAtlas puzzleBitmapTextureAtlas,
			BaseGameActivity pActivity, String pAssetFilename) {
		init(pWidth, pHeight, pColumns, pRows, puzzleBitmapTextureAtlas,
				pActivity, pAssetFilename);
	}

	private void init(int pWidth, int pHeight, int pColumns, int pRows,
			BitmapTextureAtlas puzzleBitmapTextureAtlas, BaseGameActivity pActivity, String pAssetFilename) {
		
		heightOffset = 0; //Don't Know if we necessarily want to deal with a height offset
		
		this.tiles = new ArrayList<SlideTile>();
		this.columns = pColumns > GameConst.getInstance().getMaxPuzzleColumns() ? GameConst
				.getInstance().getMaxPuzzleColumns() : pColumns;
		this.rows = pRows > GameConst.getInstance().getMaxPuzzleRows() ? GameConst
				.getInstance().getMaxPuzzleRows() : pRows;

		this.assetFilename = pAssetFilename;
		this.puzzleTextureAtlas = puzzleBitmapTextureAtlas;
		this.width = pWidth;
		this.height = pHeight;
		this.activity = pActivity;
		
		this.tileWidth = pWidth / columns;
		this.tileHeight = pHeight / rows;
		
		ITiledTextureRegion mRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(puzzleBitmapTextureAtlas, activity, pAssetFilename,0,0, columns, rows);
		puzzleBitmapTextureAtlas.load();
		
		/*
		 * Order of puzzle initialization 
		 * [0] [1] [2] 
		 * [3] [4] [5] 
		 * [6] [7] X
		 */
		for(int i = 0; i < (columns * rows) - 1; i++ )
		{
			tiles.add(new SlideTile((i%columns) * tileWidth , ((i/rows) * tileHeight) + heightOffset, i, mRegion, activity.getVertexBufferObjectManager()));
		}

	}

	public ArrayList<SlideTile> getTiles() {
		// TODO Auto-generated method stub
		return this.tiles;
	}
}
