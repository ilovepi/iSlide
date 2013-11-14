package edu.csun.group2.islide.engine;

import java.awt.Rectangle;

import com.badlogic.gdx.InputProcessor;

import edu.csun.group2.islide.global.GameInfo;

public class GameInput implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(GameInfo.getInstance().touchPoint == null)
			GameInfo.getInstance().touchPoint = new Rectangle(screenX, screenY, 1,1);
		GameInfo.getInstance().touchPoint.x = screenX;
		GameInfo.getInstance().touchPoint.y = screenY;
		GameInfo.getInstance().touchedX = screenX;
		GameInfo.getInstance().touchedY = screenY;
		GameInfo.getInstance().screenTouched = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		GameInfo.getInstance().touchPoint = null;
		GameInfo.getInstance().screenTouched = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(GameInfo.getInstance().touchPoint == null)
			GameInfo.getInstance().touchPoint = new Rectangle(screenX, screenY, 1,1);
		GameInfo.getInstance().touchPoint.x = screenX;
		GameInfo.getInstance().touchPoint.y = screenY;
		GameInfo.getInstance().screenTouched = true;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
