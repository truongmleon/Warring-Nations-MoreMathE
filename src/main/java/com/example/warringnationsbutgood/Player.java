package com.example.warringnationsbutgood;

public class Player {
    private boolean isAlive = true;
    private String[] stages = {"Arithmetic", "Geometry", "Algebruh", "Calculus", "Abstract"};
    private int[] manaForStage = {15, 35, 60, 90};
    private int[] factors = {9, 14, 19, 29, 39};
    private int health;
    private String stage;
    private String name;
    private String email;

    private int attack;
    private int defense;
    private int mana;
    private int totalMana;

    private int statsFactor = 0;

    private boolean attackFailed;

    public Player(int hitpoints, String stage, String name, String email) {
        this.health = hitpoints;
        this.stage = stage;
        this.name = name;
        this.email = email;

        if (!stage.equals("Arithmetic")) {
            for (int i = 1; i < stages.length; i++) {
                if (stage.equals(stages[i])) {
                    this.totalMana = manaForStage[i - 1];
                    break;
                }
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
    public int getHealth() { return this.health; }


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

    private void generate() {
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
