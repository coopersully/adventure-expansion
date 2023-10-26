package me.coopersully.rpgloot.rpgloot.items;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class EnderSword {

    private static HashMap<UUID, Long> cooldown = new HashMap<>();

    public static void attemptTeleport(@NotNull Player player) {

        UUID playerUUID = player.getUniqueId();
        if (cooldown.containsKey(playerUUID) && cooldown.get(playerUUID) > System.currentTimeMillis()) {

            long cooldownTime = (cooldown.get(playerUUID) - System.currentTimeMillis()) / 1000;
            player.sendActionBar(Component.text("Ability ready in " + cooldownTime + " seconds."));

        } else {

            player.launchProjectile(EnderPearl.class);
            cooldown.put(playerUUID, System.currentTimeMillis() + (15 * 1000));

        }

    }

}
