package com.example.prova1clenio;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PresenceAdapter extends RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder> {

    private List<User> userList;

    public PresenceAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_presence, parent, false);
        return new PresenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userNameTextView.setText(user.getNome());

        updateButtonState(holder.presenceButton, user.isPresent());

        holder.presenceButton.setOnClickListener(v -> {
            boolean isPresent = !user.isPresent();
            user.setPresent(isPresent);
            updateButtonState(holder.presenceButton, isPresent);
        });
    }

    private void updateButtonState(Button button, boolean isPresent) {
        if (isPresent) {
            button.setText("Presente");
            button.setBackgroundColor(Color.GREEN);
        } else {
            button.setText("Falto");
            button.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class PresenceViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        Button presenceButton;

        public PresenceViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            presenceButton = itemView.findViewById(R.id.presenceButton);
        }
    }
}
