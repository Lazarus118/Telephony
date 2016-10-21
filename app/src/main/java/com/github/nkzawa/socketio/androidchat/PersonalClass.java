package com.github.nkzawa.socketio.androidchat;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class PersonalClass extends ActionBarActivity {
    TextView name;
    ImageButton text_chat, video_chat, search, sms_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_details);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Austin");
        name = (TextView)findViewById(R.id.name);
        name.setText(value);
        // ------------------------------------ //
        text_chat = (ImageButton)findViewById(R.id.text_chat);
        text_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chat = new Intent(PersonalClass.this, LoginActivity.class);
                PersonalClass.this.startActivity(chat);
            }
        });
        // ------------------------------------ //
        video_chat = (ImageButton)findViewById(R.id.video_chat);
        video_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent video = new Intent(PersonalClass.this, VideoClass.class);
                PersonalClass.this.startActivity(video);
            }
        });
        // ------------------------------------ //
        sms_btn = (ImageButton)findViewById(R.id.send_sms_btn);
        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(PersonalClass.this, SmsClass.class);
                PersonalClass.this.startActivity(search);
            }
        });
        // ------------------------------------ //
        search = (ImageButton)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(PersonalClass.this, SearchClass.class);
                PersonalClass.this.startActivity(search);
            }
        });
    }
}
