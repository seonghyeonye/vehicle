package com.example.madcamp_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.security.Permissions;

public class Fragment3 extends Fragment {
    Context context = getActivity();
    ImageView imageView;
    private static final int CAMERA_REQUEST_CODE = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        Button searchBtn = view.findViewById(R.id.search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //팝업창 띄우기
            }
        });
        imageView=view.findViewById(R.id.cameraimage);
        ImageButton camera = view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //사진 열기
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                String filename= String.valueOf(System.currentTimeMillis()+".jpg");
//                File mainFile= new File(context.getCacheDir(),filename);
//                if(!mainFile.exists())
//                    try{
//                        mainFile.createNewFile();
//                        System.out.println("created");
//                    }catch(IOException e){
//                        e.printStackTrace();
//                    }
//                Uri imageUri= FileProvider.getUriForFile(context,BuildConfig.APPLICATION_ID+".provider",mainFile);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });
        return view;
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST_CODE){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            System.out.println("taking photo");
        }
    }
}
