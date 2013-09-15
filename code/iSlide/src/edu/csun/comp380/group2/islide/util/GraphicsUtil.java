/*
 * Graphics Utility Methods Class
 * 
 * 
 */
package edu.csun.comp380.group2.islide.util;

import java.io.ByteArrayOutputStream;

import edu.csun.comp380.group2.islide.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GraphicsUtil {

	/**
	 * 
	 * @param sender
	 *            The Activity that called the method
	 * @param id
	 *            The R-id of the image
	 * @param format
	 *            The type of image
	 * @return byteArray representing the image
	 */
	public static byte[] getByteArrayFromImage(Activity sender, int id,
			Bitmap.CompressFormat format) {
		
		try {
			Bitmap bmp = BitmapFactory
					.decodeResource(sender.getResources(), id);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(format, 100, stream);
			return stream.toByteArray();

		} catch (Exception e) {
			//Needs an error catching mechanism
			return null;
		}

	}
	public static Bitmap getDefaultBitmap(Activity sender)
	{
		Bitmap bmp;
		
		try{
			bmp = BitmapFactory.decodeResource(sender.getResources(), R.drawable.default_image);
		}catch(Exception e)
		{
			bmp = null;
			System.err.print("Bitmap Load Error, Resource ID: " + R.drawable.default_image);
		}
		return bmp;
	}
}
