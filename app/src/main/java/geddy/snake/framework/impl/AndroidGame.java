package geddy.snake.framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import geddy.snake.LoadingScreen;
import geddy.snake.framework.Audio;
import geddy.snake.framework.FileIO;
import geddy.snake.framework.Game;
import geddy.snake.framework.Graphics;
import geddy.snake.framework.Input;
import geddy.snake.framework.Screen;

/**
 * Created by geddyS on 25-2-2015.
 */
public class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderview;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakelock;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 480:320;
        int frameBufferHeight = isLandscape ? 320:480;

        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX= (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float)frameBufferHeight/ getWindowManager().getDefaultDisplay().getHeight();

        renderview = new AndroidFastRenderView(this,frameBuffer);
        graphics=new AndroidGraphics(getAssets(),frameBuffer);
        fileIO = new AndroidFileIO(this);
        input= new AndroidInput(this,renderview,scaleX,scaleY);
        audio= new AndroidAudio(this);
        screen = getStartScreen();
        setContentView(renderview);
        PowerManager powermanager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakelock= powermanager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Geddy's game");
    }

    public void onResume(){
        super.onResume();
        wakelock.acquire();
        screen.resume();
        renderview.resume();
    }

    public void onPause(){
        super.onPause();
        wakelock.release();
        renderview.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    public Input getInput(){
        return input;
    }

    public FileIO getFileIO(){
        return fileIO;
    }

    public Graphics getGraphics(){
        return graphics;
    }

    public Audio getAudio(){
        return audio;
    }

    public void setScreen(Screen screen){
        if (screen ==null)
            throw new IllegalArgumentException("screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen=screen;
    }

    public Screen getCurrentScreen(){
        return screen;
    }

    public Screen getStartScreen(){
        return screen;
    }
}