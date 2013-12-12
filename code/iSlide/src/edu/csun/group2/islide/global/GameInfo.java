package edu.csun.group2.islide.global;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class GameInfo {
	
	public static final String HIGH_SCORE_PATH = "iSlide/h_scores.txt";
	
	public boolean touching;
	public boolean justTouched;
	public Rectangle touchRectangle;
	public OrthographicCamera gameCamera;
	public boolean showNumbers = true;
	private static GameInfo INSTANCE = new GameInfo();
	
	public static GameInfo getInstance()
	{
		return INSTANCE;
	}
}
