package me.coopersully.rpgloot.rpgloot.items.treasure_items;

import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public enum TreasureItemRarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    MYTHICAL,
    DARK;

    @Override
    public @NotNull String toString() {
        String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

    public NamedTextColor getColor() {
        return switch (this) {
            case COMMON -> NamedTextColor.WHITE;
            case UNCOMMON -> NamedTextColor.YELLOW;
            case RARE -> NamedTextColor.AQUA;
            case EPIC -> NamedTextColor.LIGHT_PURPLE;
            case MYTHICAL -> NamedTextColor.RED;
            case DARK -> NamedTextColor.DARK_GRAY;
        };
    }

}
