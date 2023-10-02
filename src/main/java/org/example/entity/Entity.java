package org.example.entity;

import org.example.logging.LoggerSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class Entity {
    private int attack;
    private int armor;
    private int health;
    private final List<Integer> damageValues = new ArrayList<>();
    private final Logger logger = LoggerSingleton.getInstance();

    public void setAttack(int attackValue) {
        if (attackValue < 1 || attackValue > 30) {
            logger.log(Level.WARNING, "Incorrect attack value");
        } else {
            this.attack = attackValue;
        }
    }

    public void setArmor(int armorValue) {
        if (armorValue < 1 || armorValue > 30) {
            logger.log(Level.WARNING, "Incorrect armor value");
        } else {
            this.armor = armorValue;
        }
    }

    public void setDamageValues(int minDamage, int maxDamage) {
        for (int i = minDamage; i <= maxDamage; i++) {
            this.damageValues.add(i);
        }
    }

    public void setHealth(int healthValue) {
        this.health = healthValue;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public List<Integer> getDamageValues() {
        return damageValues;
    }

    public void attackEntity(Entity defendEntity) {
        int attackModifier = calculateAttackModifier(defendEntity);
        System.out.println("The creature will strike " + attackModifier + " attacks");
        Random random = new Random();
        for (int i = 0; i < attackModifier; i++) {
            int dieRoll = rollDie();
            if (dieRoll == 5 || dieRoll == 6) {
                int damageIndex = random.nextInt(this.getDamageValues().size());
                int damage = this.getDamageValues().get(damageIndex);
                System.out.println("Applied " + damage + " damage!");
                int newHealth = Math.max(defendEntity.getHealth() - damage, 0);
                defendEntity.setHealth(newHealth);
            } else {
                System.out.println("Miss!");
            }
        }
    }

    public int calculateAttackModifier(Entity defendEntity) {
        int calculateAttackModifier = this.attack - defendEntity.getArmor() + 1;
        if (calculateAttackModifier <= 0) {
            return 1;
        } else {
            return calculateAttackModifier;
        }
    }

    public int rollDie() {
        Random random = new Random();
        return 1 + random.nextInt(6);
    }
}
