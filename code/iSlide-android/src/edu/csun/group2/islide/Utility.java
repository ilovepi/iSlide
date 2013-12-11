package edu.csun.group2.islide;

public class Utility {
	
	public static boolean picTaken = false;
	public static int size = 4;
	public static boolean sound = false;
	public static String fileLocation = "";
	public static UtilityInfo CreateUtilityInfo()
	{
		UtilityInfo ui = new UtilityInfo(size,0,sound,fileLocation);
		return ui;
	}
}
