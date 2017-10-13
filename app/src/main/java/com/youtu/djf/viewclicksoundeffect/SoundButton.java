package com.youtu.djf.viewclicksoundeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by djf on 2017/10/13.
 */

public class SoundButton extends android.support.v7.widget.AppCompatButton {
    private Context mContext;
    private int soundResourceId;

    public SoundButton(Context context) {
        this(context, null);
        ;
        this.mContext = context;
    }

    public SoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
        this.mContext = context;
        init(attrs);
    }

    public SoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(TintContextWrapper.wrap(context), attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray types = mContext.obtainStyledAttributes(attrs, R.styleable.SoundButton);
        if (types != null) {
            soundResourceId = types.getResourceId(R.styleable.SoundButton_sound_id, -1);
        }
    }

    /**
     * 若配置了自定义音效资源（与属性soundEffectsEnabled无关），则优先播放自定音效，
     * 若没有配置则默认音效（默认音效与属性soundEffectsEnabled）
     *
     */
    @Override
    public void playSoundEffect(int soundConstant) {
        if (soundResourceId != -1) {
            SoundUtils.playSound(mContext, soundResourceId);
        } else {
            super.playSoundEffect(soundConstant);
        }
    }
}
