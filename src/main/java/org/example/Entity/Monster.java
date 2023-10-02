package org.example.Entity;

import java.util.Random;

public class Monster extends Entity {

    public Monster(int health, int armor, int attack, int damageMin, int damageMax) {
        setArmor(armor);
        setAttack(attack);
        setHealth(health);
        setDamageValues(damageMin, damageMax);
    }

    public Monster() {
        randomMonster();
    }

    public void randomMonster() {
        Random random = new Random();

        int attackRandom = 1 + random.nextInt(30);
        setAttack(attackRandom);

        int armorRandom = 1 + random.nextInt(30);
        setArmor(armorRandom);

        int healthRandom = 1 + random.nextInt(1000);
        setHealth(healthRandom);

        int minRapidDamageRandom = 1 + random.nextInt(10);
        int maxRapidDamageRandom = minRapidDamageRandom + random.nextInt(30);
        setDamageValues(minRapidDamageRandom, maxRapidDamageRandom);
    }

}
