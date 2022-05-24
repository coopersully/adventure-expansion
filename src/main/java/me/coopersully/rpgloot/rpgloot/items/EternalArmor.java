package me.coopersully.rpgloot.rpgloot.items;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EternalArmor {

    public static void playEquipEffect(@NotNull Player player) {

        World world = player.getWorld();
        Location location = player.getLocation();

        world.spawnParticle(Particle.FLASH, location, 1);
        world.playSound(location, Sound.ITEM_ARMOR_EQUIP_CHAIN, SoundCategory.PLAYERS, 2, 2);
        world.playSound(location, Sound.ITEM_TRIDENT_RETURN, SoundCategory.PLAYERS, 4, 0);

    }

}
