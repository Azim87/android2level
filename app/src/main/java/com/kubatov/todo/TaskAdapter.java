package com.kubatov.todo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubatov.todo.Interface.ClickListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {


    List<Task> list;
    private ClickListener listener;

    public TaskAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_task, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Task task = list.get(i);
        viewHolder.textTitle.setText(task.getTitle());
        viewHolder.textDescription.setText(task.getDescription());

        switch (task.getStatus()){
            case 0:
                viewHolder.textStatus.setText("срочное");
                viewHolder.textStatus.setTextColor(Color.BLACK);
                break;
            case 1:
                viewHolder.textStatus.setText("очень срочное");
                viewHolder.textStatus.setTextColor(Color.BLUE);
                break;
            case 2:
                viewHolder.textStatus.setText("сверх срочное");
                viewHolder.textStatus.setTextColor(Color.GREEN);
                break;

        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textDescription;
        TextView textStatus;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             textTitle = itemView.findViewById(R.id.text_title);
             textDescription = itemView.findViewById(R.id.text_description);
             textStatus = itemView.findViewById(R.id.textStatus);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     listener.onClicks(getAdapterPosition());

                 }
             });

             itemView.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     listener.deleteOnClick(getAdapterPosition());
                     return true;
                 }
             });


         }
     }
}
