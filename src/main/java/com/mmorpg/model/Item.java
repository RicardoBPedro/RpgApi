package com.mmorpg.model;

public class Item {

    // TODO: Interface for Weapon, Shield, Set, Ticket, etc

    public Item() {

    }

    public Item(Boolean luck) {
        this.luck = luck;
    }

    private Boolean luck;

    public boolean hasLuck() {
        return luck;
    }
}
