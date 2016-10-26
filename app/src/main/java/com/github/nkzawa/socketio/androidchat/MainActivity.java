package com.github.nkzawa.socketio.androidchat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    DBHelper dbHelper;
    private ListView listView;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String austin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***********************************************
         *  DATABASE INIT
         ***********************************************/
        dbHelper = new DBHelper(this);

        final Cursor cursor = dbHelper.getAllRecords();
        String [] columns = new String[] {
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_NUMBER,
                DBHelper.COLUMN_EMAIL,
                DBHelper.COLUMN_WEBSITE,
                DBHelper.COLUMN_QUALIFICATIONS,
                DBHelper.COLUMN_DETAILS
        };

//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_main, cursor, columns, null, 0);
//        listView = (ListView)findViewById(R.id.main_list);
//        listView.setAdapter(cursorAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
//                Cursor itemCursor = (Cursor) MainActivity.this.listView.getItemAtPosition(position);
//                int personID = itemCursor.getInt(itemCursor.getColumnIndex(DBHelper.COLUMN_ID));
//                Intent intent = new Intent(getApplicationContext(), CreateOrEditActivity.class);
//                intent.putExtra(KEY_EXTRA_CONTACT_ID, personID);
//                startActivity(intent);
//            }
//        });


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
                String values = austin;
                intent.putExtra("Austin", values); //Optional parameters
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
        austin = "Austin Lazarus";

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Friends");
        listDataHeader.add("Relatives");
        listDataHeader.add("Others");

        // Adding child data
        List<String> friends = new ArrayList<>();
        friends.add("Tukaram Bhagat");
        friends.add("Craig Nesty");
        friends.add("Veronne Nicholas");
        friends.add(austin);
        friends.add("Timothy Tavanier");
        friends.add("Al Parillon");
        friends.add("Chester Wyke");
        friends.add("Kerry");

        List<String> relatives = new ArrayList<>();
        relatives.add("...");


        List<String> others = new ArrayList<>();
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
