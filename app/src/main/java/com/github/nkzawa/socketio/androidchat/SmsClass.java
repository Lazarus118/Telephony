package com.github.nkzawa.socketio.androidchat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lazarus on 10/20/2016.
 */

public class SmsClass extends ActionBarActivity {
    Button sms_send_btn;
    EditText sms_phone_no, sms_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        // --------------------------------- //
        sms_send_btn = (Button)findViewById(R.id.send_sms_to);
        sms_phone_no = (EditText)findViewById(R.id.number_to_sms);
        sms_message = (EditText)findViewById(R.id.sms_body);

        sms_send_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage()
    {
        String phoneNo = sms_phone_no.getText().toString();
        String message = sms_message.getText().toString();

        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
