package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.nio.charset.Charset;

import static com.example.myapplication.BluetoothConnectionService.write;

public class UserOptions extends AppCompatActivity {

    private Button alarm;
    private Button play;
    private ImageButton returnButton;


    public static final String TAG = "UserOptions";

    public void initialize() {
        play = findViewById(R.id.play);
        alarm = findViewById(R.id.alarm);
        returnButton = findViewById(R.id.return_button);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserOptions.this, StartConnection.class);
                startActivity(intent);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String playOperateManually = "z";
                    byte[] bytes = playOperateManually.getBytes(Charset.defaultCharset());
                    write(bytes);
                    Log.d(TAG, "char z sent to arduino mega");
                } catch (Exception e) {
                    Toast.makeText(UserOptions.this, "Unable to connect device", Toast.LENGTH_SHORT).show();
                }


                Intent intent = new Intent(UserOptions.this, OperateManually.class);
                startActivity(intent);
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserOptions.this, SetAlarm.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        initialize();
    }
}