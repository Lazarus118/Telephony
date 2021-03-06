package com.github.nkzawa.socketio.androidchat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    DBHelper dbHelper;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    public static String name;
    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***********************************************
         *  DATABASE INIT
         ***********************************************/
        dbHelper = new DBHelper(this);
        final Cursor cursor = dbHelper.getAllRecords();
        final String finalName = name;
        final String finalCategory = category;
        while(cursor.moveToNext()) {
            // name = cursor.getString(1);
            category = cursor.getString(7);
        }

        final String send_this_name[] = new String[cursor.getCount()];
        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            send_this_name[i] = cursor.getString(1);
            i++;
            cursor.moveToNext();
        }

        /***********************************************
         *  EXPANDABLE VIEW W/ SELECT CATEGORY
         ***********************************************/
        expListView = (ExpandableListView) findViewById(R.id.lvExp); // get the listview
        prepareListData(); // preparing list data
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter); // setting list adapter
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // Listview Group click listener

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        /***********************************************
         *  LISTVIEW ON CHILD CLICK LISTERNER
         ***********************************************/
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                // PersonalDetails view
                Intent intent = new Intent(MainActivity.this, PersonalClass.class);
                intent.putExtra("Contact", send_this_name); // Send name to Personal Details
                MainActivity.this.startActivity(intent);

                return false;
            }
        });
    }

    /***********************************************
     *  PRRPARING THE LIST DATA
     ***********************************************/
    private void prepareListData() {
        // Global String variables

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add(category);
        listDataHeader.add("Relatives");
        listDataHeader.add("Others");

        // Adding child data
        List<String> friends = new ArrayList<>();

        dbHelper = new DBHelper(this);
        final Cursor cursor = dbHelper.getAllRecords();
        String array[] = new String[cursor.getCount()];
        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            array[i] = cursor.getString(1);
            // ---------------- //
            friends.add(array[i]);
            // ---------------- //
            i++;
            cursor.moveToNext();
        }

        List<String> relatives = new ArrayList<>();
        relatives.add("...");
        relatives.add("...");
        relatives.add("...");


        List<String> others = new ArrayList<>();
        others.add("...");
        others.add("...");
        others.add("...");

        listDataChild.put(listDataHeader.get(0), friends); // Header, Child data
        listDataChild.put(listDataHeader.get(1), relatives);
        listDataChild.put(listDataHeader.get(2), others);

    }

    // ----------------------------------------------- //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ----------------------------------------------- //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(login);
                return true;
            case R.id.sms:
                Intent sms = new Intent(MainActivity.this, SmsClass.class);
                MainActivity.this.startActivity(sms);
                return true;
            case R.id.new_contact:
                Intent contact = new Intent(MainActivity.this, CreateOrEditActivity.class);
                contact.putExtra(KEY_EXTRA_CONTACT_ID, 0);
                MainActivity.this.startActivity(contact);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
