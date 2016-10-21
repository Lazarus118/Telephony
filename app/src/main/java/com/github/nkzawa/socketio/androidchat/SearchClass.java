package com.github.nkzawa.socketio.androidchat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SearchClass extends ActionBarActivity {
    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        // ----------------------------------------- //
        ls = (ListView) findViewById(R.id.search_list_id);
        String[] values = new String[] {
                "Tukaram Bhagat",
                "Craig Nesty",
                "Veronne Nicholas",
                "Austin Lazarus",
                "Timothy Tavanier",
                "Al Parillon",
                "Chester Wyke",
                "Kerry"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        ls.setAdapter(adapter);
        // ----------------------------------------- //
        /*
         * ListView Item Click Listener
         */
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                String  itemValue = (String) ls.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
            }
        });
    }
}
