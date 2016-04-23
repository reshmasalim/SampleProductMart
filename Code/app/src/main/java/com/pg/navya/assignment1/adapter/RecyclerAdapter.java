package com.pg.navya.assignment1.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pg.navya.assignment1.R;
import com.pg.navya.assignment1.model.Product;

import java.util.List;

/**
 * Created by Reshma Salim on 23-04-2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private Context mContext;
    private List<Product> productList;
    private View.OnClickListener onClickListener;

    public RecyclerAdapter(Context mContext, List<Product> productList, View.OnClickListener onClickListener) {
        this.mContext = mContext;
        this.productList = productList;
        this.onClickListener=onClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_grid_item, parent, false);
        view.setOnClickListener(onClickListener);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Product product = productList.get(position);

        holder.getTvName().setText(product.getName());
        holder.getTvPrice().setText(String.format(mContext.getResources().getString(R.string.rs_x), product.getPrice()));
        holder.getIvProduct().setBackground(new BitmapDrawable(decodeSampledBitmapFromResource(mContext.getResources(), product.getImageId(), 100, 100)));


    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPrice;
        private ImageView ivProduct;
        private View rootView;

        public MyHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            rootView = itemView;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvPrice() {
            return tvPrice;
        }

        public ImageView getIvProduct() {
            return ivProduct;
        }
    }


}