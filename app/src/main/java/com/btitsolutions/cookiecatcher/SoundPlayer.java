package com.btitsolutions.cookiecatcher;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by Bereket on 10/9/2017.
 */

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;

    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;

    Context _context;

    public SoundPlayer(Context context){
        _context = context;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        }
        else{
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }

        hitSound = soundPool.load(context, R.raw.eaten, 1);
        overSound = soundPool.load(context, R.raw.game_over, 1);
    }

    public void PlayHitSound(){
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void PlayGameOverSound(){
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
