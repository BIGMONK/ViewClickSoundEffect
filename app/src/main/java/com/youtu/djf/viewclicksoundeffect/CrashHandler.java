package com.youtu.djf.viewclicksoundeffect;

import android.content.Context;


/**
 * Created by djf on 2017/9/22.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = FileUtils.GET_LOCAL_BASEPATH() + "/";
    private static final String FILE_NAME = "crash";

    //log文件的后缀名
    private static final String FILE_NAME_SUFFIX = ".txt";

    private static CrashHandler sInstance = new CrashHandler();

    //系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
//    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    //构造方法私有，防止外部构造多个实例，即采用单例模式
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    //这里主要完成初始化工作
    public void init(Context context) {
        //获取系统默认的异常处理器
//        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//                Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("reboot_tips", "程序意外终止并重启！");
//                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
//                        0, intent, PendingIntent.FLAG_ONE_SHOT);
//                AlarmManager alarmManager;
//                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                        1000, pendingIntent);

        //导出异常信息到SD卡中
        CrashInfoOutUtils.dumpExceptionToSDCard(mContext, FileUtils.GET_LOCAL_BASEPATH(), ex);
        //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
        uploadExceptionToServer();
        //打印出当前调用栈信息
        ex.printStackTrace();
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
//        if (mDefaultCrashHandler != null) {
//            mDefaultCrashHandler.uncaughtException(thread, ex);
//        } else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
        if (onListener!=null){
            onListener.onCrashListener();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        System.gc();
    }


    private CrashListener onListener;

    public void setOnListener(CrashListener onListener) {
        this.onListener = onListener;
    }

    public interface  CrashListener{
        public void onCrashListener();
    }

    private void uploadExceptionToServer() {
        //TODO Upload Exception Message To Your Web Server
    }

}