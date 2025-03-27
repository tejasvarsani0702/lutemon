package com.lutemon.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.lutemon.game.R;
import com.lutemon.game.model.Lutemon;
import com.lutemon.game.model.Storage;
import java.util.HashMap;
import java.util.Map;

public class StastisticFragment extends Fragment {
    private TextView txtGeneralStats, txtLutemonStats;
    private Storage storage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        txtGeneralStats = view.findViewById(R.id.txtGeneralStats);
        txtLutemonStats = view.findViewById(R.id.txtLutemonStats);

        storage = Storage.getInstance(getContext()); // Get storage instance

        displayGeneralStatistics();
        displayLutemonPerformance();
        return view;
    }

    private void displayGeneralStatistics() {
        String stats = "Total Lutemons Created: " + storage.getHome().size() + "\n" +
                "Total Battles: " + storage.getTotalBattles() + "\n" +
                "Total Training Sessions: " + storage.getTotalTrainingSessions();
        txtGeneralStats.setText(stats);
    }

    private void displayLutemonPerformance() {
        StringBuilder performanceText = new StringBuilder();
        HashMap<Integer, Lutemon> lutemons = storage.getLutemonMap(); // Get all Lutemons

        for (Map.Entry<Integer, Lutemon> entry : lutemons.entrySet()) {
            Lutemon lutemon = entry.getValue();
            performanceText.append(lutemon.getName())
                    .append(" (").append(lutemon.getColor()).append(")")
                    .append("\nAttack: ").append(lutemon.getAttack())
                    .append(", Defense: ").append(lutemon.getDefense())
                    .append(", Health: ").append(lutemon.getHealth()).append("/")
                    .append(lutemon.getMaxHealth())
                    .append("\nExperience: ").append(lutemon.getExperience())
                    .append("\n\n");
        }
        txtLutemonStats.setText(performanceText.toString());
    }
}
