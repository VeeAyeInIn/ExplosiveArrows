package com.grimlytwisted.minecraft.explosivearrows.core;

import com.grimlytwisted.minecraft.explosivearrows.commands.Explosions;
import com.grimlytwisted.minecraft.explosivearrows.commands.SetPower;
import com.grimlytwisted.minecraft.explosivearrows.commands.TerrainDamage;
import com.grimlytwisted.minecraft.explosivearrows.core.config.Config;
import com.grimlytwisted.minecraft.explosivearrows.listeners.ProjectileContactListener;
import com.grimlytwisted.minecraft.explosivearrows.listeners.WorldInitListener;
import com.grimlytwisted.minecraft.explosivearrows.util.Debug;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExplosiveArrows extends JavaPlugin {

    public static Config config;
    public static Debug debug;

    @Override
    public void onEnable() {

        // What happens when the plugin is enabled.

        config = new Config(this);
        debug = new Debug(this, false);

        config.setup();

        if (debug.isRunning()) {
            this.getLogger().info("Debug is running!");
        }



        YamlConfiguration yaml = config.getYaml();

        for(World world : this.getServer().getWorlds()) {
            if(config.getYaml().getConfigurationSection("config.worlds." + world.getUID()) == null) {

                String section = "config.worlds." + world.getUID();

                yaml.createSection(section);
                yaml.createSection(".world-name");
                yaml.createSection(section + ".explosive-arrows-enabled");
                yaml.createSection(section + ".can-damage-terrain");
                yaml.createSection(section + ".power");

                yaml.set(section + ".world-name", world.getName());
                yaml.set(section + ".explosive-arrows-enabled", false);
                yaml.set(section + ".can-damage-terrain", false);
                yaml.set(section + ".power", 1.0f);
            }
            config.save();
        }

        this.getLogger().info("Finished building config.yml!");

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {

        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new WorldInitListener(), this);
        pm.registerEvents(new ProjectileContactListener(), this);
    }

    private void registerCommands() {
        this.getCommand("setpower").setExecutor(new SetPower());
        this.getCommand("explosions").setExecutor(new Explosions());
        this.getCommand("tdamage").setExecutor(new TerrainDamage());
    }

    @Override
    public void onDisable() {

        // What happens when the plugin is disabled.

        config.save();
    }
}
