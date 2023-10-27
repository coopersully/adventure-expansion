package me.coopersully.rpgloot.rpgloot.items;

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

        int level = villager.getVillagerLevel();
        Villager.@NotNull Type type = villager.getVillagerType();
        int exp = villager.getVillagerExperience();
        boolean isBaby = !villager.isAdult();

        // Remove the original villager
        villager.remove();

        // Spawn a new villager with the same attributes but new trades
        Villager newVillager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        newVillager.setVillagerType(type);
        newVillager.setProfession(profession);
        newVillager.setVillagerLevel(level);
        newVillager.setVillagerExperience(exp);
        newVillager.setAge(isBaby ? -24000 : 0);  // Set age to keep the baby/adult state

        // Play sound and particle effects
        location.getWorld().playSound(location, Sound.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.MASTER, 4.0f, 1.0f);
        location.getWorld().playSound(location, Sound.ENTITY_VILLAGER_TRADE, SoundCategory.MASTER, 4.0f, 1.0f);
        location.getWorld().spawnParticle(Particle.SPELL, location.add(0, newVillager.getHeight(), 0), 50, 0.2, 0.5, 0.2, 0);
        location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.add(0, newVillager.getHeight(), 0), 50, 0.2, 0.5, 0.2, 0);

        Random random = new Random();
        if (random.nextInt(100) == 99) {
            newVillager.setProfession(Villager.Profession.NITWIT);
            location.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, location.add(0, newVillager.getHeight(), 0), 50, 0.2, 0.5, 0.2, 0);
        }
    }


}
