package edu.csun.comp380.group2.islide.engine;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import edu.csun.comp380.group2.islide.entity.PuzzleManager;
import edu.csun.comp380.group2.islide.entity.SlideTile;
import edu.csun.comp380.group2.islide.util.GameConst;

import android.R;

public class PlayGameActivity extends SimpleBaseGameActivity {
	
	private final int CAMERA_WIDTH = GameConst.getInstance().getDefaultCameraWidth();
	private final int CAMER_HEIGHT = GameConst.getInstance().getDefaultCameraHeight();
	
	private Scene mScene;
	private Camera mCamera;
	
	BitmapTextureAtlas mGameImage;
	ITiledTextureRegion mTile;
	PuzzleManager puzzle;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		mCamera = new Camera(0,0,CAMERA_WIDTH, CAMER_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMER_HEIGHT), mCamera);
		return engineOptions;
		
	}
	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mGameImage = new BitmapTextureAtlas(this.getTextureManager(),512,512);
		//mTile = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameImage, getAssets(), "testimage.png", 0, 0, 3, 3);
		//mGameImage.load();
		puzzle = new PuzzleManager(480,480,3,3, mGameImage, this, "testimage.png");
	}
	@Override
	protected Scene onCreateScene() {
		
		mScene = new Scene();
		mScene.registerUpdateHandler(new IUpdateHandler() {
		    @Override
		    public void reset() {}
		    public void onUpdate(float pSecondsElapsed) {
		        // TODO Auto-generated method stub
		     
		    }
		});
		
		
		mScene.setBackground(new Background(0,125,58));
		
		
		//TiledSprite tileSprite = new TiledSprite(0, 0, mTile, this.getVertexBufferObjectManager());
		//tileSprite.setCurrentTileIndex(1);
		//mScene.attachChild(tileSprite);
		for(SlideTile tile : puzzle.getTiles())
		{
			mScene.registerTouchArea(tile);
			mScene.attachChild(tile);
		}
		return mScene;
	}

}
