package edu.csun.group2.islide.global;

public class GameInfo {

	public byte[] imageData;
	
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
