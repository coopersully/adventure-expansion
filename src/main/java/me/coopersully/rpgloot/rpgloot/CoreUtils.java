package me.coopersully.rpgloot.rpgloot;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CoreUtils {

    public static void noteInfo(@NotNull CommandSender sender, String message) {
        sender.sendMessage(Component.text(message, NamedTextColor.GRAY));
    }

    public static void noteWarning(@NotNull CommandSender sender, String message) {
        sender.sendMessage(Component.text(message, NamedTextColor.GOLD));
    }

    public static void noteError(@NotNull CommandSender sender, String message) {
        sender.sendMessage(Component.text(message, NamedTextColor.RED));
    }

    public static void noteSuccess(@NotNull CommandSender sender, String message) {
        sender.sendMessage(Component.text(message, NamedTextColor.GREEN));
    }

    public static @Nullable Player getPlayerFromSender(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            noteError(sender, "Only players can execute this command.");
            return null;
        }
        return player;
    }

    public static @NotNull Component translateXMLCodes(String message) {
        return Component.empty().decoration(TextDecoration.ITALIC, false).append(MiniMessage.miniMessage().deserialize(message));
    }

    public static void killProjectile(@NotNull Entity entity) {
        Location killArea = entity.getLocation().clone();
        killArea.setY(-500);
        entity.teleportAsync(killArea);
    }

    public static @NotNull String getPrettyTimeFromTicks(int ticks) {
        return getPrettyTime(ticks / 20);
    }

    public static @NotNull String getPrettyTime(int seconds) {
        var minutesLeft = seconds / 60;
        var secondsLeft = seconds % 60;
        return minutesLeft + "m " + secondsLeft + "s";
    }

    public static boolean rollChances(int possibility) {
        Random random = new Random();
        return random.nextInt(100) <= possibility;
    }
}
