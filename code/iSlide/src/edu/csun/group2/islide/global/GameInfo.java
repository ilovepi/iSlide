package edu.csun.group2.islide.global;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameInfo {

	public byte[] imageData;
	public Texture gameTexture;
	public boolean touching;
	public Rectangle touchRectangle;
	private static GameInfo INSTANCE = new GameInfo();
	
	public static GameInfo getInstance()
	{
		return INSTANCE;
	}
}
