package edu.csun.comp380.group2.islide.entity;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SlideTile extends TiledSprite {
	
	public enum Direction{
		UP,DOWN,LEFT,RIGHT
	}

	
	protected int id;
	protected PuzzleManager manager;
	protected boolean isPressed;
	
	public SlideTile(int pXPosition, int pYPosition, int pTileIndex, ITiledTextureRegion pRegion, VertexBufferObjectManager pVbom, int pId, PuzzleManager pManager) {
		
		super(pXPosition, pYPosition, pRegion, pVbom);
		
		this.setCurrentTileIndex(pTileIndex);
		this.id = pId;
		this.manager = pManager;
		this.isPressed = false;
	}

	public float pressOriginX, pressOriginY;
	
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		

		switch (pSceneTouchEvent.getAction()) {
			case TouchEvent.ACTION_MOVE: {
			
				if(pSceneTouchEvent.getX() > pressOriginX ){
					manager.requestMove(this, Direction.RIGHT);
				}
				else if(pSceneTouchEvent.getX() < pressOriginX ){
					manager.requestMove(this, Direction.LEFT);				
				}
				else if(pSceneTouchEvent.getY() > pressOriginY){
					manager.requestMove(this, Direction.DOWN);
				}
				else if(pSceneTouchEvent.getY() < pressOriginY){
					manager.requestMove(this, Direction.UP);
				}
				else{
					
				}
				
				return true;
			}
			case TouchEvent.ACTION_DOWN: {
				if(!manager.locked){
					isPressed = true;
					pressOriginX = pSceneTouchEvent.getX();
					pressOriginY = pSceneTouchEvent.getY();
				}
				return true;
			}
			case TouchEvent.ACTION_UP: {
	
				return true;
			}
			case TouchEvent.ACTION_CANCEL: {
				isPressed = false;
				return true;
			}
			default: {
				isPressed = false;
				return false;
			}
		}

	}

	public int getTileID() {
		return this.id;
	}
	public boolean isPressed()
	{
		return this.isPressed;
	}

}
