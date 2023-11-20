package com.example.warringnationsbutgood;
import java.util.*;

public class Player {
    private boolean isAlive = true;
    private boolean attackFailed;

    private final Map<String, String> stages = Map.of(
            "Arithmetic", "#00FFFF",
            "Geometry", "#FFFF00",
            "Algebruh", "#ADFF2F",
            "Calculus", "#FF0000",
            "Abstract", "#FF00FF"
    );

    private final Map<String, int[]> manaCalculation = Map.of(
            "Arithmetic", new int[] {0, 9},
            "Geometry", new int[] {15, 14},
            "Algebruh", new int[] {35, 19},
            "Calculus", new int[] {60, 29},
            "Abstract", new int[] {90, 39}
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

    private int health, attack, defense, mana, totalMana, statsFactor;
    private String stage, name, currentStageColor, status, currentStatusColor;

    public Player(int hitpoints, String stage, String name) {
        this.health = hitpoints;
        this.stage = stage;
        this.name = name;
        this.status = "SAFE";
        this.totalMana = manaCalculation.get(stage)[0];
        this.currentStageColor = stages.get(stage);
        setStatusColor();
    }

    private void changeStatus() {
        setStatusColor();
    }

    private void setStatusColor() { currentStatusColor = statuses.get(status); }

    public void bonusMana(int bonus) { totalMana += bonus; }

    public void setHitpoints(int healthLost) {
        this.health -= healthLost;

        if (this.health < 0) {
            isAlive = false;
        }
    }

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

    private void attackPlayer(Player other) {
        if (attack > other.getDefense()) {
            other.setHitpoints(attack - other.getDefense());
            bonusMana(statsFactor / 4);
            attackFailed = false;
        } else {
            other.bonusMana(statsFactor / 4);
            attackFailed = true;
        }
    }

    private void defend() { defense = getDefense() * 2; }

    private void useMana() {
        totalMana += this.mana;
        List<Integer> manaThreshold = new ArrayList<>();
        //After mana is added, check if they move up a stage

        for (int[] values : manaCalculation.values()) {
            manaThreshold.add(values[1]);
        }

        Collections.sort(manaThreshold);

        for (int i = manaThreshold.size() - 1; i > 0; i--) {
            if (totalMana > manaThreshold.get(i)) {
                stage = stageNames[i];
                totalMana = manaCalculation.get(stage)[0];
                currentStageColor = stages.get(stage);
            }
        }
    }

    public void generate() {
        statsFactor = manaCalculation.get(stage)[1];

        //girl math
        this.attack = (int) (Math.random() * statsFactor) + statsFactor / 5;
        this.defense = (int) (Math.random() * statsFactor) + statsFactor / 5;
        this.mana = (int) (Math.random() * statsFactor / 2) + statsFactor / 6;
    }




}
