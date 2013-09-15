package edu.csun.comp380.group2.islide.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class SlideTile extends SurfaceView {

	private Bitmap bmpPart;
	
	private int width, height;
	float centreX, centreY;
	
	/**
	 * 
	 * @param context
	 *            The Parent Activity/Context Object
	 * @param xPos
	 *            Location on the Screen (X)
	 * @param yPos
	 *            Location on the Screen (Y)
	 * @param width
	 *            On Screen Width
	 * @param height
	 *            On Screen Height
	 * @param bmpParent
	 *            Parent Bitmap Image
	 * @param bmpX
	 *            Bitmap X Section
	 * @param bmpY
	 *            Bitmap Y Section
	 * @param bmpWidth
	 *            Bitmap Section width
	 * @param bmpHeight
	 *            Bitmap Section Height
	 */
	public SlideTile(Context context, float xPos, float yPos, Bitmap bmpParent,
			int bmpX, int bmpY, int bmpWidth, int bmpHeight) {
		super(context);

		bmpPart = Bitmap.createBitmap(bmpParent, bmpX, bmpY, bmpWidth,
				bmpHeight);
		this.setX(xPos);
		this.setY(yPos); 
		//sets width and height I suppose
		this.setLayoutParams(new LayoutParams(bmpHeight, bmpHeight));
		
		centreX = (float)(xPos + bmpWidth) /2;
		centreY = (float)(yPos + bmpWidth) /2;
	}

	@Override
	protected void onDraw(Canvas c)
	{
		super.onDraw(c);
		c.drawBitmap(bmpPart,this.getX(), this.getY(), null);
		
		
	}

	@Override
	public boolean onDragEvent(DragEvent e)
	{
		
		return false;
		
	}
	
}
