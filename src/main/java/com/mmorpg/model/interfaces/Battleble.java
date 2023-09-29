package com.mmorpg.model.interfaces;

public interface Battleble {
    Integer getPiercingDamageRate();
    Integer getCriticalDamageRate();
    Integer getExcellentDamageRate();
    Integer getDoubleDamageRate();
    Integer getTripleDamageRate();
    Long getDamagePower();
    Long getDefensivePower();
    Long getHP();
    void takeDamage(Long damage);
    void die();
}
