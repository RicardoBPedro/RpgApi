package com.mmorpg.model;

import com.mmorpg.model.interfaces.Being;

import java.util.List;

public class Mob implements Being {

    // TODO: Use lombok and records. Also, think about at least skills for this one

    public Mob() {

    }

    public Mob(Integer level, Integer damage, Integer defense) {
        this.level = level;
        this.damage = damage;
        this.defense = defense;
    }

    private Long hp;

    private Integer level;

    private Integer damage;

    private Integer defense;

    private Location location;

    private List<Item> dropList;

    @Override
    public void move() {

    }

    @Override
    public Integer getPiercingDamageRate() {
        return null;
    }

    @Override
    public Integer getCriticalDamageRate() {
        return null;
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
    public void die() {

    }

    @Override
    public Long getDamagePower() {
        return damage + (level * 20L);
    }

    @Override
    public Long getDefensivePower() {
        return defense + (level * 20L);
    }

    @Override
    public Long getHP() {
        return this.hp;
    }

    @Override
    public void takeDamage(Long damage) {
        this.hp = this.hp - damage;

        if (hp < 0) {
            die();
        }
    }
}
