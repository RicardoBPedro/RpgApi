package com.example.demo.service;

import com.example.demo.model.Damage;
import com.example.demo.model.DamageType;
import com.example.demo.model.interfaces.Battleble;

import java.util.Random;

public class DamageService {

    // TODO: improve and test it
    public Long takeDamage(Damage damage, Battleble target) {
        target.takeDamage(damage.getDamage());
        return target.getHP();
    }

    public Damage calculateDamage(Battleble attacker, Battleble defender) {
        Damage damage = new Damage();
        damage.setDamageType(DamageType.BASIC);
        long result = attacker.getDamagePower() - defender.getDefensivePower();

        Long criticalDamageBonus = getCriticalDamageBonus(attacker);
        if (criticalDamageBonus > 0) {
            damage.setDamageType(DamageType.CRITICAL);
            result = result + criticalDamageBonus;
        }

        Long piercingDamageBonus = getPiercingDamageBonus(attacker, defender);
        if (piercingDamageBonus > 0) {
            damage.setDamageType(DamageType.PIERCING);
            result = result + piercingDamageBonus;
        }

        Long excellentDamageBonus = getExcellentDamageBonus(attacker);
        if (excellentDamageBonus > 0) {
            damage.setDamageType(DamageType.EXCELLENT);
            result = result + excellentDamageBonus;
        }

        Long doubleDamageBonus = getDoubleDamageBonus(attacker);
        if (doubleDamageBonus > 0) {
            damage.setDamageType(DamageType.DOUBLE);
            result = result + doubleDamageBonus;
        }

        Long tripleDamageBonus = getTripleDamageBonus(attacker);
        if (tripleDamageBonus > 0) {
            damage.setDamageType(DamageType.TRIPLE);
            result = result + tripleDamageBonus;
        }

        setDamage(damage, result);
        return damage;
    }

    private int getPercentage() {
        Random random = new Random();
        return random.nextInt(100);
    }

    private void setDamage(Damage damage, Long value) {
        if (value <= 0) {
            damage.setDamageType(DamageType.MISS);
            damage.setDamage(0L);
        } else {
            damage.setDamage(value);
        }
    }

    // 25% Damage bonuses
    private Long getCriticalDamageBonus(Battleble attacker) {
        long criticalDamage = 0L;
        Integer criticalDamageRate = attacker.getCriticalDamageRate();

        if (getPercentage() < criticalDamageRate) {
            criticalDamage = attacker.getDamagePower() / 4L;
        }

        return criticalDamage;
    }

    // Ignore defender defense
    private Long getPiercingDamageBonus(Battleble attacker, Battleble defender) {
        long piercingDamage = 0L;
        Integer piercingDamageRate = attacker.getPiercingDamageRate();

        if (getPercentage() < piercingDamageRate) {
            piercingDamage = defender.getDefensivePower();
        }

        return piercingDamage;
    }

    // 50% Damage bonuses
    private Long getExcellentDamageBonus(Battleble attacker) {
        long excellentDamage = 0L;
        Integer excellentDamageRate = attacker.getExcellentDamageRate();

        if (getPercentage() < excellentDamageRate) {
            excellentDamage = attacker.getDamagePower() / 2L;
        }

        return excellentDamage;
    }

    // 200% Damage bonuses
    private Long getDoubleDamageBonus(Battleble attacker) {
        long doubleDamage = 0L;
        Integer doubleDamageRate = attacker.getDoubleDamageRate();

        if (getPercentage() < doubleDamageRate) {
            doubleDamage = attacker.getDamagePower();
        }

        return doubleDamage;
    }

    // 300% Damage bonuses
    private Long getTripleDamageBonus(Battleble attacker) {
        long tripleDamage = 0L;
        Integer tripleDamageRate = attacker.getTripleDamageRate();

        if (getPercentage() < tripleDamageRate) {
            tripleDamage = attacker.getDamagePower() * 2;
        }

        return tripleDamage;
    }
}
