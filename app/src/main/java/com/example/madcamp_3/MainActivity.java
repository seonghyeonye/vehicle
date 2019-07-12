package com.example.madcamp_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.mongodb.BasicDBObject;
//import com.mongodb.QueryBuilder;
//import com.mongodb.util.JSON;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //    QueryToServerMongoBuilder builderContacts = new QueryToServerMongoBuilder("madcamp", "contacts");
//    QueryToServerMongoBuilder builderGalleries = new QueryToServerMongoBuilder("madcamp", "galleries");
//    QueryToServerMongoBuilder builderTaxi= new QueryToServerMongoBuilder("madcamp","taxi");
    Fragment1 fragment1 = new Fragment1();
    Fragment2 fragment2 = new Fragment2();
    //Fragment3 fragment3 = new Fragment3();
    camera fragment3= new camera();

    Map<String, String> cookies = new HashMap<>();
    Fragment3 fragment31 = new Fragment3();
    Login_fragment loginFragment = new Login_fragment();
    MainFragment mainFragment = new MainFragment();
    //   PortToServer port = new PortToServer("http://143.248.36.38:3000", cookies);
    int currentTab;
    JSONObject account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new Login_fragment()).commit();
    }

    public void showProperFragment() {
        Fragment currentFrag;
        switch (currentTab) {
            case 1:
                currentFrag = fragment2;
                break;
            case 2:
                currentFrag = fragment3;
                break;
            default:
                currentFrag = fragment1;
                break;
        }
        System.out.println("!!!!");
        System.out.println(currentTab);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFrag).commit();
    }

    @Override
    public void onBackPressed() {
    }
    /*
        int count = getSupportFragmentManager().getBackStackEntryCount();
        System.out.println(count);
        if (count == 2) {
            super.onBackPressed();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();

            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }*/
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
//            fragment.onActivityResult(requestCode, resultCode, data);
//        }
//    }


}