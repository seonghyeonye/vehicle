package com.example.madcamp_3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment{
    View view;
    private Recycler_tab2_Adapter adapter;
    private RequestQueue mQueue;
    EditText editText;

    private ArrayList<buysell_item> recyclerItems = new ArrayList<>();
    private List<String> listName = new ArrayList<String>();
    private List<String> listYear = new ArrayList<String>();
    private List<String> listPrice = new ArrayList<String>();
    private List<Boolean> listHeart = new ArrayList<Boolean>();
    private List<Integer> listImage = new ArrayList<Integer>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        mQueue = Volley.newRequestQueue(view.getContext());
        init();
        jsonParse();

        editText = (EditText) view.findViewById(R.id.fiteredittext_id);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        return view;
    }
}
