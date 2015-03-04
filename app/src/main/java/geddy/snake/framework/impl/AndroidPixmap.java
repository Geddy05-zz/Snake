package geddy.snake.framework.impl;

import android.graphics.Bitmap;

import geddy.snake.framework.Graphics.PixmapFormat;
import geddy.snake.framework.Pixmap;

/**
 * Created by geddyS on 25-2-2015.
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format){
        this.bitmap=bitmap;
        this.format =format;
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

    public PixmapFormat getFormat(){
        return format;
    }

    public void dispose(){
        bitmap.recycle();
    }
}
