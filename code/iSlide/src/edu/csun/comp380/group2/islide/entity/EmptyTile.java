package edu.csun.comp380.group2.islide.entity;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class EmptyTile extends SlideTile {

	public EmptyTile(int pXPosition, int pYPosition, int pTileIndex,
			ITiledTextureRegion pRegion, VertexBufferObjectManager pVbom, int tileID, PuzzleManager pManager) {
		super(pXPosition, pYPosition, pTileIndex, pRegion, pVbom, tileID, pManager);
		this.setAlpha(0f);
		
	}
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
            final float pTouchAreaLocalX,
            final float pTouchAreaLocalY)
	{
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth()/2, pSceneTouchEvent.getY() - this.getHeight()/2);
	
	    switch(pSceneTouchEvent.getAction()){
	        case TouchEvent.ACTION_MOVE:{
	            
	            return true; 
	        }
	        case TouchEvent.ACTION_DOWN:{
	           
	            return true; 
	        }
	        case TouchEvent.ACTION_UP:{
	            
	            return true; 
	        }
	        case TouchEvent.ACTION_CANCEL:{
	            
	            return true; 
	        }
	        default:{
	        
	            return false;
	        }
	    }
	}
}
		

