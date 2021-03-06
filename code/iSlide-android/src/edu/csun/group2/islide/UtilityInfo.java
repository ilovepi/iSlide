package edu.csun.group2.islide;

/*
 * Utility Class designed to communicate with the information within the LIBGDX game
 */
public class UtilityInfo {
	
	public int puzzleSize;
	public int score;
	public boolean sound;
	public String path;
	public boolean showNumbers;
	//Factory Style, takes existing data and creates and object for it
	public UtilityInfo(int puzzleSize, int score, boolean sound, String path, boolean showNumbers)
	{
		this.puzzleSize = puzzleSize;
		this.score = score;
		this.sound = sound;
		this.path = path;
		this.showNumbers = showNumbers;
	}
}
