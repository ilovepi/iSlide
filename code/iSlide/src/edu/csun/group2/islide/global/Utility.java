package edu.csun.group2.islide.global;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class Utility {
	
	public static Texture convertByteArrayToTexture(byte[] bytes)
	{	
		Pixmap map = new Pixmap(bytes, 0, bytes.length);
		Texture T = new Texture(map);
		return T;
	}
}
