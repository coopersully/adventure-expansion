package me.coopersully.rpgloot.rpgloot.items.treasure_items;

import org.jetbrains.annotations.NotNull;

public enum TreasureItemSource {
    LIVING,
    UNDEAD,
    MAGIC,
    UNKNOWN;

    @Override
    public @NotNull String toString() {
        String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

}
