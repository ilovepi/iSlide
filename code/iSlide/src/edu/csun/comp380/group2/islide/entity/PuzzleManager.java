package edu.csun.comp380.group2.islide.entity;

import java.util.ArrayList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import edu.csun.comp380.group2.islide.util.GameConst;

//Update Handler allows it to update on the game loop
public class PuzzleManager implements IUpdateHandler {

	private int columns, rows, width, height, tileWidth, tileHeight;
	
	private String assetFilename;
	
	private BitmapTextureAtlas puzzleTextureAtlas;
	private BaseGameActivity activity;
	private SlideTile[][] gridTiles;
	
	
	// Important, Tells us if the user has completed the puzzle
	private boolean puzzleComplete;

	public PuzzleManager(int pWidth, int pHeight, int pColumns, int pRows, BitmapTextureAtlas puzzleBitmapTextureAtlas, BaseGameActivity pActivity, String pAssetFilename) {

		init(pWidth, pHeight, pColumns, pRows, puzzleBitmapTextureAtlas, pActivity, pAssetFilename);
	}

	private void init(int pWidth, int pHeight, int pColumns, int pRows, BitmapTextureAtlas puzzleBitmapTextureAtlas, BaseGameActivity pActivity, String pAssetFilename) {
		this.currentTile = null;
		this.locked = false;
		this.puzzleComplete = false;
		this.columns = pColumns > GameConst.getInstance().getMaxPuzzleColumns() ? GameConst.getInstance().getMaxPuzzleColumns() : pColumns;
		this.rows = pRows > GameConst.getInstance().getMaxPuzzleRows() ? GameConst.getInstance().getMaxPuzzleRows() : pRows;
		
		this.assetFilename = pAssetFilename;
		this.puzzleTextureAtlas = puzzleBitmapTextureAtlas;
		
		this.width = pWidth;
		this.height = pHeight;
		this.activity = pActivity;	

		this.tileWidth = pWidth / columns;
		this.tileHeight = pHeight / rows;
		
		ITiledTextureRegion mRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(puzzleBitmapTextureAtlas, activity, pAssetFilename, 0, 0, columns, rows);
		puzzleBitmapTextureAtlas.load();
		
		gridTiles = new SlideTile[pColumns][pRows];
		int tempId = 1;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				if(j == rows - 1 && i == columns -1){
					gridTiles[i][j] = new EmptyTile(j*tileWidth, i*tileHeight, (tempId-1) , mRegion, activity.getVertexBufferObjectManager(),0, this);
					break;
				}
				
				gridTiles[i][j] = new SlideTile(j*tileWidth, i*tileHeight, (tempId-1) , mRegion, activity.getVertexBufferObjectManager(),tempId++, this);
				
			}
		}
	}
	
	
	SlideTile currentTile;
	public boolean locked;
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(this.currentTile == null){
			for(int i = 0; i < gridTiles[0].length; i++){
				for(int j = 0; j < gridTiles.length; j++){
					if(gridTiles[i][j].isPressed){
						currentTile = gridTiles[i][j];
						locked = true;
						
					}
				}
			}
		}
		else{
			if(!currentTile.isPressed){
				currentTile = null;
				locked = false;
			}
		}
	}
	
	public void requestMove(SlideTile tile, SlideTile.Direction direction)
	{
		int iIndex = 0, jIndex = 0;
		
		for(int i = 0 ; i < gridTiles[0].length; i++){
			for(int j = 0; j < gridTiles.length; j++){
				if(tile.id == gridTiles[j][i].id){
					iIndex = i; jIndex = j;
					break;
				}
			}
		}
		
		SlideTile tempTile;
		if(direction == SlideTile.Direction.RIGHT && gridTiles[jIndex][iIndex + 1] != null && gridTiles[jIndex ][iIndex + 1].id == 0 ){
			
			tempTile = gridTiles[jIndex][iIndex + 1];
			gridTiles[jIndex][iIndex + 1] = tile;
			gridTiles[jIndex][iIndex] = tempTile;
		}
		else if(direction == SlideTile.Direction.LEFT && gridTiles[jIndex-1][iIndex] != null && gridTiles[jIndex-1][iIndex].id == 0 ){
			tempTile = gridTiles[jIndex-1][iIndex];
			gridTiles[jIndex-1][iIndex] = tile;
			gridTiles[jIndex][iIndex] = tempTile;
		}
		else if(direction == SlideTile.Direction.UP && gridTiles[jIndex][iIndex + 1] != null && gridTiles[jIndex][iIndex + 1].id == 0 ){
			tempTile = gridTiles[jIndex][iIndex+1];
			gridTiles[jIndex][iIndex+1] = tile;
			gridTiles[jIndex][iIndex] = tempTile;
		}
		else if(direction == SlideTile.Direction.DOWN && gridTiles[jIndex][iIndex - 1] != null && gridTiles[jIndex][iIndex-1].id == 0 ){
			tempTile = gridTiles[jIndex][iIndex-1];
			gridTiles[jIndex][iIndex-1] = tile;
			gridTiles[jIndex][iIndex] = tempTile;
		}
		else {
			
			return;
		}
		float tempX = tempTile.getX(), tempY = tempTile.getY();
		tempTile.setX(tile.getX());
		tempTile.setY(tile.getY());
		
		tile.setX(tempX);
		tile.setY(tempY);
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public boolean getPuzzleCompleted() {
		return this.puzzleComplete;
	}
	public SlideTile[][] getTiles() {
		return this.gridTiles;
	}
	

}
