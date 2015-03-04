package geddy.snake.framework;

/**
 * Created by geddyS on 18-2-2015.
 */
public interface Graphics {
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName,PixmapFormat format);
    public void drawPixel(int x, int y, int color);
    public void drawLine(int x, int y, int x2, int y2, int color);
    public void drawRect(int x, int y, int width,int height, int color);
    public void drawPixmap (Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
    public void drawPixmap (Pixmap pixamp, int x, int Y);
    public int getWidth();
    public int getHeight();
}
