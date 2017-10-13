package com.youtu.djf.viewclicksoundeffect;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.Hashtable;

/**
 * Created by djf on 17/10/12.
 */

public class SoundUtils {
    private static final String TAG = "SoundUtils";
    private static Hashtable<Integer, Integer> soundIds = new Hashtable<>();
    private static SoundPool soundPool;
    static {
        if (Build.VERSION.SDK_INT > 21) {
            soundPool = new SoundPool.Builder().build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
    }
    private static int re;
    public static int playSound(Context context, final int id) {
        re = 0;
        if (soundIds.keySet().contains(id)) {
            re = soundPool.play(soundIds.get(id), 1, 1, 0, 0, 1);
        } else {
            final int soundId = soundPool.load(context, id, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundIds.put(id, soundId);
                    re = soundPool.play(soundId, 1, 1, 0, 0, 1);
                }
            });
        }
        return re;
    }
}
