package com.grimlytwisted.minecraft.explosivearrows.core.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class Config {

    // Basic declarations.
    private JavaPlugin plugin;

    private File config;
    private static YamlConfiguration yaml;

    // Constructor to get JavaPlugin for easier access.
    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setup() {

        /* Begins setting up the config.
         * Similar to the onEnabled method in
         * the main class of the plugin.
         */

        File dir = plugin.getDataFolder();

        // If it doesn't exist, create it.
        if(!dir.exists()) {
            if(dir.mkdirs()) {
                plugin.getLogger().info("Created " + dir.getName());
            } else {
                plugin.getLogger().severe("Error creating " + dir.getName());
            }
        }

        config = new File(dir, "config.yml");

        if(!config.exists()) {
            plugin.saveDefaultConfig();
        }

        // Lastly, set up the YAML capabilities.

        yaml = new YamlConfiguration();

        try {
            yaml.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().severe("Could not load YAML " + config.getName());
            e.printStackTrace();
        }
    }

    public void save() {

        // Save the YAML file

        try {
            yaml.save(config);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save YAML " + config.getName());
            e.printStackTrace();
        }
    }

    public YamlConfiguration getYaml() {

        // Get the YAML to receive values in file

        return yaml;
    }
}
