package edu.csun.group2.islide.engine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;

import edu.csun.group2.islide.global.GameInfo;

public class InputHandler implements InputProcessor {

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
		GameInfo.getInstance().touching = true;
		if (GameInfo.getInstance().touchRectangle == null) {
			GameInfo.getInstance().touchRectangle = new Rectangle(screenX,
					screenY, 1, 1);
		}
		else{
			GameInfo.getInstance().touchRectangle.x = screenX;
			GameInfo.getInstance().touchRectangle.y = screenY;
		}
		GameInfo.getInstance().justTouched = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		GameInfo.getInstance().touching = false;
		GameInfo.getInstance().justTouched = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		GameInfo.getInstance().touching = true;
		if (GameInfo.getInstance().touchRectangle != null) {
			GameInfo.getInstance().touchRectangle.x = screenX;
			GameInfo.getInstance().touchRectangle.y = screenY;
		} else {
			GameInfo.getInstance().touchRectangle = new Rectangle(screenX,
					 screenY, 1, 1);
		}
		GameInfo.getInstance().justTouched = true;
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
