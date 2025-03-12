package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Sample data
        List<ExamItem> examList = new ArrayList<>();

        examList.add(new ExamItem("Math Exam", "May 23, 2015",
                "Best of Luck", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Science Exam", "June 10, 2015",
                "Do Well", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("History Exam", "July 15, 2015",
                "All the Best", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("English Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Espanyol Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Visaya Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Ilonggo Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Bulakenyo", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));


        examList.add(new ExamItem("Pampangeno Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Hagonenyo Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        examList.add(new ExamItem("Kapampangan Exam", "August 1, 2015",
                "Stay Confident", R.drawable.logo_24px,
                R.drawable.ic_user));

        // Set LayoutManager
//      A layout manager determines how items are displayed in the list.
//      LinearLayoutManager arranges items vertically (default) or horizontally in a list.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set Adapter
        ExamAdapter adapter = new ExamAdapter(examList);
        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);

    // Notify RecyclerView about the new data
//        adapter.notifyDataSetChanged();
    }
}