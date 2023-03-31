package com.example.projectassembly.clases;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.projectassembly.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<CartItem> {

    public ItemAdapter(Activity context, ArrayList<CartItem> items) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        CartItem currentItem = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView productTextView = (TextView) listItemView.findViewById(R.id.product_TextView);
        // Get the product from the current CartItem object and
        // set this text on the product_TextView
        productTextView.setText(currentItem.getProduct());
        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price_textView);
        // Get the price from the current CartItem object and
        // set this text on the price_textView
        priceTextView.setText(currentItem.getPrice());
        //Get the proper image if apply
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.productView);
        imageView.setImageResource(currentItem.getImage());

        // Return the whole list item layout (containing 2 TextViews and image)
        // so that it can be shown in the ListView
        return listItemView;
    }
}

