package com.dmb.repasoexamenpmdm2ev.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dmb.repasoexamenpmdm2ev.Adapters.ProductsAdapter;
import com.dmb.repasoexamenpmdm2ev.Models.Product;
import com.dmb.repasoexamenpmdm2ev.R;

import java.util.ArrayList;

import static com.dmb.repasoexamenpmdm2ev.MainActivity.products;

public class ListProductsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView tvEmptyList;

    RecyclerView recyclerProducts;

    ProductsAdapter pa = new ProductsAdapter(products);

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

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        if(products.isEmpty()){
            tvEmptyList.setVisibility(View.VISIBLE);
        }
        recyclerProducts.setLayoutManager(llm);
        recyclerProducts.setAdapter(pa);

        recyclerProducts.invalidate();

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
