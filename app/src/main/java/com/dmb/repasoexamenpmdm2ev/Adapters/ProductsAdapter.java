package com.dmb.repasoexamenpmdm2ev.Adapters;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dmb.repasoexamenpmdm2ev.Models.Product;
import com.dmb.repasoexamenpmdm2ev.R;

import java.util.ArrayList;

/**
 * Created by davidmari on 28/1/18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

    private ArrayList<Product> products;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public ProductsAdapter(ArrayList<Product> products, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener){
        this.products = products;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
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
    public void onBindViewHolder(final ProductViewHolder productViewHolder, final int position) {
        Product product = products.get(position);
        productViewHolder.prodName.setText(product.getName());
        productViewHolder.prodPrice.setText(product.getPrice()+"€");
        productViewHolder.prodDesc.setText(product.getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }

    }

    public interface RecyclerViewOnItemClickListener{
        void onClick(View v, int position);
    }

}