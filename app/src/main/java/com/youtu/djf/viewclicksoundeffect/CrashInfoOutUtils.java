package com.youtu.djf.viewclicksoundeffect;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by djf on 2017/9/22.
 */

public class CrashInfoOutUtils {
    private static final String TAG = "CrashInfoOutUtils";

    public static void dumpExceptionToSDCard(Context mContext,String path, Throwable ex)   {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (TextUtils.isEmpty(path)) {
            Log.w(TAG, "disk unmounted,skip dump exception");
            return;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(current));
        //以当前时间创建log文件
        File file = new File(path + "/crash" + time + ".txt");
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            //导出发生异常的时间
            pw.println("\n" + time );
            //导出手机信息
            dumpPhoneInfo(mContext,pw);
            pw.println();
            //导出异常的调用栈信息
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    private static void dumpPhoneInfo(Context mContext,PrintWriter pw) throws PackageManager.NameNotFoundException {
        //应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager
                .GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        //android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

}
