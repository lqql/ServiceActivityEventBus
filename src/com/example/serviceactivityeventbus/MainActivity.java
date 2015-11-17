package com.example.serviceactivityeventbus;

import java.util.Date;

import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button startbutton;
	private Button stopbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        startbutton=(Button)findViewById(R.id.button1);
        stopbutton=(Button)findViewById(R.id.button2);
        startbutton.setOnClickListener(this);
        stopbutton.setOnClickListener(this);
        
    }

	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case(R.id.button1):
			MyIntentService.setServiceAlarm(getApplication().getApplicationContext(),true);
		    break;
		case(R.id.button2):
			MyIntentService.setServiceAlarm(getApplication().getApplicationContext(),false);
		    break;
		default:
			break;
		}
	}
	@SuppressLint("ShowToast")
	public void onEventMainThread(Date date){
		String msg=date.toString();
		Log.d("MyIntentService","executed at"+new Date().toString());
		Toast.makeText(this, "本次更新时间："+msg, Toast.LENGTH_LONG).show();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
	


    
}
