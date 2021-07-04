package com.example.task8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdpter extends RecyclerView.Adapter<UserAdpter.ViewHolder> {

    Context MapFragment;
    ArrayList<User> usersArrayList;


    public UserAdpter(Context MapFragment, ArrayList<User> usersArrayList) {

        this.MapFragment = MapFragment;
        this.usersArrayList = usersArrayList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MapFragment).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserAdpter.ViewHolder holder, int position) {
        User users = usersArrayList.get(position);

        holder.user_name.setText(users.getName());
    }

    @Override
    public int getItemCount() {
        return  usersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView user_name;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            user_name =itemView.findViewById(R.id.user_Name);
        }
    }
}
