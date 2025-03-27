package com.lutemon.game.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private static Storage instance;
    private HashMap<Integer, Lutemon> lutemonMap = new HashMap<>();
    private ArrayList<Lutemon> home = new ArrayList<>();
    private ArrayList<Lutemon> training = new ArrayList<>();
    private ArrayList<Lutemon> battle = new ArrayList<>();
    private int totalBattles = 0;
    private int totalTrainingSessions = 0;
    private int totalWins = 0;
    private int totalLosses = 0;
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    private Storage(Context context) {
        sharedPreferences = context.getSharedPreferences("LutemonStorage", Context.MODE_PRIVATE);
        loadData();
    }

    public static Storage getInstance(Context context) {
        if (instance == null) {
            instance = new Storage(context);
        }
        return instance;
    }

    // ✅ Save all data (Lutemons & Statistics)
    public void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lutemons", gson.toJson(lutemonMap));
        editor.putString("home", gson.toJson(home));
        editor.putString("training", gson.toJson(training));
        editor.putString("battle", gson.toJson(battle));
        editor.putInt("totalBattles", totalBattles);
        editor.putInt("totalTrainingSessions", totalTrainingSessions);
        editor.putInt("totalWins", totalWins);
        editor.putInt("totalLosses", totalLosses);
        editor.apply();
    }

    // ✅ Load all data when the app starts
    public void loadData() {
        String lutemonsJson = sharedPreferences.getString("lutemons", "");
        String homeJson = sharedPreferences.getString("home", "");
        String trainingJson = sharedPreferences.getString("training", "");
        String battleJson = sharedPreferences.getString("battle", "");

        if (!lutemonsJson.isEmpty()) {
            Type type = new TypeToken<HashMap<Integer, Lutemon>>() {}.getType();
            lutemonMap = gson.fromJson(lutemonsJson, type);
        }

        if (!homeJson.isEmpty()) {
            Type type = new TypeToken<ArrayList<Lutemon>>() {}.getType();
            home = gson.fromJson(homeJson, type);
        }

        if (!trainingJson.isEmpty()) {
            Type type = new TypeToken<ArrayList<Lutemon>>() {}.getType();
            training = gson.fromJson(trainingJson, type);
        }

        if (!battleJson.isEmpty()) {
            Type type = new TypeToken<ArrayList<Lutemon>>() {}.getType();
            battle = gson.fromJson(battleJson, type);
        }

        totalBattles = sharedPreferences.getInt("totalBattles", 0);
        totalTrainingSessions = sharedPreferences.getInt("totalTrainingSessions", 0);
        totalWins = sharedPreferences.getInt("totalWins", 0);
        totalLosses = sharedPreferences.getInt("totalLosses", 0);
    }

    // ✅ Track battles, training, and outcomes
    public void incrementBattles() {
        totalBattles++;
        saveData();
    }

    public void incrementTraining() {
        totalTrainingSessions++;
        saveData();
    }

    public void incrementWins() {
        totalWins++;
        saveData();
    }

    public void incrementLosses() {
        totalLosses++;
        saveData();
    }

    // ✅ Manage Lutemons
    public void addLutemon(Lutemon lutemon) {
        lutemonMap.put(lutemon.getId(), lutemon);
        home.add(lutemon);
        saveData();
    }

    public void moveToTraining(Lutemon lutemon) {
//        home.remove(lutemon);
        training.add(lutemon);
        saveData();
    }

    public void moveToBattle(Lutemon lutemon) {
//        home.remove(lutemon);
        battle.add(lutemon);
        saveData();
    }

    public void moveToHome(Lutemon lutemon) {
        training.remove(lutemon);
        battle.remove(lutemon);
//        if (!home.contains(lutemon)) {
//            home.add(lutemon);
//        }
        saveData();
    }

    public void removeLutemon(int id) {
        Lutemon lutemon = lutemonMap.remove(id);
//        home.remove(lutemon);
        training.remove(lutemon);
        battle.remove(lutemon);
        saveData();
    }

    // ✅ Getters for Statistics
    public int getTotalBattles() { return totalBattles; }
    public int getTotalTrainingSessions() { return totalTrainingSessions; }
    public int getTotalWins() { return totalWins; }
    public int getTotalLosses() { return totalLosses; }

    // ✅ Getters for Lutemon Lists
    public ArrayList<Lutemon> getHome() { return home; }
    public ArrayList<Lutemon> getTraining() { return training; }
    public ArrayList<Lutemon> getBattle() { return battle; }

    public HashMap<Integer, Lutemon> getLutemonMap() {
        return lutemonMap;
    }
}
