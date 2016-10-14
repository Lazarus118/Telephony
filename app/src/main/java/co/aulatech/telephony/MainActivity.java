package co.aulatech.telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    Button sendSMS, sendEMAIL, goBack, sendCHAT;
    ImageButton right, left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // --------------------------------- //
        sendSMS = (Button)findViewById(R.id.sendSMS);
        sendEMAIL = (Button)findViewById(R.id.sendEmail);
        goBack = (Button)findViewById(R.id.goBack);
        right = (ImageButton)findViewById(R.id.right);
        left = (ImageButton)findViewById(R.id.left);
        sendCHAT = (Button)findViewById(R.id.chatSubmit);
        // --------------------------------- //
    }
}
