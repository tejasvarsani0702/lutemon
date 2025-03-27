package com.lutemon.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lutemon.game.model.Lutemon;
import com.lutemon.game.R;
import com.lutemon.game.model.Storage;

import java.util.ArrayList;

public class TrainingFragment extends Fragment {
    private TextView txtTrainingLog;
    private Button btnTrain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        txtTrainingLog = view.findViewById(R.id.txtTrainingLog);
        btnTrain = view.findViewById(R.id.btnTrain);
        btnTrain.setOnClickListener(v -> trainLutemons());
        return view;
    }

    private void trainLutemons() {
        ArrayList<Lutemon> trainingLutemons = Storage.getInstance(getContext()).getTraining();

        for (Lutemon lutemon : trainingLutemons) {
            lutemon.train();
            txtTrainingLog.append("\n" + lutemon.getName() + " gained experience!");
        }

    }
}
