package com.team16.oose_project.items;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.team16.oose_project.R;

import java.util.ArrayList;


// an adapter that allows us to translate an Item into a particular view.
public class ItemAdapter extends ArrayAdapter<Item> {
    // View lookup cache
    private static class ViewHolder {
        private ImageView ivImgLink;
        private TextView ivPrice;
        private TextView ivName;
        private TextView ivCondition;
        private TextView ivDistance;
        private TextView ivIsDeliver;
    }
    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    /**
     * Translates a particular `Item` given a position
     * into a relevant row within an AdapterView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the Item object for this position
        final Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_item_abstract, parent, false);

            viewHolder.ivImgLink = (ImageView)convertView.findViewById(R.id.ivItemImage);
            viewHolder.ivName = (TextView)convertView.findViewById(R.id.ivName);
            viewHolder.ivCondition = (TextView)convertView.findViewById(R.id.ivCondition);
            viewHolder.ivPrice = (TextView)convertView.findViewById(R.id.ivPrice);
            viewHolder.ivDistance = (TextView)convertView.findViewById(R.id.ivDistance);
            viewHolder.ivIsDeliver = (TextView)convertView.findViewById(R.id.ivIsDeliver);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.ivName.setText(item.getName());
        viewHolder.ivCondition.setText(item.getCondition());
        viewHolder.ivPrice.setText("$" + String.valueOf(item.getPrice()));
        // viewHolder.ivDistance.setText(??);
        viewHolder.ivIsDeliver.setText(item.isDeliver() ? "Yes" : "No");

        Picasso.with(getContext()).load(Uri.parse(item.getImgLink())).error(R.drawable.ic_noimage).into(viewHolder.ivImgLink);
        // Return the completed view to render on screen
        return convertView;
    }

}

