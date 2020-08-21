package com.kelvincodes.searchablespinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kelvincodes.searchablespinnerview.OnSearchableItemSelectedListener;
import com.kelvincodes.searchablespinnerview.SearchableSpinnerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchableSpinnerView searchableSpinnerView;
    ArrayList<Object> arrayList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchableSpinnerView = findViewById(R.id.seach);
        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("a" + i);
        }
        searchableSpinnerView.setContext(this).setTitle("SelectItem").setItemLayout(R.layout.row_item, R.id.text1).setData(arrayList).create();
        findViewById(R.id.addMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 10; i < 15; i++) {
                    arrayList.add("a" + i);
                }
                searchableSpinnerView.notifyDataSetChanged();
            }
        });

        findViewById(R.id.getselected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = searchableSpinnerView.getSelectedItemPosition();
                Log.d("indexindex", String.valueOf(index));
                Log.d("indexindex", searchableSpinnerView.getSelectedItem());
            }
        });


        searchableSpinnerView.setOnItemSelectedListener(new OnSearchableItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
