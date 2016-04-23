package com.pg.navya.assignment1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pg.navya.assignment1.adapter.FeaturedAdapter;
import com.pg.navya.assignment1.adapter.RecyclerAdapter;
import com.pg.navya.assignment1.adapter.SearchAdapter;
import com.pg.navya.assignment1.manager.ProductManager;
import com.pg.navya.assignment1.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpFeatured;
    private List<Integer> mResList;
    private RecyclerView rvGrid;
    private List<Product> productsList;
    private FeaturedAdapter featuredAdapter;
    private RecyclerAdapter recyclerAdapter;
    private GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        vpFeatured = (ViewPager) findViewById(R.id.vpFeatured);
        rvGrid = (RecyclerView) findViewById(R.id.rvGrid);
        mResList = ProductManager.getInstance(this).getFeatured();
        featuredAdapter = new FeaturedAdapter(this, mResList);

        vpFeatured.setAdapter(featuredAdapter);
        mHandler.sendEmptyMessageDelayed(0, 3000);


        gridLayoutManager = new GridLayoutManager(this, 3);
        rvGrid.setLayoutManager(gridLayoutManager);
        productsList = ProductManager.getInstance(this).getAllProducts();
        recyclerAdapter = new RecyclerAdapter(this, productsList, rvItemClickListener);
        rvGrid.setAdapter(recyclerAdapter);
        rvGrid.setNestedScrollingEnabled(false);

        final NestedScrollView nsv = (NestedScrollView) findViewById(R.id.nsv);
        nsv.post(new Runnable() {
            @Override
            public void run() {
                nsv.scrollTo(0, 0);
            }
        });
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (vpFeatured != null) {
                if (vpFeatured.getCurrentItem() == mResList.size() - 1) {
                    vpFeatured.setCurrentItem(0);
                } else {
                    vpFeatured.setCurrentItem(vpFeatured.getCurrentItem() + 1);
                }

                mHandler.sendEmptyMessageDelayed(0, 3000);
            }
            return true;
        }
    });


    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        ProductManager.getInstance(this).reInitialize();
        finish();
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lang, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)
                MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchResult(menu, query);
                return true;
            }

        });

        return true;
    }

    private void searchResult(Menu menu, String query) {

        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

        String[] columns = new String[]{"_id", "text"};
        Object[] temp = new Object[]{0, "default"};

        MatrixCursor cursor = new MatrixCursor(columns);
        List<Product> tempList = new ArrayList<>();
        for (int i = 0; i < productsList.size(); i++) {

            if (productsList.get(i).getName().toLowerCase().contains(query.toLowerCase())) {
                temp[0] = i;
                temp[1] = productsList.get(i);
                cursor.addRow(temp);
                tempList.add(productsList.get(i));
            }
        }


        search.setSuggestionsAdapter(new SearchAdapter(this, cursor, tempList,query));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.langEn:
                changeLang("en");
                break;

            case R.id.langHi:
                changeLang("hi");
                break;

            case R.id.action_search:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener rvItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = rvGrid.getChildAdapterPosition(view);
            Product item = productsList.get(itemPosition);

            Intent in = new Intent(MainActivity.this, ProductDetailActivity.class);
            in.putExtra("product", item);
            startActivity(in);
        }
    };

    @Override
    protected void onDestroy() {

        super.onDestroy();
        featuredAdapter = null;
        recyclerAdapter = null;
        gridLayoutManager.removeAllViews();
        gridLayoutManager = null;
        vpFeatured = null;
        rvGrid = null;
        productsList = null;
        mResList = null;
        mHandler = null;
    }
}
