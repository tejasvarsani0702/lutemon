package com.lutemon.game.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lutemon.game.R;
import com.lutemon.game.model.Lutemon;
import com.lutemon.game.model.Storage;

import java.util.ArrayList;

public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.ViewHolder> {
    private ArrayList<Lutemon> lutemons;
    private Storage storage;

    public LutemonAdapter(Storage storage,ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        this.storage = storage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lutemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.name.setText(lutemon.getName());
        holder.stats.setText("ATK: " + lutemon.getAttack() + " DEF: " + lutemon.getDefense() + " HP: " + lutemon.getHealth());
        holder.imageView.setImageResource(lutemon.getImageResId()); // New: Set image

        holder.btnTrain.setOnClickListener(v -> {
            storage.moveToTraining(lutemon);
            notifyDataSetChanged();
        });

        holder.btnBattle.setOnClickListener(v -> {
            storage.moveToBattle(lutemon);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() { return lutemons.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, stats;
        ImageView imageView; // New: ImageView
        Button btnTrain, btnBattle;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            stats = itemView.findViewById(R.id.txtStats);
            imageView = itemView.findViewById(R.id.imgLutemon); // New: ImageView
            btnTrain = itemView.findViewById(R.id.btnTrain);
            btnBattle = itemView.findViewById(R.id.btnBattle);
        }
    }
}
