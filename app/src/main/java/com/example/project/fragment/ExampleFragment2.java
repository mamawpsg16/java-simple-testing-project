package com.example.project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;

public class ExampleFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_example2, container, false);


        TextView textView = view.findViewById(R.id.textView2);
        Bundle args = getArguments();
        if (args != null) {
            String message = args.getString("frag2");
            textView.setText(message);
        }

        return view;
    }
}
