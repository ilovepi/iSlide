package edu.csun.comp380.group2.islide.util;

public class GameConst 
{
	private static volatile GameConst INSTANCE = new GameConst();
	
	private final int PUZZLE_HEIGHT = 480;
	private final int PUZZLE_WIDTH = 480;
	
	private final int DEFAULT_PUZZLE_COLUMNS = 3;
	private final int DEFAULT_PUZZLE_ROWS = 3;
	
	private final int MAX_PUZZLE_COLUMNS = 6;
	private final int MAX_PUZZLE_ROWS = 6;
	
	private final int DEFAULT_CAMERA_WIDTH = 480;
	private final int DEFAULT_CAMERA_HEIGHT = 800;
	
	private final String DEFAULT_IMAGE_PATH = "gfx/testimage.png";
	
	private GameConst()
	{
		
		
	}
	public static GameConst getInstance(){
		if(INSTANCE == null)
		{
			synchronized(GameConst.class){
				if(INSTANCE == null){
					INSTANCE = new GameConst();
				}
			}
		}
		return INSTANCE;
		
	}
	public int getPuzzleHeight()
	{
		return PUZZLE_HEIGHT;
	}
	public int getPuzzleWidth()
	{
		return PUZZLE_WIDTH;
	}
	public int getDefaultPuzzleRows()
	{
		return DEFAULT_PUZZLE_ROWS;
	}
	public int getDefaultPuzzleColumns()
	{
		return DEFAULT_PUZZLE_COLUMNS;
	}
	public int getMaxPuzzleRows()
	{
		return MAX_PUZZLE_ROWS;
	}
	public int getMaxPuzzleColumns()
	{
		return MAX_PUZZLE_COLUMNS;
	}
	public int getDefaultCameraWidth()
	{
		return DEFAULT_CAMERA_WIDTH;
	}
	public int getDefaultCameraHeight()
	{
		return DEFAULT_CAMERA_HEIGHT;
	}
	public String getDefaultImagePath()
	{
		return DEFAULT_IMAGE_PATH;
	}
	

}
