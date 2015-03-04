package geddy.snake;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import geddy.snake.framework.Screen;
import geddy.snake.framework.impl.AndroidGame;


public class MainActivity extends AndroidGame {
    public Screen getStartScreen(){
        return new LoadingScreen(this);
    }
}
