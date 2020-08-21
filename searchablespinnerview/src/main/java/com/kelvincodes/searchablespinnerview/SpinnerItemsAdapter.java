package com.kelvincodes.searchablespinnerview;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerItemsAdapter extends BaseAdapter implements Filterable {
    Activity activity;
    int customLayout;
    int text_view_id;
    ArrayList<SpinnerObjIndex> arrayList;
    int SelectedIndex;
    private List<SpinnerObjIndex> arrayListNames;


    public SpinnerItemsAdapter(Activity activity, int customLayout, int text_view_id, ArrayList<SpinnerObjIndex> arrayList) {
        this.activity = activity;
        this.customLayout = customLayout;
        this.text_view_id = text_view_id;
        this.arrayList = arrayList;
        this.arrayListNames = arrayList;
    }

    @Override
    public int getCount() {
        return arrayListNames.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListNames.get(i);
    }

    @Override
    public long getItemId(int i) {


        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        ViewHolder holder = new ViewHolder();

        if (view == null) {

            view = inflater.inflate(customLayout, viewGroup, false); // inflate your_layout.xml
            holder.item = view.findViewById(text_view_id);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        Object name = arrayListNames.get(i).getObject();
        holder.item.setText(name.toString());

        return view;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayListNames = (List<SpinnerObjIndex>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<SpinnerObjIndex> FilteredArrayNames = new ArrayList<SpinnerObjIndex>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < arrayList.size(); i++) {
                    Object name = arrayList.get(i).getObject();
                    String dataNames = name.toString();
                    Log.d("DADADA", dataNames);
                    if (dataNames.toLowerCase().startsWith(constraint.toString())) {
                        FilteredArrayNames.add(arrayList.get(i));
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }

    class ViewHolder {
        TextView item;
    }
}
