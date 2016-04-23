package com.pg.navya.assignment1.manager;

import android.content.Context;

import com.pg.navya.assignment1.R;
import com.pg.navya.assignment1.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reshma Salim on 23-04-2016.
 */
public class ProductManager {
    private static ProductManager ourInstance;
    private static Context mContext;
    private List<Product> productsList;
    private List<Integer> mResList;

    public static ProductManager getInstance(Context context)

    {
        mContext = context;
        if (ourInstance == null)
            ourInstance = new ProductManager(context);
        return ourInstance;
    }

    private ProductManager(Context context) {
        initialize();
    }


    public void reInitialize(){
        initialize();
    }

    private void initialize() {
        mResList = new ArrayList<>();
        mResList.add(R.drawable.featured1);
        mResList.add(R.drawable.featured2);
        mResList.add(R.drawable.featured3);


        productsList = new ArrayList<>();
        productsList.add(new Product(mContext.getResources().getString(R.string.ac), R.drawable.ac, 1000));
        productsList.add(new Product(mContext.getResources().getString(R.string.chair), R.drawable.chair, 2000));
        productsList.add(new Product(mContext.getResources().getString(R.string.fridge), R.drawable.fridge, 3000));
        productsList.add(new Product(mContext.getResources().getString(R.string.laptop), R.drawable.laptop, 4000));
        productsList.add(new Product(mContext.getResources().getString(R.string.microwave), R.drawable.microwave, 5000));
        productsList.add(new Product(mContext.getResources().getString(R.string.mobile), R.drawable.mobile, 6000));
        productsList.add(new Product(mContext.getResources().getString(R.string.table), R.drawable.table, 6000));
        productsList.add(new Product(mContext.getResources().getString(R.string.table_fan), R.drawable.tablefan, 6000));
        productsList.add(new Product(mContext.getResources().getString(R.string.tv), R.drawable.tv, 6000));
    }

    public List<Integer> getFeatured() {
        return mResList;
    }

    public List<Product> getAllProducts() {
        return productsList;
    }
}
