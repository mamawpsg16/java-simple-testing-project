package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.alert_dialog.AlertDialogMainActivity;
import com.example.project.alert_dialog.CustomDialogWithEditText;
import com.example.project.array_adapter.ArrayAdapaterMainActivity;
import com.example.project.fragment.FragmentMainExample;
import com.example.project.fragment.SwipeFragmentMain;
import com.example.project.fragment.TabSwipeFragmentMain;
import com.example.project.spinner.CustomSpinner;
import com.example.project.spinner.SpinnerActivity;

public class BundleTestActivity extends AppCompatActivity implements  View.OnClickListener{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bundle_activity);

        Button btnPassBundle = findViewById(R.id.btnPassBundles);
        Button btnPassNoBundle = findViewById(R.id.btnNoBundle);
        btnPassNoBundle.setOnClickListener(this);
        btnPassBundle.setOnClickListener(this);

//        IMPLICIT REDIRECT TO GEEKSFORGEEKS SITE
//        Intent intent=new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://www.geeksforgeeks.org/"));
//        startActivity(intent);

//        btnPassBundles.setOnClickListener(v -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("key1", "Passing Bundle From Main Activity to 2nd Activity");
//
//            Intent intent = new Intent(this, PassingBundleExample.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        });
//
//        btnNoBundle.setOnClickListener(v -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("key1", "Not passing Bundle From Main Activity");
//            bundle.clear();
//
//            Intent intent = new Intent(this, PassingBundleExample.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        });

    }

    @Override
    public void onClick(View view) {
        Bundle bundle;
        Intent intent;

        if (view.getId() == R.id.btnPassBundles) {
            // Creating a bundle instance
            bundle = new Bundle();
            Log.d("BUNDLE","pass bundles");
            // Passing the data into the bundle
            bundle.putString("key1", "Passing Bundle From Bundle Test Activity to Passing Bundle Example Activity");

            intent = new Intent(this, PassingBundleExample.class);
            // Passing the bundle to the intent
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (view.getId() == R.id.btnNoBundle) {
            Log.d("BUNDLE","no bundles");
            bundle = new Bundle();
            bundle.putString("key1", "Not passing Bundle From Main Activity");
            // Clearing the data stored in the bundle
            bundle.clear();

            intent = new Intent(this, PassingBundleExample.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void openCalculator(View view){
        Intent intent = new Intent(this, AutoResizeTextViewActivity.class);
        startActivity(intent);
    }

    public void openExamples(View view){
        Intent intent = new Intent(this, PracticalExamplesActivity.class);
        startActivity(intent);
    }

    public void openCamera(View view){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void openCustomToastActivity(View view){
        Intent intent = new Intent(this, CustomToastActivity.class);
        startActivity(intent);
    }

    public void openRecyclerView(View view){
        Intent intent = new Intent(this, RecyclerViewExample.class);
        startActivity(intent);
    }

    public void openFragmentActivity(View view){
        Intent intent = new Intent(this, FragmentMainExample.class);
        startActivity(intent);
    }

    public void openSwipeFragmentActivity(View view){
        Intent intent = new Intent(this, SwipeFragmentMain.class);
        startActivity(intent);
    }

    public void openTabSwipeFragmentActivity(View view){
        Intent intent = new Intent(this, TabSwipeFragmentMain.class);
        startActivity(intent);
    }

    public void openArrayAdapter(View view){
        Intent intent = new Intent(this, ArrayAdapaterMainActivity.class);
        startActivity(intent);
    }

    public void openSpinner(View view){
        Intent intent = new Intent(this, SpinnerActivity.class);
        startActivity(intent);
    }

    public void openCustomSpinner(View view){
        Intent intent = new Intent(this, CustomSpinner.class);
        startActivity(intent);
    }

    public void openAlertDialogActivity(View view){
        Intent intent = new Intent(this, AlertDialogMainActivity.class);
        startActivity(intent);
    }

    public void openCustomAlertDialog(View view){
        Intent intent = new Intent(this, CustomDialogWithEditText.class);
        startActivity(intent);
    }
}
