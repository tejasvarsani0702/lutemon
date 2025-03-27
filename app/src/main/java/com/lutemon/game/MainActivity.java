package com.lutemon.game;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lutemon.game.adapter.LutemonAdapter;
import com.lutemon.game.fragments.BattleFragment;
import com.lutemon.game.fragments.HomeFragment;
import com.lutemon.game.fragments.StastisticFragment;
import com.lutemon.game.fragments.TrainingFragment;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LutemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Load the HomeFragment by default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_training) {
                    selectedFragment = new TrainingFragment();
                } else if (itemId == R.id.nav_battle) {
                    selectedFragment = new BattleFragment();
                } else if (itemId == R.id.nav_statistics) {
                    selectedFragment = new StastisticFragment();
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            };
}
