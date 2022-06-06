package me.coopersully.rpgloot.rpgloot;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreUtils {
    public static final Pattern HEX_PATTERN = Pattern.compile("&#(\\w{5}[0-9a-f])");

    public static @NotNull String translateMinecraftCodes(String textToTranslate) {

        Matcher matcher = HEX_PATTERN.matcher(textToTranslate);
        StringBuilder line = new StringBuilder();

        while(matcher.find()) {
            matcher.appendReplacement(line, ChatColor.of("#" + matcher.group(1)).toString());
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(line).toString());
    }
    
    public static @NotNull Component translateXMLCodes(String message) {
        return Component.empty().decoration(TextDecoration.ITALIC, false).append(MiniMessage.miniMessage().deserialize(message));
    }
    public static void killProjectile(@NotNull Entity entity) {
        Location killArea = entity.getLocation().clone();
        killArea.setY(-500);
        entity.teleportAsync(killArea);
    }

}
