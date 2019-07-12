package com.example.madcamp_3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class camera extends Fragment {
    private static final String TAG="PhotoFragment";
    MainActivity mainActivity;
    private static final int CAMERA_REQUEST_CODE=5;
    ImageView imageView;
    Context context;
    TextView registernum;
    Bitmap bitmap;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment3,container,false);
        Log.d(TAG,"onCreateView:started.");
        ImageButton searchBtn = view.findViewById(R.id.camera);
        imageView = view.findViewById(R.id.cameraimage);
        registernum=view.findViewById(R.id.registernum);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick:launch camera");
                Intent cameraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CAMERA_REQUEST_CODE){
            bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            System.out.println("taking photo");
            FirebaseApp.initializeApp(getContext());
            detect(view);
           // FirebaseVisionImage image= FirebaseVisionImage.fromBitmap(bitmap);
//            FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
//                    .getCloudTextRecognizer();
//            FirebaseVisionCloudTextRecognizerOptions options = new FirebaseVisionCloudTextRecognizerOptions.Builder()
//                    .setLanguageHints(Arrays.asList("en", "hi"))
//                    .build();
//            Task<FirebaseVisionText> result =
//                    detector.processImage(image)
//                            .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//                                @Override
//                                public void onSuccess(FirebaseVisionText firebaseVisionText) {
//                                    // Task completed successfully
//                                    // ...
//                                    System.out.println("success");
//                                }
//                            })
//                            .addOnFailureListener(
//                                    new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            // Task failed with an exception
//                                            // ...
//                                            System.out.println("fail");
//                                        }
//                                    });
        }
    }

    public void detect(View v){
        if(bitmap==null){
            Toast.makeText(getActivity().getApplicationContext(),"bitmap null",Toast.LENGTH_LONG).show();
        }
        final FirebaseVisionImage firebaseVisionImage= FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer= FirebaseVision.getInstance().getCloudTextRecognizer();
        System.out.println("detect enter");
        firebaseVisionTextRecognizer.processImage(firebaseVisionImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        System.out.println("process_text enter");
                        process_text(firebaseVisionText);
                        System.out.println("success");
                    }
                });
    }

    private void process_text(FirebaseVisionText firebaseVisionText){
        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
        if(blocks.size()==0){
            Toast.makeText(getActivity().getApplicationContext(),"no text",Toast.LENGTH_SHORT).show();
        }
        else{
            for(FirebaseVisionText.TextBlock block: firebaseVisionText.getTextBlocks()){
                String text= block.getText();
                registernum.setText(text);
            }
        }
    }


}
