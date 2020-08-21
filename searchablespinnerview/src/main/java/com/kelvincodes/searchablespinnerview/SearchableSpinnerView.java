package com.kelvincodes.searchablespinnerview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

import java.util.ArrayList;

public class SearchableSpinnerView extends LinearLayout {
    SearchAbleDialog searchAbleDialog;
    @StyleableRes
    int index0 = 0;
    @StyleableRes
    int index1 = 1;
    @StyleableRes
    int index2 = 2;
    OnSearchableItemSelectedListener onSearchableItemSelectedListener;
    Activity activity;
    int customLayout = R.layout.row_item;
    int text_view_id = R.id.text1;
    ArrayList<Object> arrayList;
    String selectedItemValue = null;
    int selectedItemPosition;
    private TextView selectedItem;
    private ImageView spinner_icon;
    private String title = "SelectItem";

    public SearchableSpinnerView(Context context) {
        this(context, null, 0);
    }

    public SearchableSpinnerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SearchableSpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.spinner_view, this);

        initComponents();


    }

    public SearchableSpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initComponents() {
        selectedItem = findViewById(R.id.selectedItem);
        spinner_icon = findViewById(R.id.spinner_icon);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                searchAbleDialog.show();

            }
        });
    }

    public SearchableSpinnerView setContext(Activity activity) {
        this.activity = activity;
        return this;
    }

    public SearchableSpinnerView setItemLayout(int customLayout, int text_view_id) {
        this.customLayout = customLayout;
        this.text_view_id = text_view_id;
        return this;
    }

    public void create() {
        searchAbleDialog = new SearchAbleDialog(activity, title, customLayout, text_view_id, arrayList, new ItemSelectedListner() {
            @Override
            public void onItemSelect(String itemText, int index) {
                selectedItem.setText(itemText);
                selectedItemValue = itemText;
                selectedItemPosition = index;
                if (onSearchableItemSelectedListener != null) {
                    onSearchableItemSelectedListener.onItemSelected(index);
                }
            }

        });
    }

    public SearchableSpinnerView setTitle(String title) {
        this.title = title;
        return this;
    }

    public void notifyDataSetChanged() {
        Log.d("sizeee", String.valueOf(arrayList.size()));
        searchAbleDialog.notifyDataSetChanged(arrayList);
    }

    public SearchableSpinnerView setData(ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        return this;
    }


    public String getSelectedItem() {
        return selectedItemValue;
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setOnItemSelectedListener(OnSearchableItemSelectedListener onSearchableItemSelectedListener) {

        this.onSearchableItemSelectedListener = onSearchableItemSelectedListener;
    }
}
