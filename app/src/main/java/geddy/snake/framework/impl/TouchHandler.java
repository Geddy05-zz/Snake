package geddy.snake.framework.impl;

import android.view.View;

import java.util.List;

import geddy.snake.framework.Input;

/**
 * Created by geddyS on 25-2-2015.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvents();
}
