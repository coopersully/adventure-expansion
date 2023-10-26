package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteError;

public class AttachLeadToVillager implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(@NotNull PlayerInteractEntityEvent event) {

        // Check if the entity the player is interacting with is a Villager
        if (event.getRightClicked().getType() != EntityType.VILLAGER) return;

        // Cast the entity to a Villager
        Villager villager = (Villager) event.getRightClicked();

        // Get the player who interacted with the Villager
        Player player = event.getPlayer();

        // Check if the player has a lead in hand
        if (player.getInventory().getItemInMainHand().getType() != Material.LEAD) return;

        // Check for permission
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "lead.villager")) {
            noteError(player, "You don't have permission to attach leads to Villagers.");
            return;
        }

        // Cancel default event logic
        event.setCancelled(true);

        // Attach the lead to the Villager
        villager.setLeashHolder(player);

        // Remove one lead from the player's inventory
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
    }
}
