package me.coopersully.rpgloot.rpgloot.items;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static me.coopersully.rpgloot.rpgloot.AdventureExpansion.getPlugin;

public class NightmareItem {

    public static @NotNull List<Component> makeLore(@Nullable List<Component> lore) {
        if (lore == null) {
            lore = Lists.newArrayList(
                    Component.translatable(""),
                    Component.translatable("Statistics:", NamedTextColor.GRAY),
                    Component.translatable(" Living Source", NamedTextColor.DARK_GREEN),
                    Component.translatable(" Rare Treasure Item", NamedTextColor.DARK_GREEN),
                    Component.translatable(" Nightmare Infused", NamedTextColor.DARK_GREEN)
            );
            return lore;
        }

        int insertIndex = 0;
        boolean onIndentedLine = false;
        boolean insertedLine = false;
        for (int i = 0; i < lore.size(); i++) {

            if (!(lore.get(i) instanceof TextComponent component)) {
                continue;
            }

            String text = component.content();
            getPlugin().getLogger().fine(text);

            if (text.startsWith(" ")) {
                onIndentedLine = true;
            } else if (onIndentedLine) {
                insertIndex = i;
                insertedLine = true;
                lore.add(insertIndex, Component.translatable(" Nightmare Infused", NamedTextColor.DARK_GREEN));
                break;
            }
        }

        if (!insertedLine) {
            lore.add(Component.translatable(" Nightmare Infused", NamedTextColor.DARK_GREEN));
        }

        return lore;
    }

    public static void playCreateEffect(@NotNull Player player) {

        World world = player.getWorld();
        Location location = player.getLocation();

        world.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 2, 0);
        world.spawnParticle(Particle.CLOUD, location, 30);

        location.setY(500);
        world.spawnEntity(location, EntityType.LIGHTNING);

    }

    public static void playCreateEffectPlayer(@NotNull Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 6000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 6000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 6000, 0));

        player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 1000000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 1000000, 0));

        playCreateEffect(player);

    }

}
