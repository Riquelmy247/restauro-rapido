package com.example.prova1clenio;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserEnviarAdapter extends RecyclerView.Adapter<UserEnviarAdapter.UserViewHolder> {
    private List<User> users;
    private UserRepository userRepository;
    private Context context;

    public UserEnviarAdapter(Context context, List<User> users, UserRepository userRepository) {
        this.context = context;
        this.users = users;
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewUserName.setText(user.getNome());
        holder.pointsTextView.setText(String.valueOf(user.getPontos()));

        holder.buttonEnviar.setOnClickListener(v -> {
            int currentPoints = user.getPontos();
            int newPoints = Math.max(currentPoints - 10, 0);

            userRepository.updateUserPoints(user.getNome(), newPoints);

            user.setPontos(newPoints);
            showConfirmationDialog(user.getNome());

            notifyDataSetChanged();
        });
    }


    public void updateUsers(List<User> newUsers) {
        this.users.clear();
        this.users.addAll(newUsers);
        notifyDataSetChanged();
    }

    private void showConfirmationDialog(String userName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmado")
                .setMessage("Semente enviada para " + userName + "!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(context, UserListEnviarActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName, pointsTextView;
        Button buttonEnviar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            pointsTextView = itemView.findViewById(R.id.pointsTextView);
            buttonEnviar = itemView.findViewById(R.id.buttonEnviar);
        }
    }
}
