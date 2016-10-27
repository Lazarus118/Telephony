package com.github.nkzawa.socketio.androidchat;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;


public class PersonalClass extends ActionBarActivity {
    TextView name, mobile_number, email, website, qualification, details;
    ImageButton text_chat, video_chat, search, sms_btn, email_btn;
    DBHelper dbHelper;
    public static String DBname, DBnum, DBemail, DBwebsite, DBqualification, DBdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_details);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Austin");
        name = (TextView)findViewById(R.id.name);
        name.setText(value);
        // ------------------------------------ //
        dbHelper = new DBHelper(this);
        final Cursor cursor = dbHelper.getAllRecords();
        final String finalName = DBname;
        final String finalNum = DBnum;
        final String finalEmail = DBemail;
        final String finalWebsite = DBwebsite;
        final String finalQualification = DBqualification;
        final String finalDetails = DBdetails;
        while(cursor.moveToNext()) {
            DBname = cursor.getString(1);
            DBnum = cursor.getString(2);
            DBemail = cursor.getString(3);
            DBwebsite = cursor.getString(4);
            DBqualification = cursor.getString(5);
            DBdetails = cursor.getString(6);
        }
        mobile_number = (TextView)findViewById(R.id.mobile_number);
        email = (TextView)findViewById(R.id.email);
        website = (TextView)findViewById(R.id.website);
        qualification = (TextView)findViewById(R.id.qualification);
        details = (TextView)findViewById(R.id.details);
        if (Objects.equals(value, DBname)) {
            mobile_number.setText(DBnum);
            email.setText(DBemail);
            website.setText(DBwebsite);
            qualification.setText(DBqualification);
            details.setText(DBdetails);
        }
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
        email_btn = (ImageButton)findViewById(R.id.send_email);
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(PersonalClass.this, EmailClass.class);
                PersonalClass.this.startActivity(email);
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
