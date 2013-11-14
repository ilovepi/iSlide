package edu.csun.group2.islide.global;

import java.awt.Rectangle;


public class GameInfo {

	public byte[] imageData;
	public Rectangle touchPoint;
	public int touchedX, touchedY;
	public boolean screenTouched;
	
	private static GameInfo INSTANCE = null;
	public static GameInfo getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new GameInfo();
		}
		return INSTANCE;
		
	}
}
