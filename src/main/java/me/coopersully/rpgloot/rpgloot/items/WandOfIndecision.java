package me.coopersully.rpgloot.rpgloot.items;

import com.destroystokyo.paper.entity.villager.Reputation;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class WandOfIndecision {


    public static void refreshTrades(@NotNull Villager villager) {
        // Store the original villager's attributes
        Location location = villager.getLocation();
        Villager.Profession profession = villager.getProfession();

        if (profession == Villager.Profession.NITWIT) {
            location.getWorld().playSound(location, Sound.ENTITY_VILLAGER_NO, SoundCategory.MASTER, 4.0f, 1.0f);
            return;
        }

        // Store previous stats
        int level = villager.getVillagerLevel();
        int experience = villager.getVillagerExperience();
        Map<UUID, Reputation> reputation = villager.getReputations();

        // Reset offers
        villager.resetOffers();

        // Set previous stats
        villager.setVillagerLevel(level);
        villager.setVillagerExperience(experience);
        villager.setReputations(reputation);

        // Play sound
        location.getWorld().playSound(location, Sound.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.MASTER, 4.0f, 1.0f);
        location.getWorld().playSound(location, Sound.ENTITY_VILLAGER_TRADE, SoundCategory.MASTER, 4.0f, 1.0f);

        // Play particle effects
        location.getWorld().spawnParticle(Particle.CLOUD, location.add(0, villager.getHeight() / 2, 0), 50, 0.2, 0.5, 0.2, 0);
        location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.add(0, villager.getHeight() / 2, 0), 50, 0.2, 0.5, 0.2, 0);

        // Random chance to become a nitwit
        if (!CoreUtils.rollChances(1)) return;

        villager.setProfession(Villager.Profession.NITWIT);
        location.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, location.add(0, villager.getHeight() / 2, 0), 50, 0.2, 0.5, 0.2, 0);
    }


}
