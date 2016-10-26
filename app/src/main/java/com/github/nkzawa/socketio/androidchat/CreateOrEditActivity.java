package com.github.nkzawa.socketio.androidchat;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class CreateOrEditActivity extends ActionBarActivity implements View.OnClickListener {


    private DBHelper dbHelper;
    EditText name, number, email, website, qualifications, details;
    ImageButton saveButton, editButton, deleteButton;
    int personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);
        setContentView(R.layout.create_contact);

        name = (EditText)findViewById(R.id.edit_name);
        number = (EditText)findViewById(R.id.edit_number);
        email = (EditText)findViewById(R.id.edit_email);
        website = (EditText)findViewById(R.id.edit_website);
        qualifications = (EditText)findViewById(R.id.edit_qualifications);
        details = (EditText)findViewById(R.id.edit_details);
        saveButton = (ImageButton)findViewById(R.id.save_btn);
        saveButton.setOnClickListener(this);
        editButton = (ImageButton)findViewById(R.id.edit_btn);
        editButton.setOnClickListener(this);
        deleteButton = (ImageButton)findViewById(R.id.delete_btn);
        deleteButton.setOnClickListener(this);

        dbHelper = new DBHelper(this);

        if (personID > 0) {

            Cursor rs = dbHelper.getRecord(personID);
            rs.moveToFirst();
            String personName = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_NAME));
            int personNumber = rs.getInt(rs.getColumnIndex(DBHelper.COLUMN_NUMBER));
            String personEmail = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_NUMBER));
            String personWebsite = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_WEBSITE));
            String personQualification = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_QUALIFICATIONS));
            String personDetails = rs.getString(rs.getColumnIndex(DBHelper.COLUMN_DETAILS));
            if (!rs.isClosed()) {
                rs.close();
            }

            name.setText(personName + "");
            number.setText(personNumber + "");
            email.setText(personEmail + "");
            website.setText(personWebsite + "");
            qualifications.setText(personQualification + "");
            details.setText(personDetails + "");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_btn:
                persistPerson();
//                return;
//            case R.id.edit_btn:
//                saveButton.setVisibility(View.VISIBLE);
//                buttonLayout.setVisibility(View.GONE);
//                nameEditText.setEnabled(true);
//                nameEditText.setFocusableInTouchMode(true);
//                nameEditText.setClickable(true);
//
//                genderEditText.setEnabled(true);
//                genderEditText.setFocusableInTouchMode(true);
//                genderEditText.setClickable(true);
//
//                ageEditText.setEnabled(true);
//                ageEditText.setFocusableInTouchMode(true);
//                ageEditText.setClickable(true);
//                return;
            case R.id.delete_btn:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deletePerson)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.delete(personID);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Delete Person?");
                d.show();
        }
    }

    public void persistPerson() {
        if(personID > 0)
        {
            if (dbHelper.update(personID, name.getText().toString(), Integer.parseInt(number.getText().toString()), email.getText().toString(), website.getText().toString(), qualifications.getText().toString(), details.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Person Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Person Update Failed", Toast.LENGTH_SHORT).show();
            }
        }

        else
        {
            if(dbHelper.insert(name.getText().toString(), Integer.parseInt(number.getText().toString()), email.getText().toString(), website.getText().toString(), qualifications.getText().toString(), details.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Person Inserted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Could not Insert person", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}