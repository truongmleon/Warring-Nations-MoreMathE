package com.example.warringnationsbutgood;

public class Player {
    private boolean isAlive = true;
    private boolean attackFailed;
    private final String[] stages = {"Arithmetic", "Geometry", "Algebruh", "Calculus", "Abstract"};
    private final String[] stageColors = {"#00FFFF", "#FFFF00", "#ADFF2F", "#FF0000", "#FF00FF"};
    private final String[] statuses = {"SAFE", "GAINED", "FAILED", "ATTACKED", "DEFENDED", "SUCCESS"};
    private final String[] statusColors = {"#00FFFF", "#FFC0CB", "#FF0000", "#FF0000", "#FFFF00", "#ADFF2F"};
    private final int[] manaForStage = {15, 35, 60, 90};
    private final int[] factors = {9, 14, 19, 29, 39};
    private int health, attack, defense, mana, totalMana, statsFactor;
    private String stage, name, currentStageColor, status, currentStatusColor;

    public Player(int hitpoints, String stage, String name) {
        this.health = hitpoints;
        this.stage = stage;
        this.name = name;
        this.status = "SAFE";

        if (!stage.equals("Arithmetic")) {
            for (int i = 1; i < stages.length; i++) {
                if (stage.equals(stages[i])) {
                    this.totalMana = manaForStage[i - 1];
                    this.currentStageColor = stageColors[i];
                    break;
                }
            }
        } else {
            this.currentStageColor = stageColors[0];
        }

        setStatusColor();
    }

    private void changeStatus() {
        setStatusColor();
    }

    private void setStatusColor() {
        for (int i = 0; i < statuses.length; i++) {
            if (statuses[i].equals(status)) {
                this.currentStatusColor = statusColors[i];
            }
        }
    }

    public void bonusMana(int bonus) { this.totalMana += bonus; }

    public void setHitpoints(int healthLost) {
        this.health -= healthLost;

        if (this.health < 0) {
            isAlive = false;
        }
    }

    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getMana() { return this.mana; }
    public int getTotalMana() { return this.totalMana; }
    public int getHealth() { return this.health; }
    public String getStage() { return  this.stage; }
    public String getStatus() { return this.status; }
    public String getCurrentStageColor() { return this.currentStageColor; }
    public String getCurrentStatusColor() { return this.currentStatusColor; }

    private void attackPlayer(Player other) {
        if (this.attack > other.getDefense()) {
            other.setHitpoints(attack - other.getDefense());
            this.bonusMana(statsFactor / 4);
            attackFailed = false;
        } else {
            other.bonusMana(statsFactor / 4);
            attackFailed = true;
        }
    }

    private void defend() { this.defense = this.getDefense() * 2; }

    private void useMana() {
        this.totalMana += this.mana;

        //After mana is added, check if they move up a stage
        for (int i = manaForStage.length - 1; i > -1; i--) {
            if (manaForStage[i] < this.totalMana) {
                this.stage = stages[i + 1];
                break;
            }
        }
    }

    public void generate() {
        statsFactor = 0;

        //Checks for current stage for generating stats
        for (int i = 0; i < stages.length; i++) {
            if (stage.equals(stages[i])) {
                statsFactor = factors[i];
                break;
            }
        }

        this.attack = (int) (Math.random() * statsFactor) + statsFactor / 5;
        this.defense = (int) (Math.random() * statsFactor) + statsFactor / 5;
        this.mana = (int) (Math.random() * statsFactor / 2) + statsFactor / 6;
    }




}
