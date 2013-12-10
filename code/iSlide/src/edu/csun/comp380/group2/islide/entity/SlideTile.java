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

public class SlideTile extends TiledSprite 
{
	private int tileIndex;
	public SlideTile(int pXPosition, int pYPosition, int pTileIndex, ITiledTextureRegion pRegion, VertexBufferObjectManager pVbom)
	{
		super(pXPosition,pYPosition,pRegion,pVbom);
		
		this.setCurrentTileIndex(pTileIndex);
	}
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
            final float pTouchAreaLocalX,
            final float pTouchAreaLocalY)
	{
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth()/2, pSceneTouchEvent.getY() - this.getHeight()/2);
		
		return true;
		
	}

	
}
