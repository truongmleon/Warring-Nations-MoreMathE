package com.example.warringnationsbutgood;
import java.util.*;

public class Player {
    private final Map<String, String> stages = Map.of(
            "Arithmetic", "#00FFFF",
            "Geometry", "#FFFF00",
            "Algebruh", "#ADFF2F",
            "Calculus", "#FF0000",
            "Abstract", "#FF00FF"
    );

    // {mana required to be in stage, stat factor, boost mana}
    private final Map<String, int[]> manaCalculation = Map.of(
            "Arithmetic", new int[] {0, 9, 1},
            "Geometry", new int[] {12, 14, 2},
            "Algebruh", new int[] {30, 19, 3},
            "Calculus", new int[] {60, 29, 4},
            "Abstract", new int[] {90, 39, 5}
    );

    private final Map<String, String> statuses = Map.of(
            "SAFE", "#00FFFF",
            "GAINED", "#FFC0CB",
            "FAILED", "#FF0000",
            "ATTACKED", "#FF0000",
            "DEFENDED", "#FFFF00",
            "SUCCESS", "#ADFF2F"
    );

    private final String[] stageNames = {"Arithmetic", "Geometry", "Algebruh", "Calculus", "Abstract"};
    private int health, attack, defense, mana, totalMana, statsFactor, id;
    private String stage, name, currentStageColor, status, currentStatusColor;

    private boolean boostUsed = false;

    public Player(int id, int hitpoints, String stage, String name) {
        this.id = id;
        this.health = hitpoints;
        this.stage = stage;
        this.name = name;
        this.status = "SAFE";
        this.totalMana = manaCalculation.get(stage)[0];
        this.currentStageColor = stages.get(stage);
        setStatusColor();
    }

    private void setStatusColor() { currentStatusColor = statuses.get(status); }

    public void bonusMana(int bonus) { totalMana += bonus; }

    public void setHitpoints(int healthLost) { this.health -= healthLost; }

    public int getId() { return id; };

    public int getAttack() { return attack; }

    public int getDefense() { return defense; }

    public int getMana() { return mana; }

    public int getTotalMana() { return totalMana; }

    public int getHealth() { return health; }

    public String getStage() { return stage; }

    public String getStatus() { return status; }

    public String getCurrentStageColor() { return currentStageColor; }

    public String getCurrentStatusColor() { return currentStatusColor; }

    public String getName() { return this.name; }

    public void attackPlayer(Player other) {
        if (attack > other.getDefense()) {
            other.setHitpoints(attack - other.getDefense());
            bonusMana(statsFactor / 4);
            status = "SUCCESS";

            if (other.getHealth() <= 0) other.status = "DEAD";
            else other.status = "ATTACKED";

            setStatusColor();
            other.setStatusColor();
        } else {
            status = "FAILED";
            other.status = "DEFENDED";
            setStatusColor();
            other.setStatusColor();
        }
    }

    public void defend() {
        defense = getDefense() * 2;
        status = "SAFE";
        setStatusColor();
    }

    public void useMana() {
        totalMana += getMana();
        List<Integer> manaThreshold = new ArrayList<>();

        //After mana is added, check if they move up a stage
        for (int[] values : manaCalculation.values()) {
            manaThreshold.add(values[0]);
        }

        Collections.sort(manaThreshold);

        for (int i = manaThreshold.size() - 1; i > 0; i--) {
            if (totalMana > manaThreshold.get(i)) {
                stage = stageNames[i];
                currentStageColor = stages.get(stage);
                break;
            }
        }

        status = "GAINED";
        setStatusColor();
    }

    public void generate() {
        //girl math
        boostUsed = false;
        statsFactor = manaCalculation.get(stage)[1];
        attack = (int) (Math.random() * statsFactor) + statsFactor / 5;
        defense = (int) (Math.random() * statsFactor) + 2;
        mana = (int) (Math.random() * statsFactor / 1.2) + statsFactor / 6;
    }

    public void boost() {
        if (!boostUsed) {
            int extra = manaCalculation.get(stage)[2];
            attack += extra;
            defense += extra;
            mana += extra;
            boostUsed = true;
        }
    }

    public String toString() { return name; }
}
