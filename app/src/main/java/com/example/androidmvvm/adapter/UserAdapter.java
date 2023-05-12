package com.example.androidmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmvvm.R;
import com.example.androidmvvm.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    /**
     * L'Adapter sert à afficher une liste de données dans une vue, dans ce cas, une liste de User
     * Peut être utilisé pour afficher des images, données dans un tableau etc...
     */

    // todo: Supprimer le userClickListener et garder seulement la userList, se baser sur l'exemple d'alex
    public List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView emailTextView;

        public UserViewHolder(@NonNull View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameEditText);
            emailTextView = view.findViewById(R.id.emailEditText);
        }

        public void bind(final User user, final UserClickListener userClickListener) {
            nameTextView.setText(user.getName());
            emailTextView.setText(user.getEmail());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userClickListener.onUserClicked(user);
                }
            });
        }
    }

    public interface UserClickListener {
        void onUserClicked(User user);
    }
}