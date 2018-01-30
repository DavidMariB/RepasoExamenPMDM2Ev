package com.dmb.repasoexamenpmdm2ev.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dmb.repasoexamenpmdm2ev.Models.Product;
import com.dmb.repasoexamenpmdm2ev.R;

public class AddProductFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText etProdName,etProdPrice,etProdDesc;
    Button btnSaveProd,btnCleanFields;
    String getName,getPrice,getDesc;
    Boolean checkFields;

    public AddProductFragment() {
        // Required empty public constructor
    }

    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        View v = inflater.inflate(R.layout.fragment_add_product, container, false);

        etProdName = v.findViewById(R.id.etProdName);
        etProdPrice = v.findViewById(R.id.etProdPrice);
        etProdDesc = v.findViewById(R.id.etProdDesc);
        btnSaveProd = v.findViewById(R.id.btnSaveProd);
        btnCleanFields = v.findViewById(R.id.btnCleanFields);

        btnSaveProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkProdFields()){
                    Product product = new Product(getName,getPrice,getDesc);
                    mListener.addProduct(product);
                    Toast.makeText(getContext(),"Producto Guardado",Toast.LENGTH_SHORT).show();
                    etProdName.setText("");
                    etProdPrice.setText("");
                    etProdDesc.setText("");
                }
            }
        });

        btnCleanFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etProdName.setText("");
                etProdPrice.setText("");
                etProdDesc.setText("");
            }
        });

        return v;
    }

    public boolean checkProdFields(){
        getName = etProdName.getText().toString();
        getPrice = etProdPrice.getText().toString();
        getDesc = etProdDesc.getText().toString();
        if (TextUtils.isEmpty(getName)){
            etProdName.setError("El producto debe tener un nombre");
            checkFields = false;
        }else if(TextUtils.isEmpty(getPrice)){
            etProdPrice.setError("El producto debe tener un precio");
            checkFields = false;
        }else if(TextUtils.isEmpty(getDesc)){
            etProdDesc.setError("El producto debe tener una descripcion");
            checkFields = false;
        }else {
            checkFields = true;
        }
        return checkFields;
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
        void addProduct(Product product);
    }
}
