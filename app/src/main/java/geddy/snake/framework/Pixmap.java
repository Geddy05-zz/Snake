package geddy.snake.framework;

import geddy.snake.framework.Graphics.PixmapFormat;

/**
 * Created by geddyS on 18-2-2015.
 */
public interface Pixmap {
    public int getWidth();
    public int getHeight();
    public PixmapFormat getFormat();
    public void dispose();
}
