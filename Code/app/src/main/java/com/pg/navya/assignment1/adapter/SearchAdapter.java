package com.pg.navya.assignment1.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pg.navya.assignment1.ProductDetailActivity;
import com.pg.navya.assignment1.R;
import com.pg.navya.assignment1.model.Product;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Reshma Salim on 23-04-2016.
 */
public class SearchAdapter extends CursorAdapter {

    private List<Product> items;

    private TextView text;
    private String query;

    public SearchAdapter(Context context, Cursor cursor, List<Product> items, String query) {

        super(context, cursor, false);
        this.query = query;
        this.items = items;

    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        final Product product = items.get(cursor.getPosition());
        SpannableStringBuilder sb = new SpannableStringBuilder(product.getName());
        Pattern p = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(product.getName());
        while (m.find()) {
            sb.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        text.setText(sb);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, ProductDetailActivity.class);
                in.putExtra("product", product);
                context.startActivity(in);
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.search_item, parent, false);

        text = (TextView) view.findViewById(R.id.item);

        return view;

    }

}