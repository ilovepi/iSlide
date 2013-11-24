package edu.csun.group2.islide.global;

import com.badlogic.gdx.graphics.Texture;

public class GameInfo {

	public byte[] imageData;
	public Texture gameTexture;
	
	private static GameInfo INSTANCE = null;
	public GameInfo getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new GameInfo();
		}
		return INSTANCE;
	}
}
