package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.MyViewHolder> {

    private List<ExamItem> examList;

    // Constructor
    public ExamAdapter(List<ExamItem> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    //FIRST
//   This method inflates (loads) the XML layout for each item (exam_card.xml).
//   It creates and returns a ViewHolder, which holds references to the views.
//   creates and returns a MyViewHolder instance.
    // This function inflated the list_item and fits it into the Recycler View Widget
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        (converted into a Java View object).
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exam_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    //uses the ViewHolder to set data into the UI widgets.
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get data for the current position
        ExamItem examItem = examList.get(position);

        // Set data into the ViewHolder's widgets
        holder.examName.setText(examItem.getName());
        holder.examDate.setText(examItem.getDate());
        holder.examMessage.setText(examItem.getMessage());
        holder.examPic.setImageResource(examItem.getImage1());
        holder.examPic2.setImageResource(examItem.getImage2());
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    // ViewHolder class
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView examName, examDate, examMessage;
        ImageView examPic, examPic2;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            examName = itemView.findViewById(R.id.examName);
            examDate = itemView.findViewById(R.id.examDate);
            examMessage = itemView.findViewById(R.id.examMessage);
            examPic = itemView.findViewById(R.id.examPic);
            examPic2 = itemView.findViewById(R.id.examPic2);
        }
    }
}