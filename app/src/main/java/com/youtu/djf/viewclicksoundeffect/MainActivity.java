package com.youtu.djf.viewclicksoundeffect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn21;
    private Button btn22;
    private Button btn23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn11 = (Button) findViewById(R.id.btn11);
        btn12 = (Button) findViewById(R.id.btn12);
        btn13 = (Button) findViewById(R.id.btn13);
        btn21 = (Button) findViewById(R.id.btn21);
        btn22 = (Button) findViewById(R.id.btn22);
        btn23 = (Button) findViewById(R.id.btn23);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ToastUtil(this, "原生View soundEffectsEnabled=" + true);
                break;
            case R.id.btn2:
                ToastUtil(this, "原生View soundEffectsEnabled=" + false);
                break;
            case R.id.btn3:
                ToastUtil(this, "原生View soundEffectsEnabled 默认");
                break;
            case R.id.btn11:
                ToastUtil(this, "原生View 点击事件监听中添加SoundPool，同时soundEffectsEnabled=" + true);
                SoundUtils.playSound(this, R.raw.click);
                break;
            case R.id.btn12:
                ToastUtil(this, "原生View 点击事件监听中添加SoundPool，同时soundEffectsEnabled=" + false);
                SoundUtils.playSound(this, R.raw.click);
                break;
            case R.id.btn13:
                ToastUtil(this, "原生View 点击事件监听中添加SoundPool，同时soundEffectsEnabled 默认");
                SoundUtils.playSound(this, R.raw.click);
                break;
            case R.id.btn21:
                ToastUtil(this, "自定义View添加音效配置属性 soundEffectsEnabled=" + true);
                break;
            case R.id.btn22:
                ToastUtil(this, "自定义View添加音效配置属性 soundEffectsEnabled=" + false);
                break;
            case R.id.btn23:
                ToastUtil(this, "自定义View添加音效配置属性 soundEffectsEnabled 默认");
                break;
        }
    }

    private static Toast mToast;

    private static void ToastUtil(Context m, String s) {
        if (mToast == null) {
            mToast = Toast.makeText(m, "", Toast.LENGTH_LONG);
        }
        mToast.setText(s);
        mToast.show();
    }
}
