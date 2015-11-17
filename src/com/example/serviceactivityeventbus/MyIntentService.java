package com.example.serviceactivityeventbus;

import java.util.Date;

import de.greenrobot.event.EventBus;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyIntentService extends IntentService {
	public static final String TAG="MyIntentService";

	public MyIntentService() {
		super(TAG);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		String msg=new Date().toString();
		Intent notificationIntent=new Intent(this,MainActivity.class);
		PendingIntent pendingintent=PendingIntent.getActivity(this, 0, notificationIntent, 0);
		Notification notification=new NotificationCompat.Builder(this).setTicker("Notification comes")
		.setSmallIcon(R.drawable.ic_launcher).setContentTitle("refresh time:").setContentText(msg)
		.setContentIntent(pendingintent).setAutoCancel(true).build();
		NotificationManager notificationmanager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		notificationmanager.notify(0 , notification);
		//startForeground(1,notification);
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		EventBus.getDefault().post(new Date());
		

	}
	public static void setServiceAlarm(Context context,boolean ison){
		Intent intent=new Intent(context,MyIntentService.class);
		PendingIntent pi=PendingIntent.getService(context,0,intent,0);
		AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		if(ison){
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5*1000, pi);
		}else{
			alarmManager.cancel(pi);
			pi.cancel();
		}
		
	}

}
