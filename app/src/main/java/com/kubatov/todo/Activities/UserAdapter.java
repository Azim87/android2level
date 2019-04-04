package com.kubatov.todo.Activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubatov.todo.Interface.ClickListener;
import com.kubatov.todo.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser>{

    private List<User> userList;
    private ClickListener listener;

    public UserAdapter(List<User> list) {
        userList = list;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_user, viewGroup, false);

        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser viewHolderUser, int i) {

        User user = userList.get(i);
        viewHolderUser.textName.setText(user.getName());
        viewHolderUser.textAge.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {
    TextView textName;
    TextView textAge;

        public ViewHolderUser(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);


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