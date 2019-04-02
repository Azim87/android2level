package com.kubatov.todo.Activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kubatov.todo.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser>{

    private List<User> userList;

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

        //User user = userList.get(i);
        viewHolderUser.onBind(userList.get(i));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {
    TextView textName;
    TextView textAge;

        public ViewHolderUser(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.text_name);
        textAge = itemView.findViewById(R.id.text_age);

    }

        public void onBind(User user){
            textName.setText(user.name);
        }

    }
}