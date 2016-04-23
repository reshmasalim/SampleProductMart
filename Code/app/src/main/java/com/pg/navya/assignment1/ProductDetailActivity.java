package com.pg.navya.assignment1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pg.navya.assignment1.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        ivProduct = (ImageView) findViewById(R.id.ivProduct);

        Product product = (Product) getIntent().getSerializableExtra("product");
        tvName.setText(product.getName());
        tvPrice.setText(String.format(getResources().getString(R.string.rs_x), product.getPrice()));
        ivProduct.setImageResource(product.getImageId());
        setTitle(product.getName());
    }
}
