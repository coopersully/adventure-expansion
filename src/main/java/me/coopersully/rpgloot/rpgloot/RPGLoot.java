package me.coopersully.rpgloot.rpgloot;

import me.coopersully.rpgloot.rpgloot.commands.Cleanse;
import me.coopersully.rpgloot.rpgloot.commands.Meta;
import me.coopersully.rpgloot.rpgloot.commands.Nightmare;
import me.coopersully.rpgloot.rpgloot.listeners.EquipArmor;
import me.coopersully.rpgloot.rpgloot.listeners.RightClick;
import me.coopersully.rpgloot.rpgloot.listeners.PickupExp;
import me.coopersully.rpgloot.rpgloot.listeners.SculkAbility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class RPGLoot extends JavaPlugin {

    private static RPGLoot plugin;
    public static final String permissionPrefix = "crpg.";

    public static RPGLoot getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new EquipArmor(), this);
        getServer().getPluginManager().registerEvents(new PickupExp(), this);
        getServer().getPluginManager().registerEvents(new RightClick(), this);
        getServer().getPluginManager().registerEvents(new SculkAbility(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {

        switch (label) {
            case "meta" -> {
                Meta.command(sender);
                return true;
            }
            case "cleanse" -> {
                Cleanse.command(sender);
                return true;
            }
            case "nightmare", "nightmarify" -> {
                Nightmare.command(sender, args);
                return true;
            }
        }
        return false;
    }
}
