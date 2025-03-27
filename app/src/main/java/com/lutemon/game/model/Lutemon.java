package com.lutemon.game.model;

import java.io.Serializable;

public class Lutemon implements Serializable {
    private static int idCounter = 1;
    private int id;
    private String color;
    private int attack, defense, health, maxHealth, experience;
    private int imageResId; // Image resource
    private String name;


    public Lutemon(String name, String color, int attack, int defense, int health, int imageResId) {
        this.id = idCounter++;
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.health = this.maxHealth = health;
        this.experience = 0;
        this.imageResId = imageResId;
    }

    public void train() {
        experience++;
        attack++;
    }

    public void heal() {
        this.health = maxHealth;
    }

    public int attack(Lutemon opponent) {
        int randomBoost = (int) (Math.random() * 3); // Random attack boost 0-2
        int damage = Math.max(0, (this.attack + randomBoost) - opponent.defense);
        opponent.health -= damage;
        return damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getImageResId() { return imageResId; }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getExperience() {
        return experience;
    }
}
