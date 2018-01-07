package com.example.hp.assignmentfour;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    public MyService() {
    }

    TimerTask myTask = new TimerTask()
    {
        public void run()
        {
            EventBus.getDefault().post(new EventBusClass());
            stopSelf();
        }
    };

    Timer myTimer = new Timer();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"created",Toast.LENGTH_LONG).show();
     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"stoped",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle a = intent.getExtras();
        String b = a.getString("value");
        long INTERVAL = Long.parseLong(b);
        myTimer.schedule(myTask, INTERVAL);
        return super.onStartCommand(intent, flags, startId);
    }

}
