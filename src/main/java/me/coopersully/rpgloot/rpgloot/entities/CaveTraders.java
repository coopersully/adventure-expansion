package me.coopersully.rpgloot.rpgloot.entities;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.coopersully.rpgloot.rpgloot.AdventureExpansion.getPlugin;

public class CaveTraders {

    static {
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(getPlugin(), CaveTraders::randomlySpawnVillager, 1200);
    }

    public static List<Player> cavePlayers = new ArrayList<>();

    private static void refreshCavePlayers() {

        cavePlayers.clear();
        var players = Bukkit.getServer().getOnlinePlayers();
        if (players.isEmpty()) return;

        for (var player : players) {
            World world = player.getWorld();
            // If the player is not in the overworld, ignore them.
            if (!world.isBedWorks()) continue;

            Location location = player.getLocation();
            // If the player is not below sea level, ignore them.
            if (location.getBlock().getY() >= world.getSeaLevel()) continue;

            cavePlayers.add(player);
        }
    }

    private static void randomlySpawnVillager() {
        refreshCavePlayers();
        if (cavePlayers.isEmpty()) {
            var tryAgainTime = 20 * (60 * 10);
            if (AdventureExpansion.debug) getPlugin().getLogger().fine("A cave trader attempted to spawn but no players were found in caves; trying again in " + CoreUtils.getPrettyTimeFromTicks(1200));
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(getPlugin(), CaveTraders::randomlySpawnVillager, tryAgainTime);
            return;
        }

        Bukkit.getServer().getScheduler().runTask(getPlugin(), CaveTraders::spawnVillager);
    }

    public static void spawnVillager() {

        Random random = new Random();
        Player player = cavePlayers.get(random.nextInt(cavePlayers.size()));
        Location location = player.getLocation();

        World world = location.getWorld();
        WanderingTrader wanderingTrader = world.spawn(location, WanderingTrader.class);
        ConfigTrades.giveTrades(wanderingTrader);

        // Schedule next spawn
        var runtime = calculateRuntime();
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(getPlugin(), CaveTraders::randomlySpawnVillager, runtime);
        if (AdventureExpansion.debug) getPlugin().getLogger().fine("A cave trader spawned near " + player.getName() + ". Another one will spawn in " + CoreUtils.getPrettyTimeFromTicks(runtime) + " seconds near a random cave player.");
    }

    private static int calculateRuntime() {
        // Initialize runtime to 60 minutes in ticks
        int runtime = (20 * (60 * 60));

        // Calculate divisor for players
        int divisor = (cavePlayers.size() / 2);
        if (divisor <= 0) divisor = 1;

        // Divide runtime by divisor
        runtime = runtime / divisor;

        /* If the remaining runtime is less than
        1200 ticks, or 1 minute, default it to 1 minute. */
        if (runtime <= 1200) runtime = 1200;
        return runtime;
    }

}
