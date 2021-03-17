package com.example.assigments_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHoolder> {

    Context context;
    List<User> users;

    public UserAdapter(Context context, List<User> users) {

        this.context = context;
        this.users = users;

    }

    @NonNull
    @Override
    public UserHoolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);

        return new UserAdapter.UserHoolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHoolder holder, int position) {
        User user = users.get(position);
        holder.username.setText(user.getusername());
        holder.address.setText(user.getAddress());
        holder.number.setText(user.getNumber());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHoolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView number;
        TextView address;

        public UserHoolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            number = itemView.findViewById(R.id.usernumber);
            address = itemView.findViewById(R.id.useradress);

        }
    }
}
