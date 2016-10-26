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

import static com.github.nkzawa.socketio.androidchat.DatabaseHelpler.TABLE_NAME;


public class MainActivity extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    DatabaseHelpler myDb;
    String austin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***********************************************
         *  DATABASE INIT
         ***********************************************/
        myDb = new DatabaseHelpler(this);
        Cursor myContacts = myDb.getCategories();

//        while(myContacts.moveToNext())
//        {
//            String contacts = myContacts.getString(0);
//        }

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
        // ----------------------------------------------- //
        /*
         *  Listview on child click listener
         */
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
    // ----------------------------------------------- //
    /*
     * Preparing the list data
     */
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
        }
        return super.onOptionsItemSelected(item);
    }


}
