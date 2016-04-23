package com.pg.navya.assignment1.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pg.navya.assignment1.R;

import java.util.List;

/**
 * Created by Reshma Salim  on 23-04-2016.
 */
public class FeaturedAdapter extends PagerAdapter {

    Context mContext;
    List<Integer> mResList;

    public FeaturedAdapter(Context context, List<Integer> resList) {
        mContext = context;
        mResList = resList;

    }

    @Override
    public int getCount() {
        return mResList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.featured_item, container, false);
        ImageView ivFeatured = (ImageView) view.findViewById(R.id.ivFeatured);
        ivFeatured.setBackgroundResource(mResList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
