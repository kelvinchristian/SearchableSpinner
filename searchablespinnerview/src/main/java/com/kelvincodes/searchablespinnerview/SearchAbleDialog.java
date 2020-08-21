package com.kelvincodes.searchablespinnerview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchAbleDialog {
    private final Dialog searchDialog;
    private final ListView itemsList;
    private final EditText search;
    Activity activity;
    int customLayout;
    int text_view_id;
    SpinnerItemsAdapter spinnerItemsAdapter;
    ItemSelectedListner itemSelectedListnerObj;
    private TextView done, title;

    public SearchAbleDialog(Activity activity, String titleText, int customLayout, int text_view_id, final ArrayList<Object> arrayList, ItemSelectedListner itemSelectedListner) {
        this.activity = activity;
        searchDialog = new Dialog(activity);
        searchDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        searchDialog.setCancelable(true);
        searchDialog.setContentView(R.layout.dialog_seach);
        title = searchDialog.findViewById(R.id.count);
        title.setText(titleText);
        done = searchDialog.findViewById(R.id.close);
        search = searchDialog.findViewById(R.id.search);
        itemsList = searchDialog.findViewById(R.id.listItems);
        search.requestFocus();
        itemSelectedListnerObj = itemSelectedListner;
        this.customLayout = customLayout;
        this.text_view_id = text_view_id;
        setupList(arrayList);

        itemsList.setAdapter(spinnerItemsAdapter);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerObjIndex selected = (SpinnerObjIndex) spinnerItemsAdapter.getItem(i);
                Log.d("position", String.valueOf(i));
                itemSelectedListnerObj.onItemSelect(selected.getObject().toString(), selected.getIndex());
                dismiss();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                spinnerItemsAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setupList(ArrayList<Object> arrayList) {

        ArrayList<SpinnerObjIndex> tempAr = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            tempAr.add(new SpinnerObjIndex(arrayList.get(i), i));
        }

        spinnerItemsAdapter = new SpinnerItemsAdapter(activity, customLayout, text_view_id, tempAr);
        itemsList.setAdapter(spinnerItemsAdapter);
    }

    public void show() {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(searchDialog.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth * 0.9f);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight * 0.9f);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        searchDialog.show();
        searchDialog.getWindow().setAttributes(layoutParams);
    }

    public void dismiss() {
        try {
            if (searchDialog.isShowing()) {
                searchDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyDataSetChanged(ArrayList<Object> arrayList1) {

        setupList(arrayList1);
    }
}
