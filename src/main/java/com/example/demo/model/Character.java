package com.example.demo.model;

import java.util.List;

public class Character implements Being {

    // TODO: Use lombok and records. Also, think about Classes and skills for this one
    public Character() {

    }
    public Character(Integer level, Integer strength, Integer agility, Integer vitality, Integer energy) {
        this.level = level;
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
        this.energy = energy;
    }

    private Integer strength;

    private Integer agility;

    private Integer vitality;

    private Integer energy;

    private Integer level;

    private Long hp;

    private Long ag;

    private Item equippedArmor;

    private List<Item> inventory;

    @Override
    public void move() {

    }

    @Override
    public Integer getPiercingDamageRate() {
        return null;
    }

    @Override
    public Integer getCriticalDamageRate() {
        return equippedArmor.hasLuck() ? 5 : 0;
    }

    @Override
    public Integer getExcellentDamageRate() {
        return null;
    }

    @Override
    public Integer getDoubleDamageRate() {
        return null;
    }

    @Override
    public Integer getTripleDamageRate() {
        return null;
    }

    @Override
    public Long getDamagePower() {
        return strength + (level * 20L);
    }

    @Override
    public Long getDefensivePower() {
        return agility + (level * 20L);
    }

    @Override
    public Long getHP() {
        return null;
    }

    @Override
    public void takeDamage(Long damage) {
        this.hp = this.hp - damage;

        if (hp < 0) {
            die();
        }
    }

    @Override
    public void die() {

    }
}
