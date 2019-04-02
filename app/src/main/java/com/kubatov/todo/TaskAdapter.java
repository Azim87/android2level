package com.kubatov.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubatov.todo.Interface.ClickListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {


    List<Task> list;
    private ClickListener listener;
    int color;
    Context context;

    public TaskAdapter(List<Task> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_task, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Task task = list.get(position);
        viewHolder.textTitle.setText(task.getTitle());
        viewHolder.textDescription.setText(task.getDescription());
        viewHolder.textStatus.setTextColor(color);

        SharedPreferences preferences = context.getSharedPreferences("color", MODE_PRIVATE);
        int color = preferences.getInt("color", 0);
        int font = preferences.getInt("font", 1);



        switch (task.getStatus()){
            case 0:
                viewHolder.textStatus.setText("срочное");
                viewHolder.textStatus.setTextColor(color);
                viewHolder.textStatus.setTextSize(font);

                break;
            case 1:
                viewHolder.textStatus.setText("очень срочное");
                viewHolder.textStatus.setTextColor(color);
                viewHolder.textStatus.setTextSize(font);
                break;
            case 2:
                viewHolder.textStatus.setText("сверх срочное");
                viewHolder.textStatus.setTextColor(color);
                viewHolder.textStatus.setTextSize(font);
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
        TextView textTime;


         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             textTitle = itemView.findViewById(R.id.text_title);
             textDescription = itemView.findViewById(R.id.text_description);
             textStatus = itemView.findViewById(R.id.textStatus);
             textTime = itemView.findViewById(R.id.text_view_time);



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
