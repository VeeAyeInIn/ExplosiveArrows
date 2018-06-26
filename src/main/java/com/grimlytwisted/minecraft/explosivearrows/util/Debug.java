package com.grimlytwisted.minecraft.explosivearrows.util;

import com.grimlytwisted.minecraft.explosivearrows.core.ExplosiveArrows;
import org.bukkit.plugin.java.JavaPlugin;

public class Debug {

    // Basic Declarations
    private final JavaPlugin plugin;
    private final boolean debugDefault;

    // Constructor for Debugging
    public Debug(JavaPlugin plugin, boolean debugDefault) {
        this.plugin = plugin;
        this.debugDefault = debugDefault;
    }

    public boolean isRunning() {

        /* If debug is enabled in the configuration file,
         * then run.
         */

        try {
            return ExplosiveArrows.config.getYaml().getBoolean("config.debug");
        } catch (Exception e) {
            plugin.getLogger().severe("Invalid value for [config.debug]!");
            return debugDefault;
        }
    }
}
