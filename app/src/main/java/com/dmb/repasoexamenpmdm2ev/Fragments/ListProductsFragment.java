package com.dmb.repasoexamenpmdm2ev.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmb.repasoexamenpmdm2ev.Adapters.ProductsAdapter;
import com.dmb.repasoexamenpmdm2ev.Models.Product;
import com.dmb.repasoexamenpmdm2ev.R;

import java.util.ArrayList;

public class ListProductsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView tvEmptyList;
    RecyclerView recyclerProducts;
    ProductsAdapter pa;

    public ListProductsFragment() {
        // Required empty public constructor
    }

    public static ListProductsFragment newInstance(String param1, String param2) {
        ListProductsFragment fragment = new ListProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_products, container, false);

        recyclerProducts = v.findViewById(R.id.recyclerProducts);
        tvEmptyList = v.findViewById(R.id.tvEmptyList);

        pa = new ProductsAdapter(mListener.getProducts(), new ProductsAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(final View v, final int position) {
                Snackbar.make(v,mListener.getProducts().get(position).getName(),Snackbar.LENGTH_LONG).show();
                AlertDialog.Builder alertBox = new AlertDialog.Builder(v.getRootView().getContext());
                alertBox.setMessage("¿Estás seguro de que quieres eliminar este producto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar.make(v,"Producto: "+mListener.getProducts().get(position).getName()+" eliminado",Snackbar.LENGTH_LONG).show();
                                mListener.getProducts().remove(position);
                                recyclerProducts.getAdapter().notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                alertBox.show();
            }
        });

        if(mListener.getProducts().isEmpty()){
            tvEmptyList.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerProducts.setLayoutManager(llm);
        recyclerProducts.setAdapter(pa);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        ArrayList<Product> getProducts();
        Product getProduct(int position);
    }
}
