package me.coopersully.rpgloot.rpgloot.entities;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.HalaraRPG;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaveTraders {

    static {
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(HalaraRPG.getPlugin(), CaveTraders::randomlySpawnVillager, 1200);
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
            if (HalaraRPG.debug) System.out.println("A cave trader attempted to spawn but no players were found in caves; trying again in " + CoreUtils.getPrettyTimeFromTicks(1200));
            Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(HalaraRPG.getPlugin(), CaveTraders::randomlySpawnVillager, tryAgainTime);
            return;
        }

        Bukkit.getServer().getScheduler().runTask(HalaraRPG.getPlugin(), CaveTraders::spawnVillager);
    }

    public static void spawnVillager() {

        Random random = new Random();
        Player player = cavePlayers.get(random.nextInt(cavePlayers.size()));
        Location location = player.getLocation();

        World world = location.getWorld();
        WanderingTrader wanderingTrader = world.spawn(location, WanderingTrader.class);
        ConfigTrades.giveTrades(wanderingTrader);

        // Schedule next spawn
        int runtime = (20 * (60 * 60)) / (cavePlayers.size() / 2);
        if (runtime <= 1200) runtime = 1200;
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(HalaraRPG.getPlugin(), CaveTraders::randomlySpawnVillager, runtime);
        if (HalaraRPG.debug) System.out.println("A cave trader spawned near " + player.getName() + ". Another one will spawn in " + CoreUtils.getPrettyTimeFromTicks(runtime) + " seconds near a random cave player.");
    }

}
