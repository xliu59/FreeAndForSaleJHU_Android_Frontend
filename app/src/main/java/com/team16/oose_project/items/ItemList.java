package com.team16.oose_project.items;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.team16.oose_project.R;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

    private ListView lvItems;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemAdapter);

        ArrayList<Item> items = new ArrayList<Item>();
        itemAdapter = new ItemAdapter(this, items);

    }

}

