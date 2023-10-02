package org.example;

import org.example.entity.Monster;
import org.example.entity.Player;

import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        System.out.println("Welcome to the text-based JRPG!!" +
                "\nLet's create your character");
        Scanner scanner = new Scanner(System.in);
        int attack;
        int armor;
        int health;
        int damageMin;
        int damageMax;
        System.out.print("Enter your character's attack value : ");
        attack = scanner.nextInt();
        while (attack < 1 || attack > 30) {
            System.out.println("The value should be between 1 and 30!");
            attack = scanner.nextInt();

        }
        System.out.print("Enter your character's armor value : ");
        armor = scanner.nextInt();
        while (armor < 1 || armor > 30) {
            System.out.println("The value should be between 1 and 30!");
            armor = scanner.nextInt();
        }
        System.out.print("Enter your character's health value : ");
        health = scanner.nextInt();
        System.out.print("Enter your character's minimum damage value : ");
        damageMin = scanner.nextInt();
        System.out.print("Enter your character's maximum damage value : ");
        damageMax = scanner.nextInt();
        while (damageMax <= damageMin) {
            System.out.println("The value must be greater than the minimum!");
            damageMax = scanner.nextInt();
        }

        Player player = new Player(health, armor, attack, damageMin, damageMax);
        Monster monster = new Monster();

        label:
        while (true) {
            System.out.println("""

                    Enter '1' to attack a monster or '2' to heal
                    or 'q' to quit the game :\s""");

            String userInput = scanner.next();

            switch (userInput) {
                case "1":
                    System.out.println("You attack the monster!");
                    System.out.println(ANSI_RED + "Monster Characteristics: " +
                            monster.getAttack() + " attack, " +
                            monster.getArmor() + " armor, " +
                            monster.getHealth() + " health." +
                            ANSI_RESET);

                    player.attackEntity(monster);

                    System.out.println(ANSI_RED + "Monster characteristics after an attack: " +
                            monster.getAttack() + " attack, " +
                            monster.getArmor() + " armor, " +
                            monster.getHealth() + " health." +
                            ANSI_RESET);

                    if (monster.getHealth() <= 0) {
                        System.out.println("The monster has been defeated!");
                        monster = new Monster();
                    } else {
                        System.out.println("Monster attack you!");
                        monster.attackEntity(player);
                        if (player.getHealth() <= 0) {
                            System.out.println("You've lost! Game over.");
                            break label;
                        }
                    }
                    System.out.println(ANSI_BLUE + "Your current health: "
                            + player.getHealth() + ANSI_RESET);
                    break;
                case "2":
                    player.increaseHealth();
                    System.out.println(ANSI_BLUE + "Your current health: "
                            + player.getHealth() + ANSI_RESET);
                    break;
                case "q":
                    System.out.println("Game over.");
                    break label;
                default:
                    System.out.println("Incorrect command. Try again.");
                    break;
            }
        }
    }
}