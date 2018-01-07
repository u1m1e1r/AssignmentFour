package com.example.hp.assignmentfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusClass event) {
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("omar");
    }

    public void MyStartService(View view){
        editText = (EditText) findViewById(R.id.editText);
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("value",editText.getText().toString());
        startService(intent);
    }

    void StopService(View view){
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

}
