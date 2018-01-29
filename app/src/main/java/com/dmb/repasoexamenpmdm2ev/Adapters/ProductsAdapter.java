package com.dmb.repasoexamenpmdm2ev.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmb.repasoexamenpmdm2ev.Models.Product;
import com.dmb.repasoexamenpmdm2ev.R;

import java.util.ArrayList;

/**
 * Created by davidmari on 28/1/18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cardProduct;
        TextView prodName;
        TextView prodPrice;
        TextView prodDesc;

        ProductViewHolder(View itemView) {
            super(itemView);
            cardProduct = itemView.findViewById(R.id.cardProduct);
            prodName = itemView.findViewById(R.id.tvProdName);
            prodPrice = itemView.findViewById(R.id.tvProdPrice);
            prodDesc = itemView.findViewById(R.id.tvProdDesc);
        }
    }

    ArrayList<Product> products;

    public ProductsAdapter(ArrayList<Product> products){
        this.products = products;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.prodName.setText(products.get(i).getName());
        productViewHolder.prodPrice.setText(products.get(i).getPrice()+"€");
        productViewHolder.prodDesc.setText(products.get(i).getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}