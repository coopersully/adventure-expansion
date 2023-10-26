package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.NightmareItem;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.*;
import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteSuccess;

public class CommandNightmare implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length <= 0) {
            commandMain(sender);
            return false;
        }

        switch (args[0].toLowerCase().strip()) {
            case "confirm", "c", "yes" -> commandConfirmed(sender);
            default -> noteError(sender, "Usage: /nightmare [confirm]");
        }
        return true;
    }

    public static void commandMain(CommandSender sender) {

        /* Check if the CommandSender is a player and stop
        the command if it is not (if sender is Console or other). */
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return;

        // Check if sender has the required permissions
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "nightmare")) {
            noteError(sender, "You don't have permission to infuse items with Nightmare Fuel.");
            return;
        }

        // Check if sender has at least 1 nightmare fuel
        ItemStack nightmareFuel = null;
        for (ItemStack item : player.getInventory().getContents()) {

            if (item == null) continue;
            if (item.getItemMeta() == null) continue;

            if (item.getItemMeta().getPersistentDataContainer().has(ItemKeys.nightmareFuel)) {
                nightmareFuel = item;
                break;
            }
        }
        if (nightmareFuel == null) {
            noteError(sender, "You don't have Nightmare Fuel to infuse this item with.");
            return;
        }

        // Grab the player's current item and it's data
        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemMeta = mainItem.getItemMeta();

        // If the player is not holding any items
        if (mainItemMeta == null) {

            if (player.getPersistentDataContainer().has(ItemKeys.nightmare)) {
                noteError(sender, "Your corpse has already been infused with Nightmare Fuel.");
            } else {
                noteInfo(sender, "Do you want to infuse this item with Nightmare Fuel?");
                noteInfo(sender, "This will use 1x Nightmare Fuel from your inventory.");
                noteInfo(sender, "If you're sure, type /nightmare confirm to confirm.");
            }

        }
        // If the player is holding an item
        else {

            if (mainItemMeta.getPersistentDataContainer().has(ItemKeys.nightmare)) {
                noteError(sender, "This item has already been infused with Nightmare Fuel.");
            } else {
                noteInfo(sender, "Do you want to infuse your corpse with Nightmare Fuel?");
                noteInfo(sender, "Infusing an item with Nightmare Fuel that already possesses high-level enchantments may cause");
                noteInfo(sender, "some enchantments to become corrupted or unstable; great power always comes with a risk.");
                noteInfo(sender, "This will use 1x Nightmare Fuel from your inventory.");
                noteInfo(sender, "If you're sure, type /nightmare confirm  to confirm.");
            }

        }

    }

    public static void commandConfirmed(CommandSender sender) {

        /* Check if the CommandSender is a player and stop
        the command if it is not (if sender is Console or other). */
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return;

        // Check if sender has the required permissions
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "nightmare")) {
            noteError(player, "You don't have permission to infuse items with Nightmare Fuel.");
            return;
        }

        // Check if sender has at least 1 nightmare fuel
        ItemStack nightmareFuel = null;
        for (ItemStack item : player.getInventory().getContents()) {

            if (item == null) continue;
            if (item.getItemMeta() == null) continue;

            if (item.getItemMeta().getPersistentDataContainer().has(ItemKeys.nightmareFuel)) {
                nightmareFuel = item;
                break;
            }
        }

        if (nightmareFuel == null) {
            noteError(player, "You don't have Nightmare Fuel to infuse this item with.");
            return;
        }

        // Grab the player's current item and it's data
        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemMeta = mainItem.getItemMeta();

        // If the player is not holding any items
        if (mainItemMeta == null) {

            if (player.getPersistentDataContainer().has(ItemKeys.nightmare)) {
                noteError(player, "Your corpse has already been infused with Nightmare Fuel.");
                return;
            } else {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier("nightmare_fuel", 1, AttributeModifier.Operation.MULTIPLY_SCALAR_1));

                NightmareItem.playCreateEffectPlayer(player);

                player.getPersistentDataContainer().set(ItemKeys.nightmare, PersistentDataType.STRING, "1");

                noteSuccess(sender, "The winds of the night howl as you enchant your corpse.");
            }

        }
        // If the player is holding an item
        else {

            if (mainItemMeta.getPersistentDataContainer().has(ItemKeys.nightmare)) {
                noteError(player, "This item has already been infused with Nightmare Fuel.");
                return;
            } else {
                mainItemMeta.getEnchants()
                        .forEach((enchantment, integer) -> mainItem.addUnsafeEnchantment(enchantment, nightmarify(integer)));

                mainItemMeta = player.getInventory().getItemInMainHand().getItemMeta();

                mainItemMeta.getPersistentDataContainer()
                        .set(ItemKeys.nightmare, PersistentDataType.STRING, "1");

                mainItemMeta.lore(NightmareItem.makeLore(mainItem.lore()));

                mainItem.setItemMeta(mainItemMeta);

                NightmareItem.playCreateEffect(player);

                noteSuccess(player, "The winds of the night howl as you enchant your piece.");
            }

        }

        player.getInventory().removeItemAnySlot(nightmareFuel.asOne());

    }

    private static int nightmarify(int amount) {
        amount *= 2;
        if (amount > 20) amount = 20;
        return amount;
    }
}
