package edu.csun.group2.islide.global;

import com.badlogic.gdx.math.Rectangle;



public class GameInfo {

	public Rectangle touchPoint = new Rectangle();
	public int touchedX = 0, touchedY= 0 ;
	public boolean screenTouched = false;
	
	private static GameInfo INSTANCE = new GameInfo();
	public static GameInfo getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new GameInfo();
		}
		return INSTANCE;
		
	}
}
