package geddy.snake.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import geddy.snake.framework.Audio;
import geddy.snake.framework.Music;
import geddy.snake.framework.Sound;

/**
 * Created by geddyS on 18-2-2015.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
    }

    public Music newMusic(String filename){
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetDescriptor);
        }catch (IOException e) {
            throw new RuntimeException("Couldn't load music '"+filename+"'");
        }
    }

    public Sound newSound(String filename){
        try{
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool,soundId);
        }catch(IOException e){
            throw new RuntimeException("couldn't load sound '"+filename+"'");
        }
    }

}
