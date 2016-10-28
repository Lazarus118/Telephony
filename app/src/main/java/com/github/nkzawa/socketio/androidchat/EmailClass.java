package com.github.nkzawa.socketio.androidchat;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EmailClass extends ActionBarActivity {

    Button email_send_btn;
    EditText email_to, email_message, email_subject, email_cc, email_bcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        // ---------------------------------- //
        email_send_btn = (Button)findViewById(R.id.email_to);
        email_to = (EditText)findViewById(R.id.email_address);
        email_to.setText("austin.lazarus@gmail.com");
        email_cc = (EditText)findViewById(R.id.email_cc);
        email_bcc = (EditText)findViewById(R.id.email_bcc);
        email_message = (EditText)findViewById(R.id.email_body);
        email_subject = (EditText)findViewById(R.id.email_subject);
        // ---------------------------------- //
        email_send_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                String to = email_to.getText().toString();
                String subject = email_subject.getText().toString();
                String cc = email_cc.getText().toString();
                String bcc = email_bcc.getText().toString();
                String message = email_message.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_CC, new String[]{ cc});
                email.putExtra(Intent.EXTRA_BCC, new String[]{ bcc});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                // need this to prompts email client only
                // --------------------------------------
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }
}
