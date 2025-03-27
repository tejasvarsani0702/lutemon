package com.lutemon.game.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lutemon.game.R;
import com.lutemon.game.model.Lutemon;
import com.lutemon.game.model.Storage;

import java.util.ArrayList;

public class BattleFragment extends Fragment {
    private TextView txtBattleLog;
    private Button btnNextAttack;
    private ImageView imgLutemon1, imgLutemon2;
    private ArrayList<Lutemon> battleLutemons;
    private Handler handler = new Handler();
    private Storage storage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        txtBattleLog = view.findViewById(R.id.txtBattleLog);
        imgLutemon1 = view.findViewById(R.id.imgLutemon1);
        imgLutemon2 = view.findViewById(R.id.imgLutemon2);
        btnNextAttack = view.findViewById(R.id.btnNextAttack);

        storage = Storage.getInstance(getContext());
        battleLutemons = storage.getBattle();

        setView();

        btnNextAttack.setOnClickListener(v -> startBattle());
        return view;
    }

    void setView() {
        if (battleLutemons.size() < 2) {
            txtBattleLog.setText("Not enough Lutemons for battle!");
            return;
        }

        Lutemon lutemon1 = battleLutemons.get(0);
        Lutemon lutemon2 = battleLutemons.get(1);

        imgLutemon1.setImageResource(lutemon1.getImageResId());
        imgLutemon2.setImageResource(lutemon2.getImageResId());
    }

    private void startBattle() {
        if (battleLutemons.size() < 2) {
            txtBattleLog.setText("Not enough Lutemons for battle!");
            return;
        }
        Lutemon lutemon1 = battleLutemons.get(0);
        Lutemon lutemon2 = battleLutemons.get(1);
        storage.incrementBattles();
        battleLoop(lutemon1, lutemon2);
    }

    private void battleLoop(Lutemon attacker, Lutemon defender) {
        if (!attacker.isAlive() || !defender.isAlive()) {
            endBattle(attacker, defender);
            return;
        }

        int damage = attacker.attack(defender);
        txtBattleLog.append("\n" + attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage!");

        handler.postDelayed(() -> {
            battleLoop(defender, attacker);
        }, 1000);
    }

    private void endBattle(Lutemon lutemon1, Lutemon lutemon2) {
        Lutemon winner = lutemon1.isAlive() ? lutemon1 : lutemon2;
        txtBattleLog.append("\nWinner: " + winner.getName());
        winner.train();

        storage.incrementWins();
        storage.incrementLosses();

        Lutemon loser = (lutemon1.isAlive()) ? lutemon2 : lutemon1;
        txtBattleLog.append("\n" + loser.getName() + " has been defeated and returns home.");
        loser.heal();
        storage.moveToHome(loser);
    }
}
