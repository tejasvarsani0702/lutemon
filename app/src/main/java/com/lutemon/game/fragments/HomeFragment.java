package com.lutemon.game.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lutemon.game.CreateLutemonActivity;
import com.lutemon.game.adapter.LutemonAdapter;
import com.lutemon.game.model.Storage;
import com.lutemon.game.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private LutemonAdapter adapter;
    private Storage instance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        instance = Storage.getInstance(getContext());
        adapter = new LutemonAdapter(instance,instance.getHome());
        recyclerView.setAdapter(adapter);

        Button btnCreateLutemon = view.findViewById(R.id.btnCreateLutemon);
        btnCreateLutemon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateLutemonActivity.class);
            someActivityResultLauncher.launch(intent);
        });

        return view;
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        adapter = new LutemonAdapter(instance,instance.getHome());
                        recyclerView.setAdapter(adapter);
                    }
                }
            });

}
