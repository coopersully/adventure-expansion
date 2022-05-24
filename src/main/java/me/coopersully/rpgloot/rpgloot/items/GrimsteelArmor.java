package me.coopersully.rpgloot.rpgloot.items;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.jetbrains.annotations.NotNull;

public class GrimsteelArmor {

    public static void gainedExperience(@NotNull PlayerPickupExperienceEvent event) {
        int value = event.getExperienceOrb().getExperience();
        event.getPlayer().giveExp(value * -2);
    }


}
