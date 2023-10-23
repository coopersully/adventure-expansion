package me.coopersully.rpgloot.rpgloot.datapacks;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRPGPackManager {

    private File worldFolder;
    private File datapackFolder;

    public CRPGPackManager() {
        worldFolder = Bukkit.getWorlds().get(0).getWorldFolder();
        datapackFolder = new File(worldFolder.getPath() + "\\datapacks");
    }

    public File getWorldFolder() {
        return worldFolder;
    }

    public File getDatapackFolder() {
        return datapackFolder;
    }

    public List<CRPGDatapack> getValidPacks() {

        List<CRPGDatapack> validPacks = new ArrayList<>();
        if (datapackFolder == null) return validPacks;

        var datapacks = datapackFolder.listFiles();
        if (datapacks == null || datapacks.length < 1) return validPacks;

        for (File datapack : datapacks) {

            var datapackFiles = datapack.listFiles();
            if (datapackFiles == null || datapackFiles.length < 1) continue;

            for (File file : datapackFiles) {
                if (AdventureExpansion.debug) System.out.println("Loading datapack resource: " + datapack.getName() + "/" + file.getName());
                if (file.getName().equals(".crpg")) {
                    float version;
                    try {
                        // Get datapack's version from .crpg file
                        Scanner fileReader = new Scanner(file);
                        version = fileReader.nextFloat();
                    } catch (FileNotFoundException e) { throw new RuntimeException(e); }

                    /* Create new CRPGDatapack and add it to
                    the list of valid packs to be returned. */
                    CRPGDatapack dp = new CRPGDatapack(datapack, file, version);
                    System.out.println(ChatColor.GREEN + "Loading valid datapack: " + dp);
                    validPacks.add(dp);
                }
            }

        }
        return validPacks;
    }

    public @Nullable CRPGDatapack getBestFit() {
        var allValidPacks = getValidPacks();
        if (allValidPacks.size() == 0) {
            if (AdventureExpansion.debug) System.out.println(ChatColor.RED + "No valid packs were found.");
            return null;
        }
        else if (allValidPacks.size() == 1) return allValidPacks.get(0);

        CRPGDatapack bestFit = null;
        float maxVersion = 0;
        for (var validPack : allValidPacks) {
            if (validPack.version > maxVersion) {
                bestFit = validPack;
                maxVersion = bestFit.version;
            }
        }
        return bestFit;
    }

    public float getLatestVersion() throws IOException {
        URL url = new URL("https://api.github.com/repos/coopersully/rpgloot-datapack/releases/latest");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.ou
    }
}
