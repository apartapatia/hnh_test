package org.example.entity;

public class Player extends Entity {

    private int rapidHealth;

    public Player(int health, int armor, int attack, int damageMin, int damageMax) {
        setArmor(armor);
        setAttack(attack);
        setHealth(health);
        setDamageValues(damageMin, damageMax);
        this.rapidHealth = 0;
    }

    public void increaseHealth() {
        final int MAX_HEALTH_INCREASES = 4;

        if (rapidHealth < MAX_HEALTH_INCREASES) {
            int currentHealth = getHealth();
            int healthIncrease = (int) (currentHealth * 0.3);
            setHealth(currentHealth + healthIncrease);
            rapidHealth++;
        } else {
            System.out.println("Health increase limit reached.");
        }
    }

}
